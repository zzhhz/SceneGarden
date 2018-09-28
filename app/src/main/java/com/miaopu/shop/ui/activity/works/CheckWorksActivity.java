package com.miaopu.shop.ui.activity.works;

import android.content.Intent;
import android.graphics.Color;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.miaopu.shop.ui.activity.works.adapter.WorksAdapter;
import com.miaopu.shop.ui.base.BaseShopActivity;
import com.miaopu.shop.ui.base.BaseSwipeActivity;
import com.miaopu.shop.ui.model.Condition2;
import com.miaopu.shop.ui.model.Product;
import com.miaopu.shop.ui.model.ProductCheck;
import com.miaopu.shop.ui.model.WrapperModel;
import com.miaopu.shop.utils.Constants;
import com.miaopu.shop.utils.Utils;
import com.miaopu.shop.utils.l;
import com.miaopu.shop.web.BaseHandleConsumer;
import com.miaopu.shop.web.RetrofitUtils;
import com.zzh.sexual.secret.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CheckWorksActivity extends BaseSwipeActivity implements OnLoadMoreListener, OnRefreshListener{

    @BindView(R.id.tv_create)
    TextView tvCreate;
    @BindView(R.id.recyclerView)
    LRecyclerView mRecyclerView;

    private LRecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private WorksAdapter mListAdapter;
    private int page = 1;

    private boolean hasNextPage = false;
    private List<ProductCheck> productChecks = new ArrayList();
    @Override
    protected int setLayoutId() {
        return R.layout.activity_check_works;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initToolbar(R.string.my_design, Color.parseColor("#020202"));
        mListAdapter = new WorksAdapter(mContext);
        mLayoutManager = new LinearLayoutManager(mContext);
        mAdapter = new LRecyclerViewAdapter(mListAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnLoadMoreListener(this);
        mRecyclerView.setOnRefreshListener(this);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (list.get(position).getIsSelected() == 0) {
                    list.get(position).setIsSelected(1);

                } else if (list.get(position).getIsSelected() == 1) {
                    list.get(position).setIsSelected(0);
                }
                mListAdapter.clear();
                mListAdapter.addAll(list);
                mListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void initData() {
        mRecyclerView.forceToRefresh();
    }

    @Override
    protected void initSetListener() {

    }
    @Override
    public void onLoadMore() {
        if (hasNextPage) {
            page++;
            reloadData();
        } else {
            mRecyclerView.refreshComplete(Constants.PAGE_SIZE);
        }

    }

    @Override
    public void onRefresh() {
        page = 1;
        if (page == 1) {
            if (mContext instanceof BaseSwipeActivity) {
                ((BaseSwipeActivity) mContext).showDialog();
            } else if (mContext instanceof BaseShopActivity) {
                ((BaseShopActivity) mContext).showDialog();
            }
        }
        mListAdapter.clear();
        mAdapter.notifyDataSetChanged();
        reloadData();

    }

    private List<Product> list = new ArrayList<>();
    private void reloadData() {
        Condition2 condition2 = new Condition2();
        condition2.setTitle("");
        String content = JSON.toJSONString(condition2);
        l.d(content);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), content);
        RetrofitUtils.Api().getQueryProducts(1, Constants.PAGE_SIZE , body).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseHandleConsumer<WrapperModel<Product>>() {
            @Override
            public void onSuccess(WrapperModel<Product> model) {
                if (model != null && model.getList() != null && !model.getList().isEmpty()) {
                    if (model.isIsFirstPage()) {
                        mListAdapter.clear();
                        list.clear();
                    }
                    list.addAll(model.getList());

                    Intent intent = getIntent();
                    if(intent.getSerializableExtra("data") != null){
                        productChecks = (List<ProductCheck>) intent.getSerializableExtra("data");
                        for(ProductCheck productCheck : productChecks){
                            for(Product product : list){
                                if(productCheck.getProductId().equals(product.getId())){
                                    product.setIsSelected(1);
                                }
                            }
                        }
                    }
                    mListAdapter.addAll(model.getList());
                    mAdapter.notifyDataSetChanged();
                }
                hasNextPage = model.isHasNextPage();
                mRecyclerView.refreshComplete(Constants.PAGE_SIZE);
                if (mContext instanceof BaseSwipeActivity) {
                    ((BaseSwipeActivity) mContext).dismissDialog();
                } else if (mContext instanceof BaseShopActivity) {
                    ((BaseShopActivity) mContext).dismissDialog();
                }
            }

            @Override
            public void onFailed(String code, String message) {
                mAdapter.notifyDataSetChanged();
                Utils.toastTips(mContext, message);
                mRecyclerView.refreshComplete(Constants.PAGE_SIZE);
                if (mContext instanceof BaseSwipeActivity) {
                    ((BaseSwipeActivity) mContext).dismissDialog();
                } else if (mContext instanceof BaseShopActivity) {
                    ((BaseShopActivity) mContext).dismissDialog();
                }
            }
        });
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }

    @OnClick(R.id.tv_create)
    public void onViewClicked() {
        ProductCheck productCheck;
        productChecks.clear();
        for(Product product : list){
            if(product.getIsSelected() == 1){
                productCheck = new ProductCheck();
                productCheck.setNum("1");
                productCheck.setProductId(product.getId());
                productCheck.setTitle(product.getTitle());
                productChecks.add(productCheck);
            }
        }
        if(productChecks == null || productChecks.size() < 1){
            setResult(RESULT_OK);
            finish();
        }else {
            Intent intent = new Intent();
            intent.putExtra("list", (Serializable) productChecks);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
