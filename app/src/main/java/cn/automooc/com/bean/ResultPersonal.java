package cn.automooc.com.bean;

/**
 * Created by jiuzheyange on 2016/8/17.
 */
public class ResultPersonal {
    String status;
    String message;
    Personal data;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Personal getData() {
        return data;
    }

    public void setData(Personal data) {
        this.data = data;
    }
}
