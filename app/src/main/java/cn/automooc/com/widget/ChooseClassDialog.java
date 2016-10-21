package cn.automooc.com.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.automooc.com.R;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.ClassArrBean;
import cn.automooc.com.bean.ResultJoin;
import cn.automooc.com.ui.CourseActivity;
import cn.automooc.com.ui.PayActivity;
import cn.automooc.com.ui.VideoActivity;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.LoadImgUtils;
import cn.automooc.com.utils.Md5Utils;
import cn.automooc.com.videointerface.OnChooseCourseListener;

public class ChooseClassDialog extends BottomBaseDialog<ChooseClassDialog> {

    OnChooseCourseListener onChooseCourseListener;
    String url;
    String courseTitle;
    TextView go_pay;

    ImageView image_iv;
    TextView price_text;
    TextView course_title;
    FlowLayout class_fl;
    List<ClassArrBean> mClassArrBeanList = new ArrayList<>();

    String teachername;

    public ChooseClassDialog(Context context, String image, String title, String teacher,List<ClassArrBean> classArrBeanList, View animateView) {
        super(context, animateView);
        this.url = image;
        this.courseTitle = title;
        this.mClassArrBeanList = classArrBeanList;
        this.teachername = teacher;
    }

    public ChooseClassDialog(Context context) {
        super(context);
    }

    public void setOnChooseCourseListener(OnChooseCourseListener listener){
        this.onChooseCourseListener = listener;
    }

    @Override
    public View onCreateView() {
        View inflate = View.inflate(mContext, R.layout.dialog_ios_taobao, null);
        image_iv = (ImageView) inflate.findViewById(R.id.image_iv);
        price_text = (TextView) inflate.findViewById(R.id.price_text);
        course_title = (TextView) inflate.findViewById(R.id.course_title);
        class_fl = (FlowLayout) inflate.findViewById(R.id.class_fl);
        go_pay = (TextView) inflate.findViewById(R.id.go_pay);

        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        //初始化UI
        LoadImgUtils.setImage(mContext, url, image_iv);
        setUpFlowView();
    }

    List<TextView>  flowTextList = new ArrayList<>();

