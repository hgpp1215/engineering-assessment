package cn.wzl.assessment.demo.infra.listener;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.wzl.assessment.demo.business.service.FacilityService;
import cn.wzl.assessment.demo.infra.utils.DayHourResolveUtil;
import cn.wzl.assessment.demo.infra.utils.FoodsResolveUtil;
import cn.wzl.assessment.demo.pojo.vo.DayHourVO;
import cn.wzl.assessment.demo.pojo.vo.FoodFacilityImportVO;
import cn.wzl.assessment.demo.pojo.vo.FoodsVO;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


/**
 * @author wzl
 * @version 1.0 2024/3/21
 */
@Slf4j
public class FacilityImportListener implements ReadListener<FoodFacilityImportVO> {

    private final FacilityService service;

    /**
     * cache container
     */
    private final List<FoodFacilityImportVO> cachedList = new ArrayList<>();


    public FacilityImportListener(FacilityService service) {
        this.service = service;
    }

    @Override
    public void invoke(FoodFacilityImportVO data, AnalysisContext context) {
        log.info(String.format("Processing Data N0.%d:%s", context.readRowHolder().getRowIndex(), data));
        // The verification process is necessary, but it's too boring and time-consuming for our demo, so i left a TODO here.
        // Pardon me :)
        // TODO validation
        // parsing foodsItem in raw data
        if (StrUtil.isNotBlank(data.getFoodItems())) {
            List<String> foods = FoodsResolveUtil.parseFood(data.getFoodItems());
            if (CollUtil.isNotEmpty(foods)) {
                List<FoodsVO> foodsVOS = foods.stream().map(foodName -> new FoodsVO(data.getLocationId(), foodName)).toList();
                data.setFoodsVOS(foodsVOS);
            }
        }
        // parsing time in raw data
        if (StrUtil.isNotBlank(data.getDayshours())) {
            try {
                List<DayHourVO> dayHourVOS = DayHourResolveUtil.parseDayHour(data.getDayshours());
                dayHourVOS.forEach(dayHourVO -> dayHourVO.setLocationId(data.getLocationId()));
                data.setDayHoursVOS(dayHourVOS);
            } catch (Exception e) {
                data.getErrors().add(e.getMessage());
            }
        }
        // error items
        if (CollUtil.isNotEmpty(data.getErrors())) {
            data.setErrorText(String.join(";", data.getErrors()));
        }
        cachedList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
    }

    private void saveData() {
        try {
            List<FoodFacilityImportVO> saveList = cachedList.stream().filter(data -> CollUtil.isEmpty(data.getErrors())).toList();
            log.info("{} items，Saving..", saveList);
            int i = service.saveData(saveList);
            log.info("save success！，total:{}", i);
        } catch (Exception e) {
            log.error("save fail！", e);
        }
    }

    public List<FoodFacilityImportVO> getData() {
        return this.cachedList;
    }
}

