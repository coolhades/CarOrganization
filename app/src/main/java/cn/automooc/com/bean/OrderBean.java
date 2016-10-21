package cn.automooc.com.bean;

import java.util.List;

/**
 * Created by Hades on 16/9/21.
 */
public class OrderBean {

    /**
     * status : 1
     * message : ok
     * data : {"id":"ch_CKyXrL4ajHKGHOeXH4GGazDC","object":"charge","created":1474437499,"livemode":true,"paid":false,"refunded":false,"app":"app_1iD0m5LePKyHafzL","channel":"alipay","order_no":"16092198979710","client_ip":"58.209.166.191","amount":1,"amount_settle":1,"currency":"cny","subject":"汽车学堂课程","body":"汽车学堂课程","extra":{},"time_paid":null,"time_expire":1474523899,"time_settle":null,"transaction_no":null,"refunds":{"object":"list","url":"/v1/charges/ch_CKyXrL4ajHKGHOeXH4GGazDC/refunds","has_more":false,"data":[]},"amount_refunded":0,"failure_code":null,"failure_msg":null,"metadata":{"user_id":"ZHUCHUNJIE"},"credential":{"object":"credential","alipay":{"orderInfo":"service=\"mobile.securitypay.pay\"&_input_charset=\"utf-8\"&notify_url=\"https%3A%2F%2Fapi.pingxx.com%2Fnotify%2Fcharges%2Fch_CKyXrL4ajHKGHOeXH4GGazDC\"&partner=\"2088221987755174\"&out_trade_no=\"16092198979710\"&subject=\"汽车学堂课程\"&body=\"汽车学堂课程\"&total_fee=\"0.01\"&payment_type=\"1\"&seller_id=\"2088221987755174\"&it_b_pay=\"2016-09-22 13:58:19\"&sign=\"gVwbb2%2FP0S4W870iC9gHYGj5mka%2BxHe1SWojIV0RroRwWQ%2B2yp%2Bg%2BMmwKrI1bVyaWioSQ1izSxNUmuO%2F50caLNqcqkfh1dqEmsjEd87jhHyMFPWECIIjJmCvFW914kRRQBHvBhxAJNWT6LzEw0eaYD8b6CmG7TJ5qyei7tTbFNQ%3D\"&sign_type=\"RSA\""}},"description":null}
     */

    private int status;
    private String message;
    /**
     * id : ch_CKyXrL4ajHKGHOeXH4GGazDC
     * object : charge
     * created : 1474437499
     * livemode : true
     * paid : false
     * refunded : false
     * app : app_1iD0m5LePKyHafzL
     * channel : alipay
     * order_no : 16092198979710
     * client_ip : 58.209.166.191
     * amount : 1
     * amount_settle : 1
     * currency : cny
     * subject : 汽车学堂课程
     * body : 汽车学堂课程
     * extra : {}
     * time_paid : null
     * time_expire : 1474523899
     * time_settle : null
     * transaction_no : null
     * refunds : {"object":"list","url":"/v1/charges/ch_CKyXrL4ajHKGHOeXH4GGazDC/refunds","has_more":false,"data":[]}
     * amount_refunded : 0
     * failure_code : null
     * failure_msg : null
     * metadata : {"user_id":"ZHUCHUNJIE"}
     * credential : {"object":"credential","alipay":{"orderInfo":"service=\"mobile.securitypay.pay\"&_input_charset=\"utf-8\"&notify_url=\"https%3A%2F%2Fapi.pingxx.com%2Fnotify%2Fcharges%2Fch_CKyXrL4ajHKGHOeXH4GGazDC\"&partner=\"2088221987755174\"&out_trade_no=\"16092198979710\"&subject=\"汽车学堂课程\"&body=\"汽车学堂课程\"&total_fee=\"0.01\"&payment_type=\"1\"&seller_id=\"2088221987755174\"&it_b_pay=\"2016-09-22 13:58:19\"&sign=\"gVwbb2%2FP0S4W870iC9gHYGj5mka%2BxHe1SWojIV0RroRwWQ%2B2yp%2Bg%2BMmwKrI1bVyaWioSQ1izSxNUmuO%2F50caLNqcqkfh1dqEmsjEd87jhHyMFPWECIIjJmCvFW914kRRQBHvBhxAJNWT6LzEw0eaYD8b6CmG7TJ5qyei7tTbFNQ%3D\"&sign_type=\"RSA\""}}
     * description : null
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String object;
        private int created;
        private boolean livemode;
        private boolean paid;
        private boolean refunded;
        private String app;
        private String channel;
        private String order_no;
        private String client_ip;
        private int amount;
        private int amount_settle;
        private String currency;
        private String subject;
        private String body;
        private ExtraBean extra;
        private Object time_paid;
        private int time_expire;
        private Object time_settle;
        private Object transaction_no;
        /**
         * object : list
         * url : /v1/charges/ch_CKyXrL4ajHKGHOeXH4GGazDC/refunds
         * has_more : false
         * data : []
         */

        private RefundsBean refunds;
        private int amount_refunded;
        private Object failure_code;
        private Object failure_msg;
        /**
         * user_id : ZHUCHUNJIE
         */

