package cn.automooc.com.bean;

import java.util.List;

/**
 * Created by Hades on 16/9/30.
 */

public class TeacherListBean {

    /**
     * status : 1
     * message : ok
     * data : {"list":[{"teacher_id":"89B752884B8447BDBCC9E45AA07833A0","teacher_name":"曹安","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/2D89C53BFD3945A78D6C982EAFB61E80.png","introduction":"一直从事汽车底盘系统的开发，曾在重庆长安、北汽福田乘用车工作。","course_num":"1"},{"teacher_id":"1219BB47DD314FB9880172C25A18B964","teacher_name":"陈小道","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/60D939F9F09E4527B949E64A43F14266.png","introduction":"捷豹路虎经销商技术支持工程师","course_num":"2"},{"teacher_id":"6FBE003E06864A7B9564D1A9669643A0","teacher_name":"董金聪","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/E7DF2B6F717740CCBBBF120AA05748EF.png","introduction":"张家港清研再制造产业研究院副院长,资深职业经理人。","course_num":"1"},{"teacher_id":"809466894B7A4F2D8A75CE9A997EF1D8","teacher_name":"韩宗奇","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/359B5EE76F5C45D6A10886290591FAC4.png","introduction":"燕山大学车辆工程专业教授、博士","course_num":"1"},{"teacher_id":"A1ABC4AD171E442B8598DEFA32167459","teacher_name":"华伦","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/24672E1FA63E41A78E55861AB674F3FE.png","introduction":"华伦，副研究员，吉林大学物理学博士，清华大学汽车系博士后，清华大学苏州汽车研究院排气净化技术研究所副所长，江苏清研昊宸排气净化科技有限公司常务副总经理。","course_num":"1"},{"teacher_id":"3DA4F860F992439EB8A07CFAC668A323","teacher_name":"金爱艳","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/8752F66CC9B84708B9837AB188068C2B.png","introduction":"苏州大学，双硕士，讲师。","course_num":"0"},{"teacher_id":"A75B9D70C3A8474FAD18DB434E63F744","teacher_name":"康红朋","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/83E19162DF424797AD8849F0A8352BC3.png","introduction":"河北工程大学机械设计制造专业，从事白车身零件设计工作多年。","course_num":"0"},{"teacher_id":"F99AEBA3865B4095B510C6D9A762E5EB","teacher_name":"李秉忠","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160927/11BC9E236CEA41F7A6DC7349F82CB67D.png","introduction":"1986年毕业于上海交通大学，1989年机械科学研究院机械学硕士研究生毕业，现任武汉材料保护研究所副总工程师，研究员，硕士研究生导师","course_num":"0"},{"teacher_id":"647D61ACF2CC4426B2B81E9787166E27","teacher_name":"李常涛","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/0AE9ACF3192644B6ACB4F9F19D944D34.png","introduction":"中鑫之宝，奔驰技术主管，处理全国奔驰技术难题。","course_num":"4"},{"teacher_id":"6124BBB600134D1D9E334E455011CDF3","teacher_name":"李钢亮","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/FF2AC9864A58456DAD36F1A958C339D5.png","introduction":"李钢亮，技术经理。","course_num":"0"},{"teacher_id":"A0B0E2485C5C41D18FC711EA193510DE","teacher_name":"李克强","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/3105AE0A70E1447CB1982476604A81B4.png","introduction":"教育部长江学者特聘教授、清华大学汽车工程系系主任","course_num":"1"},{"teacher_id":"A3F52C250E6540EEBC3A7B9E053DEE88","teacher_name":"李铁良","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/A21F8A4AD7B7416CB5D71554126FD9BC.png","introduction":"电气工程及其自动化专业，先后在中兴汽车、长城汽车等公司从事汽车总装工艺工作。","course_num":"0"},{"teacher_id":"1BA0A67DB57D486CB4FEC17DB099F743","teacher_name":"李鑫","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/2DCB1D3AFB034955B121A50DF38CA4F7.png","introduction":"","course_num":"1"},{"teacher_id":"BA62406C8D234C69B36120F5598C5590","teacher_name":"刘坚东","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/D3B1E86DE1AD477390DB19FE5C927DFA.png","introduction":"高级工程师，从事过产品工艺设计、产品设计开发、标准化管理等工作，具有丰富的汽车行业实际工作经验。","course_num":"1"},{"teacher_id":"97BFEA1471D044CCB6D06DE3D107C746","teacher_name":"卢晓江","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160926/CCD2A425970045F8947E61562BCEE691.png","introduction":"现任加拿大艾伯塔大学化学工程系客座教授，天津科技大学机械工程学院教授。","course_num":"1"},{"teacher_id":"4E05ED4D4AAA42F181709DCE556AACFD","teacher_name":"裘文才","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160911/765ECB8BCDD34EC3A704FAAEE59763A0.png","introduction":"中国汽车工程学会汽车应用技术与服务专业特聘专家，上海市汽车销售行业协会专家组专家","course_num":"5"},{"teacher_id":"5460B94F6BF14B6E92647D8AC5BD4448","teacher_name":"冉艳玲","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/CB749DB1441240B898DAF16AC462FB1D.png","introduction":"冉艳玲，北京长城华冠汽车股份有限公司先期制造部部长。","course_num":"1"},{"teacher_id":"C6CEF3908E8643EA91C2FD83856F4288","teacher_name":"史杰","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/CF3BCFA5CB6D4EC8B84A6672D3B53C8A.png","introduction":"上海汽车集团商用车技术中心\u2014汽车系统开发工程师。","course_num":"1"},{"teacher_id":"BC2201605F1E48CFAB67B47072A2141D","teacher_name":"苏亚军","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/4D95C864681D425198E6E802619AE0E1.png","introduction":"北京长城华冠汽车科技股份公司车身工程二部金属材料工程师、工学博士。","course_num":"0"},{"teacher_id":"4F6E7B7217354BF794ABE1BFA65BFA8C","teacher_name":"陶秀申","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/B227A845B1DE4BEF86C14E85BD5338DE.png","introduction":"中北大学材料系，金属腐蚀与防护专业，先后在白云机械厂（兵工）、金杯汽车、中顺汽车、三一重工等涂装工作。","course_num":"1"},{"teacher_id":"993282F465DC45178C5849B63464A93A","teacher_name":"王迪娜","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/9123B46580A547E5969A741090D7C244.png","introduction":"汽车后市场资深专家","course_num":"1"},{"teacher_id":"8163EFA188E54FA892B079EC22109CAA","teacher_name":"徐先弟","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/59E3D6F746C246EAAD90E5F5973A0D2F.png","introduction":"丰富的CATIA软件操作经验,长期担任公司内外CATIA软件基础培训工作,且为主机厂提供过软件培训服务获得一致好评。","course_num":"1"},{"teacher_id":"92B49744A28F4696A0FE6F71BC4CC12B","teacher_name":"一点就悟-奥迪小宝","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/FA6687DC811A4C84AE18AF2C8E2C3D7A.png","introduction":"长期从事奥迪一线维修工作，擅长奥迪各系统讲解。","course_num":"3"},{"teacher_id":"380716B921574C49A1695D292689A7B6","teacher_name":"张玺","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/B1D0A3988C234E9CAB54C0EFF51EE499.png","introduction":"毕业于南京航空航天大学飞行器设计与工程专业","course_num":"0"},{"teacher_id":"C13475115627464DAFC5D05C5FCE6F2D","teacher_name":"赵凯","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/CEBB9972882540B3872A032B3334B54E.png","introduction":"北京长城华冠汽车股份有限公司先期制造部焊装工艺工程师","course_num":"0"},{"teacher_id":"D104CFCA8FBA48DE84BD16064D8F6681","teacher_name":"赵玉风","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/1E0720D25AE4445D824DF0EC1AA91D85.png","introduction":"长城华冠电子电器主任工程师","course_num":"1"}],"num":"26","page":1,"pagesize":500,"pagemax":1}
     */

