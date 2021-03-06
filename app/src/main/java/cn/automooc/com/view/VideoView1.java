package cn.automooc.com.view;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bokecc.sdk.mobile.play.DWMediaPlayer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.automooc.com.R;
import cn.automooc.com.adapter.VideoView1ListViewAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.JieBean;
import cn.automooc.com.bean.ResultVideo;
import cn.automooc.com.bean.ZhangBean;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.Md5Utils;
import cn.automooc.com.videointerface.OnVideoDataListener;

import static android.view.View.inflate;

/**
 * Created by jiuzheyange on 2016/8/10.
 */
public class VideoView1 {

    Context mContext;
    View mView;

    List<ZhangBean> parentLists;
    List<List<JieBean>>  childLists;
    VideoView1ListViewAdapter adapter;
    ExpandableListView listView;
    List<ZhangBean> zhangBeanList;
    DWMediaPlayer player;
    Handler handler;
    ResultVideo resultVideo;
    OnVideoDataListener onVideoDataListener;//设置视频数据 监听
    MyApplication app;

    int parentVideo_index; //用来获取 父节点位置
    int childVideo_index = 0; //当前节点位置

    public VideoView1(Context mContext,List<ZhangBean> zhangBean, DWMediaPlayer player,Handler handler) {
        this.mContext = mContext;
        this.zhangBeanList=zhangBean;
        this.player=player;
        this.handler=handler;
    }

    public void setOnVideoDataListener(OnVideoDataListener listener){
        if (listener != null) {
            this.onVideoDataListener = listener;
        }

    }

    public View getView()
    {
        mView= inflate(mContext, R.layout.video_view1,null);
        app = (MyApplication) mContext.getApplicationContext();
        initView();
        initData();
        initEvent();

        return mView;
    }

    private void initView() {

        listView= (ExpandableListView) mView.findViewById(R.id.view1_listview);
        listView.setGroupIndicator(null);
    }

    private void initData() {

        parentLists=new ArrayList<>();
        childLists=new  ArrayList<List<JieBean>>();
        parentLists=zhangBeanList;

//        ToastUtils.showTextToast(parentLists.get(0), mContext);


        for(int i=0;i<parentLists.size();i++)
        {
            childLists.add(zhangBeanList.get(i).getChild());
        }

        if(parentLists!=null&&parentLists.size()>0) {

            adapter = new VideoView1ListViewAdapter(mContext, parentLists, childLists,player);

            listView.setAdapter(adapter);

            for (int i = 0; i < parentLists.size(); i++) {
                listView.expandGroup(i);
            }
        }

    }

