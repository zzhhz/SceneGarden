package com.miaopu.shop.ui.activity;

import android.graphics.Paint;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.miaopu.shop.ui.base.BaseSwipeActivity;
import com.miaopu.shop.utils.CountDownTimers;
import com.miaopu.shop.utils.Utils;
import com.miaopu.shop.utils.l;
import com.miaopu.shop.web.BaseConsumer;
import com.miaopu.shop.web.BaseHandleConsumer;
import com.miaopu.shop.web.RetrofitUtils;
import com.zzh.sexual.secret.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends BaseSwipeActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.tv_stroll)
    TextView tvStroll;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private CountDownTimers mDownTimers;
    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvStroll.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    protected void initData() {
        mDownTimers = new CountDownTimers(tvGetCode);
    }

    @Override
    protected void initSetListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }

    @OnClick({R.id.iv_back, R.id.tv_get_code, R.id.tv_stroll, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                RegisterActivity.this.finish();
                break;
            case R.id.tv_get_code:
                if (!"".equals(etPhoneNum.getText().toString()) && Utils.isMobileNO(etPhoneNum.getText().toString())) {
                    getObtain();
                } else {
                    showMessage("请输入正确的手机号码");
                }
                break;
            case R.id.tv_stroll:
                RegisterActivity.this.finish();
                break;
            case R.id.btn_login:
                register();
                break;
        }
    }

    private void register() {
        showDialog();
        Map<String, String> params = new HashMap<>(8);
        params.put("mobile", etPhoneNum.getText().toString());
        params.put("validCode", etCode.getText().toString());
        RetrofitUtils.Api().register(RetrofitUtils.map2Params(params))
                     .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                     .subscribe(new BaseHandleConsumer<String>() {
                         @Override
                         public void onSuccess(String model) {
                             dismissDialog();
                             l.d(model);
                             showMessage("注册成功");
                             finish();
                         }

                         @Override
                         public void onFailed(String code, String message) {
                             dismissDialog();
                             showMessage(message);
                         }
                     });

    }

    /**
     * 获取验证码
     */
    private void getObtain() {
        showDialog();
        Map<String, String> map = new HashMap<>(2);
        map.put("mobile", etPhoneNum.getText().toString());
        map.put("type", "register");
        RetrofitUtils.Api().getCode(RetrofitUtils.map2Params(map)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseHandleConsumer<String>() {
            @Override
            public void onSuccess(String model) {
                l.e("验证码 code ：" + model);
                dismissDialog();
                mDownTimers.start();
            }

            @Override
            public void onFailed(String code) {
                dismissDialog();
                showMessage("获取验证码失败");

            }
        }, new BaseConsumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismissDialog();
            }
        });
    }
}
