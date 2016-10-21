package com.hades.quicklibrary.ui.interf;

/**
 * Created by Hades on 16/10/12.
 */

public interface ILogin extends IRootView {
    void onLoginSuccess(String s);
    void onLoginError(String s);
}
