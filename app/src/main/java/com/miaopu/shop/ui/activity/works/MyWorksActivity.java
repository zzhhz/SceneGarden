package com.miaopu.shop.ui.activity.works;

import android.content.Intent;
import android.graphics.Color;
import android.os.Message;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.miaopu.shop.ShopApplication;
import com.miaopu.shop.ui.activity.works.adapter.CaseStaggerAdapter;
import com.miaopu.shop.ui.base.BaseSwipeActivity;
import com.miaopu.shop.ui.model.Works;
import com.miaopu.shop.ui.model.WrapperModel;
import com.miaopu.shop.utils.Constants;
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

public class MyWorksActivity extends BaseSwipeActivity implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.recyclerView)
    LRecyclerView recyclerView;
    @BindView(R.id.tv_create)
    TextView tv_create;
    private LRecyclerViewAdapter mAdapter;
    private CaseStaggerAdapter mStaggerAdapter;
    private int page = 1;
    private boolean hasNextPage = false;
    private String type;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_my_works;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mStaggerAdapter = new CaseStaggerAdapter(mContext);
        type = intent.getStringExtra("type");
        tv_create.setVisibility(View.VISIBLE);
        if (type != null) {
            if ("0102".equals(type)) {
                initToolbar(R.string.my_design, Color.parseColor("#020202"));
                mStaggerAdapter.setIsWorks(true);
                tv_create.setVisibility(View.VISIBLE);//设计师显示创建作品
            } else if ("0103".equals(type)) {
                initToolbar(R.string.my_case, Color.parseColor("#020202"));
                mStaggerAdapter.setIsWorks(false);
            }
        }

        mAdapter = new LRecyclerViewAdapter(mStaggerAdapter);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setOnRefreshListener(this);
        recyclerView.setOnLoadMoreListener(this);
    }

    @Override
    protected void initData() {
        recyclerView.forceToRefresh();
    }

    private void reloadData() {
        if (ShopApplication.getCurrentUser().getType() != null) {
            if ("0102".equals(ShopApplication.getCurrentUser().getType())) {
                Map<String, String> params = new HashMap<>();
                params.put("pageNo", page + "");
                params.put("pageSize", page * 3 + "");
                RetrofitUtils.Api().getQueryMyWorks(page, Constants.PAGE_SIZE, RetrofitUtils.map2Params(params)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        new BaseHandleConsumer<WrapperModel<Works>>() {
                            @Override
                            public void onSuccess(WrapperModel<Works> model) {
                                if (model != null && model.getList() != null && !model.getList().isEmpty()) {
                                    mStaggerAdapter.addAll(model.getList());
                                    mAdapter.notifyDataSetChanged();
                                } else {
                                    showMessage("无数据");
                                }
                                hasNextPage = model.isHasNextPage();
                                recyclerView.refreshComplete(Constants.PAGE_SIZE);
                            }

                            @Override
                            public void onFailed(String code, String message) {
                                Utils.toastTips(mContext, message);
                                recyclerView.refreshComplete(Constants.PAGE_SIZE);

                            }
                        }, new BaseConsumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                l.d(throwable.getMessage());
                            }
                        }
                );
            } else if ("0103".equals(ShopApplication.getCurrentUser().getType())) {
                Map<String, String> params = new HashMap<>();
                params.put("pageNo", page + "");
                params.put("pageSize", page * 3 + "");
                RetrofitUtils.Api().getMyCase(page, Constants.PAGE_SIZE, RetrofitUtils.map2Params(params)).
                        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        new BaseHandleConsumer<WrapperModel<Works>>() {
                            @Override
                            public void onSuccess(WrapperModel<Works> model) {
                                if (model != null && model.getList() != null && !model.getList().isEmpty()) {
                                    mStaggerAdapter.addAll(model.getList());
                                    mAdapter.notifyDataSetChanged();
                                } else {
                                    showMessage("无数据");
                                }
                                hasNextPage = model.isHasNextPage();
                                recyclerView.refreshComplete(Constants.PAGE_SIZE);
                            }

                            @Override
                            public void onFailed(String code, String message) {
                                Utils.toastTips(mContext, message);
                                recyclerView.refreshComplete(Constants.PAGE_SIZE);

                            }
                        }, new BaseConsumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                l.d(throwable.getMessage());
                            }
                        }
                );
            }
        }

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

    @Override
    public void onLoadMore() {
        if (hasNextPage) {
            page++;
            reloadData();
        } else {
            recyclerView.refreshComplete(Constants.PAGE_SIZE);
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        mStaggerAdapter.clear();
        reloadData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 112 && resultCode == RESULT_OK) {
            page = 1;
            mStaggerAdapter.clear();
            reloadData();
        }
    }

    @OnClick(R.id.tv_create)
    public void onViewClicked() {


        if ("0102".equals(type)) {
            Intent intent = new Intent(this, CreateWorksActivity.class);
            startActivityForResult(intent, 112);
        } else if ("0103".equals(type)) {
            Intent intent = new Intent(this, CreateCaseActivity.class);
            startActivityForResult(intent, 112);
        }
    }
}
