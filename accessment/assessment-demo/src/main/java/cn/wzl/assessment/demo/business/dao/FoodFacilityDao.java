package cn.wzl.assessment.demo.business.dao;

import cn.wzl.assessment.demo.pojo.po.FoodFacility;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */

public interface FoodFacilityDao extends BaseMapper<FoodFacility> {
    int batchInsert(@Param("saveList") List<FoodFacility> saveList);
}
