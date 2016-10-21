package cn.automooc.com.bean;

import java.util.List;

/**
 * Created by Hades on 16/9/21.
 */
public class PayTypeBean {

    /**
     * status : 1
     * message : ok
     * data : [{"title":"支付宝App","code":"alipay","icon":"http://www.ab-auto-mooc.com/static/img/alipay64.png","status":true,"client":"mobile"}]
     */

    private int status;
    private String message;
    /**
     * title : 支付宝App
     * code : alipay
     * icon : http://www.ab-auto-mooc.com/static/img/alipay64.png
     * status : true
     * client : mobile
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String title;
        private String code;
        private String icon;
        private boolean status;
        private String client;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }
    }
}
