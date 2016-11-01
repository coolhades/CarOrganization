package cn.automooc.com.presenter;

/**
 * Created by Hades on 16/9/29.
 *
 */

public interface ILoginPresenter {
    void Login(final String account, final String passwd);
    void Register(final String account, final String passwd, final String vcode);
    void FetchCode(final String mobile);
}