    private int status;
    private String message;
    /**
     * list : [{"teacher_id":"89B752884B8447BDBCC9E45AA07833A0","teacher_name":"曹安","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/2D89C53BFD3945A78D6C982EAFB61E80.png","introduction":"一直从事汽车底盘系统的开发，曾在重庆长安、北汽福田乘用车工作。","course_num":"1"},{"teacher_id":"1219BB47DD314FB9880172C25A18B964","teacher_name":"陈小道","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/60D939F9F09E4527B949E64A43F14266.png","introduction":"捷豹路虎经销商技术支持工程师","course_num":"2"},{"teacher_id":"6FBE003E06864A7B9564D1A9669643A0","teacher_name":"董金聪","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/E7DF2B6F717740CCBBBF120AA05748EF.png","introduction":"张家港清研再制造产业研究院副院长,资深职业经理人。","course_num":"1"},{"teacher_id":"809466894B7A4F2D8A75CE9A997EF1D8","teacher_name":"韩宗奇","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/359B5EE76F5C45D6A10886290591FAC4.png","introduction":"燕山大学车辆工程专业教授、博士","course_num":"1"},{"teacher_id":"A1ABC4AD171E442B8598DEFA32167459","teacher_name":"华伦","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/24672E1FA63E41A78E55861AB674F3FE.png","introduction":"华伦，副研究员，吉林大学物理学博士，清华大学汽车系博士后，清华大学苏州汽车研究院排气净化技术研究所副所长，江苏清研昊宸排气净化科技有限公司常务副总经理。","course_num":"1"},{"teacher_id":"3DA4F860F992439EB8A07CFAC668A323","teacher_name":"金爱艳","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/8752F66CC9B84708B9837AB188068C2B.png","introduction":"苏州大学，双硕士，讲师。","course_num":"0"},{"teacher_id":"A75B9D70C3A8474FAD18DB434E63F744","teacher_name":"康红朋","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/83E19162DF424797AD8849F0A8352BC3.png","introduction":"河北工程大学机械设计制造专业，从事白车身零件设计工作多年。","course_num":"0"},{"teacher_id":"F99AEBA3865B4095B510C6D9A762E5EB","teacher_name":"李秉忠","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160927/11BC9E236CEA41F7A6DC7349F82CB67D.png","introduction":"1986年毕业于上海交通大学，1989年机械科学研究院机械学硕士研究生毕业，现任武汉材料保护研究所副总工程师，研究员，硕士研究生导师","course_num":"0"},{"teacher_id":"647D61ACF2CC4426B2B81E9787166E27","teacher_name":"李常涛","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/0AE9ACF3192644B6ACB4F9F19D944D34.png","introduction":"中鑫之宝，奔驰技术主管，处理全国奔驰技术难题。","course_num":"4"},{"teacher_id":"6124BBB600134D1D9E334E455011CDF3","teacher_name":"李钢亮","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/FF2AC9864A58456DAD36F1A958C339D5.png","introduction":"李钢亮，技术经理。","course_num":"0"},{"teacher_id":"A0B0E2485C5C41D18FC711EA193510DE","teacher_name":"李克强","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/3105AE0A70E1447CB1982476604A81B4.png","introduction":"教育部长江学者特聘教授、清华大学汽车工程系系主任","course_num":"1"},{"teacher_id":"A3F52C250E6540EEBC3A7B9E053DEE88","teacher_name":"李铁良","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/A21F8A4AD7B7416CB5D71554126FD9BC.png","introduction":"电气工程及其自动化专业，先后在中兴汽车、长城汽车等公司从事汽车总装工艺工作。","course_num":"0"},{"teacher_id":"1BA0A67DB57D486CB4FEC17DB099F743","teacher_name":"李鑫","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/2DCB1D3AFB034955B121A50DF38CA4F7.png","introduction":"","course_num":"1"},{"teacher_id":"BA62406C8D234C69B36120F5598C5590","teacher_name":"刘坚东","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/D3B1E86DE1AD477390DB19FE5C927DFA.png","introduction":"高级工程师，从事过产品工艺设计、产品设计开发、标准化管理等工作，具有丰富的汽车行业实际工作经验。","course_num":"1"},{"teacher_id":"97BFEA1471D044CCB6D06DE3D107C746","teacher_name":"卢晓江","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160926/CCD2A425970045F8947E61562BCEE691.png","introduction":"现任加拿大艾伯塔大学化学工程系客座教授，天津科技大学机械工程学院教授。","course_num":"1"},{"teacher_id":"4E05ED4D4AAA42F181709DCE556AACFD","teacher_name":"裘文才","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160911/765ECB8BCDD34EC3A704FAAEE59763A0.png","introduction":"中国汽车工程学会汽车应用技术与服务专业特聘专家，上海市汽车销售行业协会专家组专家","course_num":"5"},{"teacher_id":"5460B94F6BF14B6E92647D8AC5BD4448","teacher_name":"冉艳玲","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/CB749DB1441240B898DAF16AC462FB1D.png","introduction":"冉艳玲，北京长城华冠汽车股份有限公司先期制造部部长。","course_num":"1"},{"teacher_id":"C6CEF3908E8643EA91C2FD83856F4288","teacher_name":"史杰","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/CF3BCFA5CB6D4EC8B84A6672D3B53C8A.png","introduction":"上海汽车集团商用车技术中心\u2014汽车系统开发工程师。","course_num":"1"},{"teacher_id":"BC2201605F1E48CFAB67B47072A2141D","teacher_name":"苏亚军","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/4D95C864681D425198E6E802619AE0E1.png","introduction":"北京长城华冠汽车科技股份公司车身工程二部金属材料工程师、工学博士。","course_num":"0"},{"teacher_id":"4F6E7B7217354BF794ABE1BFA65BFA8C","teacher_name":"陶秀申","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/B227A845B1DE4BEF86C14E85BD5338DE.png","introduction":"中北大学材料系，金属腐蚀与防护专业，先后在白云机械厂（兵工）、金杯汽车、中顺汽车、三一重工等涂装工作。","course_num":"1"},{"teacher_id":"993282F465DC45178C5849B63464A93A","teacher_name":"王迪娜","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/9123B46580A547E5969A741090D7C244.png","introduction":"汽车后市场资深专家","course_num":"1"},{"teacher_id":"8163EFA188E54FA892B079EC22109CAA","teacher_name":"徐先弟","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/59E3D6F746C246EAAD90E5F5973A0D2F.png","introduction":"丰富的CATIA软件操作经验,长期担任公司内外CATIA软件基础培训工作,且为主机厂提供过软件培训服务获得一致好评。","course_num":"1"},{"teacher_id":"92B49744A28F4696A0FE6F71BC4CC12B","teacher_name":"一点就悟-奥迪小宝","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/FA6687DC811A4C84AE18AF2C8E2C3D7A.png","introduction":"长期从事奥迪一线维修工作，擅长奥迪各系统讲解。","course_num":"3"},{"teacher_id":"380716B921574C49A1695D292689A7B6","teacher_name":"张玺","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/B1D0A3988C234E9CAB54C0EFF51EE499.png","introduction":"毕业于南京航空航天大学飞行器设计与工程专业","course_num":"0"},{"teacher_id":"C13475115627464DAFC5D05C5FCE6F2D","teacher_name":"赵凯","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/CEBB9972882540B3872A032B3334B54E.png","introduction":"北京长城华冠汽车股份有限公司先期制造部焊装工艺工程师","course_num":"0"},{"teacher_id":"D104CFCA8FBA48DE84BD16064D8F6681","teacher_name":"赵玉风","teacher_avatar":"http://img1.auto-mooc.com/user/avatar/20160923/1E0720D25AE4445D824DF0EC1AA91D85.png","introduction":"长城华冠电子电器主任工程师","course_num":"1"}]
     * num : 26
     * page : 1
     * pagesize : 500
     * pagemax : 1
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
        private String num;
        private int page;
        private int pagesize;
        private int pagemax;
        /**
         * teacher_id : 89B752884B8447BDBCC9E45AA07833A0
         * teacher_name : 曹安
         * teacher_avatar : http://img1.auto-mooc.com/user/avatar/20160923/2D89C53BFD3945A78D6C982EAFB61E80.png
         * introduction : 一直从事汽车底盘系统的开发，曾在重庆长安、北汽福田乘用车工作。
         * course_num : 1
         */

        private List<ListBean> list;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public int getPagemax() {
            return pagemax;
        }

        public void setPagemax(int pagemax) {
            this.pagemax = pagemax;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String teacher_id;
            private String teacher_name;
            private String teacher_avatar;
            private String introduction;
            private String course_num;

            public String getTeacher_id() {
                return teacher_id;
            }

            public void setTeacher_id(String teacher_id) {
                this.teacher_id = teacher_id;
            }

            public String getTeacher_name() {
                return teacher_name;
            }

            public void setTeacher_name(String teacher_name) {
                this.teacher_name = teacher_name;
            }

            public String getTeacher_avatar() {
                return teacher_avatar;
            }

            public void setTeacher_avatar(String teacher_avatar) {
                this.teacher_avatar = teacher_avatar;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getCourse_num() {
                return course_num;
            }

            public void setCourse_num(String course_num) {
                this.course_num = course_num;
            }
        }
    }
}
