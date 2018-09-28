package com.miaopu.shop.ui.activity.designer;

import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.miaopu.shop.ShopApplication;
import com.miaopu.shop.ui.activity.designer.adapter.DesignWorkAdapter;
import com.miaopu.shop.ui.base.BaseStoreFragment;
import com.miaopu.shop.ui.model.Works;
import com.miaopu.shop.ui.model.WrapperModel;
import com.miaopu.shop.utils.Constants;
import com.miaopu.shop.utils.l;
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
 * Created by user on 2018/1/11.
 *
 * @date: 2018/1/11
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 作品列表
 */
public class WorksFragment extends BaseStoreFragment {
    @BindView(R.id.recyclerView)
    public RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private DesignWorkAdapter mWorkAdapter;

    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_works;
    }

    @Override
    protected void initView(View fragment) {
        ButterKnife.bind(this, fragment);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mWorkAdapter = new DesignWorkAdapter(mContext);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        mRecyclerView.setAdapter(mWorkAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setViewListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    /**
     * 作品
     *
     * @param id
     */
    public void queryMyDesignByPage(String id) {
        if (TextUtils.isEmpty(ShopApplication.getToken())) {
            return;
        }
        Map<String, String> params = new HashMap<>(2);
        params.put("memberId", id);
        params.put("state", "success");
        l.d("id: " + id);
        RetrofitUtils.Api().queryMyDesignByPage(1, Constants.PAGE_SIZE * 10, RetrofitUtils.map2Params(params)).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseHandleConsumer<WrapperModel<Works>>() {
            @Override
            public void onSuccess(WrapperModel<Works> model) {
                if (model.getList() != null && !model.getList().isEmpty()) {
                    mWorkAdapter.setOnClickItemEnable(true);
                    mWorkAdapter.addAll(model.getList());
                    mWorkAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * 案例
     *
     * @param id
     */
    public void queryByPageWithMap(String id) {
        if (TextUtils.isEmpty(ShopApplication.getToken())) {
            return;
        }
        Map<String, String> params = new HashMap<>(2);
        params.put("memberId", id);
        params.put("state", "success");
        l.d("id: " + id);
        RetrofitUtils.Api().queryByPageWithMap(1, Constants.PAGE_SIZE * 10, RetrofitUtils.map2Params(params)).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseHandleConsumer<WrapperModel<Works>>() {
            @Override
            public void onSuccess(WrapperModel<Works> model) {
                if (model.getList() != null && !model.getList().isEmpty()) {
                    mWorkAdapter.setOnClickItemEnable(false);
                    mWorkAdapter.addAll(model.getList());
                    mWorkAdapter.notifyDataSetChanged();

                }
            }
        });
    }
}
