package cn.automooc.com.views.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.automooc.com.BaseFragment;
import cn.automooc.com.R;
import cn.automooc.com.presenter.ILoginPresenter;
import cn.automooc.com.presenter.LoginPresenter;
import cn.automooc.com.utils.AuthorizedLoginUtils;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.ToastUtils;
import cn.automooc.com.views.viewinterface.IOnLogin;

/**
 * Created by jiuzheyange on 2016/8/10.
 */
public class MyLoginFragment extends BaseFragment implements IOnLogin {

    View mView;
    //TextView forgetPasswordBt;
    TextView loginBt;

    ImageView qqLoginBt;
    ImageView wxLoginBt;
    ImageView wbLoginBt;
    
    EditText inputEmail;
    EditText inputPassword;
    
    LinearLayout loginBar;
    
    TextView tip;

    ILoginPresenter login;//Presenter
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     
        mView=inflater.inflate(R.layout.login_fragmentt,null);
        initView();
        initData();
        initEvent();

        return mView;
        
    }

    @Override
    protected void initView() {
        login = new LoginPresenter(getActivity() ,this);


       // forgetPasswordBt= (TextView) mView.findViewById(R.id.forget_password);
        loginBt= (TextView) mView.findViewById(R.id.login_bt);
        qqLoginBt= (ImageView) mView.findViewById(R.id.qq_login_bt);
        wxLoginBt= (ImageView) mView.findViewById(R.id.wx_login_bt);
        wbLoginBt= (ImageView) mView.findViewById(R.id.wb_login_bt);
        inputEmail= (EditText) mView.findViewById(R.id.input_email);
        inputPassword= (EditText) mView.findViewById(R.id.input_password);
        loginBar= (LinearLayout) mView.findViewById(R.id.login_bar);
        
        tip= (TextView) mView.findViewById(R.id.tip);

        if(ConstantSet.confiMap!=null) {
            if (ConstantSet.confiMap.get("App.Switch.Third-Party.Login").equals("0")) {
                loginBar.setVisibility(View.GONE);
                tip.setVisibility(View.GONE);
            }
        }
        
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {


/*
        forgetPasswordBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                
                startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
            }
        });
*/
        
        
         loginBt.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 
                 if(!((inputEmail.getText().toString().length()>0)&&(inputPassword.getText().toString().length()>0)))
                 {
                     showShortToast("用户账号或密码为空");
                 }
                 else{
                     //调用p接口
                     login.Login(inputEmail.getText().toString(), inputPassword.getText().toString());

//                     loginUser(inputEmail.getText().toString(),inputPassword.getText().toString());
                     
                 }
//                 startActivity(new Intent(getActivity(), HomeActivity.class));
//                 getActivity().finish();
             }
         });


        qqLoginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthorizedLoginUtils.authorzedQQ(getActivity());
                Toast.makeText(getActivity(),"请稍等",Toast.LENGTH_SHORT).show();

            }
        });

        wxLoginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthorizedLoginUtils.authorzedWechat(getActivity());
                showShortToast("请稍等");
            }
        });

        wbLoginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthorizedLoginUtils.authorzedSina(getActivity());
                showShortToast("请稍等");
//                getActivity().startActivity(new Intent(getActivity(),HomeActivity.class));
//                getActivity().finish();
                
            }
        });


    }


    @Override
    public void OnLoginSuccess(String result) {
        ToastUtils.showTextToast("欢迎 "+result, getActivity());
        getActivity().finish();

    }

    @Override
    public void OnLoginFailed(String result) {
        ToastUtils.showTextToast(result, getActivity());
    }

    @Override
    public void OnResigterSuccess(String result) {

    }
}
