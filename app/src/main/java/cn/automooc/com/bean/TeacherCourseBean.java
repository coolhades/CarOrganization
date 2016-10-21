package cn.automooc.com.bean;

import java.util.List;

/**
 * Created by Hades on 16/9/22.
 */
public class TeacherCourseBean {

    /**
     * status : 1
     * message : ok
     * data : {"item":{"list":[{"course_id":"946CF4CF4446427F924081F84693745A","course_name":"MOOC的视频表现形式","course_album":"http://img1.auto-mooc.com/course/album/8A/8ADFDF6C322340589DE5A6023B62F66B.jpg","teacher_name":"孙胤胤","date_start":"未设置","date_end":"未设置","num_visit":"393","num_hour":"8","price_original":"0","price":0,"recommend_type":1},{"course_id":"3875D4221C43417BA7E7633AB3A40621","course_name":"汽车工程基础知识","course_album":"http://img1.auto-mooc.com/course/album/29/2955C5DEC6A04745A7FBCB3E47C20EF7.jpg","teacher_name":"孙胤胤","date_start":"未设置","date_end":"未设置","num_visit":"120","num_hour":"15","price_original":"0","price":0,"recommend_type":1}],"pagination":{"pageParam":"page","pageSizeParam":"per-page","forcePageParam":true,"route":null,"params":null,"urlManager":null,"validatePage":true,"totalCount":"2","defaultPageSize":20,"pageSizeLimit":[1,50]}},"teacherInfo":{"user_nick_name":"孙胤胤","teacher_photo":"http://img1.auto-mooc.com/user/avatar/20160911/2E39050ABDF44B168EE5CA8149EF0769.png","user_avatar":"http://img1.auto-mooc.com/user/avatar/20160911/2E39050ABDF44B168EE5CA8149EF0769.png","user_desc_s":"清华大学苏州汽车研究院高级培训师；清研车联教育MOOC事业部总监。","user_desc_l":"孙胤胤，清华大学苏州汽车研究院高级培训师；清研车联教育MOOC事业部总监，汽车学堂产品经理，毕业于苏州大学，电子与通信工程硕士，MBA。工程师、汽车维修高级技师。研究方向为汽车在线职业教育，长期为职业院校教师、后市场企业做现代教师、企业转型互联网能力提升培训。主讲课程有《教你如何做MOOC》、《基于MOOC的汽车人才培养混合式教学开展》、《汽车售后服务互联网转型》、《MOOC的表现形式》。","user_title":"MOOC事业部总监"}}
     */

    private int status;
    private String message;
    /**
     * item : {"list":[{"course_id":"946CF4CF4446427F924081F84693745A","course_name":"MOOC的视频表现形式","course_album":"http://img1.auto-mooc.com/course/album/8A/8ADFDF6C322340589DE5A6023B62F66B.jpg","teacher_name":"孙胤胤","date_start":"未设置","date_end":"未设置","num_visit":"393","num_hour":"8","price_original":"0","price":0,"recommend_type":1},{"course_id":"3875D4221C43417BA7E7633AB3A40621","course_name":"汽车工程基础知识","course_album":"http://img1.auto-mooc.com/course/album/29/2955C5DEC6A04745A7FBCB3E47C20EF7.jpg","teacher_name":"孙胤胤","date_start":"未设置","date_end":"未设置","num_visit":"120","num_hour":"15","price_original":"0","price":0,"recommend_type":1}],"pagination":{"pageParam":"page","pageSizeParam":"per-page","forcePageParam":true,"route":null,"params":null,"urlManager":null,"validatePage":true,"totalCount":"2","defaultPageSize":20,"pageSizeLimit":[1,50]}}
     * teacherInfo : {"user_nick_name":"孙胤胤","teacher_photo":"http://img1.auto-mooc.com/user/avatar/20160911/2E39050ABDF44B168EE5CA8149EF0769.png","user_avatar":"http://img1.auto-mooc.com/user/avatar/20160911/2E39050ABDF44B168EE5CA8149EF0769.png","user_desc_s":"清华大学苏州汽车研究院高级培训师；清研车联教育MOOC事业部总监。","user_desc_l":"孙胤胤，清华大学苏州汽车研究院高级培训师；清研车联教育MOOC事业部总监，汽车学堂产品经理，毕业于苏州大学，电子与通信工程硕士，MBA。工程师、汽车维修高级技师。研究方向为汽车在线职业教育，长期为职业院校教师、后市场企业做现代教师、企业转型互联网能力提升培训。主讲课程有《教你如何做MOOC》、《基于MOOC的汽车人才培养混合式教学开展》、《汽车售后服务互联网转型》、《MOOC的表现形式》。","user_title":"MOOC事业部总监"}
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
        /**
         * list : [{"course_id":"946CF4CF4446427F924081F84693745A","course_name":"MOOC的视频表现形式","course_album":"http://img1.auto-mooc.com/course/album/8A/8ADFDF6C322340589DE5A6023B62F66B.jpg","teacher_name":"孙胤胤","date_start":"未设置","date_end":"未设置","num_visit":"393","num_hour":"8","price_original":"0","price":0,"recommend_type":1},{"course_id":"3875D4221C43417BA7E7633AB3A40621","course_name":"汽车工程基础知识","course_album":"http://img1.auto-mooc.com/course/album/29/2955C5DEC6A04745A7FBCB3E47C20EF7.jpg","teacher_name":"孙胤胤","date_start":"未设置","date_end":"未设置","num_visit":"120","num_hour":"15","price_original":"0","price":0,"recommend_type":1}]
         * pagination : {"pageParam":"page","pageSizeParam":"per-page","forcePageParam":true,"route":null,"params":null,"urlManager":null,"validatePage":true,"totalCount":"2","defaultPageSize":20,"pageSizeLimit":[1,50]}
         */

