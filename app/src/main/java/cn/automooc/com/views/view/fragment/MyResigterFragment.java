package cn.automooc.com.views.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.automooc.com.BaseFragment;
import cn.automooc.com.R;
import cn.automooc.com.presenter.ILoginPresenter;
import cn.automooc.com.presenter.LoginPresenter;
import cn.automooc.com.ui.HomeActivity;
import cn.automooc.com.utils.AuthorizedLoginUtils;
import cn.automooc.com.utils.CodeUtils;
import cn.automooc.com.utils.RegularUtils;
import cn.automooc.com.utils.ToastUtils;
import cn.automooc.com.views.viewinterface.IOnLogin;

/**
 * Created by jiuzheyange on 2016/8/10.
 */
public class MyResigterFragment extends BaseFragment implements IOnLogin {

    View mView;
    TextView nextBt;
    
    ImageView qqLoginBt;
    ImageView wxLoginBt;
    ImageView wbLoginBt;
    
    EditText inputEmail;
    EditText inputPassword1;
    EditText inputPassword2;
    ILoginPresenter registerInterface;
    ImageView code_iv;
    Button change_code;
    Button runcode_bt;
    EditText input_code;
    EditText input_runcode;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     
        mView=inflater.inflate(R.layout.register_fragment,null);
        
        initView();
        initData();
        initEvent();

        return mView;
        
    }

    @Override
    protected void initView() {
        registerInterface = new LoginPresenter(getActivity(), this);

        nextBt= (TextView) mView.findViewById(R.id.next_bt);
        qqLoginBt= (ImageView) mView.findViewById(R.id.qq_login_bt);
        wxLoginBt= (ImageView) mView.findViewById(R.id.wx_login_bt);
        wbLoginBt= (ImageView) mView.findViewById(R.id.wb_login_bt);
        
        inputEmail= (EditText) mView.findViewById(R.id.input_email);
        inputPassword1= (EditText) mView.findViewById(R.id.input_password1);
        inputPassword2= (EditText) mView.findViewById(R.id.input_password2);

        code_iv = (ImageView) mView.findViewById(R.id.code_iv);
        change_code = (Button) mView.findViewById(R.id.change_code);
        runcode_bt = (Button) mView.findViewById(R.id.runcode_bt);
        input_code = (EditText) mView.findViewById(R.id.input_code);
        input_runcode = (EditText) mView.findViewById(R.id.input_runcode);

    }

    @Override
    protected void initData() {
        code_iv.setImageBitmap(CodeUtils.getInstance().createBitmap());

    }

    @Override
    protected void initEvent() {

        runcode_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (! input_code.getText().toString().trim().equalsIgnoreCase(CodeUtils.getInstance().getmCode())){
                    ToastUtils.showTextToast("静态验证码错误!", getActivity());
                    return;
                }
                //获取验证码
//                registerInterface.fetchCode();
            }
        });

        change_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code_iv.setImageBitmap(CodeUtils.getInstance().createBitmap());
            }
        });

        nextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (inputEmail.getText().toString().trim().isEmpty()){
//                    ToastUtils.showTextToast("请输入手机号!", getActivity());
//                    return;
//                }
//                if (! input_code.getText().toString().trim().equalsIgnoreCase(CodeUtils.getInstance().getmCode())){
//                    ToastUtils.showTextToast("静态验证码错误!", getActivity());
//                    return;
//                }
//                //动态验证码
//                if (TextUtils.isEmpty(input_runcode.getText().toString().trim())){
//                    ToastUtils.showTextToast("请输入动态验证码!", getActivity());
//                    return;
//                }
//                if (inputPassword1.getText().toString().isEmpty()){
//                    ToastUtils.showTextToast("请输入密码!", getActivity());
//                    return;
//                }
//                if (inputPassword2.getText().toString().isEmpty()){
//                    ToastUtils.showTextToast("请确认密码!", getActivity());
//                    return;
//                }
//                if (! ValidateUtils.isMobileNO(TrimNumberUtils.trimTelNum(inputEmail.getText().toString())  )){
//                    ToastUtils.showTextToast("请检查手机号码是否有误!", getActivity());
//                    return;
//                }
//                if (!((inputPassword1.getText().toString()).equals(inputPassword2.getText().toString()))){
//                    ToastUtils.showTextToast("两次密码输入不一致!", getActivity());
//                    return;
//                }
//
//                if((RegularUtils.emailValidation(inputEmail.getText().toString()))&&(inputPassword1.getText().toString()).equals(inputPassword2.getText().toString()))
//                {
//                    ToastUtils.showTextToast("请稍等...", getActivity());
////                        resigterUser(inputEmail.getText().toString(),inputPassword1.getText().toString());
//                    registerInterface.Register(inputEmail.getText().toString(), inputPassword1.getText().toString());
//
//                }



              //  startActivity(new Intent(getActivity(), SetPasswordActivity.class));
                //提交注册时检查邮箱格式，密码是否一致
                if((inputEmail.getText().toString().length()>0)&&(inputPassword1.getText().toString().length()>0)&&(inputPassword2.getText().toString().length()>0))
                {
                   if(!(RegularUtils.emailValidation(inputEmail.getText().toString())))
                   {
                      showShortToast("请检查邮箱格式是否有误");
                   }
                    else if(!((inputPassword1.getText().toString()).equals(inputPassword2.getText().toString())))
                    {

                        showShortToast("两次密码输入不一致");
                    }
                    if((RegularUtils.emailValidation(inputEmail.getText().toString()))&&(inputPassword1.getText().toString()).equals(inputPassword2.getText().toString()))
                    {

                        showShortToast("请稍等");

//                        resigterUser(inputEmail.getText().toString(),inputPassword1.getText().toString());
                        registerInterface.Register(inputEmail.getText().toString(), inputPassword1.getText().toString());

                    }

                }
                else
                {
                    showShortToast("邮箱或密码为空");
                }
                
                
            }
        });


        qqLoginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"请稍等",Toast.LENGTH_SHORT).show();
                AuthorizedLoginUtils.authorzedQQ(getActivity());
               
                
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
            }
        });
    }


    @Override
    public void OnLoginSuccess(String result) {

    }

    @Override
    public void OnLoginFailed(String result) {
        ToastUtils.showTextToast(result, getActivity());
    }

    @Override
    public void OnResigterSuccess(String result) {
        ToastUtils.showTextToast(result, getActivity());
        getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }
}
