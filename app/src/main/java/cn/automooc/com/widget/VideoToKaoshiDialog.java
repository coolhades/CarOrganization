package cn.automooc.com.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
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

import cn.automooc.com.R;
import cn.automooc.com.adapter.MyExamineListViewAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.MyExam;
import cn.automooc.com.bean.ResultMyExam;
import cn.automooc.com.utils.ConstantSet;

public class VideoToKaoshiDialog extends Dialog {

    ListView myExamineListView;

    List<MyExam> lists;

    MyExamineListViewAdapter adapter;

    ImageView back;
    ImageView guide_iv;
    Context mcontext;

    public interface OnCustomDialogListener {
        public void back(String name);
    }

    public VideoToKaoshiDialog(Context context, Handler handler) {
        super(context, R.style.AppTheme);
        this.mcontext = context;

    }

    @Override
    protected void onStart() {
        super.onStart();

//		FlurryAgent.onStartSession(this, UIApplication.FLURRY_KEY);
    }

    public void colseDialog() {
        
//        flag=false;
//        if (mVideoHandler != null) {
//            Message msg = Message.obtain();
//            msg.arg1 = 10010;
//            mVideoHandler.sendMessage(msg);
//        }
//        VideoToKaoshiDialog.this.dismiss();
//        if (player != null) {
//            player.start();
//        }
        VideoToKaoshiDialog.this.dismiss();

    }

    @Override
    protected void onStop() {
        super.onStop();

//		FlurryAgent.onEndSession(this);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_examine);


        back = (ImageView) findViewById(R.id.back);

        //返回键
        {
            back.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    colseDialog();
                }
            });
        }

        myExamineListView= (ListView) findViewById(R.id.my_examine_listview);
        guide_iv = (ImageView) findViewById(R.id.guide_iv);

        lists=new ArrayList<MyExam>();

        getData(); //获取特定课程

    }
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
           /* switch(keyCode)
            {
            case KeyEvent.KEYCODE_BACK:
//                if(webview!=null && webview.canGoBack() == true && webview.getUrl() != null){
//                	webview.goBack();
//                }else{
//                    //finish();
//                }
                return true;
            }*/
            
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }


    // 获取某一课程下考试列表
    public void getData()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"user/getmyexam?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                System.out.print("response  "+response+"    "+response.length());
                Log.i("TAGTAG考试列表", response);
                // showShortToast(response);
                try {

                    Gson gson = new Gson();
                    ResultMyExam resultUser = gson.fromJson(response, new TypeToken<ResultMyExam>() {
                    }.getType());

                    if (resultUser.getStatus().equalsIgnoreCase("1")) {
                        lists = resultUser.getData();
                        adapter = new MyExamineListViewAdapter(resultUser.getData(), mcontext);

                        myExamineListView.setAdapter(adapter);

                    }

//                    GongGaoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//
//                            WebViewKaoshiDialog dialog =  new  WebViewKaoshiDialog(ExamineActivity.this,ConstantSet.homeAddress+lists.get(i).getExam_link(),
//                                    "考试", 0, null,null);
//
//                            Window window = dialog.getWindow();
//                            window.setGravity(Gravity.BOTTOM);
//                            dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
//                            dialog.show();
//                        }
//                    });

//                    if (resultUser.getStatus().equals("1")) {
//
//
//
//                    }
                }catch (Exception e){

                }

            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mcontext, "网络请求失败", Toast.LENGTH_SHORT).show();

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                if(ConstantSet.user!=null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }
                map.put("class_id", ConstantSet.class_id);
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }

}
