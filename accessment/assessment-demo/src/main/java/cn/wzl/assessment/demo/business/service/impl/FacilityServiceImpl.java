package cn.wzl.assessment.demo.business.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.wzl.assessment.demo.business.dao.FacilityFoodsDao;
import cn.wzl.assessment.demo.business.dao.FacilityTimeDao;
import cn.wzl.assessment.demo.business.dao.FoodFacilityDao;
import cn.wzl.assessment.demo.business.service.FacilityService;
import cn.wzl.assessment.demo.infra.enums.StatusEnum;
import cn.wzl.assessment.demo.infra.enums.WeekDayEnum;
import cn.wzl.assessment.demo.infra.exception.BusinessException;
import cn.wzl.assessment.demo.infra.utils.PojoConverterUtil;
import cn.wzl.assessment.demo.pojo.po.FacilityFoods;
import cn.wzl.assessment.demo.pojo.po.FacilityTime;
import cn.wzl.assessment.demo.pojo.po.FoodFacility;
import cn.wzl.assessment.demo.pojo.vo.FacilityVO;
import cn.wzl.assessment.demo.pojo.vo.FoodFacilityImportVO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FacilityServiceImpl implements FacilityService {

    private final FoodFacilityDao foodFacilityDao;

    private final FacilityFoodsDao facilityFoodsDao;

    private final FacilityTimeDao facilityTimeDao;

    private final int INSERT_PARTITION = 100;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveData(List<FoodFacilityImportVO> list) {
        if (CollUtil.isEmpty(list)) {
            log.warn("[saveData]Save fail: save list is empty.");
            return 0;
        }
        int count = 0;
        try {
            log.info("[saveData]Facility saving");
            List<FoodFacility> foodFacilityList = list.stream().map(PojoConverterUtil::FacilityImportVoToPo).peek(po -> {
                po.setCreateTime(new Date());
                po.setCreateBy("someone");
            }).collect(Collectors.toList());
            List<List<FoodFacility>> facilityPartition = Lists.partition(foodFacilityList, INSERT_PARTITION);
            int facilityNum = 1;
            for (List<FoodFacility> foodFacilities : facilityPartition) {
                log.info(String.format("[saveData]Facility saving processing[%d/%d]", facilityNum, facilityPartition.size()));
                count += foodFacilityDao.batchInsert(foodFacilities);
                ++facilityNum;
            }

            log.info("[saveData]Facility Foods saving");
            List<FacilityFoods> foodsList = list.stream().filter(f -> CollUtil.isNotEmpty(f.getFoodsVOS()))
                    .flatMap(f -> f.getFoodsVOS().stream().map(PojoConverterUtil::FacilityFoodsVoToPo))
                    .peek(po -> {
                        po.setCreateTime(new Date());
                        po.setCreateBy("someone");
                    })
                    .toList();
            List<List<FacilityFoods>> foodsPartition = Lists.partition(foodsList, INSERT_PARTITION);
            int foodsNum = 1;
            for (List<FacilityFoods> foodFacilities : foodsPartition) {
                log.info(String.format("[saveData]Facility Foods saving processing[%d/%d]", foodsNum, foodsPartition.size()));
                facilityFoodsDao.batchInsert(foodFacilities);
                ++foodsNum;
            }

            log.info("[saveData]Facility Time saving");
            List<FacilityTime> timeList = list.stream().filter(f -> CollUtil.isNotEmpty(f.getDayHoursVOS()))
                    .flatMap(f -> f.getDayHoursVOS().stream().map(PojoConverterUtil::FacilityTimeVoToPo))
                    .peek(po -> {
                        po.setCreateTime(new Date());
                        po.setCreateBy("someone");
                    })
                    .toList();
            List<List<FacilityTime>> timePartition = Lists.partition(timeList, INSERT_PARTITION);
            int timeNum = 1;
            for (List<FacilityTime> facilityTimes : timePartition) {
                log.info(String.format("[saveData]Facility Time saving processing[%d/%d]", timeNum, timePartition.size()));
                facilityTimeDao.batchInsert(facilityTimes);
                ++timeNum;
            }

            log.info("[saveData]Saving success");
        } catch (Exception e) {
            log.error("[saveData]Saving fail", e);
            throw new BusinessException(e);
        }
        return count;
    }

    @Override
    public List<FacilityVO> getAvailableFacility(Integer locationId) {
        int hour = DateUtil.thisHour(true);
        int week = DateUtil.thisDayOfWeek();
        WeekDayEnum dayEnum = WeekDayEnum.findBySort(week);
        if (null == dayEnum) {
            throw new BusinessException("day of week is wrong");
        }
        List<FacilityTime> facilities = facilityTimeDao.selectList(Wrappers.<FacilityTime>lambdaQuery()
                .like(FacilityTime::getWeekDay, String.join("", "%", dayEnum.alias, "%"))
                .lt(FacilityTime::getOpenTime, hour)
                .gt(FacilityTime::getCloseTime, hour)
                .eq(null != locationId, FacilityTime::getLocationId, locationId));
        if (CollUtil.isEmpty(facilities)) {
            return Collections.emptyList();
        }
        Set<Integer> locationIds = facilities.stream().map(FacilityTime::getLocationId).collect(Collectors.toSet());
        List<FoodFacility> foodFacilities = foodFacilityDao.selectList(
                Wrappers.<FoodFacility>lambdaQuery()
                        .in(FoodFacility::getLocationId, locationIds)
                        // TODO Status field may cause too much data to be filtered out
                        .eq(FoodFacility::getStatus, StatusEnum.APPROVED.code));
        if (null == foodFacilities) {
            return Collections.emptyList();
        }

        return foodFacilities.stream().map(PojoConverterUtil::facilityImportPoToVo).toList();
    }
}
