package cn.wzl.assessment.demo.infra.utils;

import cn.wzl.assessment.demo.infra.enums.StatusEnum;
import cn.wzl.assessment.demo.pojo.po.FacilityFoods;
import cn.wzl.assessment.demo.pojo.po.FacilityTime;
import cn.wzl.assessment.demo.pojo.po.FoodFacility;
import cn.wzl.assessment.demo.pojo.vo.DayHourVO;
import cn.wzl.assessment.demo.pojo.vo.FacilityVO;
import cn.wzl.assessment.demo.pojo.vo.FoodFacilityImportVO;
import cn.wzl.assessment.demo.pojo.vo.FoodsVO;

import java.util.Arrays;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */
public class PojoConverterUtil {
    private PojoConverterUtil() {
        throw new RuntimeException("Utility class cannot be instantiated!");
    }

    public static FoodFacility FacilityImportVoToPo(FoodFacilityImportVO vo) {
        if (null == vo) {
            return null;
        }
        FoodFacility po = new FoodFacility();
        po.setLocationId(vo.getLocationId());
        po.setApplicant(vo.getApplicant());
        po.setFacilityType(vo.getFacilityType());
        po.setCnn(vo.getCnn());
        po.setLocationDescription(vo.getLocationDescription());
        po.setAddress(vo.getAddress());
        po.setBlockLot(vo.getBlockLot());
        po.setBlock(vo.getBlock());
        po.setLot(vo.getLot());
        po.setPermit(vo.getPermit());
        StatusEnum statusEnum = StatusEnum.getByName(vo.getStatus());
        if (null != statusEnum) {
            po.setStatus(statusEnum.code);
        }
        po.setFoodItems(vo.getFoodItems());
        po.setLocationX(vo.getLocationX());
        po.setLocationY(vo.getLocationY());
        po.setLatitude(vo.getLatitude());
        po.setLongitude(vo.getLongitude());
        po.setSchedule(vo.getSchedule());
        po.setDayshours(vo.getDayshours());
        po.setNoiSent(vo.getNoiSent());
        po.setApprovedDate(vo.getApprovedDate());
        po.setReceived(vo.getReceived());
        po.setExpirationDate(vo.getExpirationDate());
        po.setPriorPermit(vo.getPriorPermit());
        po.setLocation(vo.getLocation());
        po.setFirePreventionDistricts(vo.getFirePreventionDistricts());
        po.setPoliceDistricts(vo.getPoliceDistricts());
        po.setSupervisorDistricts(vo.getSupervisorDistricts());
        po.setZipCodes(vo.getZipCodes());
        po.setNeighborhoods(vo.getNeighborhoods());
        return po;
    }

    public static FacilityFoods FacilityFoodsVoToPo(FoodsVO vo) {
        if (null == vo) {
            return null;
        }
        FacilityFoods po = new FacilityFoods();
        po.setLocationId(vo.getLocationId());
        po.setFoodsName(vo.getFood());
        return po;
    }

    public static FacilityTime FacilityTimeVoToPo(DayHourVO vo) {
        if (null == vo) {
            return null;
        }
        FacilityTime po = new FacilityTime();
        po.setLocationId(vo.getLocationId());
        po.setWeekDay(vo.getWeekDay());
        po.setOpenTime(vo.getOpenTime());
        po.setCloseTime(vo.getCloseTime());
        return po;
    }

    public static FacilityVO facilityImportPoToVo(FoodFacility po) {
        if (null == po) {
            return null;
        }
        FacilityVO vo = new FacilityVO();
        vo.setLocationId(po.getLocationId());
        vo.setLocation(po.getLocationDescription());
        vo.setLocationX(po.getLocationX());
        vo.setLocationY(po.getLocationY());
        vo.setFoods(Arrays.stream(po.getFoodItems().split(":")).map(String::trim).toList());
        return vo;
    }
}
