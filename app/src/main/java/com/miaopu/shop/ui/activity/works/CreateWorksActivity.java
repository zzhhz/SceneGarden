package com.miaopu.shop.ui.activity.works;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.miaopu.shop.ui.activity.works.adapter.GoodsAdapter;
import com.miaopu.shop.ui.adapter.FullyGridLayoutManager;
import com.miaopu.shop.ui.adapter.GridImageAdapter;
import com.miaopu.shop.ui.base.BaseSwipeActivity;
import com.miaopu.shop.ui.model.Images;
import com.miaopu.shop.ui.model.ProductCheck;
import com.miaopu.shop.ui.model.Tags;
import com.miaopu.shop.ui.model.WorksData;
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
import java.io.Serializable;
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

public class CreateWorksActivity extends BaseSwipeActivity {

    @BindView(R.id.et_work_name)
    EditText etWorkName;
    @BindView(R.id.et_zi)
    EditText etZi;
    @BindView(R.id.tv_leibie)
    TextView tvLeibie;
    @BindView(R.id.rl_leibie)
    RelativeLayout rlLeibie;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.rl_area)
    RelativeLayout rlArea;
    @BindView(R.id.tv_chidu)
    TextView tvChidu;
    @BindView(R.id.rl_chidu)
    RelativeLayout rlChidu;
    @BindView(R.id.tv_style)
    TextView tvStyle;
    @BindView(R.id.rl_style)
    RelativeLayout rlStyle;
    @BindView(R.id.et_info)
    EditText etInfo;
    @BindView(R.id.rl_input)
    RelativeLayout rlInput;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.tv_check)
    TextView tv_check;
    @BindView(R.id.parentView)
    RelativeLayout parentView;
    @BindView(R.id.flowlayout)
    TagFlowLayout flowlayout;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.recyclerView)
    LRecyclerView recyclerView1;

    private List<String> tagList = new ArrayList<>();
    private List<String> tagIdList = new ArrayList<>();
    private TagAdapter<String> mAdapter;
    private LayoutInflater mInflater ;

    private OptionsPickerView leibieOptions, areaOptions, chiduOptions, styleOptions;
    private List<String> leibieIdList = new ArrayList<>();
    private List<String> areaIdList = new ArrayList<>();
    private List<String> chiduIdList = new ArrayList<>();
    private List<String> styleIdList = new ArrayList<>();

    private List<String> leibieList = new ArrayList<>();
    private List<String> areaList = new ArrayList<>();
    private List<String> chiduList = new ArrayList<>();
    private List<String> styleList = new ArrayList<>();

    private String leibieId;
    private String areaId;
    private String chiduId;
    private String styleId;
    private int themeId;
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;
    private GoodsAdapter goodsAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private List<ProductCheck> productCheckList = new ArrayList<>();
    @Override
    protected int setLayoutId() {
        return R.layout.activity_create_works;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initToolbar(R.string.create_work, Color.parseColor("#020202"));
        initPhoto();
    }

    private void initPhoto() {
        themeId = R.style.picture_default_style;
        FullyGridLayoutManager manager = new FullyGridLayoutManager(CreateWorksActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(CreateWorksActivity.this, onAddPicClickListener);
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
                        //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                        PictureSelector.create(CreateWorksActivity.this).externalPicturePreview(position, selectList);
                        break;
                    case 2:
                        // 预览视频
                        PictureSelector.create(CreateWorksActivity.this).externalPictureVideo(media.getPath());
                        break;
                    case 3:
                        // 预览音频
                        PictureSelector.create(CreateWorksActivity.this).externalPictureAudio(media.getPath());
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
                    PictureFileUtils.deleteCacheDirFile(CreateWorksActivity.this);
                } else {
                    Toast.makeText(CreateWorksActivity.this,
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

        goodsAdapter = new GoodsAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView1.setLayoutManager(layoutManager);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(goodsAdapter);
        recyclerView1.setAdapter(mLRecyclerViewAdapter);
        goodsAdapter.setOnDelListener(pos -> {
            l.d(productCheckList.size() + "");
            //RecyclerView关于notifyItemRemoved的那点小事 参考：http://blog.csdn.net/jdsjlzx/article/details/52131528
            goodsAdapter.getDataList().remove(pos);
            goodsAdapter.notifyItemRemoved(pos);//推荐用这个

            productCheckList.remove(pos);
            if(pos != (goodsAdapter.getDataList().size())){ // 如果移除的是最后一个，忽略 注意：这里的mDataAdapter.getDataList()不需要-1，因为上面已经-1了
                goodsAdapter.notifyItemRangeChanged(pos, goodsAdapter.getDataList().size() - pos);
            }
            l.d(productCheckList.size() +"");
            //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
        });
    }

    @Override
    protected void initData() {

        mInflater = LayoutInflater.from(this);
        RetrofitUtils.Api().getWorksData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new BaseHandleConsumer<WorksData>() {
                    @Override
                    public void onSuccess(WorksData model) {
                        for(WorksData.WorkscategoryBean workscategoryBean : model.getWorkscategory()){
                            leibieList.add(workscategoryBean.getLabel());
                            leibieIdList.add(workscategoryBean.getCode());
                        }
                        for(WorksData.WorksareaBean worksareaBean : model.getWorksarea()){
                            areaList.add(worksareaBean.getLabel());
                            areaIdList.add(worksareaBean.getCode());
                        }
                        for(WorksData.WorksscaleBean worksscaleBean : model.getWorksscale()){
                            chiduList.add(worksscaleBean.getLabel());
                            chiduIdList.add(worksscaleBean.getCode());
                        }
                        for(WorksData.WorksstyleBean worksstyleBean : model.getWorksstyle()){
                            styleList.add(worksstyleBean.getLabel());
                            styleIdList.add(worksstyleBean.getCode());
                        }
                        for(WorksData.DesigntagBean designtagBean : model.getDesigntag()){
                            tagList.add(designtagBean.getLabel());
                            tagIdList.add(designtagBean.getId());
                        }
                        mAdapter = new TagAdapter<String>(tagList) {
                            @Override
                            public View getView(FlowLayout parent, int position, String s)
                            {
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
        initAreaOptionsPicker();
        initChiduOptionsPicker();
        initLeibieOptionsPicker();
        initStyleOptionsPicker();
    }

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case 1:
                submitData((ArrayList<String>) msg.obj);
                break;
        }
    }

    @Override
    public void onClick(View v) {

    }

    @OnClick({R.id.rl_leibie, R.id.rl_area, R.id.rl_chidu, R.id.rl_style, R.id.tv_confirm, R.id.tv_check})
    public void onViewClicked(View view) {
        if(Utils.isSoftInputShow(this)){
            Utils.closeKeybord(etZi, this);
        }
        switch (view.getId()) {
            case R.id.rl_leibie:
                leibieOptions.show();
                break;
            case R.id.rl_area:
                areaOptions.show();
                break;
            case R.id.rl_chidu:
                chiduOptions.show();
                break;
            case R.id.rl_style:
                styleOptions.show();
                break;
            case R.id.tv_check:
                Intent intent = new Intent(this, CheckWorksActivity.class);
                intent.putExtra("data", (Serializable) productCheckList);
                startActivityForResult(intent, 116);
                break;
            case R.id.tv_confirm:
                if(check()){
                    postPhoto();
                }
                break;
        }
    }

    private void submitData(ArrayList<String> objList) {
        showDialog();
        Works works = new Works();
        works.setTitle(etWorkName.getText().toString());
        List<Tags> tagsList = new ArrayList<>();
        Set<Integer> selectedList = flowlayout.getSelectedList();
        if(selectedList != null && selectedList.size() > 0){
            Iterator<Integer> it = selectedList.iterator();
            Tags tags;
            while (it.hasNext()) {
                Integer str = it.next();
                tags = new Tags();
                tags.setDicId(tagIdList.get(str));
                tags.setDicValue(tagList.get(str));
                tagsList.add(tags);
            }
            works.setTags(tagsList);
        }
        works.setSubTitle(etZi.getText().toString());
        works.setCategory(leibieId);
        works.setArea(areaId);
        works.setScale(chiduId);
        works.setStyle(styleId);
        works.setBrief(etInfo.getText().toString());
        works.setDefaultImg(objList.get(0));
        Images images ;
        List<Images> imagesList = new ArrayList<>();
        for (String img : objList){
            images = new Images();
            images.setImgUrl(img);
            imagesList.add(images);
        }
        works.setAttaches(imagesList);
        works.setProducts(productCheckList);

        String  sJson = JSON.toJSONString(works);
        l.d(sJson);
        RequestBody body =RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), sJson);
        RetrofitUtils.Api().confrimWorksData(body).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseHandleConsumer<String>() {
                    @Override
                    public void onSuccess(String bean) {
                        l.d(bean);
                        EventUtils.sendEventLogin();
                        dismissDialog();
                        showMessage("创建作品成功");
                        setResult(RESULT_OK);
                        CreateWorksActivity.this.finish();
                    }

                    @Override
                    public void onFailed(String code) {
                        dismissDialog();
                    }
                });
    }

    private boolean check() {
        if ("".equals(etWorkName.getText().toString())) {
            Utils.toastTips(this, "请输入您的作品名称");
            return false;
        }
        if (flowlayout.getSelectedList() == null || flowlayout.getSelectedList().size() < 1) {
            Utils.toastTips(this, "请选择作品标签");
            return false;
        }
        if ("".equals(etZi.getText().toString())) {
            Utils.toastTips(this, "请输入您的子标题");
            return false;
        }
        if ("".equals(tvLeibie.getText().toString())) {
            Utils.toastTips(this, "请选择类别");
            return false;
        }
        if ("".equals(tvArea.getText().toString())) {
            Utils.toastTips(this, "请选择区域");
            return false;
        }
        if ("".equals(tvChidu.getText().toString())) {
            Utils.toastTips(this, "请选择尺度");
            return false;
        }
        if ("".equals(tvStyle.getText().toString())) {
            Utils.toastTips(this, "请选择风格");
            return false;
        }
        if ("".equals(etInfo.getText().toString())) {
            Utils.toastTips(this, "请输入作品描述");
            return false;
        }
        if (selectList == null || selectList.size() < 1) {
            Utils.toastTips(this, "请上传详情展示图片");
            return false;
        }
//        if (productCheckList == null || productCheckList.size() < 1) {
//            Utils.toastTips(this, "请上传详情展示图片");
//            return false;
//        }
        return true;
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(CreateWorksActivity.this)
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

    /**
     * 上传图片到七牛
     */
    private void postPhoto() {
        showDialog();
        Thread thread = new Thread() {
            @Override
            public void run() {
                ArrayList<String> imgData = new ArrayList<>();
                for(LocalMedia localMedia : selectList){
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
                case 116:
                    if(data != null && data.getSerializableExtra("list") != null){
                        productCheckList = (List<ProductCheck>) data.getSerializableExtra("list");
                        goodsAdapter.clear();
                        goodsAdapter.addAll(productCheckList);
                        goodsAdapter.notifyDataSetChanged();
                    }else {
                        productCheckList.clear();
                        goodsAdapter.clear();
                        goodsAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }
    private void initLeibieOptionsPicker() {
        leibieOptions = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            tvLeibie.setText(leibieList.get(options1));
            leibieId = leibieIdList.get(options1);
        }).build();
        leibieOptions.setPicker(leibieList);
    }
    private void initAreaOptionsPicker() {
        areaOptions = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            tvArea.setText(areaList.get(options1));
            areaId = areaIdList.get(options1);
        }).build();
        areaOptions.setPicker(areaList);
    }
    private void initChiduOptionsPicker() {
        chiduOptions = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            tvChidu.setText(chiduList.get(options1));
            chiduId = chiduIdList.get(options1);
        }).build();
        chiduOptions.setPicker(chiduList);
    }
    private void initStyleOptionsPicker() {
        styleOptions = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            tvStyle.setText(styleList.get(options1));
            styleId = styleIdList.get(options1);
        }).build();
        styleOptions.setPicker(styleList);
    }

    static class Works{
        private String        title;
        private String        subTitle;
        private String        category;
        private String        area;
        private String        scale;
        private String        style;
        private String        brief;
        private String        id;
        private String        defaultImg;
        private List<Tags>    tags;
        private List<Images>  attaches;
        private List<ProductCheck> products;

        public List<ProductCheck> getProducts() {
            return products;
        }

        public void setProducts(List<ProductCheck> products) {
            this.products = products;
        }

        public List<Images> getAttaches() {
            return attaches;
        }

        public void setAttaches(List<Images> attaches) {
            this.attaches = attaches;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getScale() {
            return scale;
        }

        public void setScale(String scale) {
            this.scale = scale;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
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
    }
}
