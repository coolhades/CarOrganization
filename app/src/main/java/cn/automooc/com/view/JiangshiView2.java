package cn.automooc.com.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
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
import cn.automooc.com.bean.TeacherCourseBean;
import cn.automooc.com.utils.ConstantSet;


/**
 * Created by jiuzheyange on 2016/8/18.
 * 讲师页面 viewpager 讲师简介
 */
public class JiangshiView2 {
    
    Context mContext;
    View mView;

    TextView teacher_name;
    TextView introduce_tv;

    public JiangshiView2(Context mContext) {
        this.mContext = mContext;
    }
    
    
    public View getView()
    {
        mView=View.inflate(mContext, R.layout.jiangshi_view2,null);
        initView();
        initData();
        initEvent();
        return mView;
    }

    private void initView() {
        teacher_name = (TextView) mView.findViewById(R.id.teacher_name);
        introduce_tv = (TextView) mView.findViewById(R.id.introduce_tv);

    }


    private void initData() {
        getData();
    }

    private void initEvent() {
    }

    public void getData()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"teacher/detail?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
//                Log.i("TAG-Teacher", "onResponse: "+response);
                Gson gson = new Gson();
                TeacherCourseBean result = gson.fromJson(response, new TypeToken<TeacherCourseBean>() {
                }.getType());
                if (result.getStatus() == 1) {
                    Log.i("TAG-Teacher", "onResponse: "+gson.toJson(result.getData().getItem().getList() ));
                    teacher_name.setText(result.getData().getTeacherInfo().getUser_nick_name());
                    introduce_tv.setText(result.getData().getTeacherInfo().getUser_desc_l());

                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                map.put("teacher_id",ConstantSet.teacher_id);
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
}
