package com.miaopu.shop.ui.activity.recommend;

import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.miaopu.shop.ui.activity.recommend.adapter.UserCommentAdapter;
import com.miaopu.shop.ui.base.BaseStoreFragment;
import com.miaopu.shop.ui.model.Comment;
import com.miaopu.shop.ui.model.WrapperModel;
import com.miaopu.shop.utils.Constants;
import com.miaopu.shop.utils.l;
import com.miaopu.shop.web.BaseHandleConsumer;
import com.miaopu.shop.web.RetrofitUtils;
import com.zzh.sexual.secret.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by user on 2018/1/17.
 *
 * @date: 2018/1/17
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 用户评价
 */
public class UserCommentFragment extends BaseStoreFragment {
    @BindView(R.id.recyclerView)
    public LRecyclerView mRecyclerView;
    LRecyclerViewAdapter mAdapter;
    private UserCommentAdapter mCommentAdapter;
    private LinearLayoutManager mLayoutManager;

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_user_comment;
    }

    @Override
    protected void initView(View fragment) {
        ButterKnife.bind(this, fragment);

        mLayoutManager = new LinearLayoutManager(mContext);
        mCommentAdapter = new UserCommentAdapter(mContext);
        mAdapter = new LRecyclerViewAdapter(mCommentAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNoMore(true);
        mRecyclerView.setPullRefreshEnabled(false);
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

    @Override
    protected void lazyLoad() {
        if (!TextUtils.isEmpty(id)) {
            reloadComment(id);
        }
    }

    public void reloadComment(String targetId) {
        Map<String, String> map = new HashMap<>(2);
        map.put("targetId", targetId);
        RetrofitUtils.Api().getProductComment(1, Constants.PAGE_SIZE * 4, RetrofitUtils.map2Params(map)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseHandleConsumer<WrapperModel<Comment>>() {
            @Override
            public void onSuccess(WrapperModel<Comment> model) {
                if (model != null && model.getList() != null && !model.getList().isEmpty()) {
                    bindData(model);
                } else {
                    l.d("failed");
                }
            }
        });
    }

    public void bindData(WrapperModel<Comment> model) {
        if (mCommentAdapter != null) {
            mCommentAdapter.clear();
            mCommentAdapter.addAll(model.getList());
            mAdapter.notifyDataSetChanged();
        }
        l.d(Arrays.toString(model.getList().toArray()));
    }
}
