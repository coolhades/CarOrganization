package cn.automooc.com.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.automooc.com.BaseFragment;
import cn.automooc.com.R;
import cn.automooc.com.adapter.MyClassListViewAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.MyClass;
import cn.automooc.com.bean.ResultPersonal;
import cn.automooc.com.bean.Sign;
import cn.automooc.com.bean.UserInfoBean;
import cn.automooc.com.ui.BuyHistoryActivity;
import cn.automooc.com.ui.ExamineActivity;
import cn.automooc.com.ui.GongGaoActivity;
import cn.automooc.com.ui.GuideListActivity;
import cn.automooc.com.ui.MyClassActivity;
import cn.automooc.com.ui.MyCollectActivity;
import cn.automooc.com.ui.MyQuestionActivity;
import cn.automooc.com.ui.PersonalSetActivity;
import cn.automooc.com.ui.SettingActivity;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.LoadImgUtils;
import cn.automooc.com.utils.Md5Utils;
import cn.automooc.com.utils.ShowMaskDialogUtils;
import cn.automooc.com.widget.CircularImage;
import cn.automooc.com.widget.WebViewDialog;

/**
 * Created by jiuzheyange on 2016/8/8.
 */
public class MyselfFragment extends BaseFragment {

    View view;
    LinearLayout settingBt;
    LinearLayout myClassBt;
    LinearLayout myCollect;
    LinearLayout myExamineBt;
    LinearLayout buyHistoryBt;
    LinearLayout myQuestionBt;
    // LinearLayout downLoadBt;
    LinearLayout qianDaoBt;
    LinearLayout jifenBt;
    LinearLayout jifenPaimingBt;
    LinearLayout jifenDuihuanBt;
    LinearLayout dangAnBt;
    // ImageView informationBt;
    LinearLayout guide_bt; //引导
    LinearLayout announce_bt; //公告


    TextView kechengText;
    TextView keshiText;
    TextView jinduText;
    TextView myClassText;
    TextView myCollectText;
    TextView myExamText;
    TextView myHistoryBuyText;
    TextView myQuestionText;
    // TextView myDownloadText;

    List<MyClass> lists;


    ListView listView;

    CircularImage headImg;

    TextView nickName;

    ImageView sign_in;
    ImageView gonggao;

    MyClassListViewAdapter adapter;
    //  MaterialRefreshLayout materialRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initView();
        initData();
        initEvent();
        
