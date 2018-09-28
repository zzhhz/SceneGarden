package com.miaopu.shop.ui.activity.recommend;

import android.os.Build;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.miaopu.shop.ui.base.BaseStoreFragment;
import com.zzh.sexual.secret.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2018/1/17.
 *
 * @date: 2018/1/17
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 图文详情
 */
public class ProductDetailFragment extends BaseStoreFragment {

    public WebSettings settings;
    @BindView(R.id.web_view)
    public WebView mWebView;


    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_product_detail;
    }

    @Override
    protected void initView(View fragment) {
        ButterKnife.bind(this, fragment);

    }

    @Override
    protected void initData() {
        settings = mWebView.getSettings();
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
    }

    @Override
    public void setViewListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    public void openWebView(String data) {
        settings.setDefaultFontSize(14);
        openWebView(mWebView, data);
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

    public void setContentData(String content) {
        if (!TextUtils.isEmpty(content)) {
            openWebView(content);
        }
    }
}
