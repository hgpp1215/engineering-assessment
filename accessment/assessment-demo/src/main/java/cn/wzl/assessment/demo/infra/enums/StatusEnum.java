package cn.wzl.assessment.demo.infra.enums;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */
public enum StatusEnum {
    APPROVED(1),
    EXPIRED(-1),
    ISSUED(2),
    REQUESTED(3),
    SUSPEND(4);

    StatusEnum(int code) {
        this.code = code;
    }

    public final int code;

    public static StatusEnum getByName(String name) {
        for (StatusEnum value : values()) {
            if (value.name().equalsIgnoreCase(name)) {
                return value;
            }
        }
        return null;
    }
}
