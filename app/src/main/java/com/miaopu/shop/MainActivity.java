package com.miaopu.shop;

import android.content.Intent;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;

import com.miaopu.shop.ui.activity.LoginActivity;
import com.miaopu.shop.ui.adapter.ViewPagerAdapter;
import com.miaopu.shop.ui.base.BaseShopActivity;
import com.miaopu.shop.ui.base.BaseStoreFragment;
import com.miaopu.shop.ui.fragment.DIYFragment;
import com.miaopu.shop.ui.fragment.HomeFragment;
import com.miaopu.shop.ui.fragment.MineFragment;
import com.miaopu.shop.ui.fragment.ProductFragment;
import com.miaopu.shop.utils.Utils;
import com.zzh.sexual.secret.R;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZZH on 2017/12/22
 *
 * @date: 2017/12/22 下午3:34
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @Description: 主页面框架
 */
public class MainActivity extends BaseShopActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.viewPager)
    public ViewPager mViewPager;
    @BindView(R.id.rg_home_tab)
    public RadioGroup mRadioGroup;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ShopApplication.addActivity(this);
        ButterKnife.bind(this);
        mRadioGroup.setOnCheckedChangeListener(this);
        List<BaseStoreFragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new ProductFragment());
        list.add(new DIYFragment());
        list.add(new MineFragment());
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), list));
        mViewPager.addOnPageChangeListener(this);
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
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                mViewPager.setCurrentItem(0, true);
                break;
            case R.id.rb_product:
                mViewPager.setCurrentItem(1, true);
                break;
            case R.id.rb_diy:
                //token为空，跳出登录页面
                /*if (TextUtils.isEmpty(ShopApplication.getToken())) {
                    startActivityForResult(new Intent(this, LoginActivity.class), 100);
                }else {*/
                mViewPager.setCurrentItem(2, true);
                //}
                break;
            case R.id.rb_shop_car:
                //token为空，跳出登录页面
                if (ShopApplication.getCurrentUser() == null) {
                    startActivityForResult(new Intent(this, LoginActivity.class), 101);
                } else {
                    mViewPager.setCurrentItem(3, true);
                }
                break;
            case R.id.rb_mine:
                //token为空，跳出登录页面
                if (ShopApplication.getCurrentUser() == null) {
                    startActivityForResult(new Intent(this, LoginActivity.class), 102);
                } else {
                    mViewPager.setCurrentItem(4, true);
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (RESULT_OK == resultCode) {
            switch (requestCode) {
                case 100:
                    mViewPager.setCurrentItem(2);
                    break;
                case 101:
                    mViewPager.setCurrentItem(3);
                    break;
                case 102:
                    mViewPager.setCurrentItem(4);
                    break;
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        switch (position) {
            case 0:
                mRadioGroup.check(R.id.rb_home);
                break;
            case 1:
                mRadioGroup.check(R.id.rb_product);
                break;
            case 2:
                mRadioGroup.check(R.id.rb_diy);
                //}
                break;
            case 3:
                //token为空，跳出登录页面
                if (ShopApplication.getCurrentUser() == null) {
                    startActivityForResult(new Intent(this, LoginActivity.class), 101);
                } else {
                    mRadioGroup.check(R.id.rb_shop_car);
                }
                break;
            case 4://token为空，跳出登录页面
                if (ShopApplication.getCurrentUser() == null) {
                    startActivityForResult(new Intent(this, LoginActivity.class), 102);
                } else {
                    mRadioGroup.check(R.id.rb_mine);
                }
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 两次点击的间隔时间
     */
    private long waitTime = 1000;
    private long touchTime = 0;

    /**
     * 退出应用提示
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 两次返回键，退出程序
        if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - touchTime) >= waitTime) {
                Utils.toastTips(this, "再按一次退应用");
                touchTime = currentTime;
            } else {
                ShopApplication.exitActivity();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
