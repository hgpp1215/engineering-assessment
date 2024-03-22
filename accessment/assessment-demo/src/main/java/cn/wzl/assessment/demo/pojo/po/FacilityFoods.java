package cn.wzl.assessment.demo.pojo.po;

import cn.wzl.assessment.demo.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("facility_foods")
public class FacilityFoods extends BaseEntity {


    private Integer locationId;

    private String foodsName;
}
