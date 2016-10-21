package cn.automooc.com.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import cn.automooc.com.adapter.PrefectureTeacherListAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.PrefectureBean;
import cn.automooc.com.ui.JiangshiActivity;
import cn.automooc.com.utils.ConstantSet;

/**
 * Created by jiuzheyange on 2016/8/18.
 */
//
public class PrefectureTeacherView {
    Context mContext;
    View mView;

    ListView teacherListView;

    List<PrefectureBean.DataBean.PgcTeacherListBean> list2;


    PrefectureTeacherListAdapter adapter2;
    LinearLayout empty_ly;


    public PrefectureTeacherView(Context mContext) {
        this.mContext = mContext;
    }

    public View getView() {
        mView = View.inflate(mContext, R.layout.teacherview_pager, null);
        initView();
        initData();
        initEvent();


        return mView;
    }

    private void initView() {
        teacherListView = (ListView) mView.findViewById(R.id.teacher_listview);
//        prefectureListView.addHeaderView(View.inflate(mContext,R.layout.mingshi_listview_head,null));
        empty_ly = (LinearLayout) mView.findViewById(R.id.empty_ly);
    }


    private void initData() {

        list2=new ArrayList<>();
        getDataMingshi("1","10");

    }


    private void initEvent() {

        teacherListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(list2!=null&&list2.size()>0) {
                    ConstantSet.teacher_name=list2.get(i).getTeacher_name();
                    ConstantSet.teacher_url=list2.get(i).getTeacher_avatar();
                    ConstantSet.teacher_id = list2.get(i).getTeacher_id();
                }
                mContext.startActivity(new Intent(mContext, JiangshiActivity.class));
            }
        });
    }


    public void getDataMingshi(final String page, final String pagesize) {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"zone/detail?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.i("TAG-Prefecture", "onResponse: "+response);
                Gson gson = new Gson();
                PrefectureBean result = gson.fromJson(response, new TypeToken<PrefectureBean>() {
                }.getType());
                if (result.getStatus() == 1) {
                    Log.i("TAG-TeacherList", "onResponse: "+gson.toJson(result.getData().getPgcTeacherList() ));

//
                    if (!result.getData().getPgcInfo().getTeacher_count().equalsIgnoreCase("0")) {
                        list2 = result.getData().getPgcTeacherList();
                        adapter2 = new PrefectureTeacherListAdapter(list2, mContext);
                        teacherListView.setAdapter(adapter2);
                    }else {
                        teacherListView.setVisibility(View.GONE);
                        empty_ly.setVisibility(View.VISIBLE);
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
