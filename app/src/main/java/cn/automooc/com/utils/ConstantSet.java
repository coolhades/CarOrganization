package cn.automooc.com.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.automooc.com.bean.MyClass;
import cn.automooc.com.bean.User;

/**
 * Created by jiuzheyange on 2016/8/12.
 */

/*
"App.Switch.Course.MaskInfo": "1",
        "App.Switch.Teacher.Show": "1",
        "App.Switch.Course.Evaluate.Show": "1",
        "App.Switch.PGC.Show": "1",
        "App.Switch.MyOrder.Show": "1",
        "App.Switch.Third-Party.Login": "1"*/
public class ConstantSet {
    
//    public static String homeAddress="http://fjbcapi.auto-mooc.com/";//http://fjbcapi.auto-mooc.com/
    public static String newhomeAddress = "http://api.auto-mooc.com/main/getdomain";
    public static String newhomeAddress2 = "http://api.auto-mooc.com/";


    public static String homeAddress;
    public static String class_getvideo = "class/getvideo";
    public static String MD5Header = "mooc";


    // CC视频帐号信息 账户信息  加密账号
    public static String CC_Account_id = "EB85B37C4546E6A4";
    public static String CC_Account_Key = "SsJifp5ht0KJUzgiEGACUrpVNYLdaAkR";


    // CC视频帐号信息 账户信息 非加密账号
    public static String CC_Account_NO_id = "1C1C1C9EEB01D75E";
    public static String CC_Account_NO_Key = "RRjzwvSvJbLRB3z81zrwAjPt9wLv29N8";

    //专区 机构的 position
    public static String zone_index;

    /**
     * 微信支付渠道
     */
    public static final String CHANNEL_WECHAT = "wx";
    /**
     * 支付支付渠道
     */
    public static final String CHANNEL_ALIPAY = "alipay";


    public static String course_id;
    public static String tag;
    public static String keyWord; 
    public static String cancel="";
    public static String userId="1";//默认为1
    public static String teacher_id;
    public static String teacher_name="未知";
    public static String teacher_url;

    public static boolean sign=true;
    public static User user;
    
    public static String section_uid;
    
   public static  Map<String,String> confiMap;
    
    public static String videoTitle;
    
    public static List<String> exam_url = new ArrayList<>();
    public static List<String> exam_name= new ArrayList<>();
    public static List<String> exam_time= new ArrayList<>();
    
    public static List<String> can_close= new ArrayList<>();
    public static String impower_id;
    public static String class_id;
    public static String jifen;


    
    public static String sharedImageUrl="http://img1.auto-mooc.com/general/img/29/14/B3/29B114A0B3E346EB93147E40CE3C0345.png";

    public static  List<MyClass> myClassList;

    public static String obj_id;
    public static String Target_id;
    public static String uid;
    public static String home_impower_id;
    public static String home_course_id;

    public static String class_data_url;


    
}
