package cn.automooc.com.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import cn.automooc.com.R;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.ClassArrBean;
import cn.automooc.com.bean.Jianjie;
import cn.automooc.com.bean.ResultJoin;
import cn.automooc.com.ui.CourseActivity;
import cn.automooc.com.ui.LoginAndRegisterActivity;
import cn.automooc.com.ui.PayActivity;
import cn.automooc.com.ui.VideoActivity;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.Md5Utils;
import cn.automooc.com.utils.SaveUser;
import cn.automooc.com.videointerface.OnChooseCourseListener;
import cn.automooc.com.widget.ChooseClassDialog;

import static android.view.View.inflate;


/**
* 创建时间 16/9/20
* auther Hades
* 描述  参加课程页面  可购买加入班级 加入后跳转到First1
**/
public class CourseIntroduceView {

    Context mContext;
    View mView;

    private TextView detailTitle;
    private TextView teacherDate;
    private TextView keshiText;
    private TextView renqiText;
    private TextView yuanjiaText;
    private TextView xianJiaText;
    private TextView gaishuText;

    private static int REFRESH_PRICE_TEXT = 104;//刷新UI
    private static int REFRESH_JOIN_CLASS = 105;
    private static int REFRESH_GOTO_CLASS = 106;
    private static int REFRESH_BUY_CLASS = 107;


    private Jianjie jianjie;
    private TextView joinLesson;
    private LinearLayout choosecourse_ly;//选择课程

