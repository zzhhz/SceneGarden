package com.miaopu.shop.ui.activity.example;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.miaopu.shop.ShopApplication;
import com.miaopu.shop.ui.activity.LoginActivity;
import com.miaopu.shop.ui.activity.recommend.adapter.UserCommentAdapter;
import com.miaopu.shop.ui.activity.works.adapter.SingleImage2Adapter;
import com.miaopu.shop.ui.adapter.HomeHorizontalAdsAdapter;
import com.miaopu.shop.ui.base.BaseSwipeActivity;
import com.miaopu.shop.ui.model.Comment;
import com.miaopu.shop.ui.model.Tags;
import com.miaopu.shop.ui.model.Works;
import com.miaopu.shop.ui.model.WrapperModel;
import com.miaopu.shop.ui.view.ActionSheetDialog;
import com.miaopu.shop.utils.Constants;
import com.miaopu.shop.utils.GlideUtils;
import com.miaopu.shop.utils.Utils;
import com.miaopu.shop.utils.l;
import com.miaopu.shop.web.BaseConsumer;
import com.miaopu.shop.web.BaseHandleConsumer;
import com.miaopu.shop.web.RetrofitUtils;
import com.zzh.sexual.secret.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by user.
 *
 * @date: 2018/3/12
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: MiaoPuShop
 * @since 1.0
 */
public class CaseDetailActivity extends BaseSwipeActivity {
    public static final int REQUEST_CODE_PAY = 2000;

    /**
     * 作品id
     */
    public static final String KEY_ID = "case_id";

    @BindView(R.id.iv_zoom)
    public ImageView iv_zoom;
    @BindView(R.id.iv_user_head)
    ImageView iv_user_head;
    @BindView(R.id.toolbar)
    public Toolbar sToolbar;

    @BindView(R.id.recyclerView_comment)
    public RecyclerView mRecyclerViewComment;
    @BindView(R.id.recyclerView)
    public RecyclerView attaches;
    private SingleImage2Adapter mSingleImageAdapter;

    private UserCommentAdapter mUserCommentAdapter;
    private LRecyclerViewAdapter mCommentAdapter;
    private HomeHorizontalAdsAdapter mHorizontalAdsAdapter;

