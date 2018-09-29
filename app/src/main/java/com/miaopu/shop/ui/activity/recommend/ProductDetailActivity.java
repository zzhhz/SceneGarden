package com.miaopu.shop.ui.activity.recommend;

import android.content.Intent;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.miaopu.shop.ShopApplication;
import com.miaopu.shop.ui.activity.LoginActivity;
import com.miaopu.shop.ui.adapter.SingleImageAdapter;
import com.miaopu.shop.ui.adapter.ViewPagerAdapter;
import com.miaopu.shop.ui.base.BaseStoreFragment;
import com.miaopu.shop.ui.base.BaseSwipeActivity;
import com.miaopu.shop.ui.model.Product;
import com.miaopu.shop.utils.Constants;
import com.miaopu.shop.utils.GlideUtils;
import com.miaopu.shop.utils.l;
import com.miaopu.shop.web.BaseHandleConsumer;
import com.miaopu.shop.web.RetrofitUtils;
import com.zzh.horizontal.recyclerview.CardScaleHelper;
import com.zzh.sexual.secret.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by user on 2018/1/17.
 *
 * @date: 2018/1/17
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 商品详情
 */
public class ProductDetailActivity extends BaseSwipeActivity {

    public static final String DATA_PRODUCT = "DATA_PRODUCT_ID";

    @BindView(R.id.tabLayout)
    public TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    public ViewPager mViewPagerDetail;
    @BindView(R.id.iv_background)
    public ImageView iv_background;

    public ViewPagerAdapter mPagerAdapter;
    ProductDetailFragment detailFragment;
    UserCommentFragment commentFragment;
    ProduceFragment mProduceFragment;
    String titles[] = {"图文详情", "产品参数", "用户评价"};
    SingleImageAdapter mAdsAdapter;
    CardScaleHelper mCardAdapterHelper;
    @BindView(R.id.tv_price)
    TextView price;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_tag)
    TextView tv_style;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private String extra;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mCardAdapterHelper = new CardScaleHelper();
        Intent intent = getIntent();
        extra = intent.getStringExtra(DATA_PRODUCT);
        List<BaseStoreFragment> listDetail = new ArrayList<>();
        detailFragment = new ProductDetailFragment();
        commentFragment = new UserCommentFragment();
        commentFragment.setId(extra);
        mProduceFragment = new ProduceFragment();
        listDetail.add(detailFragment);
        listDetail.add(mProduceFragment);
        listDetail.add(commentFragment);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), listDetail);
        mViewPagerDetail.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPagerDetail);
        int tabCount = mTabLayout.getTabCount();

        for (int i = 0; i < tabCount; i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setText(titles[i]);
        }

    }

    @Override
    protected void initData() {
        showDialog();

        l.d(extra);
        RetrofitUtils.Api().getProductDetail(extra).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseHandleConsumer<Product>() {
            @Override
            public void onSuccess(Product model) {
                l.d(model.toString());
                GlideUtils.loadImageView(mContext, model.getDefaultImage(), iv_background, R.mipmap.ic_launcher);
                detailFragment.setContentData(model.getContent());
                mProduceFragment.bindData(model.getTitle(), model.getColor(),model.getShelvesTime(),
                        model.getFlowerTime(), model.getBotanyVariety(), model.getBotanyCategory(),
                        model.getBotanyFunction(),model.getNorms(),
                        model.getFitPosition(), model.getFitRegion(), model.getFitSeason(),
                        model.getMoveDifficulty());
                price.setText("￥ " + model.getPrice());
                tv_title.setText(model.getTitle());
                String style = model.getCategoryNamePtah();
                if (!TextUtils.isEmpty(style) && style.contains("/")) {
                    style = style.replace("/", " * ");
                }
                tv_style.setText(style);
                dismissDialog();
            }

            @Override
            public void onFailed(String code, String message) {
                dismissDialog();
            }
        });

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

    @OnClick({R.id.ic_back, R.id.tv_add_car, R.id.fab})
    public void onClickView(View v) {
        switch (v.getId()) {
            case R.id.ic_back:
                finish();
                break;
            case R.id.tv_add_car:
                if (!TextUtils.isEmpty(ShopApplication.getToken())) {
                    addCar();
                } else {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.fab:
                break;
        }
    }

    private void addCar() {
        Map<String, String> params = new HashMap<>(4);
        params.put("productId", extra);
        params.put("number", "1");
        RetrofitUtils.Api().addShopCar(1, extra).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseHandleConsumer<String>() {
            @Override
            public void onSuccess(String model) {
                showMessage("添加购物车成功");
                EventBus.getDefault().post(Constants.EVENT_REFRESH_SHOP_CAR);
            }

            @Override
            public void onFailed(String code, String message) {
                showMessage(message);
            }
        });

    }
}
