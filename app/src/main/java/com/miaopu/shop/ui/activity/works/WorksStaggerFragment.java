package com.miaopu.shop.ui.activity.works;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.miaopu.shop.ui.activity.works.adapter.WorksStaggerAdapter;
import com.miaopu.shop.ui.base.BaseStoreFragment;
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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by user on 2018/1/16.
 *
 * @date: 2018/1/16
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 作品列表，瀑布流形式
 */
public class WorksStaggerFragment extends BaseStoreFragment implements OnRefreshListener, OnLoadMoreListener {
    public static final String KEY_DATA = "KEY_DATA";

    @BindView(R.id.recyclerView)
    public LRecyclerView recyclerView;
    private LRecyclerViewAdapter mAdapter;
    private WorksStaggerAdapter mStaggerAdapter;
    private StaggeredGridLayoutManager mLayoutManager;
    private String title;
    private int page = 1;
    private boolean hasNextPage = false;


    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_works_stagger;
    }

    @Override
    protected void initView(View fragment) {
        ButterKnife.bind(this, fragment);
        Bundle arguments = getArguments();
        title = arguments.getString(KEY_DATA, "全部");
        mStaggerAdapter = new WorksStaggerAdapter(mContext);
        mAdapter = new LRecyclerViewAdapter(mStaggerAdapter);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setOnRefreshListener(this);
        recyclerView.setOnLoadMoreListener(this);
    }

    @Override
    protected void initData() {
        recyclerView.forceToRefresh();
    }

    @Override
    public void setViewListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onRefresh() {
        page = 1;
        mStaggerAdapter.clear();
        mStaggerAdapter.notifyDataSetChanged();
        reloadData();
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

    private void reloadData() {
        Map<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(title)) {
            params.put("tag", title);
            l.d(title);
            params.put("state", "success");
        }
        RetrofitUtils.Api().getQueryWorks(page, Constants.PAGE_SIZE * 3, RetrofitUtils.map2Params(params)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new BaseHandleConsumer<WrapperModel<Works>>() {
                    @Override
                    public void onSuccess(WrapperModel<Works> model) {
                        if (model != null && model.getList() != null && !model.getList().isEmpty()) {
                            mStaggerAdapter.addAll(model.getList());
                            mAdapter.notifyDataSetChanged();
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
        /*
        new BaseHandleConsumer<WrapperModel<Works>>() {
            @Override
            public void onSuccess(WrapperModel<Works> model) {
                if (model != null && model.getList() != null && !model.getList().isEmpty()) {
                    mStaggerAdapter.addAll(model.getList());
                    mAdapter.notifyDataSetChanged();
                }
                hasNextPage = model.isHasNextPage();
                recyclerView.refreshComplete(Constants.PAGE_SIZE);

            }

            @Override
            public void onFailed(String code, String message) {
                Utils.toastTips(mContext, message);
                recyclerView.refreshComplete(Constants.PAGE_SIZE);

            }
        }
        */
    }
}
