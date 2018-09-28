package com.miaopu.shop.ui.activity.fee;

import android.Manifest;
import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.luck.picture.lib.permissions.RxPermissions;
import com.zzh.sexual.secret.R;
import com.miaopu.shop.pay.PayEnum;
import com.miaopu.shop.pay.PayPresenter;
import com.miaopu.shop.ui.base.BasePayActivity;
import com.miaopu.shop.utils.l;
import com.miaopu.shop.web.RetrofitUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by user on 2018/2/23.
 *
 * @date: 2018/2/23
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 打赏页面
 */
public class FeeActivity extends BasePayActivity<PayPresenter> {

    public static final String KEY_PAY = "pay_way";
    public static final String KEY_FEE_TO_ID = "KEY_FEE_TO_ID";
    public static final String KEY_MEMBER_ID = "memberId";
    private PayEnum mPayEnum;

    @BindView(R.id.et_ps)
    public EditText et_ps;
    @BindView(R.id.et_fee)
    public EditText et_fee;
    private String memberId;
    private RxPermissions mPermissions;

    @BindView(R.id.iv_close)
    public ImageView iv_close;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_fee;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mPermissions = new RxPermissions(this);
        Intent intent = getIntent();
        memberId = intent.getStringExtra(KEY_MEMBER_ID);
        mPayHandler = mHandler;
        int intExtra = intent.getIntExtra(KEY_PAY, 0);
        if (intExtra == 0) {
            mPayEnum = PayEnum.AliPay;
        } else {
            mPayEnum = PayEnum.WxPay;
        }
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
    public void onClick(View v) {

    }

    @OnClick({R.id.btn_commit, R.id.iv_close})
    public void onClickView(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
                if (TextUtils.isEmpty(et_fee.getText().toString())) {
                    showMessage("请输入打赏金额");
                    break;
                }
                if (mPermissions.isGranted(Manifest.permission.READ_PHONE_STATE)) {

                    fee(et_fee.getText().toString());
                } else {
                    mPermissions.request(Manifest.permission.READ_PHONE_STATE).subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean flag) throws Exception {
                            if (flag) {
                                fee(et_fee.getText().toString());
                            } else {
                                showMessage("缺少必要的权限");
                            }

                        }
                    });
                }
                break;
            case R.id.iv_close:
                finish();
                break;
        }

    }

    /**
     * {
     * "memberId":"1",
     * "type":"mobile",
     * "remark":"备注",
     * "tradeNo":"123",
     * "fee":2.0
     * }
     *
     * @param s
     */
    private void fee(String s) {
        Map<String, String> params = new HashMap<>(4);
        params.put("memberId", memberId);
        params.put("type", "mobile");
        params.put("fee", s);
        params.put("remark", et_ps.getText().toString());
        params.put("tradeNo", "");
        RetrofitUtils.Api().fee(RetrofitUtils.map2Params(params)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(feeRespModel -> {
                    if (feeRespModel != null && feeRespModel.isSuccess()) {
                        presenter.pay(mPayEnum, FeeActivity.this, feeRespModel.getMessage(), Double.parseDouble(s));
                    } else {
                        showMessage(feeRespModel.getMessage());
                    }
                }, throwable -> {
                    l.d(throwable.getMessage());
                });
    }

    @Override
    protected void payResultCallback(PayEnum plat, boolean paySuccess, String resultCode) {

        l.d(paySuccess + ", " + resultCode);
        if (paySuccess) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
