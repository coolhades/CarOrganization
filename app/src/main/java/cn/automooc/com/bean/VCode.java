package cn.automooc.com.bean;

import java.util.List;

/**
 * Created by Hades on 2016/10/31.
 */

public class VCode {

    /**
     * status : 1
     * message : 注册码发送成功 请耐心等待
     * data : []
     */

    private int status;
    private String message;
    private List<?> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
