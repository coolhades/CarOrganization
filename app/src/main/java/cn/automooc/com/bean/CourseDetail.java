package cn.automooc.com.bean;

import java.util.List;

/**
 * Created by Hades on 16/9/20.
 */
public class CourseDetail {

    /**
     * status : 1
     * message : ok
     * data : {"info":{"impower_id":"946CF4CF4446427F924081F84693745A","impower_price":"0","course_name":"MOOC的视频表现形式","course_album":"http://img1.auto-mooc.com/course/album/8A/8ADFDF6C322340589DE5A6023B62F66B.jpg","course_intro_video":"CCCFCD50496B4AEEA52044E8E0F2B45E","course_desc_m":"     通过本门课程的学习，学员能够快速了解MOOC视频的多种表现形式，以及其适用场合，作为MOOC课程教师在各种表现形式下，应当如何准备课程。本课程能够为刚接触并想投身于MOOC教学的讲师，MOOC课程策划者、MOOC课程的摄影师与视频剪辑师提供参考和指导。","teacher_name":"孙胤胤","num_visit":"381","recommend_type":1,"is_follow":0,"classArr":[{"class_name":"Mooc的视频表现形式-2016","class_apply_type":"1","begin_date":"2016-08-31 00:00:00","end_date":"2017-12-31 00:00:00","real_price":"0.01","class_id":"B5C7AC905CB845C3A8B1151810D260F3","is_classmember":0,"is_paid":0},{"class_name":"Mooc的视频表现-TEST001","class_apply_type":"1","begin_date":"0000-00-00 00:00:00","end_date":"2017-09-19 21:25:30","real_price":"0.03","class_id":"TEST001","is_classmember":0,"is_paid":0},{"class_name":"Mooc的视频表现-TEST002","class_apply_type":"1","begin_date":"0000-00-00 00:00:00","end_date":"2017-09-19 21:25:30","real_price":"0.02","class_id":"TEST002","is_classmember":0,"is_paid":0}]},"section":[{"section_uid":"F359B1E089824204A2949A6058451599","node_type":"1","node_caption":"第一章 MOOC的视频表现形式","is_watched":0,"video_duration":"0","child":[{"section_uid":"AC7915B340D5486F81A10E3BCB00E41C","node_type":"2","node_caption":"1.1 挑选适合老师的视频表达脚本","is_watched":0,"video_duration":"154"},{"section_uid":"944B8C473FB5400E8327E2928C9BF73C","node_type":"2","node_caption":"1.2 出境讲解的示例与注意事项","is_watched":0,"video_duration":"348"},{"section_uid":"71660FAC06184E419B0DE242623FEC6B","node_type":"2","node_caption":"1.3 录屏讲解的操作要点","is_watched":0,"video_duration":"109"},{"section_uid":"34D82BE172784350BB90F1A348F3F0D6","node_type":"2","node_caption":"1.4 实景演示应该如何表现","is_watched":0,"video_duration":"84"},{"section_uid":"363AABBCA0A14F51A0DF6B63F565752E","node_type":"2","node_caption":"1.5 访谈式教学的特点","is_watched":0,"video_duration":"154"},{"section_uid":"48CAF8A99A944CA883517B2F2B718CC7","node_type":"2","node_caption":"1.6 宣传片教学的特点","is_watched":0,"video_duration":"358"},{"section_uid":"66EFC816A1C445109098C2A34F9FF570","node_type":"2","node_caption":"1.7 动画式讲解的操作要点","is_watched":0,"video_duration":"188"},{"section_uid":"B6D7C2814293461D8F3BFE1666C2D1BB","node_type":"2","node_caption":"1.8 手绘式教学的操作要点","is_watched":0,"video_duration":"408"}]}]}
     */