        System.out.println("touxiang  "+ConstantSet.user.getAvatar());
        return view;
    }

    @Override
    protected void initView() {

        view = View.inflate(getActivity(), R.layout.myself_fragment, null);
        settingBt = (LinearLayout) view.findViewById(R.id.setting_bt);

        myClassBt = (LinearLayout) view.findViewById(R.id.my_class_bt);
        myCollect = (LinearLayout) view.findViewById(R.id.collect_class_bt);
        myExamineBt = (LinearLayout) view.findViewById(R.id.my_examine_bt);
        buyHistoryBt = (LinearLayout) view.findViewById(R.id.buy_history_bt);
        myQuestionBt = (LinearLayout) view.findViewById(R.id.my_question_bt);
        // downLoadBt = (LinearLayout) view.findViewById(R.id.download_bt);
        qianDaoBt = (LinearLayout) view.findViewById(R.id.qiandao_bt);
        guide_bt = (LinearLayout) view.findViewById(R.id.guide_bt);

        //  informationBt = (ImageView) view.findViewById(R.id.information_bt);


        kechengText = (TextView) view.findViewById(R.id.kecheng_text);
        keshiText = (TextView) view.findViewById(R.id.keshi_text);
        jinduText = (TextView) view.findViewById(R.id.jindu_text);
        myClassText = (TextView) view.findViewById(R.id.my_class_text);
        myCollectText = (TextView) view.findViewById(R.id.my_collect_text);
        myExamText = (TextView) view.findViewById(R.id.my_examine_text);
        myHistoryBuyText = (TextView) view.findViewById(R.id.buy_history_text);
        myQuestionText = (TextView) view.findViewById(R.id.my_question_text);
        // myDownloadText = (TextView) view.findViewById(R.id.download_text);


//        listView = (ListView) view.findViewById(R.id.listview);

        headImg = (CircularImage) view.findViewById(R.id.head_img);

        nickName = (TextView) view.findViewById(R.id.nick_name);

        //jifenBt= (LinearLayout) view.findViewById(R.id.jifen_bt);
        jifenPaimingBt = (LinearLayout) view.findViewById(R.id.jifenpaiming_bt);
        jifenDuihuanBt = (LinearLayout) view.findViewById(R.id.jifenduihuan_bt);
        dangAnBt = (LinearLayout) view.findViewById(R.id.dangan_bt);
        // materialRefreshLayout = (MaterialRefreshLayout)view.findViewById(R.id.refresh);

//        if (ConstantSet.user != null) {
//            LoadImgUtils.setImage(getActivity(), ConstantSet.user.getAvatar(), headImg);
//            nickName.setText(ConstantSet.user.getNickname());
//
//        }

        if (ConstantSet.confiMap != null) {
            if (ConstantSet.confiMap.get("App.Switch.MyOrder.Show").equals("0")) {

                buyHistoryBt.setVisibility(View.GONE);
            }
        }
        sign_in = (ImageView) view.findViewById(R.id.sign_in);
        gonggao = (ImageView) view.findViewById(R.id.gonggao);
        announce_bt = (LinearLayout) view.findViewById(R.id.announce_bt);

    }

    @Override
    protected void initData() {
        lists = new ArrayList<MyClass>();
        getData();
        isSignIn();
        fetchUserLoginInfo();
//        getMyClass();
    }

    @Override
    protected void initEvent() {

      /*  materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {


                initView();
                initData();
                initEvent();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //load more refreshing...
            }
        });*/

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                ConstantSet.videoTitle = lists.get(i).getCourse_name();
//                ConstantSet.course_id = lists.get(i).getImpower_id();
//
//                startActivity(new Intent(getActivity(), CourseActivity.class));
//            }
//        });


        settingBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });


        myClassBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MyClassActivity.class));
            }
        });


        myCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), MyCollectActivity.class));
            }
        });

        myExamineBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), ExamineActivity.class);
                i.putExtra("Learn", "MyExam");
                startActivity(i);


//                SaveString save=new SaveString(getActivity());
//                String videoFirst=save.getPhoneNumber("myExamFile","myExam");
//
//                if(videoFirst==null||videoFirst.equals("")) {
//                    MaskDialog dialog1 = new MaskDialog(getActivity(),"myExamFile","myExam","Guide.Grade.Share");
//
//                    Window window1 = dialog1.getWindow();
//                    window1.setGravity(Gravity.CENTER);
//                    dialog1.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
//                    dialog1.show();
//                }


//                WebViewDialog dialog =  new WebViewDialog(getActivity(),"https://www.jiguang.cn/products/",
//                        "我的考试", 0, null,null);
//
//                Window window = dialog.getWindow();
//                window.setGravity(Gravity.BOTTOM);
//                dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
//                dialog.show();
//
//
//
//                SaveString save=new SaveString(getActivity());
//                String videoFirst=save.getPhoneNumber("myExamFile","myExam");
//
//                if(videoFirst==null||videoFirst.equals("")) {
//                    MaskDialog dialog1 = new MaskDialog(getActivity(),"myExamFile","myExam","Guide.Grade.Share");
//
//                    Window window1 = dialog1.getWindow();
//                    window1.setGravity(Gravity.CENTER);
//                    dialog1.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
//                    dialog1.show();
//                }
            }
        });


        buyHistoryBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BuyHistoryActivity.class));
            }
        });

        myQuestionBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MyQuestionActivity.class));
            }
        });


      /*  downLoadBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DownloadManagerActivity.class));
            }
        });*/

    /*    informationBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), InformationActivity.class));
            }
        });*/


        //签到历史无分享
        qianDaoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConstantSet.user != null) {
                    WebViewDialog dialog = new WebViewDialog(getActivity(), null,ConstantSet.homeAddress + "template/dinglist?user_id=" + ConstantSet.user.getUid(),
                            "签到历史", 0, null, null,"");

                    
                    System.out.print("aaaaa   "+ConstantSet.homeAddress + "template/dinglist?user_id=" + ConstantSet.user.getUid());
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.BOTTOM);
                    dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
                    dialog.show();
                }

            }
        });


        headImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               startActivity(new Intent(getActivity(), PersonalSetActivity.class));

            }
        });

