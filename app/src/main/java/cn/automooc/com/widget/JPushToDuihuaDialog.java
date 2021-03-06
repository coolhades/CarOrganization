package cn.automooc.com.widget;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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

import cn.automooc.com.BaseActivity;
import cn.automooc.com.R;
import cn.automooc.com.adapter.DuihuaListViewAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.QuestionListBean;
import cn.automooc.com.utils.ConstantSet;

public class JPushToDuihuaDialog extends BaseActivity {

    /*
     * author:qideli
       date:2016/1/23
       */
    public interface OnCustomDialogListener {
        public void back(String name);
    }

    /* TextView mCancel;
     TextView mOk;*/

    List<QuestionListBean.DataBean> lists;
    ListView listView;

    ImageView back;

    DuihuaListViewAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duihua_layout);
        //  setTitle(name);

        initView();
        initData();
        initEvent();


    }

    protected void initView() {

        listView = (ListView) findViewById(R.id.list_view);
        back= (ImageView) findViewById(R.id.back);
    }

    protected void initData() {

        lists = new ArrayList<QuestionListBean.DataBean>();

        getData("1");
    }

    protected void initEvent() {


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JPushToDuihuaDialog.this.finish();
            }
        });
    }



    public void getData(final String page) {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "comment/getanswerlist?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                try {

                    //
                    Log.i("TAGTAG", "会话列表"+response);
                    Gson gson = new Gson();

                    QuestionListBean result = gson.fromJson(response, new TypeToken<QuestionListBean>() {
                    }.getType());
                    if (result.getStatus() == 1) {
                        lists = result.getData();
                        adapter = new DuihuaListViewAdapter(lists, JPushToDuihuaDialog.this);

                        listView.setAdapter(adapter);
                    }

                   /* LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) listView.getLayoutParams();

                    params.height = (int) (lists.size() * ((120 *context.getResources().getDisplayMetrics().density) + 0.5f));
                    listView.setLayoutParams(params);*/


                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(JPushToDuihuaDialog.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("obj_id",ConstantSet.Target_id);
                if(ConstantSet.user!=null) {
                    map.put("uid", ConstantSet.user.getUid());
                }
                map.put("page", page);
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }


}