    private int status;
    private String message;
    /**
     * info : {"impower_id":"946CF4CF4446427F924081F84693745A","impower_price":"0","course_name":"MOOC的视频表现形式","course_album":"http://img1.auto-mooc.com/course/album/8A/8ADFDF6C322340589DE5A6023B62F66B.jpg","course_intro_video":"CCCFCD50496B4AEEA52044E8E0F2B45E","course_desc_m":"     通过本门课程的学习，学员能够快速了解MOOC视频的多种表现形式，以及其适用场合，作为MOOC课程教师在各种表现形式下，应当如何准备课程。本课程能够为刚接触并想投身于MOOC教学的讲师，MOOC课程策划者、MOOC课程的摄影师与视频剪辑师提供参考和指导。","teacher_name":"孙胤胤","num_visit":"381","recommend_type":1,"is_follow":0,"classArr":[{"class_name":"Mooc的视频表现形式-2016","class_apply_type":"1","begin_date":"2016-08-31 00:00:00","end_date":"2017-12-31 00:00:00","real_price":"0.01","class_id":"B5C7AC905CB845C3A8B1151810D260F3","is_classmember":0,"is_paid":0},{"class_name":"Mooc的视频表现-TEST001","class_apply_type":"1","begin_date":"0000-00-00 00:00:00","end_date":"2017-09-19 21:25:30","real_price":"0.03","class_id":"TEST001","is_classmember":0,"is_paid":0},{"class_name":"Mooc的视频表现-TEST002","class_apply_type":"1","begin_date":"0000-00-00 00:00:00","end_date":"2017-09-19 21:25:30","real_price":"0.02","class_id":"TEST002","is_classmember":0,"is_paid":0}]}
     * section : [{"section_uid":"F359B1E089824204A2949A6058451599","node_type":"1","node_caption":"第一章 MOOC的视频表现形式","is_watched":0,"video_duration":"0","child":[{"section_uid":"AC7915B340D5486F81A10E3BCB00E41C","node_type":"2","node_caption":"1.1 挑选适合老师的视频表达脚本","is_watched":0,"video_duration":"154"},{"section_uid":"944B8C473FB5400E8327E2928C9BF73C","node_type":"2","node_caption":"1.2 出境讲解的示例与注意事项","is_watched":0,"video_duration":"348"},{"section_uid":"71660FAC06184E419B0DE242623FEC6B","node_type":"2","node_caption":"1.3 录屏讲解的操作要点","is_watched":0,"video_duration":"109"},{"section_uid":"34D82BE172784350BB90F1A348F3F0D6","node_type":"2","node_caption":"1.4 实景演示应该如何表现","is_watched":0,"video_duration":"84"},{"section_uid":"363AABBCA0A14F51A0DF6B63F565752E","node_type":"2","node_caption":"1.5 访谈式教学的特点","is_watched":0,"video_duration":"154"},{"section_uid":"48CAF8A99A944CA883517B2F2B718CC7","node_type":"2","node_caption":"1.6 宣传片教学的特点","is_watched":0,"video_duration":"358"},{"section_uid":"66EFC816A1C445109098C2A34F9FF570","node_type":"2","node_caption":"1.7 动画式讲解的操作要点","is_watched":0,"video_duration":"188"},{"section_uid":"B6D7C2814293461D8F3BFE1666C2D1BB","node_type":"2","node_caption":"1.8 手绘式教学的操作要点","is_watched":0,"video_duration":"408"}]}]
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
         * impower_id : 946CF4CF4446427F924081F84693745A
         * impower_price : 0
         * course_name : MOOC的视频表现形式
         * course_album : http://img1.auto-mooc.com/course/album/8A/8ADFDF6C322340589DE5A6023B62F66B.jpg
         * course_intro_video : CCCFCD50496B4AEEA52044E8E0F2B45E
         * course_desc_m :      通过本门课程的学习，学员能够快速了解MOOC视频的多种表现形式，以及其适用场合，作为MOOC课程教师在各种表现形式下，应当如何准备课程。本课程能够为刚接触并想投身于MOOC教学的讲师，MOOC课程策划者、MOOC课程的摄影师与视频剪辑师提供参考和指导。
         * teacher_name : 孙胤胤
         * num_visit : 381
         * recommend_type : 1
         * is_follow : 0
         * classArr : [{"class_name":"Mooc的视频表现形式-2016","class_apply_type":"1","begin_date":"2016-08-31 00:00:00","end_date":"2017-12-31 00:00:00","real_price":"0.01","class_id":"B5C7AC905CB845C3A8B1151810D260F3","is_classmember":0,"is_paid":0},{"class_name":"Mooc的视频表现-TEST001","class_apply_type":"1","begin_date":"0000-00-00 00:00:00","end_date":"2017-09-19 21:25:30","real_price":"0.03","class_id":"TEST001","is_classmember":0,"is_paid":0},{"class_name":"Mooc的视频表现-TEST002","class_apply_type":"1","begin_date":"0000-00-00 00:00:00","end_date":"2017-09-19 21:25:30","real_price":"0.02","class_id":"TEST002","is_classmember":0,"is_paid":0}]
         */

