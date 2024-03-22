package cn.wzl.assessment.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */
@Data
public abstract class BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;
}
