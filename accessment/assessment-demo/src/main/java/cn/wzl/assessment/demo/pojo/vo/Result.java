package cn.wzl.assessment.demo.pojo.vo;


import lombok.Data;

/**
 * Base Result
 *
 * @param <T>
 * @author wzl
 * @version 1.0 2024/3/21
 */
@Data
public class Result<T> {
    private int code;

    private boolean success;

    private String message;

    private T result;

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(200);
        result.setMessage("");
        return result;
    }

    public static <T> Result<T> success(T t) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(200);
        result.setMessage("");
        result.setResult(t);
        return result;
    }

    public static <T> Result<T> success(String message, T t) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(200);
        result.setMessage(message);
        result.setResult(t);
        return result;
    }

    public static <T> Result<T> empty() {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> failed(int code, String message) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