    private void initEvent() {
        
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                getChildVideo(i,i1);
                parentVideo_index = i;
                childVideo_index = i1;

                return false;
            }
        });

        
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
//                adapter.notifyDataSetChanged();

                getParentVideo(i);//获取链接并播放
                parentVideo_index = i;

                return true;
            }
        });
    }


    public void playNext(){
        if (parentLists.get(parentVideo_index).getChild()== null)//目前章是否有节
        {
            //无节 进入下一章
            int i = parentVideo_index;
//            ToastUtils.showTextToast("现在是第"+parentVideo_index+"章"+"总共"+parentLists.size()+"章", mContext);
            Log.d("TAG-ParentIndex-next", "第"+parentVideo_index+"章"+"     总共"+parentLists.size()+"章");
            if (++i <parentLists.size()) {
                //有下一章的情况
                parentVideo_index++;
//                playNext();
                if (parentLists.get(parentVideo_index).getChild() == null){
                    getParentVideo(parentVideo_index);
                }else {
                    playNext();
                }
            }else {
                Toast.makeText(mContext, "已经是最后一集！", Toast.LENGTH_SHORT).show();
                return;
            }
        }else {
            //有节的情况
            if (childVideo_index<parentLists.get(parentVideo_index).getChild().size()-1){
                //不是最后一节
                getChildVideo(parentVideo_index, ++childVideo_index);
            }else {
                //最后一节 跳转下一章
                int i = parentVideo_index;
                if (++i <parentLists.size()) {

                    //有下一章的情况
                    parentVideo_index++;
                    childVideo_index = -1;
                    playNext();
                }else {
                    Toast.makeText(mContext, "已经是最后一集！", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }

    }

//
    public void getChildVideo(final int  groupPosition, final int  childPosition)
    {
        Toast.makeText(mContext, "加载视频，请稍后...", Toast.LENGTH_SHORT).show();
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + ConstantSet.class_getvideo, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

              //  Toast.makeText(mContext,response+"",Toast.LENGTH_SHORT).show();
                Log.i("TAG-VideoInfo",""+response);
                try {

                    Gson gson=new Gson();

                    resultVideo = gson.fromJson(response, new TypeToken<ResultVideo>() {
                    }.getType());
                    if (resultVideo.getStatus().equalsIgnoreCase("1")) {
                        String s = resultVideo.getData().getWatch_time();
                        String p;
                        Message msg = Message.obtain();
                        msg.arg1 = 1001;
                        if (s.contains(".")) {
                            String[] sArray = s.split("\\.");
                            p = sArray[0];
                            msg.what = Integer.parseInt(p);
                        } else {
                            msg.what = Integer.parseInt(resultVideo.getData().getWatch_time());
                        }
                        if ((resultVideo.getData().getExam() != null) && (resultVideo.getData().getExam().size() > 0)) {
                            ConstantSet.exam_name.clear();
                            ConstantSet.exam_url.clear();
                            ConstantSet.can_close.clear();

                            int index = resultVideo.getData().getExam().size();
                            msg.arg2 = index;//考试次数传给video

                            //保存考试 链接 等信息
                            for (int i = 0; i < index; i++) {
//                            msg.arg2 = Integer.parseInt(resultVideo.getData().getExam().get(i).getVideo_pos());
                                ConstantSet.exam_name.add(resultVideo.getData().getExam().get(i).getExam_name());
                                ConstantSet.exam_url.add(resultVideo.getData().getExam().get(i).getExam_url());
                                ConstantSet.can_close.add(resultVideo.getData().getExam().get(i).getCan_close());
                                ConstantSet.exam_time.add(resultVideo.getData().getExam().get(i).getVideo_pos());//考试时间点
                            }

//                        msg.arg2 = Integer.parseInt(resultVideo.getData().getExam().get(0).getVideo_pos());
//                        ConstantSet.exam_name=resultVideo.getData().getExam().get(0).getExam_name();
//                        ConstantSet.exam_url=resultVideo.getData().getExam().get(0).getExam_url();
//                        ConstantSet.can_close=resultVideo.getData().getExam().get(0).getCan_close();
                        }
                        msg.obj = resultVideo.getData().getVideo_title();
                        handler.sendMessage(msg);

                        if (resultVideo.getData().getStore_value() != null && (!resultVideo.getData().getStore_value().equals(""))) {
                            ConstantSet.section_uid = childLists.get(groupPosition).get(childPosition).getSection_uid();
//                        reSetPlayer(player, resultVideo.getData().getStore_value(), resultVideo.getData().getStore_type(), resultVideo.getData().getStore_config());
                            //设置接口回调
                            onVideoDataListener.reSetPlayerData(resultVideo.getData().getStore_value(), resultVideo.getData().getStore_type(), resultVideo.getData().getStore_config());
                            parentVideo_index = groupPosition;
                            childVideo_index = childPosition;

                        } else {
                            Toast.makeText(mContext, "视频不存在", Toast.LENGTH_SHORT).show();
                        }

                    }

                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败-child", Toast.LENGTH_SHORT).show();
                Log.i("TAG-VideoInfo", error.toString());
//                reSetPlayer(player,"EC5EA551527A46759C33DC5901307461","2");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                map.put("section_uid",childLists.get(groupPosition).get(childPosition).getSection_uid());//后期改成course_id
                map.put("okey", Md5Utils.md5("moocclassgetvideo"+childLists.get(groupPosition).get(childPosition).getSection_uid()));
                if(ConstantSet.user!=null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }
                map.put("class_id",ConstantSet.class_id);
                return map;
            }
        };

        MyApplication.getRq().add(rq);

    }



    public void getParentVideo(final int i)
    {
        Toast.makeText(mContext, "加载视频，请稍后...", Toast.LENGTH_SHORT).show();
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + ConstantSet.class_getvideo, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                Log.d("parent_video",response);
                try {

                        Gson gson=new Gson();
                        ResultVideo resultVideo = gson.fromJson(response, new TypeToken<ResultVideo>() {
                        }.getType());
                        if (resultVideo.getStatus().equalsIgnoreCase("1")) {

                            Message msg = Message.obtain();
                            msg.arg1 = 1001;
                            String s = resultVideo.getData().getWatch_time();
                            String p;
                            if (s.contains(".")) {
                                String[] sArray = s.split("\\.");
                                p = sArray[0];
                                msg.what = Integer.parseInt(p);
                            } else {
                                msg.what = Integer.parseInt(resultVideo.getData().getWatch_time());
                            }
                            //msg.what 目前观看时间
                            if ((resultVideo.getData().getExam() != null) && (resultVideo.getData().getExam().size() > 0)) {
                                ConstantSet.exam_name.clear();
                                ConstantSet.exam_url.clear();
                                ConstantSet.can_close.clear();

                                int index = resultVideo.getData().getExam().size();
                                msg.arg2 = index;//考试次数传给video

                                //保存考试 链接 等信息
                                for (int i = 0; i < index; i++) {
//                            msg.arg2 = Integer.parseInt(resultVideo.getData().getExam().get(i).getVideo_pos());
                                    ConstantSet.exam_name.add(resultVideo.getData().getExam().get(i).getExam_name());
                                    ConstantSet.exam_url.add(resultVideo.getData().getExam().get(i).getExam_url());
                                    ConstantSet.can_close.add(resultVideo.getData().getExam().get(i).getCan_close());
                                    ConstantSet.exam_time.add(resultVideo.getData().getExam().get(i).getVideo_pos());//考试时间点
                                }
                                //旧版只支持1此考试
//                        msg.arg2 = Integer.parseInt(resultVideo.getData().getExam().get(0).getVideo_pos());
//                        ConstantSet.exam_name=resultVideo.getData().getExam().get(0).getExam_name();
//                        ConstantSet.exam_url=resultVideo.getData().getExam().get(0).getExam_url();
//                        ConstantSet.can_close=resultVideo.getData().getExam().get(0).getCan_close();

                            }
                            msg.obj = resultVideo.getData().getVideo_title();
                            handler.sendMessage(msg);

                            if (resultVideo.getData().getStore_value() != null && (!resultVideo.getData().getStore_value().equals(""))) {
                                ConstantSet.section_uid = parentLists.get(i).getSection_uid();
//                        reSetPlayer(player, resultVideo.getData().getStore_value(), resultVideo.getData().getStore_type(), resultVideo.getData().getStore_config());
                                onVideoDataListener.reSetPlayerData(resultVideo.getData().getStore_value(), resultVideo.getData().getStore_type(), resultVideo.getData().getStore_config());
                                parentVideo_index = i;
                                Log.d("TAG-ParentIndex", "第" + parentVideo_index + "章" + "     总共" + parentLists.size() + "章");

                            } else {
                                Toast.makeText(mContext, "视频不存在", Toast.LENGTH_SHORT).show();
                            }

                        }

                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败-parent", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                Log.i("TAGTAG", "i="+i);
                map.put("section_uid",parentLists.get(i).getSection_uid());//后期改成course_id
                if(ConstantSet.user!=null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }
                map.put("class_id",ConstantSet.class_id);
                map.put("okey", Md5Utils.md5(ConstantSet.MD5Header+ConstantSet.class_getvideo+parentLists.get(i).getSection_uid()));
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }

    //重置player状态  已弃用
    private void reSetPlayer(DWMediaPlayer player, String store_value, String store_type, String store_config)
    {
        player.reset();//reset

        if(store_type.equals("2"))
        {
            //此处进行加密 未加密判断
            if (store_config.equalsIgnoreCase("2"))
            {
                //设置数据
                // DRM加密播放
                player.setDRMServerPort(app.getDrmServerPort());
                player.setVideoPlayInfo(store_value, ConstantSet.CC_Account_id, ConstantSet.CC_Account_Key,mContext);
            }else if (store_config.equalsIgnoreCase("1")){
                //设置数据
                player.setDRMServerPort(app.getDrmServerPort());
                player.setVideoPlayInfo(store_value, ConstantSet.CC_Account_NO_id, ConstantSet.CC_Account_NO_Key,mContext);
            }

            player.prepareAsync();//准备

            Message msg=Message.obtain();
            msg.arg1=1001;
            if(resultVideo!=null) {
                msg.obj = resultVideo.getData().getVideo_title();
            }
            handler.sendMessage(msg);

        }else if(store_type.equals("1"))
        {
            try {
                if(store_value!=null&&store_value!="") {
                    player.setDataSource(mContext, Uri.parse(store_value));
                    player.prepareAsync();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public int zhuanMiao(String time)
    {
        return Integer.parseInt(time.substring(0,2))*3600+Integer.parseInt(time.substring(3,5))*60+Integer.parseInt(time.substring(6,8));
    }
    
    
}
