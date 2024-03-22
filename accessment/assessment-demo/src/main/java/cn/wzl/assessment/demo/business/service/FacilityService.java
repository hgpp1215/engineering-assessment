package cn.wzl.assessment.demo.business.service;

import cn.wzl.assessment.demo.pojo.vo.FacilityVO;
import cn.wzl.assessment.demo.pojo.vo.FoodFacilityImportVO;

import java.util.List;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */
public interface FacilityService {
    /**
     * parse the Excel raw data into po, and save them into DB
     *
     * @param list raw data
     * @return count of success
     */
    int saveData(List<FoodFacilityImportVO> list);

    /**
     * Find the facilities open right now
     * If location ID is not null, you can find specified one.
     * The status of result set will be matches APPROVED
     *
     * @param locationId specified locationId, is not necessary
     * @return facilities
     */
    List<FacilityVO> getAvailableFacility(Integer locationId);
}
