package cn.automooc.com.bean;

/**
 * Created by Hades on 16/9/27.
 */
public class UserInfoBean {

    /**
     * status : 1
     * message : Ok
     * data : {"uid":"D4F0A35F9FE8DECE5F2D0911C938BE4B","account":"723760950@qq.com","is_teacher":0,"avatar":"http://upload.auto-mooc.com/user/avatar/2016/09/27/D4F0A35F9FE8DECE5F2D0911C938BE4B.png?t=1474949206","nickname":"","email":"","phone":"","sex":"1","birthday":"1991-06-14","user_real_name":""}
     */

    private int status;
    private String message;
    /**
     * uid : D4F0A35F9FE8DECE5F2D0911C938BE4B
     * account : 723760950@qq.com
     * is_teacher : 0
     * avatar : http://upload.auto-mooc.com/user/avatar/2016/09/27/D4F0A35F9FE8DECE5F2D0911C938BE4B.png?t=1474949206
     * nickname :
     * email :
     * phone :
     * sex : 1
     * birthday : 1991-06-14
     * user_real_name :
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
        private String uid;
        private String account;
        private int is_teacher;
        private String avatar;
        private String nickname;
        private String email;
        private String phone;
        private String sex;
        private String birthday;
        private String user_real_name;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public int getIs_teacher() {
            return is_teacher;
        }

        public void setIs_teacher(int is_teacher) {
            this.is_teacher = is_teacher;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getUser_real_name() {
            return user_real_name;
        }

        public void setUser_real_name(String user_real_name) {
            this.user_real_name = user_real_name;
        }
    }
}
