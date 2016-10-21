package cn.automooc.com.ui;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.automooc.com.BaseActivity;
import cn.automooc.com.R;
import cn.automooc.com.adapter.InformationListViewAdapter;

public class InformationActivity extends BaseActivity {

    
    ListView informationListView;
    InformationListViewAdapter adapter;
    List<String> lists;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        
        initView();
        initData();
        initEvent();
    }

    @Override
    protected void initView() {
        informationListView= (ListView) findViewById(R.id.information_listview);
    }

    @Override
    protected void initData() {

        
        lists=new ArrayList<String>();
        for(int i=0;i<5;i++)
        {
            lists.add("item"+i);
        }
        
        adapter=new InformationListViewAdapter(lists,this);
        informationListView.setAdapter(adapter);
        
    }

    @Override
    protected void initEvent() {

    }
}
