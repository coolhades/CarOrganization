package cn.automooc.com.api;

/**
 * Created by Hades on 16/9/29.
 */

public class ApiConstants {

    public static String HomeAdress;

    //Main
    public static String MainGetHome = "main/gethome";
    public static String MainGetTags = "main/gettags";
    public static String MainGetGuid = "";


    //User
    public static String UserLogin = "user/login";
    public static String UserLoginThird= "user/loginthird";
    public static String UserRegister = "user/register";
    public static String UserGetCount = "user/getcount";
    public static String UserGetUserInfo = "user/getuserinfo";
    public static String ActionsDoactions = "actions/doactions";//关注、取消关注||点赞、踩
    public static String UserIsfollow = "user/isfollow";
    public static String UserGetUserClassList = "user/getuserclasslist";
    public static String UserUploadAvatar = "user/uploadavatar";
    public static String UserEditUserInfo = "user/edituserinfo";
    public static String UserGetMyQuestions = "user/getmyquestions";
    public static String UserGetMyExam = "user/getmyexam";
    public static String UserGetLearnProgress = "user/getlearnprogress";
    public static String UserGetUserScore = "user/getuserscore";
    public static String UserGetMyCourse = "user/getmycourse";
    public static String UserGetMyFollowCourse = "user/getmyfollowcourse";
    public static String UserResetPass = "user/resetpass";


    //Course
    public static String CourseList = "course/getdetail";
    public static String CourseJoinClass = "course/joinclass";
    public static String CourseGetTeacherItem = "course/getteacheritem";
    public static String CourseGetAllTeacher = "course/getallteacher";
    public static String CourseSearch = "course/search";

    //Class
    public static String ClassGetVideo = "class/getvideo";
    public static String ClassSaveWatchVideo = "class/savewatchvideo";
    public static String ClassGetDetail = "class/getdetail";

    //Order
    public static String OrderOrderInfo = "order/orderinfo";
    public static String OrderGenerateOrder = "order/generateorder";
    public static String Ordergetpaychannel = "order/getpaychannel";

    //MyOrder
    public static String UserGetUserOrderList = "user/getuserorderlist";

    //Teacher
    public static String TeacherIndex = "teacher/index";
    public static String TeacherDetail = "teacher/detail";

    //Zone
    public static String ZoneIndex = "zone/index";
    public static String ZoneDetail = "zone/detail";

    //Comment
    public static String CommentAddComment = "comment/addcomment";
    public static String CommentGetCommentList = "comment/getcommentlist";
    public static String CommentReplyComment = "comment/replycomment";
    public static String CommentGetAnswerList = "comment/getanswerlist";









}
