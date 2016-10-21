package cn.automooc.com.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.automooc.com.R;
import cn.automooc.com.adapter.PrefectureDetailCourseAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.PrefectureBean;
import cn.automooc.com.utils.ConstantSet;

/**
 * Created by jiuzheyange on 2016/8/18.
 * 讲师Activity中的课程列表
 */
public class PrefectureDetailCourseView {

    Context mContext;
    View mView;

    List<PrefectureBean.DataBean.PgcCourseListBean.ListBean> lists;
//    List<TeacherCourseBean.DataBean> lists;
    PrefectureDetailCourseAdapter kedanAdapter;
    RecyclerView listView;
    LinearLayout empty_ly;

    public PrefectureDetailCourseView(Context mContext) {
        this.mContext = mContext;
    }
    
    
    public View getView()
    {
        mView=View.inflate(mContext, R.layout.jiangshi_view1,null);
        initView();
        initData();
        initEvent();
        return mView;
    }

    private void initView() {

        lists=new ArrayList<>();
        listView= (RecyclerView) mView.findViewById(R.id.list_view);
        empty_ly = (LinearLayout) mView.findViewById(R.id.empty_ly);
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
                        if (!result.getData().getPgcInfo().getCourse_count().equalsIgnoreCase("0")){
                            lists.clear();
                            lists = result.getData().getPgcCourseList().getList();
                            kedanAdapter=new PrefectureDetailCourseAdapter(mContext,lists);
                            listView.setAdapter(kedanAdapter);
                            listView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                        }else {
                            empty_ly.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.GONE);
                        }
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