    private void setUpFlowView() {

        flowTextList.clear();

        int size = mClassArrBeanList.size();
        for (int i = 0; i<size; i++ ){
            //创建flow
            View v = getLayoutInflater().inflate(R.layout.flowitem_ly, null);
            v.setLayoutParams(new ViewGroup.MarginLayoutParams(
                    ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT));
            final TextView t = (TextView) v.findViewById(R.id.tv);
            t.setText(mClassArrBeanList.get(i).getClass_name());
            final int finalI = i;
            flowTextList.add(t);

            if (i == 0){  //默认选中第一个
                //重置textview 背景
                resetFlowText();
                //回传结果
                t.setBackground(getContext().getResources().getDrawable(R.drawable.course_text_choosed_bg));
                t.setTextColor(Color.parseColor("#ffffff"));
                onChooseCourseListener.getCourseData(mClassArrBeanList.get(finalI), finalI);
                price_text.setText("￥： "+mClassArrBeanList.get(finalI).getReal_price());
                course_title.setText(mClassArrBeanList.get(finalI).getClass_name());

                if (mClassArrBeanList.get(finalI).getReal_price().equalsIgnoreCase("0")){
                    if (mClassArrBeanList.get(finalI).getIs_classmember().equalsIgnoreCase("0")) {
//                        加入班级
                        go_pay.setText("加入班级");
                        go_pay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                joinClass(finalI);
                            }
                        });


                    }else{
//                        进入班级
                        go_pay.setText("进入班级");
                        go_pay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goToClass(finalI);
                            }
                        });

                    }
                }else {
                    if (mClassArrBeanList.get(finalI).getIs_paid().equalsIgnoreCase("0")) {
//                                "购买课程"
                        go_pay.setText("购买课程");
                        go_pay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goPay(finalI);
                            }
                        });


                    }else{
//                                "进入班级"
                        go_pay.setText("进入班级");
                        go_pay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goToClass(finalI);
                            }
                        });

                    }

                }


            }

            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //重置textview 背景
                    resetFlowText();
                    //回传结果
                    t.setBackground(getContext().getResources().getDrawable(R.drawable.course_text_choosed_bg));
                    t.setTextColor(Color.parseColor("#ffffff"));
                    onChooseCourseListener.getCourseData(mClassArrBeanList.get(finalI), finalI);
                    price_text.setText("￥： "+mClassArrBeanList.get(finalI).getReal_price());
                    course_title.setText(mClassArrBeanList.get(finalI).getClass_name());

                    if (mClassArrBeanList.get(finalI).getReal_price().equalsIgnoreCase("0")){
                        if (mClassArrBeanList.get(finalI).getIs_classmember().equalsIgnoreCase("0")) {
//                        加入班级
                            go_pay.setText("加入班级");
                            go_pay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    joinClass(finalI);
                                }
                            });


                        }else{
//                        进入班级
                            go_pay.setText("进入班级");
                            go_pay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    goToClass(finalI);
                                }
                            });

                        }
                    }else {
                        if (mClassArrBeanList.get(finalI).getIs_paid().equalsIgnoreCase("0")) {
//                                "购买课程"
                            go_pay.setText("购买课程");
                            go_pay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    goPay(finalI);
                                }
                            });


                        }else{
//                                "进入班级"
                            go_pay.setText("进入班级");
                            go_pay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    goToClass(finalI);
                                }
                            });

                        }

                    }
                }
            });

            class_fl.addView(v);

        }


    }

    /**
     * 创建时间 16/9/22
     * auther Hades
     * 描述  购买课程
     **/

    private void goPay(int chooseClassIndex){
        Gson gson = new Gson();
        Intent i = new Intent(mContext, PayActivity.class);
        i.putExtra("class_index", chooseClassIndex);
        i.putExtra("teacher_name", teachername);
        i.putExtra("class", gson.toJson(mClassArrBeanList.get(chooseClassIndex)) );
        mContext.startActivity(i);
        ((CourseActivity) mContext).finish();
    }

    /**
     * 创建时间 16/9/22
     * auther Hades
     * 描述  加入班级
     **/
    private void goToClass(int chooseClassIndex){
        Intent i = new Intent(mContext, VideoActivity.class);
        i.putExtra("class_id", mClassArrBeanList.get(chooseClassIndex).getClass_id());
        mContext.startActivity(i);
        ((CourseActivity) mContext).finish();

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
                    goToClass(classChoosed);
                } else {
                    Toast.makeText(mContext, "此课程无法加入", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("user_id", ConstantSet.user.getUid());//后期改成course_id
                map.put("class_id", mClassArrBeanList.get(classChoosed).getClass_id());
                map.put("okey", Md5Utils.md5("mooccoursejoinclass" + ConstantSet.user.getUid() + mClassArrBeanList.get(classChoosed).getClass_id()));//设置班级
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }


    //重置FlowText的背景色
    private void resetFlowText() {
        if (flowTextList.isEmpty()){
            return;
        }
        int size = flowTextList.size();
        for (int i = 0; i<size; i++){
            flowTextList.get(i).setBackground(getContext().getResources().getDrawable(R.drawable.course_text_bg));
            flowTextList.get(i).setTextColor(Color.parseColor("#000000"));
        }

    }

    private void goPay(ClassArrBean bean,int chooseClassIndex) {

        Gson gson = new Gson();
        Intent i = new Intent(mContext, PayActivity.class);
        i.putExtra("class_index", chooseClassIndex);
        i.putExtra("teacher_name", teachername);
        i.putExtra("class", gson.toJson(bean) );
        mContext.startActivity(i);
        ChooseClassDialog.this.dismiss();
    }




    private BaseAnimatorSet mWindowInAs;
    private BaseAnimatorSet mWindowOutAs;

    @Override
    protected BaseAnimatorSet getWindowInAs() {
        if (mWindowInAs == null) {
            mWindowInAs = new WindowsInAs();
        }
        return mWindowInAs;
    }

    @Override
    protected BaseAnimatorSet getWindowOutAs() {
        if (mWindowOutAs == null) {
            mWindowOutAs = new WindowsOutAs();
        }
        return mWindowOutAs;
    }

    class WindowsInAs extends BaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", 10, 0f).setDuration(150);
            rotationX.setStartDelay(200);
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.8f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.8f).setDuration(350),
//                    ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.5f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "rotationX", 0f, 10).setDuration(200),
                    rotationX,
                    ObjectAnimator.ofFloat(view, "translationY", 0, -0.1f * mDisplayMetrics.heightPixels).setDuration(350)
            );
        }
    }

    class WindowsOutAs extends BaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", 10, 0f).setDuration(150);
            rotationX.setStartDelay(200);
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1.0f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "scaleY", 0.8f, 1.0f).setDuration(350),
//                    ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.5f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "rotationX", 0f, 10).setDuration(200),
                    rotationX,
                    ObjectAnimator.ofFloat(view, "translationY", -0.1f * mDisplayMetrics.heightPixels, 0).setDuration(350)
            );
        }
    }
}
