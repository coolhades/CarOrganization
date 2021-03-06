package cn.automooc.com.view;

import android.content.Context;
import android.view.View;
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
import cn.automooc.com.adapter.FirstVideoView3ListViewAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.Jianjie;
import cn.automooc.com.bean.Question;
import cn.automooc.com.bean.ResultQuestion;
import cn.automooc.com.utils.ConstantSet;

import static android.view.View.inflate;

/**
* 创建时间 16/9/21
* auther Hades
* 描述  提问列表 view
**/
public class ListOfCommentView {
    Context mContext;
    View mView;
    
    ListView listView;
    FirstVideoView3ListViewAdapter adapter;
    
    List<Question> lists;

    Jianjie info;
    public ListOfCommentView(Context mContext, Jianjie info) {
        this.mContext = mContext;
        this.info=info;
    }
    
    public View getView()
    {
        mView= inflate(mContext, R.layout.video_view_first3,null);
        initView();
        initData();
        initEvent();
        
        return mView;
        
    }

    private void initView() {
        
        listView= (ListView) mView.findViewById(R.id.view3_listview);
    }

    private void initData() {

        lists=new ArrayList<Question>();
       
        getData("1");
    }

    private void initEvent() {
    }


    public void getData(final String page) {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "comment/getcommentlist?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                try {

                    Gson gson = new Gson();

                    ResultQuestion result = gson.fromJson(response, new TypeToken<ResultQuestion>() {
                    }.getType());
                    if (result.getStatus().equalsIgnoreCase("1")) {
                        lists = result.getData();
                        adapter = new FirstVideoView3ListViewAdapter(lists, mContext);

                        listView.setAdapter(adapter);

                    }
                   /* LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) listView.getLayoutParams();

                    params.height=(int)(lists.size()*((120*mContext.getResources().getDisplayMetrics().density)+0.5f));
                    listView.setLayoutParams(params);*/


                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败-Commons", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("obj_id",info.getClass_id());
                map.put("page",page);
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
}
