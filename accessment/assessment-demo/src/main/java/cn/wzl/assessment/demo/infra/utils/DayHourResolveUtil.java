package cn.wzl.assessment.demo.infra.utils;

import cn.hutool.core.collection.CollUtil;
import cn.wzl.assessment.demo.infra.enums.WeekDayEnum;
import cn.wzl.assessment.demo.infra.exception.BusinessException;
import cn.wzl.assessment.demo.pojo.vo.DayHourVO;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */
@Slf4j
public class DayHourResolveUtil {

    private static final Pattern PATTERN = Pattern.compile("(\\d{1,2})(AM|PM)");

    private DayHourResolveUtil() {
        throw new RuntimeException("Utility class cannot be instantiated!");
    }

    public static List<DayHourVO> parseDayHour(String foodText) {
        if (null == foodText || foodText.isEmpty()) {
            return null;
        }
        // the group like "Mo-Fr:2PM-3PM"
        List<String> openingTimeGroups = Arrays.stream(foodText.split(";")).toList();
        if (CollUtil.isEmpty(openingTimeGroups)) {
            return null;
        }
        List<DayHourVO> result = new ArrayList<>();
        for (String dayStr : openingTimeGroups) {
            // valid
            if (!dayStr.contains(":")) {
                throw new BusinessException("wrong dayhour format" + dayStr);
            }
            if (!dayStr.contains("/") && !dayStr.contains("-")) {
                throw new BusinessException("wrong dayhour format" + dayStr);
            }
            // start resolve
            String[] dayAndTime = dayStr.split(":");
            // 1. day's parsing e.g. Mo/Mo/Mo OR Mo-Fr
            String day = dayAndTime[0];
            Set<WeekDayEnum> enumSet = Arrays.stream(day.split("/")).flatMap(s -> {
                if (s.contains("-")) {
                    // e.g. Mo-Fr
                    String[] twoDays = s.split("-");
                    WeekDayEnum firstDay = WeekDayEnum.findByAlias(twoDays[0]);
                    WeekDayEnum lastDay = WeekDayEnum.findByAlias(twoDays[1]);
                    if (null == firstDay || null == lastDay) {
                        throw new BusinessException("wrong dayhour format" + s);
                    }
                    if (firstDay.sort > lastDay.sort) {
                        // reverse for sort and filter
                        firstDay = WeekDayEnum.findByAlias(twoDays[1]);
                        lastDay = WeekDayEnum.findByAlias(twoDays[0]);
                    }
                    int firstDayNum = firstDay.sort;
                    int lastDayNum = lastDay.sort;
                    return Arrays.stream(WeekDayEnum.values()).filter(e -> e.sort >= firstDayNum && e.sort <= lastDayNum);
                } else {
                    WeekDayEnum dayEnum = WeekDayEnum.findByAlias(s);
                    if (null == dayEnum) {
                        throw new BusinessException("wrong dayhour format" + s);
                    }
                    return Stream.of(dayEnum);
                }
            }).collect(Collectors.toSet());
            // 2. time's parsing e.g. 7AM-7PM/11PM-12PM
            String time = dayAndTime[1];
            List<DayHourVO> subResult = Arrays.stream(time.split("/")).map(times -> {
                String[] timeSplit = times.split("-");
                if (timeSplit.length < 2) {
                    throw new BusinessException("wrong dayhour format" + times);
                }
                String open = timeSplit[0];
                String close = timeSplit[1];
                //e.g. 11PM-12PM
                DayHourVO dayHour = new DayHourVO();
                dayHour.setWeekDay(enumSet.stream().map(e -> e.alias).collect(Collectors.joining(",")));
                try {
                    dayHour.setOpenTime(parseDate(open));
                    dayHour.setCloseTime(parseDate(close));
                } catch (Exception e) {
                    throw new BusinessException("wrong dayhour format" + times);
                }
                return dayHour;
            }).toList();
            result.addAll(subResult);
        }
        return result;
    }

    /**
     * Parse the string like "12AM" into 24hours
     *
     * @param time date string
     * @return 0-24
     */
    private static int parseDate(String time) throws BusinessException {
        Matcher matcher = PATTERN.matcher(time);
        if (matcher.matches()) {
            int num = Integer.parseInt(matcher.group(1));
            if (num > 12 || num < 0) {
                throw new BusinessException();
            }
            String ampm = matcher.group(2);
            if ("PM".equalsIgnoreCase(ampm)) {
                num += 12;
            }
            return num;
        } else {
            throw new BusinessException();
        }
    }
}