        private InfoBean info;
        /**
         * section_uid : F359B1E089824204A2949A6058451599
         * node_type : 1
         * node_caption : 第一章 MOOC的视频表现形式
         * is_watched : 0
         * video_duration : 0
         * child : [{"section_uid":"AC7915B340D5486F81A10E3BCB00E41C","node_type":"2","node_caption":"1.1 挑选适合老师的视频表达脚本","is_watched":0,"video_duration":"154"},{"section_uid":"944B8C473FB5400E8327E2928C9BF73C","node_type":"2","node_caption":"1.2 出境讲解的示例与注意事项","is_watched":0,"video_duration":"348"},{"section_uid":"71660FAC06184E419B0DE242623FEC6B","node_type":"2","node_caption":"1.3 录屏讲解的操作要点","is_watched":0,"video_duration":"109"},{"section_uid":"34D82BE172784350BB90F1A348F3F0D6","node_type":"2","node_caption":"1.4 实景演示应该如何表现","is_watched":0,"video_duration":"84"},{"section_uid":"363AABBCA0A14F51A0DF6B63F565752E","node_type":"2","node_caption":"1.5 访谈式教学的特点","is_watched":0,"video_duration":"154"},{"section_uid":"48CAF8A99A944CA883517B2F2B718CC7","node_type":"2","node_caption":"1.6 宣传片教学的特点","is_watched":0,"video_duration":"358"},{"section_uid":"66EFC816A1C445109098C2A34F9FF570","node_type":"2","node_caption":"1.7 动画式讲解的操作要点","is_watched":0,"video_duration":"188"},{"section_uid":"B6D7C2814293461D8F3BFE1666C2D1BB","node_type":"2","node_caption":"1.8 手绘式教学的操作要点","is_watched":0,"video_duration":"408"}]
         */

        private List<SectionBean> section;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<SectionBean> getSection() {
            return section;
        }

        public void setSection(List<SectionBean> section) {
            this.section = section;
        }

        public static class InfoBean {
            private String impower_id;
            private String impower_price;
            private String course_name;
            private String course_album;
            private String course_intro_video;
            private String course_desc_m;
            private String teacher_name;
            private String num_visit;
            private int recommend_type;
            private int is_follow;
            /**
             * class_name : Mooc的视频表现形式-2016
             * class_apply_type : 1
             * begin_date : 2016-08-31 00:00:00
             * end_date : 2017-12-31 00:00:00
             * real_price : 0.01
             * class_id : B5C7AC905CB845C3A8B1151810D260F3
             * is_classmember : 0
             * is_paid : 0
             */

            private List<ClassArrBean> classArr;

            public String getImpower_id() {
                return impower_id;
            }

            public void setImpower_id(String impower_id) {
                this.impower_id = impower_id;
            }

            public String getImpower_price() {
                return impower_price;
            }