    @BindView(R.id.tv_publish_time)
    TextView publishTime;
    @BindView(R.id.tv_user_name)
    TextView nickname;
    @BindView(R.id.tv_works_name)
    TextView workName;
    @BindView(R.id.tv_tag)
    TextView tv_tag;
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.tv_msg)
    TextView tv_msg;
    @BindView(R.id.tv_comment_count_2)
    TextView tv_comment_count_2;
    @BindView(R.id.tv_comment_count)
    TextView tv_comment_count;
    @BindView(R.id.tv_count_2)
    TextView tv_count_2;
    @BindView(R.id.tv_collect)
    TextView tv_collect;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;
    @BindView(R.id.et_comment)
    EditText et_comment;
    private String id;
    private Works mWorks;

    private String memberId;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_case_detail;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        sToolbar.setNavigationIcon(R.mipmap.ic_back);
        sToolbar.setTitle("案例详情");
        setSupportActionBar(sToolbar);


        mSingleImageAdapter = new SingleImage2Adapter(mContext, null);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        attaches.setLayoutManager(manager);
        attaches.setNestedScrollingEnabled(false);
        attaches.setAdapter(mSingleImageAdapter);
    }

    @Override
    protected void initData() {
        mUserCommentAdapter = new UserCommentAdapter(mContext);
        mCommentAdapter = new LRecyclerViewAdapter(mUserCommentAdapter);
        LinearLayoutManager commentManager = new LinearLayoutManager(mContext);
        mRecyclerViewComment.setLayoutManager(commentManager);
        mRecyclerViewComment.setAdapter(mUserCommentAdapter);
        //mHorizontalAdsAdapter = new HomeHorizontalAdsAdapter(mContext);


        Intent intent = getIntent();
        mWorks = intent.getParcelableExtra(KEY_ID);
        if (mWorks != null) {
            id = mWorks.getId();
            bindCase();
            RetrofitUtils.Api().getCaseDetail(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseHandleConsumer<Works>() {
                @Override
                public void onSuccess(Works model) {
                    memberId = model.getMemberId();
                    GlideUtils.loadImageView(mContext, model.getDefaultImg(), iv_zoom, R.mipmap.ic_launcher);
                    if (!TextUtils.isEmpty(model.getHeadImg())) {
                        GlideUtils.loadCircleImageView(mContext, model.getHeadImg(), iv_user_head, R.mipmap.ic_launcher);
                    }
                    publishTime.setText(Utils.formatDateTime(model.getUpdateTime()));
                    workName.setText(model.getTitle());
                    title.setText(model.getTitle());
                    nickname.setText(model.getNickname());
                    StringBuilder builder = new StringBuilder();
                    List<Tags> tags = model.getTags();
                    if (tags != null && tags.size() > 0) {
                        for (Tags t : tags) {
                            builder.append(t.getDicValue()).append(" | ");
                        }
                        if (builder.toString().endsWith("| ")) {
                            builder.deleteCharAt(builder.length() - 1);
                            builder.deleteCharAt(builder.length() - 1);
                        }
                        tv_tag.setText(builder.toString());
                    } else {
                        tv_tag.setVisibility(View.INVISIBLE);
                    }
                    sToolbar.setTitle(model.getTitle());
                    sToolbar.setTitleTextColor(Color.BLACK);
                    tv_msg.setText(model.getContent());
                    mSingleImageAdapter.addAll(model.getAttaches());
                    mSingleImageAdapter.notifyDataSetChanged();
                }
            });

        }
        getCommentData();
    }

    private void bindCase() {
        title.setText(mWorks.getTitle());
        nickname.setText(mWorks.getNickname());
        GlideUtils.loadImageView(mContext, mWorks.getDefaultImg(), iv_zoom, R.mipmap.ic_launcher);
        GlideUtils.loadCircleImageView(mContext, TextUtils.isEmpty(mWorks.getHeadImg()) ? mWorks.getDefaultImg() : mWorks.getHeadImg(), iv_user_head, R.mipmap.ic_launcher);

        publishTime.setText(Utils.formatDateTime(mWorks.getUpdateTime()));
        tv_msg.setText(mWorks.getContent());
    }

    private void getCommentData() {
        Map<String, String> params = new HashMap<>(2);
        params.put("targetId", id);
        RetrofitUtils.Api().getProductComment(1, Constants.PAGE_SIZE * 3, RetrofitUtils.map2Params(params)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseHandleConsumer<WrapperModel<Comment>>() {
            @Override
            public void onSuccess(WrapperModel<Comment> model) {
                mUserCommentAdapter.clear();
                mUserCommentAdapter.addAll(model.getList());
                mUserCommentAdapter.notifyDataSetChanged();
                tv_comment_count.setText(String.valueOf(model.getTotal()));
                tv_comment_count_2.setText(model.getTotal() + "条评论");
            }
        });
    }

    @Override
    protected void initSetListener() {

        et_comment.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                if (TextUtils.isEmpty(ShopApplication.getToken())) {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                }
            } else {
            }
        });
        et_comment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KeyEvent.ACTION_DOWN) {
                    sendComment(v.getText().toString());
                }
                et_comment.setText("");
                return false;
            }
        });
        sToolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }

    private void sendComment(String s) {
        if (TextUtils.isEmpty(s)) {
            showMessage("请输入评论内容");
            return;
        }
        showDialog();
        Map<String, String> params = new HashMap<>();
        params.put("targetId", id);
        params.put("content", s);
        params.put("type", "design");
        //params.put("images", "[]");
        RetrofitUtils.Api().sendComment(RetrofitUtils.map2Params(params)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseHandleConsumer<String>() {
            @Override
            public void onSuccess(String model) {
                hideKeyBroad();
                dismissDialog();
                getCommentData();
            }

            @Override
            public void onFailed(String code, String message) {
                hideKeyBroad();
                dismissDialog();
            }
        }, new BaseConsumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                l.d(throwable.getMessage());
                hideKeyBroad();
                dismissDialog();
            }
        });

    }

    private void hideKeyBroad() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//隐藏软键盘 //
        imm.hideSoftInputFromWindow(et_comment.getWindowToken(), 0);
//显示软键盘
        //imm.showSoftInputFromInputMethod(tv.getWindowToken(), 0);
        //切换软键盘的显示与隐藏
        //imm.toggleSoftInputFromWindow(tv.getWindowToken(), 0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }

    public ActionSheetDialog mSheetDialog;

    @OnClick({R.id.iv_fee, R.id.tv_one_key_order})
    public void onClickView(View v) {
        switch (v.getId()) {
            case R.id.iv_fee:
                sendComment(et_comment.getText().toString());
                et_comment.setText("");
                break;
            case R.id.tv_one_key_order:

                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_PAY:
                if (resultCode == RESULT_OK) {
                    showMessage("打赏成功");
                } else {
                    showMessage("打赏失败");
                }
                break;
        }
    }
}
