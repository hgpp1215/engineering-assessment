package cn.wzl.assessment.demo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodsVO {
    private Integer locationId;

    private String food;
}