        private ItemBean item;
        /**
         * user_nick_name : 孙胤胤
         * teacher_photo : http://img1.auto-mooc.com/user/avatar/20160911/2E39050ABDF44B168EE5CA8149EF0769.png
         * user_avatar : http://img1.auto-mooc.com/user/avatar/20160911/2E39050ABDF44B168EE5CA8149EF0769.png
         * user_desc_s : 清华大学苏州汽车研究院高级培训师；清研车联教育MOOC事业部总监。
         * user_desc_l : 孙胤胤，清华大学苏州汽车研究院高级培训师；清研车联教育MOOC事业部总监，汽车学堂产品经理，毕业于苏州大学，电子与通信工程硕士，MBA。工程师、汽车维修高级技师。研究方向为汽车在线职业教育，长期为职业院校教师、后市场企业做现代教师、企业转型互联网能力提升培训。主讲课程有《教你如何做MOOC》、《基于MOOC的汽车人才培养混合式教学开展》、《汽车售后服务互联网转型》、《MOOC的表现形式》。
         * user_title : MOOC事业部总监
         */

        private TeacherInfoBean teacherInfo;

        public ItemBean getItem() {
            return item;
        }

        public void setItem(ItemBean item) {
            this.item = item;
        }

        public TeacherInfoBean getTeacherInfo() {
            return teacherInfo;
        }

        public void setTeacherInfo(TeacherInfoBean teacherInfo) {
            this.teacherInfo = teacherInfo;
        }

        public static class ItemBean {
            /**
             * pageParam : page
             * pageSizeParam : per-page
             * forcePageParam : true
             * route : null
             * params : null
             * urlManager : null
             * validatePage : true
             * totalCount : 2
             * defaultPageSize : 20
             * pageSizeLimit : [1,50]
             */

            private PaginationBean pagination;
            /**
             * course_id : 946CF4CF4446427F924081F84693745A
             * course_name : MOOC的视频表现形式
             * course_album : http://img1.auto-mooc.com/course/album/8A/8ADFDF6C322340589DE5A6023B62F66B.jpg
             * teacher_name : 孙胤胤
             * date_start : 未设置
             * date_end : 未设置
             * num_visit : 393
             * num_hour : 8
             * price_original : 0
             * price : 0
             * recommend_type : 1
             */

            private List<ListBean> list;

            public PaginationBean getPagination() {
                return pagination;
            }

