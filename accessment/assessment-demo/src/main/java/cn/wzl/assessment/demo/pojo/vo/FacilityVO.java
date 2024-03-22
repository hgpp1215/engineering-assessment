package cn.wzl.assessment.demo.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */
@Data
public class FacilityVO {
    private Integer locationId;
    private String location;
    private Integer locationX;
    private Integer locationY;
    private List<String> foods;
}
