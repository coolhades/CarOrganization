package cn.automooc.com.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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
import cn.automooc.com.adapter.VideoView3ListViewAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.Question;
import cn.automooc.com.bean.ResultQuestion;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.widget.TiWenDialog;

import static android.view.View.inflate;

/**
 * Created by jiuzheyange on 2016/8/10.
 */
public class VideoView3 {
    
    Context mContext;
    View mView;
    
    ListView listView;
    VideoView3ListViewAdapter adapter;
    
    List<Question> lists;
    String info;
    
    TextView tiwenText;
    Handler videoHandler;
    
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1==10000)
            {
                getData("1"); 
            }
            
        }
    };

    public VideoView3(Context mContext,String info, Handler handler) {
        this.mContext = mContext;
        this.info=info;
        this.videoHandler = handler;
    }
    
    public View getView()
    {
        mView= inflate(mContext, R.layout.video_view3,null);
        initView();
        initData();
        initEvent();
        
        return mView;
        
    }

    private void initView() {
        
        listView= (ListView) mView.findViewById(R.id.view3_listview);

        tiwenText= (TextView) mView.findViewById(R.id.tiwen_text);
    }

    private void initData() {

        lists=new ArrayList<Question>();

        getData("1");
    }

    private void initEvent() {

       tiwenText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Message msg = Message.obtain();
                msg.arg1 = 10011;
                videoHandler.sendMessage(msg);

                TiWenDialog dialog = new TiWenDialog(mContext,info,handler);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
                dialog.show();
                

              
            }
        });
    }


    public void getData(final String page) {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "comment/getcommentlist?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.i("TAG-Comment", response);

                if (response.length() > 80) {

                    Gson gson = new Gson();


                    ResultQuestion result = gson.fromJson(response, new TypeToken<ResultQuestion>() {
                    }.getType());

                    lists.clear();
                    lists.addAll(result.getData());
                    if(adapter==null) {
                        adapter = new VideoView3ListViewAdapter(lists, mContext);

                        listView.setAdapter(adapter);
                    }
                    else
                    {
                        adapter.notifyDataSetChanged();
                    }

                    LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) listView.getLayoutParams();

                    params.height=(int)(lists.size()*((120*mContext.getResources().getDisplayMetrics().density)+0.5f));
                    listView.setLayoutParams(params);


                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败-Comment", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("obj_id",info);
                map.put("page",page);
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
}