            public void setImpower_price(String impower_price) {
                this.impower_price = impower_price;
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

            public String getCourse_intro_video() {
                return course_intro_video;
            }

            public void setCourse_intro_video(String course_intro_video) {
                this.course_intro_video = course_intro_video;
            }

            public String getCourse_desc_m() {
                return course_desc_m;
            }

            public void setCourse_desc_m(String course_desc_m) {
                this.course_desc_m = course_desc_m;
            }

            public String getTeacher_name() {
                return teacher_name;
            }

            public void setTeacher_name(String teacher_name) {
                this.teacher_name = teacher_name;
            }

            public String getNum_visit() {
                return num_visit;
            }

            public void setNum_visit(String num_visit) {
                this.num_visit = num_visit;
            }

            public int getRecommend_type() {
                return recommend_type;
            }

            public void setRecommend_type(int recommend_type) {
                this.recommend_type = recommend_type;
            }

            public int getIs_follow() {
                return is_follow;
            }

            public void setIs_follow(int is_follow) {
                this.is_follow = is_follow;
            }

            public List<ClassArrBean> getClassArr() {
                return classArr;
            }

            public void setClassArr(List<ClassArrBean> classArr) {
                this.classArr = classArr;
            }

            public static class ClassArrBean {
                private String class_name;
                private String class_apply_type;
                private String begin_date;
                private String end_date;
                private String real_price;
                private String class_id;
                private int is_classmember;
                private int is_paid;

                public String getClass_name() {
                    return class_name;
                }

                public void setClass_name(String class_name) {
                    this.class_name = class_name;
                }

                public String getClass_apply_type() {
                    return class_apply_type;
                }

                public void setClass_apply_type(String class_apply_type) {
                    this.class_apply_type = class_apply_type;
                }

                public String getBegin_date() {
                    return begin_date;
                }

                public void setBegin_date(String begin_date) {
                    this.begin_date = begin_date;
                }

                public String getEnd_date() {
                    return end_date;
                }

                public void setEnd_date(String end_date) {
                    this.end_date = end_date;
                }

                public String getReal_price() {
                    return real_price;
                }

                public void setReal_price(String real_price) {
                    this.real_price = real_price;
                }

                public String getClass_id() {
                    return class_id;
                }

                public void setClass_id(String class_id) {
                    this.class_id = class_id;
                }

                public int getIs_classmember() {
                    return is_classmember;
                }

                public void setIs_classmember(int is_classmember) {
                    this.is_classmember = is_classmember;
                }

                public int getIs_paid() {
                    return is_paid;
                }

                public void setIs_paid(int is_paid) {
                    this.is_paid = is_paid;
                }
            }
        }

        public static class SectionBean {
            private String section_uid;
            private String node_type;
            private String node_caption;
            private int is_watched;
            private String video_duration;
            /**
             * section_uid : AC7915B340D5486F81A10E3BCB00E41C
             * node_type : 2
             * node_caption : 1.1 挑选适合老师的视频表达脚本
             * is_watched : 0
             * video_duration : 154
             */

            private List<ChildBean> child;

            public String getSection_uid() {
                return section_uid;
            }

            public void setSection_uid(String section_uid) {
                this.section_uid = section_uid;
            }

            public String getNode_type() {
                return node_type;
            }

            public void setNode_type(String node_type) {
                this.node_type = node_type;
            }

            public String getNode_caption() {
                return node_caption;
            }

            public void setNode_caption(String node_caption) {
                this.node_caption = node_caption;
            }

            public int getIs_watched() {
                return is_watched;
            }

            public void setIs_watched(int is_watched) {
                this.is_watched = is_watched;
            }

            public String getVideo_duration() {
                return video_duration;
            }

            public void setVideo_duration(String video_duration) {
                this.video_duration = video_duration;
            }

            public List<ChildBean> getChild() {
                return child;
            }

            public void setChild(List<ChildBean> child) {
                this.child = child;
            }

            public static class ChildBean {
                private String section_uid;
                private String node_type;
                private String node_caption;
                private int is_watched;
                private String video_duration;

                public String getSection_uid() {
                    return section_uid;
                }

                public void setSection_uid(String section_uid) {
                    this.section_uid = section_uid;
                }

                public String getNode_type() {
                    return node_type;
                }

                public void setNode_type(String node_type) {
                    this.node_type = node_type;
                }

                public String getNode_caption() {
                    return node_caption;
                }

                public void setNode_caption(String node_caption) {
                    this.node_caption = node_caption;
                }

                public int getIs_watched() {
                    return is_watched;
                }

                public void setIs_watched(int is_watched) {
                    this.is_watched = is_watched;
                }

                public String getVideo_duration() {
                    return video_duration;
                }

                public void setVideo_duration(String video_duration) {
                    this.video_duration = video_duration;
                }
            }
        }
    }
}