            public void setPagination(PaginationBean pagination) {
                this.pagination = pagination;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class PaginationBean {
                private String pageParam;
                private String pageSizeParam;
                private boolean forcePageParam;
                private Object route;
                private Object params;
                private Object urlManager;
                private boolean validatePage;
                private String totalCount;
                private int defaultPageSize;
                private List<Integer> pageSizeLimit;

                public String getPageParam() {
                    return pageParam;
                }

                public void setPageParam(String pageParam) {
                    this.pageParam = pageParam;
                }

                public String getPageSizeParam() {
                    return pageSizeParam;
                }

                public void setPageSizeParam(String pageSizeParam) {
                    this.pageSizeParam = pageSizeParam;
                }

                public boolean isForcePageParam() {
                    return forcePageParam;
                }

                public void setForcePageParam(boolean forcePageParam) {
                    this.forcePageParam = forcePageParam;
                }

                public Object getRoute() {
                    return route;
                }

                public void setRoute(Object route) {
                    this.route = route;
                }

                public Object getParams() {
                    return params;
                }

                public void setParams(Object params) {
                    this.params = params;
                }

                public Object getUrlManager() {
                    return urlManager;
                }

                public void setUrlManager(Object urlManager) {
                    this.urlManager = urlManager;
                }

                public boolean isValidatePage() {
                    return validatePage;
                }

                public void setValidatePage(boolean validatePage) {
                    this.validatePage = validatePage;
                }

                public String getTotalCount() {
                    return totalCount;
                }

                public void setTotalCount(String totalCount) {
                    this.totalCount = totalCount;
                }

                public int getDefaultPageSize() {
                    return defaultPageSize;
                }

                public void setDefaultPageSize(int defaultPageSize) {
                    this.defaultPageSize = defaultPageSize;
                }

                public List<Integer> getPageSizeLimit() {
                    return pageSizeLimit;
                }

                public void setPageSizeLimit(List<Integer> pageSizeLimit) {
                    this.pageSizeLimit = pageSizeLimit;
                }
            }

            public static class ListBean {
                private String course_id;
                private String course_name;
                private String course_album;
                private String teacher_name;
                private String date_start;
                private String date_end;
                private String num_visit;
                private String num_hour;
                private String price_original;
                private String price;
                private int recommend_type;

                public String getCourse_id() {
                    return course_id;
                }

                public void setCourse_id(String course_id) {
                    this.course_id = course_id;
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

                public String getTeacher_name() {
                    return teacher_name;
                }

                public void setTeacher_name(String teacher_name) {
                    this.teacher_name = teacher_name;
                }

                public String getDate_start() {
                    return date_start;
                }

                public void setDate_start(String date_start) {
                    this.date_start = date_start;
                }

                public String getDate_end() {
                    return date_end;
                }

                public void setDate_end(String date_end) {
                    this.date_end = date_end;
                }

                public String getNum_visit() {
                    return num_visit;
                }

                public void setNum_visit(String num_visit) {
                    this.num_visit = num_visit;
                }

                public String getNum_hour() {
                    return num_hour;
                }

                public void setNum_hour(String num_hour) {
                    this.num_hour = num_hour;
                }

                public String getPrice_original() {
                    return price_original;
                }

                public void setPrice_original(String price_original) {
                    this.price_original = price_original;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public int getRecommend_type() {
                    return recommend_type;
                }

                public void setRecommend_type(int recommend_type) {
                    this.recommend_type = recommend_type;
                }
            }
        }

        public static class TeacherInfoBean {
            private String user_nick_name;
            private String teacher_photo;
            private String user_avatar;
            private String user_desc_s;
            private String user_desc_l;
            private String user_title;

            public String getUser_nick_name() {
                return user_nick_name;
            }

            public void setUser_nick_name(String user_nick_name) {
                this.user_nick_name = user_nick_name;
            }

            public String getTeacher_photo() {
                return teacher_photo;
            }

            public void setTeacher_photo(String teacher_photo) {
                this.teacher_photo = teacher_photo;
            }

            public String getUser_avatar() {
                return user_avatar;
            }

            public void setUser_avatar(String user_avatar) {
                this.user_avatar = user_avatar;
            }

            public String getUser_desc_s() {
                return user_desc_s;
            }

            public void setUser_desc_s(String user_desc_s) {
                this.user_desc_s = user_desc_s;
            }

            public String getUser_desc_l() {
                return user_desc_l;
            }

            public void setUser_desc_l(String user_desc_l) {
                this.user_desc_l = user_desc_l;
            }

            public String getUser_title() {
                return user_title;
            }

            public void setUser_title(String user_title) {
                this.user_title = user_title;
            }
        }
    }
}
