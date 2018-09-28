package com.miaopu.shop.ui.activity.personal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.miaopu.shop.ShopApplication;
import com.miaopu.shop.ui.activity.LoginActivity;
import com.miaopu.shop.ui.activity.recommend.adapter.UserCommentAdapter;
import com.miaopu.shop.ui.base.BaseSwipeActivity;
import com.miaopu.shop.ui.model.Comment;
import com.miaopu.shop.ui.model.WrapperModel;
import com.miaopu.shop.utils.Constants;
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

public class MyWebViewActivity extends BaseSwipeActivity {
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.tv_comment_count_2)
    TextView tv_comment_count_2;
    @BindView(R.id.tv_send)
    TextView tv_send;
    @BindView(R.id.recyclerView_comment)
    public RecyclerView mRecyclerViewComment;
    @BindView(R.id.et_comment)
    EditText et_comment;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    private WebSettings settings;

    private UserCommentAdapter mUserCommentAdapter;
    private LRecyclerViewAdapter mCommentAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_my_web_view;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initToolbar(R.string.brand_story, Color.parseColor("#020202"));
        settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDatabaseEnabled(true);
        // 启用地理定位
        settings.setGeolocationEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
    }

    private String id;

    @Override
    protected void initData() {
        mRecyclerViewComment.setFocusable(false);
        mUserCommentAdapter = new UserCommentAdapter(mContext);
        mCommentAdapter = new LRecyclerViewAdapter(mUserCommentAdapter);
        LinearLayoutManager commentManager = new LinearLayoutManager(mContext){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecyclerViewComment.setLayoutManager(commentManager);
        mRecyclerViewComment.setAdapter(mUserCommentAdapter);

        Intent intent = getIntent();
        if (intent.getStringExtra("data") != null) {
            openWebView(webView, intent.getStringExtra("data"));
        }
        if (intent.getStringExtra("id") != null) {
            id = intent.getStringExtra("id");
            getCommentData();
        }
        if (intent.getStringExtra("num") != null) {
            tv_comment_count_2.setText(intent.getStringExtra("num") + " 次浏览");
        }
        scrollView.smoothScrollTo(0,0);
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
            }
        });
    }

    @Override
    protected void initSetListener() {

        et_comment.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                //got focus
                if (TextUtils.isEmpty(ShopApplication.getToken())) {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                }
            } else {
                //lost focus
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
    }

    private void sendComment(String s) {
        showDialog();
        Map<String, String> params = new HashMap<>();
        params.put("targetId", id);
        params.put("content", s);
        params.put("type", "article");
        RetrofitUtils.Api().sendComment(RetrofitUtils.map2Params(params)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseHandleConsumer<String>() {
            @Override
            public void onSuccess(String model) {
                hideKeyBroad();
                dismissDialog();
                et_comment.setText("");
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
    public void onClick(View view) {

    }

    private void openWebView(WebView web, String urlDate) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        web.loadDataWithBaseURL(null, getHtmlData(urlDate), "text/html", "utf-8", null);
        web.getSettings().setJavaScriptEnabled(false);
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head><style>img{max-width: 100%; width:auto; height: auto;}</style></head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    @OnClick(R.id.tv_send)
    public void onViewClicked() {
        if(!"".equals(et_comment.getText().toString())){
            sendComment(et_comment.getText().toString());
        }
    }
}
