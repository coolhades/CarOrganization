package cn.automooc.com.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.automooc.com.R;
import cn.automooc.com.adapter.JiangshiListAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.TeacherListBean;
import cn.automooc.com.ui.JiangshiActivity;
import cn.automooc.com.utils.ConstantSet;

/**
 * Created by jiuzheyange on 2016/8/18.
 */
//
public class TeacherView {
    Context mContext;
    View mView;

    ListView teacherListView;

    List<TeacherListBean.DataBean.ListBean> list2;


    JiangshiListAdapter adapter2;


    public TeacherView(Context mContext) {
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
    }


    private void initData() {

        list2=new ArrayList<>();
        getDataMingshi();

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


    public void getDataMingshi() {
        StringRequest rq = new StringRequest(Request.Method.GET, ConstantSet.homeAddress + "teacher/index?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.i("TAG-Teacher-teacher", response);

                    Gson gson = new Gson();
                try {
                    TeacherListBean resultTeacher = gson.fromJson(response, new TypeToken<TeacherListBean>() {
                    }.getType());
                    if (resultTeacher.getStatus() == 1) {
                        list2 = resultTeacher.getData().getList();
                        adapter2 = new JiangshiListAdapter(list2, mContext);
                        teacherListView.setAdapter(adapter2);
                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败1", Toast.LENGTH_SHORT).show();
            }
        });

        MyApplication.getRq().add(rq);
    }
}
