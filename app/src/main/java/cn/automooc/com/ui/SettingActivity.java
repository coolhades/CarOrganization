package cn.automooc.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.File;
import cn.automooc.com.BaseActivity;
import cn.automooc.com.R;
import cn.automooc.com.bean.User;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.SaveUser;

public class SettingActivity extends BaseActivity {

    LinearLayout aboutUsBt;
    TextView cacheText;
    
    TextView logoutBt;
    
    LinearLayout cacheBar;
    ImageView back;
    
    TextView ziliao;
    
    LinearLayout changePassword;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initData();
        initEvent();
    }

    @Override
    protected void initView() {
        aboutUsBt= (LinearLayout) findViewById(R.id.about_us_bt);
        cacheText= (TextView) findViewById(R.id.cache_text);
        logoutBt= (TextView) findViewById(R.id.logout_bt);
        cacheBar= (LinearLayout) findViewById(R.id.cache_bar);
        back= (ImageView) findViewById(R.id.back);

        ziliao= (TextView) findViewById(R.id.ziliao);

        changePassword= (LinearLayout) findViewById(R.id.change_password);
    }

    @Override
    protected void initData() {

        File file=new File(Environment.getDownloadCacheDirectory().getPath());
        try {
           // getFolderSize(file);
          //  Toast.makeText(SettingActivity.this,getFolderSize(file)+"",Toast.LENGTH_LONG).show();

            cacheText.setText(getFolderSize(file)+"MB");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void initEvent() {

        aboutUsBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this,AboutUsActivity.class));
            }
        });



        ziliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, PersonalSetActivity.class));
            }
        });

        cacheBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cacheText.setText("0MB");
                showShortToast("清理完成");
            }
        });
        


        logoutBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstantSet.user=new User("0","0","0");

                SaveUser save=new SaveUser(SettingActivity.this);
                save.saveData("userFile","user",ConstantSet.user);
                
                startActivity(new Intent(SettingActivity.this,HomeActivity.class));
                SettingActivity.this.finish();
                
                
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingActivity.this.finish();
            }
        });


        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this,ChangePasswordActivity.class));
            }
        });
        
    }


    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件  
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }
}
