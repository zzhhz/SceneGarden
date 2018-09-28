package com.miaopu.shop.ui.activity.works;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.miaopu.shop.ui.adapter.FullyGridLayoutManager;
import com.miaopu.shop.ui.adapter.GridImageAdapter;
import com.miaopu.shop.ui.base.BaseSwipeActivity;
import com.miaopu.shop.ui.model.Images;
import com.miaopu.shop.ui.model.Tags;
import com.miaopu.shop.utils.Constants;
import com.miaopu.shop.utils.EventUtils;
import com.miaopu.shop.utils.Utils;
import com.miaopu.shop.utils.l;
import com.miaopu.shop.web.BaseHandleConsumer;
import com.miaopu.shop.web.RetrofitUtils;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UploadManager;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zzh.sexual.secret.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * Created by user.
 *
 * @date: 2018/3/16
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: MiaoPuShop 创建案例
 * @since 1.0
 */
public class CreateCaseActivity extends BaseSwipeActivity {

    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;
    private int themeId;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.flowlayout)
    TagFlowLayout flowlayout;
    private List<String> tagList = new ArrayList<>();
    private List<String> tagIdList = new ArrayList<>();
    private TagAdapter<String> mAdapter;
    private LayoutInflater mInflater;
    @BindView(R.id.et_work_name)
    EditText etWorkName;
    @BindView(R.id.et_info)
    EditText etInfo;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_create_case;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initToolbar(R.string.create_case, Color.parseColor("#020202"));
        initPhoto();
    }

    private void initPhoto() {
        themeId = R.style.picture_default_style;
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(1);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((position, v) -> {
            if (selectList.size() > 0) {
                LocalMedia media = selectList.get(position);
                String pictureType = media.getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                switch (mediaType) {
                    case 1:
                        // 预览图片 可自定长按保存路径
                        PictureSelector.create(CreateCaseActivity.this).externalPicturePreview(position, selectList);
                        break;
                    case 2:
                        // 预览视频
                        PictureSelector.create(CreateCaseActivity.this).externalPictureVideo(media.getPath());
                        break;
                    case 3:
                        // 预览音频
                        PictureSelector.create(CreateCaseActivity.this).externalPictureAudio(media.getPath());
                        break;
                }
            }
        });

        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(CreateCaseActivity.this);
                } else {
                    Toast.makeText(CreateCaseActivity.this,
                            getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    protected void initData() {

        mInflater = LayoutInflater.from(this);
        RetrofitUtils.Api().getCaseStyle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new BaseHandleConsumer<List<Tags>>() {
                    @Override
                    public void onSuccess(List<Tags> model) {
                        for (Tags designtagBean : model) {
                            tagList.add(designtagBean.getLabel());
                            tagIdList.add(designtagBean.getId());
                        }
                        mAdapter = new TagAdapter<String>(tagList) {
                            @Override
                            public View getView(FlowLayout parent, int position, String s) {
                                TextView tv = (TextView) mInflater.inflate(R.layout.tag_flow,
                                        flowlayout, false);
                                tv.setText(s);
                                return tv;
                            }
                        };
                        flowlayout.setAdapter(mAdapter);
                    }
                }
        );

        flowlayout.setOnSelectListener(selectPosSet -> setTitle("choose:" + selectPosSet.toString()));
    }

    @Override
    protected void initSetListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case 1:
                submitData((ArrayList<String>) msg.obj);
                break;
        }
    }

    private void submitData(ArrayList<String> objList) {
        showDialog();
        List<Tags> tagsList = new ArrayList<>();
        Set<Integer> selectedList = flowlayout.getSelectedList();
        if (selectedList != null && selectedList.size() > 0) {
            Iterator<Integer> it = selectedList.iterator();
            Tags tags;
            while (it.hasNext()) {
                Integer str = it.next();
                tags = new Tags();
                tags.setDicId(tagIdList.get(str));
                tags.setDicValue(tagList.get(str));
                tagsList.add(tags);
            }
        }
        Images images;
        List<Images> imagesList = new ArrayList<>();
        for (String img : objList) {
            images = new Images();
            images.setImgUrl(img);
            imagesList.add(images);
        }

        CreateCase createCase = new CreateCase();
        createCase.setContent(etInfo.getText().toString());
        createCase.setName(etWorkName.getText().toString());
        createCase.setId("");
        createCase.setDefaultImg(imagesList.get(0).getImgUrl());
        createCase.setAttaches(imagesList);
        createCase.setTags(tagsList);
        String sJson = JSON.toJSONString(createCase);
        l.d(sJson);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), sJson);
        RetrofitUtils.Api().confrimCaseData(body).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseHandleConsumer<String>() {
                    @Override
                    public void onSuccess(String bean) {
                        l.d(bean);
                        EventUtils.sendEventLogin();
                        dismissDialog();
                        showMessage("创建案例成功");
                        setResult(RESULT_OK);
                        CreateCaseActivity.this.finish();
                    }

                    @Override
                    public void onFailed(String code) {
                        dismissDialog();
                        showMessage("创建案例失败");
                    }
                });
    }

    @Override
    public void onClick(View v) {

    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(CreateCaseActivity.this)
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                    .maxSelectNum(3)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                    .isCamera(true)// 是否显示拍照按钮
                    .compress(true)// 是否压缩
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .selectionMedia(selectList)// 是否传入已选图片
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList) {
                        Log.i("图片-----》", media.getPath());
                    }
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @OnClick({R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                if (check()) {
                    postPhoto();
                }
                break;
        }
    }

    private boolean check() {
        if ("".equals(etWorkName.getText().toString())) {
            Utils.toastTips(this, "请输入您的作品名称");
            return false;
        }
        if (flowlayout.getSelectedList() == null || flowlayout.getSelectedList().size() < 1) {
            Utils.toastTips(this, "请选择案例风格标签");
            return false;
        }

        if ("".equals(etInfo.getText().toString())) {
            Utils.toastTips(this, "请输入案例描述");
            return false;
        }

        if (etInfo.getText().toString().length() > 220) {
            Utils.toastTips(this, "输入的案例描述不要超过200个字");
            return false;
        }

        if (selectList == null || selectList.size() < 1) {
            Utils.toastTips(this, "请上传详情展示图片");
            return false;
        }
        return true;
    }

    /**
     * 上传图片到七牛
     */
    private void postPhoto() {
        showDialog();
        Thread thread = new Thread() {
            @Override
            public void run() {
                ArrayList<String> imgData = new ArrayList<>();
                for (LocalMedia localMedia : selectList) {
                    imgData.add(localMedia.getPath());
                }
                Message msg = Message.obtain();

                msg.what = 1;
                List<String> imgUrl = new ArrayList<>();
                if (!imgData.isEmpty()) {
                    UploadManager uploadManager = new UploadManager();
                    for (String img : imgData) {
                        if (!img.startsWith("http://")) {
                            File file = new File(img);
                            String key = "MiaoPu_" + System.currentTimeMillis() + ".jpg";
                            ResponseInfo resp = uploadManager.syncPut(file, key, Constants.getToken(), null);
                            if (resp.isOK()) {
                                imgUrl.add((Constants.QINIUBASE_URL) + ("/") + (resp.path) + (key));
                            } else {
                                l.e("上传失败：" + img);
                            }
                        } else {
                            imgUrl.add(img);
                        }
                    }
                }

                dismissDialog();
                msg.obj = imgUrl;
                mHandler.sendMessage(msg);
            }
        };
        thread.start();
    }

    static class CreateCase {

        /**
         * name : 123123
         * tags : [{"dicId":"8dbda7a73cdd440a8b5cf370cafb29b6","dicValue":"中式风格"},{"dicId":"8de22118d6514c29bcfaf68d1791746c","dicValue":"欧式风格"}]
         * content : 123123
         * id :
         * attaches : [{"imgUrl":"http://p28t2n2ld.bkt.clouddn.com/398318ccf9d54a4db486b34fc9e2ff1d17.png"},{"imgUrl":"http://p28t2n2ld.bkt.clouddn.com/3950c1629ca369489e859796f4bdfb11e5.png"}]
         * defaultImg : http://p28t2n2ld.bkt.clouddn.com/398318ccf9d54a4db486b34fc9e2ff1d17.png
         */

        private String name;
        private String content;
        private String id;
        private String defaultImg;
        private List<Tags> tags;
        private List<Images> attaches;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDefaultImg() {
            return defaultImg;
        }

        public void setDefaultImg(String defaultImg) {
            this.defaultImg = defaultImg;
        }

        public List<Tags> getTags() {
            return tags;
        }

        public void setTags(List<Tags> tags) {
            this.tags = tags;
        }

        public List<Images> getAttaches() {
            return attaches;
        }

        public void setAttaches(List<Images> attaches) {
            this.attaches = attaches;
        }
    }
}
