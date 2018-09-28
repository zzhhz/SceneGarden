package com.miaopu.shop.ui.camera;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.util.FileUtils;
import com.common.util.ImageUtils;
import com.common.util.StringUtils;
import com.common.util.TimeUtils;
import com.customview.LabelView;
import com.customview.MyHighlightView;
import com.customview.MyImageViewDrawableOverlay;
import com.miaopu.shop.ShopApplication;
import com.miaopu.shop.ui.base.CameraBaseActivity;
import com.miaopu.shop.ui.camera.adapter.FilterAdapter;
import com.miaopu.shop.ui.camera.adapter.StickerToolAdapter;
import com.miaopu.shop.ui.model.DIYProduct;
import com.miaopu.shop.ui.model.camera.FilterEffect;
import com.miaopu.shop.ui.model.camera.TagItem;
import com.miaopu.shop.utils.AppConstants;
import com.miaopu.shop.utils.Constants;
import com.miaopu.shop.utils.camera.CameraManager;
import com.miaopu.shop.utils.camera.EffectService;
import com.miaopu.shop.utils.camera.EffectUtil;
import com.miaopu.shop.utils.camera.GPUImageFilterTools;
import com.zzh.sexual.secret.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

/**
 * 图片处理界面
 * Created by sky on 2015/7/8.
 * Weibo: http://weibo.com/2030683111
 * Email: 1132234509@qq.com
 */
public class PhotoProcessActivity extends CameraBaseActivity {

    //滤镜图片
    @BindView(R.id.gpuimage)
    GPUImageView mGPUImageView;
    //绘图区域
    @BindView(R.id.drawing_view_container)
    ViewGroup drawArea;
    //底部按钮
    @BindView(R.id.sticker_btn)
    TextView stickerBtn;
    @BindView(R.id.filter_btn)
    TextView filterBtn;
    //工具区
    @BindView(R.id.list_tools)
    HListView bottomToolBar;
    @BindView(R.id.toolbar_area)
    ViewGroup toolArea;
    private MyImageViewDrawableOverlay mImageView;
    //private LabelSelector labelSelector;

    //当前选择底部按钮
    private TextView currentBtn;
    //当前图片
    private Bitmap currentBitmap;
    //用于预览的小图片
    private Bitmap smallImageBackgroud;
    //小白点标签
    private LabelView emptyLabelView;

    private List<LabelView> labels = new ArrayList<LabelView>();