    Handler mhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.arg1 == REFRESH_PRICE_TEXT)
            {
                price_tv.setText("￥ "+ mprice);//需要在UI线程 回调
            }else if (msg.arg1 == REFRESH_JOIN_CLASS){
                joinLesson.setText("加入班级");
            }else if (msg.arg1 == REFRESH_GOTO_CLASS){
                joinLesson.setText("进入班级");
            }else if (msg.arg1 == REFRESH_BUY_CLASS){
                joinLesson.setText("购买课程");
            }

            return false;
        }
    });

    private TextView price_tv;

    private int chooseClassIndex;
    private ClassArrBean resultClassBean;
    private String mprice;


    public CourseIntroduceView(Context mContext, Jianjie jianjie) {
        this.mContext = mContext;
        this.jianjie = jianjie;

    }

    public View getView() {
        mView = inflate(mContext, R.layout.video_view_first2, null);
        initView();
        initEvent();

        return mView;

    }

    private void initView() {

        detailTitle = (TextView) mView.findViewById(R.id.detail_title);
        teacherDate = (TextView) mView.findViewById(R.id.teacher_and_date);
        keshiText = (TextView) mView.findViewById(R.id.keshi_text);
        renqiText = (TextView) mView.findViewById(R.id.renqi_text);
        yuanjiaText = (TextView) mView.findViewById(R.id.yuanjia_text);
        xianJiaText = (TextView) mView.findViewById(R.id.xianjia_text);
        gaishuText = (TextView) mView.findViewById(R.id.gaishu_text);
        choosecourse_ly = (LinearLayout) mView.findViewById(R.id.choosecourse_ly);

        detailTitle.setText(jianjie.getCourse_name());
        teacherDate.setText(jianjie.getTeacher_name());
        keshiText.setText(jianjie.getNum_hour() + "课时");
        renqiText.setText("人气" + jianjie.getNum_visit());

        price_tv = (TextView) mView.findViewById(R.id.price_tv);
        yuanjiaText.setVisibility(View.INVISIBLE);
        if (jianjie.getClassArr().get(0).getReal_price().equals("0")) {
            xianJiaText.setText("免费");
        } else {
            xianJiaText.setText("￥" + jianjie.getClassArr().get(0).getReal_price());
        }
        gaishuText.setText(jianjie.getCourse_desc_m());
        joinLesson = (TextView) mView.findViewById(R.id.jion_lesson); //参加按钮

        if ((jianjie.getClassArr().get(0).getClass_id().equals("0"))) {
            joinLesson.setVisibility(View.INVISIBLE);
        }

    }


    /**
    * 创建时间 16/9/22
    * auther Hades
    * 描述  购买课程
    **/
    private void goPay(){
        Gson gson = new Gson();
        Intent i = new Intent(mContext, PayActivity.class);
        i.putExtra("class_index", chooseClassIndex);
        i.putExtra("teacher_name", jianjie.getTeacher_name());
        i.putExtra("class", gson.toJson(resultClassBean) );
        mContext.startActivity(i);
    }

    /**
    * 创建时间 16/9/22
    * auther Hades
    * 描述  加入班级
    **/
    private void goToClass(){
        Intent i = new Intent(mContext, VideoActivity.class);
        i.putExtra("class_id", resultClassBean.getClass_id());
        mContext.startActivity(i);
        ((CourseActivity) mContext).finish();

    }

    private void initEvent() {

        joinLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SaveUser save=new SaveUser(mContext);
                ConstantSet.user=(save.getData("userFile","user"));

                if(ConstantSet.user == null)
                {
                    Toast.makeText(mContext, "请登录...", Toast.LENGTH_SHORT).show();
                    mContext.startActivity(new Intent(mContext, LoginAndRegisterActivity.class));
//                    ((CourseActivity) mContext).finish();

                }
                else if (ConstantSet.user.getUid().equalsIgnoreCase("0")){
                    Toast.makeText(mContext, "请登录...", Toast.LENGTH_SHORT).show();
                    mContext.startActivity(new Intent(mContext, LoginAndRegisterActivity.class));

                }else {
                    //判断是否是某个class

                    if (resultClassBean == null) {

//                        showChooseClassDialog();

                        //                弹出弹窗
                        final ChooseClassDialog dialog = new ChooseClassDialog(mContext, jianjie.getCourse_album(),
                                jianjie.getCourse_name(),
                                jianjie.getTeacher_name(),
                                jianjie.getClassArr(),
                                (View) mView.getParent());
                        dialog.setOnChooseCourseListener(new OnChooseCourseListener() {
                            @Override
                            public void getCourseData(ClassArrBean classbean, int index) {
                                mprice = classbean.getReal_price();
                                chooseClassIndex = index;
                                resultClassBean = new ClassArrBean();
                                resultClassBean = classbean;

                                Message msg = Message.obtain();
                                msg.arg1 = REFRESH_PRICE_TEXT;
                                mhandler.sendMessage(msg);


                                reFreshUI();
                            }
                        });
                        dialog.show();

                    } else if (resultClassBean.getReal_price().equalsIgnoreCase("0")) {
                        if (resultClassBean.getIs_classmember().equalsIgnoreCase("0")) {
//                        加入班级
                            joinLesson.setText("加入班级");
                            joinClass(chooseClassIndex);

                        } else {
//                        进入班级
                            joinLesson.setText("进入班级");
                            goToClass();
                        }
                    } else {
                        if (resultClassBean.getIs_paid().equalsIgnoreCase("0")) {
//                                "购买课程"
                            joinLesson.setText("购买课程");
                            goPay();
                        } else {
//                                "进入班级"
                            joinLesson.setText("进入班级");
                            goToClass();
                        }

                    }
                }
            }
        });

        choosecourse_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                showChooseClassDialog();

                SaveUser save = new SaveUser(mContext);
                ConstantSet.user = (save.getData("userFile", "user"));

                if (ConstantSet.user == null) {
                    Toast.makeText(mContext, "请登录...", Toast.LENGTH_SHORT).show();
                    mContext.startActivity(new Intent(mContext, LoginAndRegisterActivity.class));
//                    ((CourseActivity) mContext).finish();

                } else if (ConstantSet.user.getUid().equalsIgnoreCase("0")) {
                    Toast.makeText(mContext, "请登录...", Toast.LENGTH_SHORT).show();
                    mContext.startActivity(new Intent(mContext, LoginAndRegisterActivity.class));

                } else {

//                弹出弹窗
                    final ChooseClassDialog dialog = new ChooseClassDialog(mContext, jianjie.getCourse_album(),
                            jianjie.getCourse_name(),
                            jianjie.getTeacher_name(),
                            jianjie.getClassArr(),
                            (View) mView.getParent());
                    dialog.setOnChooseCourseListener(new OnChooseCourseListener() {
                        @Override
                        public void getCourseData(ClassArrBean classbean, int index) {
                            mprice = classbean.getReal_price();
                            chooseClassIndex = index;
                            resultClassBean = new ClassArrBean();
                            resultClassBean = classbean;

                            Message msg = Message.obtain();
                            msg.arg1 = REFRESH_PRICE_TEXT;
                            mhandler.sendMessage(msg);


                            reFreshUI();
                        }
                    });
                    dialog.show();


                }
            }
        });

    }

    /**
    * 创建时间 16/9/22
    * auther Hades
    * 描述  回调后更新UI
    **/
    private void reFreshUI() {
        Message msg = Message.obtain();
        if (resultClassBean == null) {
            Toast.makeText(mContext, "请选择要加入的班级", Toast.LENGTH_SHORT).show();
            return;
        }
        if (resultClassBean.getReal_price().equalsIgnoreCase("0")) {
            if (resultClassBean.getIs_classmember().equalsIgnoreCase("0")) {
                msg.arg1 = REFRESH_JOIN_CLASS;
                mhandler.sendMessage(msg);

            } else {
                joinLesson.setText("进入班级");
                msg.arg1 = REFRESH_GOTO_CLASS;
                mhandler.sendMessage(msg);
            }
        } else {
            if (resultClassBean.getIs_paid().equalsIgnoreCase("0")) {
                joinLesson.setText("购买课程");
                msg.arg1 = REFRESH_BUY_CLASS;
                mhandler.sendMessage(msg);

            } else {
                joinLesson.setText("进入班级");
                msg.arg1 = REFRESH_GOTO_CLASS;
                mhandler.sendMessage(msg);
            }
        }
    }


    public void joinClass(final int classChoosed) {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "course/joinclass?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.d("TAG-JoinClass", response);
                Gson gson = new Gson();
                ResultJoin result = gson.fromJson(response, new TypeToken<ResultJoin>() {
                }.getType());

                if (result.getStatus().equals("1")) {
                    Toast.makeText(mContext,"加入成功",Toast.LENGTH_SHORT).show();
                     goToClass();
                } else {
                    Toast.makeText(mContext, "此课程无法加入", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败-joinclass", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("user_id", ConstantSet.user.getUid());//后期改成course_id
                map.put("class_id", jianjie.getClassArr().get(classChoosed).getClass_id());
                map.put("okey", Md5Utils.md5("mooccoursejoinclass" + ConstantSet.user.getUid() + jianjie.getClassArr().get(classChoosed).getClass_id()));//设置班级
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
}
