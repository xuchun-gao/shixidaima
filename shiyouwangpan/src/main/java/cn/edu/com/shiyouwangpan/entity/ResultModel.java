package cn.edu.com.shiyouwangpan.entity;

import java.util.List;

public class ResultModel<T> {

    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE = 500;

    /**
     * 操作是否成功
     */
    private int code;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 总数
     */
    private int total;

    /**
     * 结果数据
     */
    private T data;






    public ResultModel(int code, String msg, T data, int total) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.total=total;
    }

    public ResultModel(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }



    public ResultModel() {
    }

    public static <T> ResultModel<T> success() {
        return success("操作成功 !", null);
    }

    public static <T> ResultModel<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> ResultModel<T> success(String msg, T data) {
        return new ResultModel<>(ResultModel.SUCCESS_CODE, msg, data);
    }

    public static <T> ResultModel<T> success(String msg, T data,int total) {
        return new ResultModel<>(ResultModel.SUCCESS_CODE, msg, data,total);
    }



    public static <T> ResultModel<T> error() {
        return error("操作失败 !", null);
    }

    public static <T> ResultModel<T> dbError() {
        return error("数据库 操作失败 !", null);
    }

    public static <T> ResultModel<T> hdfsError() {
        return error("HDFS 操作失败 !", null);
    }

    public static <T> ResultModel<T> error(String msg) {
        return error(msg, null);
    }

    public static <T> ResultModel<T> error(String msg, T data) {
        return new ResultModel<>(ResultModel.ERROR_CODE, msg, data);
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
