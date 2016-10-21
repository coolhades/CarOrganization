package cn.automooc.com.bean;

import java.util.List;

/**
 * Created by Hades on 16/9/23.
 */
public class MyOrderListBean {

    /**
     * status : 1
     * message : ok
     * data : {"list":[{"uid":"CE9C11E9F4F5EBC8F4E08FA25524B7CA","order_id":"16092349555410","money_need":"0.00","money_pay":"0.01","money_discount":"0.00","target_type":"class","target_id":"11EF7BB211A049E0BE259B587855CAB4","user_id":"C962D443817885272248FF8F13542B0A","status":"1","createtime":"2016-09-23 08:59:29","discount_status":"0","buy_type":"alipay","course_id":"28E512C98EB34D5DB4405AC85B070A43","class_name":"汽车工程基础知识","impower_id":"3875D4221C43417BA7E7633AB3A40621","course_name":"汽车工程基础知识","course_album":"http://img1.auto-mooc.com/course/album/29/2955C5DEC6A04745A7FBCB3E47C20EF7.jpg","buy_name":"支付宝App"},{"uid":"825FFE51C6D26854BA06CC77379F9229","order_id":"16092257509710","money_need":"0.00","money_pay":"0.01","money_discount":"0.00","target_type":"class","target_id":"11EF7BB211A049E0BE259B587855CAB4","user_id":"C962D443817885272248FF8F13542B0A","status":"1","createtime":"2016-09-22 12:58:49","discount_status":"0","buy_type":"alipay","course_id":"28E512C98EB34D5DB4405AC85B070A43","class_name":"汽车工程基础知识","impower_id":"3875D4221C43417BA7E7633AB3A40621","course_name":"汽车工程基础知识","course_album":"http://img1.auto-mooc.com/course/album/29/2955C5DEC6A04745A7FBCB3E47C20EF7.jpg","buy_name":"支付宝App"},{"uid":"628F9B5D1469EB347A3E85C2DA1D8361","order_id":"16092257535650","money_need":"0.00","money_pay":"0.01","money_discount":"0.00","target_type":"class","target_id":"594A1A1283F44CF3B935838FC30E8F2A","user_id":"C962D443817885272248FF8F13542B0A","status":"0","createtime":"2016-09-22 11:24:25","discount_status":"0","buy_type":null,"course_id":"1CE43993B668450790113672196FDEFD","class_name":"新常态条件下4S店营销突破","impower_id":"C01357E488924554B7758A862BFB560D","course_name":"新常态条件下4S店营销突破","course_album":"http://img1.auto-mooc.com/course/album/6C/6C1D6C5FDE2F4A6C81CEB4D92B920434.jpg","buy_name":"无"},{"uid":"90511ABDB7BADC6CAC8F9E066ECF64E5","order_id":"16092248991025","money_need":"0.00","money_pay":"0.01","money_discount":"0.00","target_type":"class","target_id":"11EF7BB211A049E0BE259B587855CAB4","user_id":"C962D443817885272248FF8F13542B0A","status":"0","createtime":"2016-09-22 10:21:20","discount_status":"0","buy_type":null,"course_id":"28E512C98EB34D5DB4405AC85B070A43","class_name":"汽车工程基础知识","impower_id":"3875D4221C43417BA7E7633AB3A40621","course_name":"汽车工程基础知识","course_album":"http://img1.auto-mooc.com/course/album/29/2955C5DEC6A04745A7FBCB3E47C20EF7.jpg","buy_name":"无"},{"uid":"DA270D9F3DC1B5EC7FE207E5E01B13BA","order_id":"16092252501015","money_need":"0.00","money_pay":"0.01","money_discount":"0.00","target_type":"class","target_id":"C5DD5908C3074131B289926C0D46EA6D","user_id":"C962D443817885272248FF8F13542B0A","status":"0","createtime":"2016-09-22 09:59:16","discount_status":"0","buy_type":null,"course_id":"1CE43993B668450790113672196FDEFD","class_name":"新常态条件下4S店营销突破2","impower_id":"C01357E488924554B7758A862BFB560D","course_name":"新常态条件下4S店营销突破","course_album":"http://img1.auto-mooc.com/course/album/6C/6C1D6C5FDE2F4A6C81CEB4D92B920434.jpg","buy_name":"无"},{"uid":"5FB4E4350A907246CF3F3C92FDA5BAF2","order_id":"16092154101994","money_need":"0.00","money_pay":"0.01","money_discount":"0.00","target_type":"class","target_id":"11EF7BB211A049E0BE259B587855CAB4","user_id":"C962D443817885272248FF8F13542B0A","status":"0","createtime":"2016-09-21 16:38:14","discount_status":"0","buy_type":null,"course_id":"28E512C98EB34D5DB4405AC85B070A43","class_name":"汽车工程基础知识","impower_id":"3875D4221C43417BA7E7633AB3A40621","course_name":"汽车工程基础知识","course_album":"http://img1.auto-mooc.com/course/album/29/2955C5DEC6A04745A7FBCB3E47C20EF7.jpg","buy_name":"无"}]}
     */

