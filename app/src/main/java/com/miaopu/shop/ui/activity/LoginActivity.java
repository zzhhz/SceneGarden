package com.miaopu.shop.ui.activity;

import android.Manifest;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.luck.picture.lib.permissions.RxPermissions;
import com.miaopu.shop.ShopApplication;
import com.miaopu.shop.ui.base.BaseSwipeActivity;
import com.miaopu.shop.ui.model.User;
import com.miaopu.shop.utils.EventUtils;
import com.miaopu.shop.utils.SpUtils;
import com.zzh.sexual.secret.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends BaseSwipeActivity {
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.btn_login)
    Button btnLogin;
    RxPermissions mPermissions;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mPermissions = new RxPermissions(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initSetListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View view) {

    }

    @OnClick({R.id.btn_login, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                setResult(RESULT_CANCELED);
                LoginActivity.this.finish();
                break;
            case R.id.btn_login:
                if (!TextUtils.isEmpty(etCode.getText().toString())) {
                    //login();
                    mPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Boolean aBoolean) {
                            if (aBoolean) {
                                login();
                            } else {
                                showMessage("请授予程序读写权限");
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
                } else {
                    showMessage("请输入验证码");
                }
                break;
        }
    }

    private void login() {
        showDialog();

        String name = etPhoneNum.getText().toString();
        String pwd = etCode.getText().toString();
        if (!"13006594053".equals(name)) {
            return;
        }
        User user = new User();
        user.setHeadImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538130862380&di=8376d86d75f785800d9020ae2198974a&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201407%2F16%2F20140716023921_u3juM.thumb.700_0.jpeg");
        user.setNickname("诸子百家");
        user.setMobile(name);
        user.setPassword(pwd);
        user.setLevel("100");
        user.setUsername(name);
        ShopApplication.setCurrentUser(user);
        SpUtils.saveLogin(this, user);
        EventUtils.sendEventLogin();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 两次返回键，退出程序
        if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            setResult(RESULT_CANCELED);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void scrollToFinishActivity() {
        setResult(RESULT_CANCELED);
        super.scrollToFinishActivity();
    }
}
