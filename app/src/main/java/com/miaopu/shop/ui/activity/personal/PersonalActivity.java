package com.miaopu.shop.ui.activity.personal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.callback.Callback;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.miaopu.shop.ShopApplication;
import com.miaopu.shop.ui.base.BaseSwipeActivity;
import com.miaopu.shop.ui.model.User;
import com.miaopu.shop.ui.view.ActionSheetDialog;
import com.miaopu.shop.ui.view.AlertDialog;
import com.miaopu.shop.utils.Constants;
import com.miaopu.shop.utils.DataCleanManager;
import com.miaopu.shop.utils.EventUtils;
import com.miaopu.shop.utils.GlideUtils;
import com.miaopu.shop.utils.SpUtils;
import com.miaopu.shop.utils.Utils;
import com.miaopu.shop.utils.l;
import com.miaopu.shop.web.BaseHandleConsumer;
import com.miaopu.shop.web.RetrofitUtils;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.zzh.sexual.secret.R;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PersonalActivity extends BaseSwipeActivity {
    @BindView(R.id.iv_head)
    ImageView      ivHead;
    @BindView(R.id.layout_head)
    RelativeLayout layoutHead;
    @BindView(R.id.layout_data)
    RelativeLayout layoutData;
    @BindView(R.id.layout_address)
    RelativeLayout layoutAddress;
    @BindView(R.id.layout_change_phone)
    RelativeLayout layoutChangePhone;
    @BindView(R.id.tv_cache)
    TextView       tvCache;
    @BindView(R.id.layout_cache)
    RelativeLayout layoutCache;
    @BindView(R.id.layout_about_us)
    RelativeLayout layoutAboutUs;
    @BindView(R.id.btn_logout)
    Button         btn_logout;


    private static final int IMAGE_REQUEST_CODE  = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_cache_arrow)
    TextView tvCacheArrow;
    private String filename;
    private String id;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        initToolbar(R.string.my_info, Color.parseColor("#020202"));
    }

    @Override
    protected void initData() {
        try {
            tvCache.setText(DataCleanManager.getTotalCacheSize(PersonalActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ShopApplication.getCurrentUser() != null){
            User user = ShopApplication.getCurrentUser();
            id = user.getId();
            GlideUtils.loadCircleImageView(mContext, user.getHeadImg(), ivHead, R.mipmap.ic_test_bg);
        }else {
            ivHead.setVisibility(View.INVISIBLE);
            btn_logout.setVisibility(View.GONE);
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

    private List<LocalMedia> selectList = new ArrayList<>();
    private String imgPath;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    selectList = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia media : selectList) {
                        Log.i("图片-----》", media.getPath());
                    }
                    imgPath = selectList.get(0).getPath();
                    postPhoto();
                    break;
            }
        }
    }

    /**
     * 上传图片到七牛
     */
    private void postPhoto() {
        if (imgPath != null) {
            showDialog();
            UploadManager uploadManager = new UploadManager();
            File file     = new File(imgPath);
            uploadManager.put(file, file.getName(), Constants.getToken(), new UpCompletionHandler() {
                @Override
                public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {

                    dismissDialog();
                    l.e(responseInfo.toString());
                    l.e("responseInfo.isOK() =" + responseInfo.isOK());
                    if (responseInfo.isOK()) {
                        String headUrl = Constants.QINIUBASE_URL + "/" + s;
                        User currentUser = ShopApplication.getCurrentUser();
                        currentUser.setHeadImg(headUrl);
                        SpUtils.saveLogin(mContext, currentUser);
                        GlideUtils.loadCircleImageView(mContext, headUrl, ivHead, R.mipmap.ic_test_bg);
                        showMessage("头像上传成功");
                        setResult(RESULT_OK);
                    } else {
                        showMessage("头像上传失败");
                    }
                }
            }, null);
        }else {
            showMessage("头像上传失败");
        }
    }

    private void comfirm(String imgPath) {
        showDialog();
        Map<String, String> params = new HashMap<>();
        params.put("headImg", imgPath);

        if(id != null && !"".equals(id)){
            params.put("id", id);
        }
        RetrofitUtils.Api().saveMember(RetrofitUtils.map2Params(params)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseHandleConsumer<String>() {
                    @Override
                    public void onSuccess(String bean) {
                        l.d(bean);
                        dismissDialog();
                        ShopApplication.getCurrentUser().setHeadImg(imgPath);
                        GlideUtils.loadCircleImageView(mContext, imgPath, ivHead, R.mipmap.ic_test_bg);
                        showMessage("头像上传成功");
                        setResult(RESULT_OK);
                    }

                    @Override
                    public void onFailed(String code) {
                        dismissDialog();
                    }
                });
    }

    @OnClick({R.id.layout_head, R.id.layout_data, R.id.layout_address, R.id.layout_change_phone, R.id.layout_cache, R.id.layout_about_us, R.id.btn_logout})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.layout_head:
                if(TextUtils.isEmpty(ShopApplication.getToken())){
                    Toast.makeText(mContext, "请先登录！", Toast.LENGTH_SHORT).show();
                    return;
                }
                new ActionSheetDialog(this).builder().setCancelable(true).setCanceledOnTouchOutside(true).addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue, which -> {
//                    Intent           intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    SimpleDateFormat t                 = new SimpleDateFormat("yyyyMMddssSSS");
//                    filename = "Petition" + (t.format(new Date())) + ".jpg";
//                    intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), filename)));
//                    startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
                    PictureSelector.create(PersonalActivity.this)
                            .openCamera(PictureMimeType.ofImage())
                            .maxSelectNum(1)// 最大图片选择数量
                            .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                            .compress(true)// 是否压缩
                            .minimumCompressSize(100)// 小于100kb的图片不压缩
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                }).addSheetItem("相册", ActionSheetDialog.SheetItemColor.Blue, which -> {
//                    Intent intentFromGallery = new Intent(Intent.ACTION_PICK, null);
//                    intentFromGallery.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//                    startActivityForResult(intentFromGallery, IMAGE_REQUEST_CODE);
                    PictureSelector.create(PersonalActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .maxSelectNum(1)// 最大图片选择数量
                            .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                            .compress(true)// 是否压缩
                            .minimumCompressSize(100)// 小于100kb的图片不压缩
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                }).show();
                break;
            case R.id.layout_cache:
                new AlertDialog(this).builder().setTitle("是否清理缓存？").setPositiveButton("确定", v -> {
                    Utils.toastTips(PersonalActivity.this, getString(R.string.clearing_cache));
                    DataCleanManager.clearAllCache(PersonalActivity.this);
                    try {
                        tvCache.setText(DataCleanManager.getTotalCacheSize(PersonalActivity.this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).setNegativeButton("取消", v -> {

                }).show();
                break;
            case R.id.layout_about_us:
                intent = new Intent(this, MyWebViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_logout:
                SpUtils.saveToken(mContext, "");
                EventUtils.sendEventLogin();
                ShopApplication.setCurrentUser(null);
                SpUtils.logout(this);
                ChatClient.getInstance().logout(true, new Callback(){
                    @Override
                    public void onSuccess() {
                        l.d("环信登出成功");
                    }

                    @Override
                    public void onError(int i, String s) {
                        l.d("环信登出失败");
                    }

                    @Override
                    public void onProgress(int i, String s) {}
                });
                PersonalActivity.this.finish();
                break;
        }
    }
}
