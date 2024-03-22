package cn.wzl.assessment.demo.infra.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */
@Slf4j
public class FoodsResolveUtil {
    private FoodsResolveUtil() {
        throw new RuntimeException("Utility class cannot be instantiated!");
    }

    public static List<String> parseFood(String foodText) {
        if (null == foodText || foodText.isEmpty()) {
            return null;
        }
        try {
            return Arrays.stream(foodText.split(":")).map(String::trim).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Foods parsing error:", e);
            return null;
        }
    }
}
