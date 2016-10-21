package cn.automooc.com.bean;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/14.
 */
public class Teacher {
    
    String teacher_id;
    String teacher_name;
    String teacher_avatar;
    String introduction;
    String course_num;

    public String getIntroduction() {
        return introduction;
    }

    public String getCourse_num() {
        return course_num;
    }

        List<TeacherTag> teacher_tag;

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

    public List<TeacherTag> getTeacher_tag() {
        return teacher_tag;
    }
//
//    public void setTeacher_tag(List<TeacherTag> teacher_tag) {
//        this.teacher_tag = teacher_tag;
//    }

    public String getTeacher_avatar() {
        return teacher_avatar;
    }

    public void setTeacher_avatar(String teacher_avatar) {
        this.teacher_avatar = teacher_avatar;
    }
}
