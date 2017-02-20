package com.geek.dailynews.bean;

/**
 * Created by Xiho on 2017/2/20.
 * 与服务器交互，封装单个实体
 */

public class Result<T> {

    /**
     * 结果信息
     */
    private String reason;
    /**
     * 结果实体类
     */
    private T result;


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }
}
