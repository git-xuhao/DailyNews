package com.geek.dailynews.bean;

/**
 * Created by Xiho on 2017/2/20.
 * 返回结果的实体类
 */

public class ResultBean<T> {

    private String stat;
    private T data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;

    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "stat='" + stat + '\'' +
                ", data=" + data +
                '}';
    }
}
