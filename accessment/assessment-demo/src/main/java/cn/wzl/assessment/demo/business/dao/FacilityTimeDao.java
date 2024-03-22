package cn.wzl.assessment.demo.business.dao;

import cn.wzl.assessment.demo.pojo.po.FacilityTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */

public interface FacilityTimeDao extends BaseMapper<FacilityTime> {
    int batchInsert(@Param("saveList") List<FacilityTime> saveList);
}
