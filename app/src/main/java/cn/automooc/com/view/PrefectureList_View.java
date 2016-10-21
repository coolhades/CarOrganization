package cn.automooc.com.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import cn.automooc.com.adapter.PrefectureListAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.PrefectureListBean;
import cn.automooc.com.ui.LoginAndRegisterActivity;
import cn.automooc.com.ui.PrefectureDetailActivity;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.SaveUser;

/**
 * Created by Hades on 16/9/21.
 */
public class PrefectureList_View {
    Context mContext;
    View mView;

    ListView prefectureListView;

    List<PrefectureListBean.DataBean.ListBean> list2;


    PrefectureListAdapter adapter2;


    public PrefectureList_View(Context mContext) {
        this.mContext = mContext;
    }

    public View getView() {
        mView = View.inflate(mContext, R.layout.teacherview_pager, null);//复用ListView
        initView();
        initData();
        initEvent();


        return mView;
    }

    private void initView() {
        prefectureListView = (ListView) mView.findViewById(R.id.teacher_listview);
//        prefectureListView.addHeaderView(View.inflate(mContext,R.layout.mingshi_listview_head,null));
    }


    private void initData() {

        list2=new ArrayList<>();
        getDataMingshi("1","10");

    }


    private void initEvent() {

        prefectureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(list2!=null&&list2.size()>0) {
                    ConstantSet.teacher_name=list2.get(i).getPgc_name();
                    ConstantSet.teacher_url=list2.get(i).getPgc_logo();
//                    ConstantSet.teacher_id = list2.get(i).getTeacher_id();
                    ConstantSet.zone_index = list2.get(i).getUid();
                }
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
                    mContext.startActivity(new Intent(mContext, PrefectureDetailActivity.class));
                }
            }
        });
    }


    public void getDataMingshi(final String page, final String pagesize) {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "zone/index?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.i("TAG-TeacherList", response);

                Gson gson = new Gson();
                PrefectureListBean resultPrefecture = gson.fromJson(response, new TypeToken<PrefectureListBean>() {
                }.getType());
                if (resultPrefecture.getStatus() == 1){
                    list2 = resultPrefecture.getData().getList();
                    adapter2 = new PrefectureListAdapter(list2, mContext);
                    prefectureListView.setAdapter(adapter2);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败1", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();

                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
}
