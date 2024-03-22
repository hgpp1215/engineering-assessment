package cn.wzl.assessment.demo.pojo.vo;

import lombok.Data;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */
@Data
public class DayHourVO {
    private Integer locationId;

    private String weekDay;

    private Integer openTime;

    private Integer closeTime;
}