    private int status;
    private String message;
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
        /**
         * uid : CE9C11E9F4F5EBC8F4E08FA25524B7CA
         * order_id : 16092349555410
         * money_need : 0.00
         * money_pay : 0.01
         * money_discount : 0.00
         * target_type : class
         * target_id : 11EF7BB211A049E0BE259B587855CAB4
         * user_id : C962D443817885272248FF8F13542B0A
         * status : 1
         * createtime : 2016-09-23 08:59:29
         * discount_status : 0
         * buy_type : alipay
         * course_id : 28E512C98EB34D5DB4405AC85B070A43
         * class_name : 汽车工程基础知识
         * impower_id : 3875D4221C43417BA7E7633AB3A40621
         * course_name : 汽车工程基础知识
         * course_album : http://img1.auto-mooc.com/course/album/29/2955C5DEC6A04745A7FBCB3E47C20EF7.jpg
         * buy_name : 支付宝App
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String uid;
            private String order_id;
            private String money_need;
            private String money_pay;
            private String money_discount;
            private String target_type;
            private String target_id;
            private String user_id;
            private String status;
            private String createtime;
            private String discount_status;
            private String buy_type;
            private String course_id;
            private String class_name;
            private String impower_id;
            private String course_name;
            private String course_album;
            private String buy_name;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getMoney_need() {
                return money_need;
            }

            public void setMoney_need(String money_need) {
                this.money_need = money_need;
            }

            public String getMoney_pay() {
                return money_pay;
            }

            public void setMoney_pay(String money_pay) {
                this.money_pay = money_pay;
            }

            public String getMoney_discount() {
                return money_discount;
            }

            public void setMoney_discount(String money_discount) {
                this.money_discount = money_discount;
            }

            public String getTarget_type() {
                return target_type;
            }

            public void setTarget_type(String target_type) {
                this.target_type = target_type;
            }

            public String getTarget_id() {
                return target_id;
            }

            public void setTarget_id(String target_id) {
                this.target_id = target_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getDiscount_status() {
                return discount_status;
            }

            public void setDiscount_status(String discount_status) {
                this.discount_status = discount_status;
            }

            public String getBuy_type() {
                return buy_type;
            }

            public void setBuy_type(String buy_type) {
                this.buy_type = buy_type;
            }

            public String getCourse_id() {
                return course_id;
            }

            public void setCourse_id(String course_id) {
                this.course_id = course_id;
            }

            public String getClass_name() {
                return class_name;
            }

            public void setClass_name(String class_name) {
                this.class_name = class_name;
            }

            public String getImpower_id() {
                return impower_id;
            }

            public void setImpower_id(String impower_id) {
                this.impower_id = impower_id;
            }

            public String getCourse_name() {
                return course_name;
            }

            public void setCourse_name(String course_name) {
                this.course_name = course_name;
            }

            public String getCourse_album() {
                return course_album;
            }

            public void setCourse_album(String course_album) {
                this.course_album = course_album;
            }

            public String getBuy_name() {
                return buy_name;
            }

            public void setBuy_name(String buy_name) {
                this.buy_name = buy_name;
            }
        }
    }
}
