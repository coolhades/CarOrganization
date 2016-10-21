package cn.automooc.com.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hades.libam.cache.ACache;

import java.util.ArrayList;
import java.util.List;

import cn.automooc.com.BaseFragment;
import cn.automooc.com.R;
import cn.automooc.com.adapter.HomeFragmentListViewAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.Banner;
import cn.automooc.com.bean.LastResultHome;
import cn.automooc.com.bean.Lessonblock;
import cn.automooc.com.bean.ResultHome;
import cn.automooc.com.ui.SearchActivity;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.view.HomeFragmentFootView;
import cn.automooc.com.view.HomeFragmentHeadView;

/**
 * Created by jiuzheyange on 2016/8/8.
 */
public class HomeFragment extends BaseFragment {

    View view;

    LinearLayout searchLayout;
    ListView listView;
    List<Lessonblock> lists;
    HomeFragmentListViewAdapter adapter;
    HomeFragmentHeadView headView;
    HomeFragmentFootView footView;

    List<Banner> listBanner;

//    ProgressDialog dialog;

    MaterialRefreshLayout materialRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initView();
        initData();
        initEvent();

        return view;
    }

    @Override
    protected void initView() {
        view = View.inflate(getActivity(), R.layout.home_fragment, null);
        searchLayout = (LinearLayout) view.findViewById(R.id.search_layout);

        listView = (ListView) view.findViewById(R.id.home_fragment_listview);

        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        materialRefreshLayout.setWaveColor(Color.parseColor("#55FFFFFF"));
        materialRefreshLayout.setIsOverLay(true);
        materialRefreshLayout.setWaveShow(true);

        lists = new ArrayList<>();
    }

    @Override
    protected void initData() {

        if (loadCache()) {

        }else {

            getData();
        }

//        getData();

        footView = new HomeFragmentFootView(getActivity());
        listView.addFooterView(footView.getView());
    }

    private Boolean loadCache() {
        //此处先加载缓存、然后刷新一次数据
        ACache cache = ACache.get(getActivity());
        Gson gson = new Gson();
        ResultHome homedata = gson.fromJson(cache.getAsJSONObject("HomeData").toString(), ResultHome.class);
        if (homedata != null) {
            Log.d("TAG-HomeData", gson.toJson(homedata));
            //刷新数据
            lists.clear();
            lists.addAll(homedata.getBlock());
            if (adapter == null) {
                listBanner = homedata.getBanner();
                headView = new HomeFragmentHeadView(getActivity());
                listView.addHeaderView(headView.getView(homedata.getBanner(), homedata.getCategory()));
                adapter = new HomeFragmentListViewAdapter(lists, getActivity());
                listView.setAdapter(adapter);
            } else {
                materialRefreshLayout.finishRefresh();
                adapter.notifyDataSetChanged();
            }

            return true;
        }else {
            return false;//无缓存
        }

    }

    @Override
    protected void initEvent() {

        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });


        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //load more refreshing...
            }
        });

    }


    public void getData() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "main/gethome?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.i("TAGTAG-Home", response);
                Gson gson = new Gson();
                try {
                    LastResultHome lastResultHome = gson.fromJson(response, new TypeToken<LastResultHome>() {
                    }.getType());
                    if (lastResultHome.getStatus().equalsIgnoreCase("1")) {
                        lists.clear();
                        lists.addAll(lastResultHome.getData().getBlock());
                        if (adapter == null) {
                            listBanner = lastResultHome.getData().getBanner();
                            headView = new HomeFragmentHeadView(getActivity());
                            listView.addHeaderView(headView.getView(lastResultHome.getData().getBanner(), lastResultHome.getData().getCategory()));
                            adapter = new HomeFragmentListViewAdapter(lists, getActivity());
                            listView.setAdapter(adapter);
                        } else {
                            materialRefreshLayout.finishRefresh();
                            adapter.notifyDataSetChanged();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "网络请求失败", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });

        MyApplication.getRq().add(rq);
    }


}