        private MetadataBean metadata;
        /**
         * object : credential
         * alipay : {"orderInfo":"service=\"mobile.securitypay.pay\"&_input_charset=\"utf-8\"&notify_url=\"https%3A%2F%2Fapi.pingxx.com%2Fnotify%2Fcharges%2Fch_CKyXrL4ajHKGHOeXH4GGazDC\"&partner=\"2088221987755174\"&out_trade_no=\"16092198979710\"&subject=\"汽车学堂课程\"&body=\"汽车学堂课程\"&total_fee=\"0.01\"&payment_type=\"1\"&seller_id=\"2088221987755174\"&it_b_pay=\"2016-09-22 13:58:19\"&sign=\"gVwbb2%2FP0S4W870iC9gHYGj5mka%2BxHe1SWojIV0RroRwWQ%2B2yp%2Bg%2BMmwKrI1bVyaWioSQ1izSxNUmuO%2F50caLNqcqkfh1dqEmsjEd87jhHyMFPWECIIjJmCvFW914kRRQBHvBhxAJNWT6LzEw0eaYD8b6CmG7TJ5qyei7tTbFNQ%3D\"&sign_type=\"RSA\""}
         */

        private CredentialBean credential;
        private Object description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public int getCreated() {
            return created;
        }

        public void setCreated(int created) {
            this.created = created;
        }

        public boolean isLivemode() {
            return livemode;
        }

        public void setLivemode(boolean livemode) {
            this.livemode = livemode;
        }

        public boolean isPaid() {
            return paid;
        }

        public void setPaid(boolean paid) {
            this.paid = paid;
        }

        public boolean isRefunded() {
            return refunded;
        }

        public void setRefunded(boolean refunded) {
            this.refunded = refunded;
        }

        public String getApp() {
            return app;
        }

        public void setApp(String app) {
            this.app = app;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getClient_ip() {
            return client_ip;
        }

        public void setClient_ip(String client_ip) {
            this.client_ip = client_ip;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAmount_settle() {
            return amount_settle;
        }

        public void setAmount_settle(int amount_settle) {
            this.amount_settle = amount_settle;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public ExtraBean getExtra() {
            return extra;
        }

        public void setExtra(ExtraBean extra) {
            this.extra = extra;
        }

        public Object getTime_paid() {
            return time_paid;
        }

        public void setTime_paid(Object time_paid) {
            this.time_paid = time_paid;
        }

        public int getTime_expire() {
            return time_expire;
        }

        public void setTime_expire(int time_expire) {
            this.time_expire = time_expire;
        }

        public Object getTime_settle() {
            return time_settle;
        }

        public void setTime_settle(Object time_settle) {
            this.time_settle = time_settle;
        }

        public Object getTransaction_no() {
            return transaction_no;
        }

        public void setTransaction_no(Object transaction_no) {
            this.transaction_no = transaction_no;
        }

        public RefundsBean getRefunds() {
            return refunds;
        }

        public void setRefunds(RefundsBean refunds) {
            this.refunds = refunds;
        }

        public int getAmount_refunded() {
            return amount_refunded;
        }

        public void setAmount_refunded(int amount_refunded) {
            this.amount_refunded = amount_refunded;
        }

        public Object getFailure_code() {
            return failure_code;
        }

        public void setFailure_code(Object failure_code) {
            this.failure_code = failure_code;
        }

        public Object getFailure_msg() {
            return failure_msg;
        }

        public void setFailure_msg(Object failure_msg) {
            this.failure_msg = failure_msg;
        }

        public MetadataBean getMetadata() {
            return metadata;
        }

        public void setMetadata(MetadataBean metadata) {
            this.metadata = metadata;
        }

        public CredentialBean getCredential() {
            return credential;
        }

        public void setCredential(CredentialBean credential) {
            this.credential = credential;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public static class ExtraBean {
        }

        public static class RefundsBean {
            private String object;
            private String url;
            private boolean has_more;
            private List<?> data;

            public String getObject() {
                return object;
            }

            public void setObject(String object) {
                this.object = object;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isHas_more() {
                return has_more;
            }

            public void setHas_more(boolean has_more) {
                this.has_more = has_more;
            }

            public List<?> getData() {
                return data;
            }

            public void setData(List<?> data) {
                this.data = data;
            }
        }

        public static class MetadataBean {
            private String user_id;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }
        }

        public static class CredentialBean {
            private String object;
            /**
             * orderInfo : service="mobile.securitypay.pay"&_input_charset="utf-8"&notify_url="https%3A%2F%2Fapi.pingxx.com%2Fnotify%2Fcharges%2Fch_CKyXrL4ajHKGHOeXH4GGazDC"&partner="2088221987755174"&out_trade_no="16092198979710"&subject="汽车学堂课程"&body="汽车学堂课程"&total_fee="0.01"&payment_type="1"&seller_id="2088221987755174"&it_b_pay="2016-09-22 13:58:19"&sign="gVwbb2%2FP0S4W870iC9gHYGj5mka%2BxHe1SWojIV0RroRwWQ%2B2yp%2Bg%2BMmwKrI1bVyaWioSQ1izSxNUmuO%2F50caLNqcqkfh1dqEmsjEd87jhHyMFPWECIIjJmCvFW914kRRQBHvBhxAJNWT6LzEw0eaYD8b6CmG7TJ5qyei7tTbFNQ%3D"&sign_type="RSA"
             */

            private AlipayBean alipay;

            public String getObject() {
                return object;
            }

            public void setObject(String object) {
                this.object = object;
            }

            public AlipayBean getAlipay() {
                return alipay;
            }

            public void setAlipay(AlipayBean alipay) {
                this.alipay = alipay;
            }

            public static class AlipayBean {
                private String orderInfo;

                public String getOrderInfo() {
                    return orderInfo;
                }

                public void setOrderInfo(String orderInfo) {
                    this.orderInfo = orderInfo;
                }
            }
        }
    }
}