//        jifenBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                
//                
//                
//            }
//        });

        //不传actiontype 以""做判断
        jifenPaimingBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConstantSet.user != null) {
                    WebViewDialog dialog = new WebViewDialog(getActivity(), null,ConstantSet.homeAddress + "template/integral?action=share&user_id=" + ConstantSet.user.getUid(),
                            "我的积分", 0, null, null,"");

                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.BOTTOM);
                    dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
                    dialog.show();

                }

                ShowMaskDialogUtils.showMaskDialog(getActivity(), "paiMingFile", "paiMing", "Guide. Integrate.Share");

//                SaveString save = new SaveString(getActivity());
//                String videoFirst = save.getPhoneNumber("paiMingFile", "paiMing");
//
//                if (videoFirst == null || videoFirst.equals("")) {
//                    MaskDialog dialog1 = new MaskDialog(getActivity(), "paiMingFile", "paiMing", "Guide. Integrate.Share");
//
//                    Window window1 = dialog1.getWindow();
//                    window1.setGravity(Gravity.CENTER);
//                    dialog1.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
//                    dialog1.show();
//                }

            }
        });

        //积分兑换 app传不了
        jifenDuihuanBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ConstantSet.user != null) {
                    WebViewDialog dialog = new WebViewDialog(getActivity(), null,ConstantSet.homeAddress + "template/integralexchange?user_id=" + ConstantSet.user.getUid() + "&okey=" + Md5Utils.md5("moocintegralexchange" + ConstantSet.user.getUid()),
                            "积分兑换", 0, null, null,"");

                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.BOTTOM);
                    dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
                    dialog.show();


                }

                ShowMaskDialogUtils.showMaskDialog(getActivity(), "duiHuanFile", "duiHuan", "Guide. Integrate.Share");

//                SaveString save = new SaveString(getActivity());
//                String videoFirst = save.getPhoneNumber("duiHuanFile", "duiHuan");
//
//                if (videoFirst == null || videoFirst.equals("")) {
//                    MaskDialog dialog1 = new MaskDialog(getActivity(), "duiHuanFile", "duiHuan", "Guide. Integrate.Share");
//
//                    Window window1 = dialog1.getWindow();
//                    window1.setGravity(Gravity.CENTER);
//                    dialog1.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
//                    dialog1.show();
//                }

            }
        });


        //学习、积分页面 分享不传action_type
        // share.study	分享了学习 action_target不用
//        share.integral	分享了积分详情 action_target不用
        dangAnBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConstantSet.user != null) {
                    WebViewDialog dialog = new WebViewDialog(getActivity(), null,ConstantSet.homeAddress + "template/study?action=share&user_id=" + ConstantSet.user.getUid(),
                            "学习档案", 0, null, null, "");

                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.BOTTOM);
                    dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
                    dialog.show();
                }

                ShowMaskDialogUtils.showMaskDialog(getActivity(), "dangAnFile", "dangAn", "Guide.Studey.Share");

