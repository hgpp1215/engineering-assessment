package cn.wzl.assessment.demo.infra.enums;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */
public enum WeekDayEnum {
    SUNDAY("Su", 1),
    MONDAY("Mo", 2),
    TUESDAY("Tu", 3),
    WEDNESDAY("We", 4),
    THURSDAY("Th", 5),
    FRIDAY("Fr", 6),
    SATURDAY("Sa", 7);

    public final String alias;
    public final int sort;

    WeekDayEnum(String alias, int sort) {
        this.alias = alias;
        this.sort = sort;
    }

    public static WeekDayEnum findByAlias(String alias) {
        if (null == alias || alias.isEmpty()) {
            return null;
        }
        for (WeekDayEnum value : values()) {
            if (value.alias.equalsIgnoreCase(alias)) {
                return value;
            }
        }
        return null;
    }

    public static WeekDayEnum findBySort(Integer sort) {
        if (null == sort) {
            return null;
        }
        for (WeekDayEnum value : values()) {
            if (value.sort == sort) {
                return value;
            }
        }
        return null;
    }
}
