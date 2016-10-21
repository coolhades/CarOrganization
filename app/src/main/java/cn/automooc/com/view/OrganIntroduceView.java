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
import cn.automooc.com.bean.PrefectureBean;
import cn.automooc.com.utils.ConstantSet;


/**
 * Created by jiuzheyange on 2016/8/18.
 * 讲师页面 viewpager 讲师简介
 */
public class OrganIntroduceView {

    Context mContext;
    View mView;

    TextView teacher_name;
    TextView introduce_tv;

    public OrganIntroduceView(Context mContext) {
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
        teacher_name.setText("机构简介");
        introduce_tv = (TextView) mView.findViewById(R.id.introduce_tv);

    }


    private void initData() {

        getData();
    }

    private void initEvent() {
    }

    public void getData()
    {

        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"zone/detail?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.i("TAG-Prefecture", "onResponse: "+response);
                Gson gson = new Gson();
                PrefectureBean result = gson.fromJson(response, new TypeToken<PrefectureBean>() {
                }.getType());
                if (result.getStatus() == 1) {
                    Log.i("TAG-Teacher", "onResponse: "+gson.toJson(result.getData() ));

                    introduce_tv.setText(result.getData().getPgcInfo().getPgc_desc());

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
                map.put("zone_id", ConstantSet.zone_index);
                return map;
            }
        };

        MyApplication.getRq().add(rq);

    }
}