    //标签区域
    private View commonLabelArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_process);
        ButterKnife.bind(this);
        EffectUtil.clear();
        initView();
        initEvent();
        initStickerToolBar();

        ImageUtils.asyncLoadImage(this, getIntent().getData(), new ImageUtils.LoadImageCallback() {
            @Override
            public void callback(Bitmap result) {
                currentBitmap = result;
                mGPUImageView.setImage(currentBitmap);
            }
        });

        ImageUtils.asyncLoadSmallImage(this, getIntent().getData(), new ImageUtils.LoadImageCallback() {
            @Override
            public void callback(Bitmap result) {
                smallImageBackgroud = result;
            }
        });

    }

    private void initView() {
        //添加贴纸水印的画布
        View overlay = LayoutInflater.from(PhotoProcessActivity.this).inflate(
                R.layout.view_drawable_overlay, null);
        mImageView = (MyImageViewDrawableOverlay) overlay.findViewById(R.id.drawable_overlay);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ShopApplication.getInstance().getScreenWidth(),
                ShopApplication.getInstance().getScreenWidth());
        mImageView.setLayoutParams(params);
        overlay.setLayoutParams(params);
        drawArea.addView(overlay);
        //添加标签选择器
        RelativeLayout.LayoutParams rparams = new RelativeLayout.LayoutParams(ShopApplication.getInstance().getScreenWidth(), ShopApplication.getInstance().getScreenWidth());
        /*labelSelector = new LabelSelector(this);
        labelSelector.setLayoutParams(rparams);
        drawArea.addView(labelSelector);
        labelSelector.hide();*/

        //初始化滤镜图片
        mGPUImageView.setLayoutParams(rparams);


        //初始化空白标签
        emptyLabelView = new LabelView(this);
        emptyLabelView.setEmpty();
        EffectUtil.addLabelEditable(mImageView, drawArea, emptyLabelView,
                mImageView.getWidth() / 2, mImageView.getWidth() / 2);
        emptyLabelView.setVisibility(View.INVISIBLE);

        //初始化推荐标签栏
        commonLabelArea = LayoutInflater.from(PhotoProcessActivity.this).inflate(
                R.layout.view_label_bottom, null);
        commonLabelArea.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        toolArea.addView(commonLabelArea);
        commonLabelArea.setVisibility(View.GONE);
    }

    private void initEvent() {
        stickerBtn.setOnClickListener(v -> {
            if (!setCurrentBtn(stickerBtn)) {
                return;
            }
            bottomToolBar.setVisibility(View.VISIBLE);
            //labelSelector.hide();
            emptyLabelView.setVisibility(View.GONE);
            commonLabelArea.setVisibility(View.GONE);
            initStickerToolBar();
        });

        filterBtn.setOnClickListener(v -> {
            if (!setCurrentBtn(filterBtn)) {
                return;
            }
            bottomToolBar.setVisibility(View.VISIBLE);
            //labelSelector.hide();
            emptyLabelView.setVisibility(View.INVISIBLE);
            commonLabelArea.setVisibility(View.GONE);
            initFilterToolBar();
        });
        /*labelSelector.setTxtClicked(v -> {
            //EditTextActivity.openTextEdit(PhotoProcessActivity.this,"",8, AppConstants.ACTION_EDIT_LABEL);
        });*/
        /*labelSelector.setAddrClicked(v -> {
            //EditTextActivity.openTextEdit(PhotoProcessActivity.this,"",8, AppConstants.ACTION_EDIT_LABEL_POI);

        });*/
        //mImageView.setOnDrawableEventListener(wpEditListener);
        mImageView.setSingleTapListener(() -> {
            emptyLabelView.updateLocation((int) mImageView.getmLastMotionScrollX(),
                    (int) mImageView.getmLastMotionScrollY());
            emptyLabelView.setVisibility(View.VISIBLE);

            //labelSelector.showToTop();
            drawArea.postInvalidate();
        });
        /*labelSelector.setOnClickListener(v -> {
            labelSelector.hide();
            emptyLabelView.updateLocation((int) labelSelector.getmLastTouchX(),
                    (int) labelSelector.getmLastTouchY());
            emptyLabelView.setVisibility(View.VISIBLE);
        });*/


        titleBar.setRightBtnOnclickListener(v -> {
            savePicture();
        });
    }

    //保存图片
    private void savePicture() {
        //加滤镜
        final Bitmap newBitmap = Bitmap.createBitmap(mImageView.getWidth(), mImageView.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(newBitmap);
        RectF dst = new RectF(0, 0, mImageView.getWidth(), mImageView.getHeight());
        try {
            cv.drawBitmap(mGPUImageView.capture(), null, dst, null);
        } catch (InterruptedException e) {
            e.printStackTrace();
            cv.drawBitmap(currentBitmap, null, dst, null);
        }
        //加贴纸水印
        EffectUtil.applyOnSave(cv, mImageView);

        new SavePicToFileTask().execute(newBitmap);
    }

    private class SavePicToFileTask extends AsyncTask<Bitmap, Void, String> {
        Bitmap bitmap;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog("图片处理中...");
        }

        @Override
        protected String doInBackground(Bitmap... params) {
            String fileName = null;
            try {
                bitmap = params[0];

                String picName = TimeUtils.dtFormat(new Date(), "yyyyMMddHHmmss") + ".jpg";
                fileName = ImageUtils.saveToFile(FileUtils.getInst().getPhotoSavedPath() + "/" + picName, false, bitmap);

            } catch (Exception e) {
                e.printStackTrace();
                toast("图片处理错误，请退出相机并重试", Toast.LENGTH_LONG);
            }
            return fileName;
        }

        @Override
        protected void onPostExecute(String fileName) {
            super.onPostExecute(fileName);
            dismissProgressDialog();
            if (StringUtils.isEmpty(fileName)) {
                return;
            }
            CameraManager.getInst().close();
            Intent intent = new Intent(PhotoProcessActivity.this, PreviewActivity.class);
            intent.putExtra("path", fileName);
            startActivity(intent);
        }
    }


    public void tagClick(View v) {
        TextView textView = (TextView) v;
        TagItem tagItem = new TagItem(AppConstants.POST_TYPE_TAG, textView.getText().toString());
        addLabel(tagItem);
    }

    private MyImageViewDrawableOverlay.OnDrawableEventListener wpEditListener = new MyImageViewDrawableOverlay.OnDrawableEventListener() {
        @Override
        public void onMove(MyHighlightView view) {
        }

        @Override
        public void onFocusChange(MyHighlightView newFocus, MyHighlightView oldFocus) {
        }

        @Override
        public void onDown(MyHighlightView view) {

        }

        @Override
        public void onClick(MyHighlightView view) {
            //labelSelector.hide();
        }

        @Override
        public void onClick(final LabelView label) {
            if (label.equals(emptyLabelView)) {
                return;
            }
            alert("温馨提示", "是否需要删除该标签！", "确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EffectUtil.removeLabelEditable(mImageView, drawArea, label);
                    labels.remove(label);
                }
            }, "取消", null);
        }
    };

    private boolean setCurrentBtn(TextView btn) {
        if (currentBtn == null) {
            currentBtn = btn;
        } else if (currentBtn.equals(btn)) {
            return false;
        } else {
            currentBtn.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        Drawable myImage = getResources().getDrawable(R.drawable.select_icon);
        btn.setCompoundDrawablesWithIntrinsicBounds(null, null, null, myImage);
        currentBtn = btn;
        return true;
    }


    //初始化贴图
    private void initStickerToolBar() {
        EffectUtil.addonList.add(new DIYProduct("http://pfr5h8aqm.bkt.clouddn.com/timg1.jpg"));
        EffectUtil.addonList.add(new DIYProduct("http://pfr5h8aqm.bkt.clouddn.com/penjing_2.jpg"));
        EffectUtil.addonList.add(new DIYProduct("http://pfr5h8aqm.bkt.clouddn.com/penjing_3.jpg"));
        EffectUtil.addonList.add(new DIYProduct("http://pfr5h8aqm.bkt.clouddn.com/penjjing_4.jpg"));
        EffectUtil.addonList.add(new DIYProduct("http://pfr5h8aqm.bkt.clouddn.com/penjjing_5.jpg"));
        EffectUtil.addonList.add(new DIYProduct("http://pfr5h8aqm.bkt.clouddn.com/penjjing_6.jpg"));
        bottomToolBar.setAdapter(new StickerToolAdapter(PhotoProcessActivity.this, EffectUtil.addonList));
        bottomToolBar.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
            Constants.addDIY(EffectUtil.addonList.get(arg2));
            ImageView imageView = arg1.findViewById(R.id.effect_image);
            EffectUtil.addStickerImage(mImageView, PhotoProcessActivity.this, imageView.getDrawable()
                    , EffectUtil.addonList.get(arg2),
                    sticker -> {
                    });
        });
        setCurrentBtn(stickerBtn);

        /*RetrofitUtils.Api().getDiyProductList(1, Constants.PAGE_SIZE * 3, RetrofitUtils.map2Params(new HashMap<>()))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseHandleConsumer<WrapperModel<DIYProduct>>() {
                    @Override
                    public void onSuccess(WrapperModel<DIYProduct> model) {
                        EffectUtil.addonList = model.getList();
                        bottomToolBar.setAdapter(new StickerToolAdapter(PhotoProcessActivity.this, EffectUtil.addonList));
                        bottomToolBar.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
                            Constants.addDIY(EffectUtil.addonList.get(arg2));
                            ImageView imageView = arg1.findViewById(R.id.effect_image);
                            EffectUtil.addStickerImage(mImageView, PhotoProcessActivity.this, imageView.getDrawable()
                                    , EffectUtil.addonList.get(arg2),
                                    sticker -> {
                                    });
                        });
                        setCurrentBtn(stickerBtn);
                    }
                });*/

    }


    //初始化滤镜
    private void initFilterToolBar() {
        final List<FilterEffect> filters = EffectService.getInst().getLocalFilters();
        final FilterAdapter adapter = new FilterAdapter(PhotoProcessActivity.this, filters, smallImageBackgroud);
        bottomToolBar.setAdapter(adapter);
        bottomToolBar.setOnItemClickListener(new it.sephiroth.android.library.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(it.sephiroth.android.library.widget.AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //labelSelector.hide();
                if (adapter.getSelectFilter() != arg2) {
                    adapter.setSelectFilter(arg2);
                    GPUImageFilter filter = GPUImageFilterTools.createFilterForType(
                            PhotoProcessActivity.this, filters.get(arg2).getType());
                    mGPUImageView.setFilter(filter);
                    GPUImageFilterTools.FilterAdjuster mFilterAdjuster = new GPUImageFilterTools.FilterAdjuster(filter);
                    //可调节颜色的滤镜
                    if (mFilterAdjuster.canAdjust()) {
                        //mFilterAdjuster.adjust(100); 给可调节的滤镜选一个合适的值
                    }
                }
            }
        });
    }

    //添加标签
    private void addLabel(TagItem tagItem) {
        //labelSelector.hide();
        emptyLabelView.setVisibility(View.INVISIBLE);
        if (labels.size() >= 5) {
            alert("温馨提示", "您只能添加5个标签！", "确定", null, null, null, true);
        } else {
            int left = emptyLabelView.getLeft();
            int top = emptyLabelView.getTop();
            if (labels.size() == 0 && left == 0 && top == 0) {
                left = mImageView.getWidth() / 2 - 10;
                top = mImageView.getWidth() / 2;
            }
            LabelView label = new LabelView(PhotoProcessActivity.this);
            label.init(tagItem);
            EffectUtil.addLabelEditable(mImageView, drawArea, label, left, top);
            labels.add(label);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //labelSelector.hide();
        super.onActivityResult(requestCode, resultCode, data);
        if (AppConstants.ACTION_EDIT_LABEL == requestCode && data != null) {
            String text = data.getStringExtra(AppConstants.PARAM_EDIT_TEXT);
            if (StringUtils.isNotEmpty(text)) {
                TagItem tagItem = new TagItem(AppConstants.POST_TYPE_TAG, text);
                addLabel(tagItem);
            }
        } else if (AppConstants.ACTION_EDIT_LABEL_POI == requestCode && data != null) {
            String text = data.getStringExtra(AppConstants.PARAM_EDIT_TEXT);
            if (StringUtils.isNotEmpty(text)) {
                TagItem tagItem = new TagItem(AppConstants.POST_TYPE_POI, text);
                addLabel(tagItem);
            }
        }
    }
}