//                SaveString save = new SaveString(getActivity());
//                String videoFirst = save.getPhoneNumber("dangAnFile", "dangAn");
//
//                if (videoFirst == null || videoFirst.equals("")) {
//                    MaskDialog dialog1 = new MaskDialog(getActivity(), "dangAnFile", "dangAn", "Guide.Studey.Share");
//
//                    Window window1 = dialog1.getWindow();
//                    window1.setGravity(Gravity.CENTER);
//                    dialog1.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
//                    dialog1.show();
//                }
            }
        });

        //签到没有分享
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //未签到过
                if (!ConstantSet.sign){
                    SignIn();
                }else {
                    Toast.makeText(getActivity(), "已签到过~", Toast.LENGTH_SHORT).show();

                    if (ConstantSet.user != null) {
                        WebViewDialog dialog = new WebViewDialog(getActivity(), null, ConstantSet.homeAddress + "template/dinglist?user_id=" + ConstantSet.user.getUid(),
                                "签到历史", 0, null, null,"");


                        System.out.print("aaaaa   "+ConstantSet.homeAddress + "template/dinglist?user_id=" + ConstantSet.user.getUid());
                        Window window = dialog.getWindow();
                        window.setGravity(Gravity.BOTTOM);
                        dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
                        dialog.show();
                    }
                }
            }
        });

        gonggao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转公告
                startActivity(new Intent(getActivity(), GongGaoActivity.class));
            }
        });


        guide_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GuideListActivity.class));
            }
        });

        announce_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GongGaoActivity.class));
            }
        });

    }


    public void getData() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "user/getcount?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                // showShortToast("me  " + response);
                try {

                    Gson gson = new Gson();

                    ResultPersonal result = gson.fromJson(response, new TypeToken<ResultPersonal>() {
                    }.getType());
                    Log.d("TAGMySelf", response);

                    kechengText.setText(result.getData().getNum_course());

//                    keshiText.setText(result.getData().getUser_integral());      //改版成 积分--> 考试
                    keshiText.setText(result.getData().getNum_exam());
                    jinduText.setText(result.getData().getNum_progress()+"%");
                    myClassText.setText(result.getData().getNum_course());
                    myCollectText.setText(result.getData().getNum_like_course());
                    myExamText.setText(result.getData().getNum_exam());
                    myHistoryBuyText.setText(result.getData().getNum_buy());
                    myQuestionText.setText(result.getData().getNum_question());
                    //   myDownloadText.setText(result.getData().getNum_download());


                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                if (ConstantSet.user != null) {
                    map.put("user_id", ConstantSet.user.getUid());
                    Log.i("TAG-userid", "userid="+ConstantSet.user.getUid());
                }
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }

    void fetchUserLoginInfo(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "user/getuserinfo", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.d("TAG-UserInfo", s);
                try{
                    Gson gson = new Gson();
                    UserInfoBean userInfoBean = gson.fromJson(s, UserInfoBean.class);
                    if (userInfoBean.getStatus() == 1) {
                        LoadImgUtils.setImage(getActivity(), userInfoBean.getData().getAvatar(), headImg);
                        nickName.setText(userInfoBean.getData().getNickname());

                    }
                }catch (Exception e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                map.put("user_id",ConstantSet.user.getUid());
                return map;
            }
        };
        MyApplication.getRq().add(stringRequest);
    }

    //签到
    public void SignIn()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"ding/ding?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                System.out.print("response  "+response+"    "+(new Date()).getTime()+""+response.length());
                Log.i("TAGTAG", response);
                try {

                    Gson gson = new Gson();
                    Sign resultUser = gson.fromJson(response, new TypeToken<Sign>() {
                    }.getType());
                    //Toast.makeText(StartupActivity.this,response+"222",Toast.LENGTH_SHORT).show();
                    if(resultUser.getStatus()==1) {
                        Toast.makeText(getActivity(),"签到成功",Toast.LENGTH_SHORT).show();
                        sign_in.setImageResource(R.mipmap.attand);
                        ConstantSet.sign = true;
                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                map.put("obj_type","course");
                map.put("user_uid",ConstantSet.user.getUid());
                map.put("okey", Md5Utils.md5("moocuserdingcourse"));


                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }


    //判断是否签到过
    public void isSignIn()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"ding/judgeding?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub



                // Toast.makeText(StartupActivity.this,response, Toast.LENGTH_SHORT).show();
                System.out.print("response 12312 "+response+"    "+response.length());
                try {

                    //Toast.makeText(StartupActivity.this,response+"222",Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    Sign resultUser = gson.fromJson(response, new TypeToken<Sign>() {
                    }.getType());
                    if (resultUser.getStatus() == 1) {

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String str = formatter.format(new Date());
                        if (resultUser.getData() != null) {
                            ConstantSet.jifen = resultUser.getData().getIntegral_value();
                        }

                        if (resultUser.getMessage().equals("今天未签到")) {
                            ConstantSet.sign = false;
                            sign_in.setImageResource(R.mipmap.attandnot);
                        } else {
                            sign_in.setImageResource(R.mipmap.attand);
                        }

                    }


                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                if(ConstantSet.user!=null) {
                    map.put("user_uid", ConstantSet.user.getUid());
                }

                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }


    @Override
    public void onResume() {
        super.onResume();
        
    }
}
