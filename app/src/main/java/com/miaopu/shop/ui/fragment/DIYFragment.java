package com.miaopu.shop.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.luck.picture.lib.permissions.Permission;
import com.luck.picture.lib.permissions.RxPermissions;
import com.miaopu.shop.ui.adapter.SingleImageAdapter;
import com.miaopu.shop.ui.base.BaseStoreFragment;
import com.miaopu.shop.ui.camera.PhotoProcessActivity;
import com.miaopu.shop.ui.model.Diybg;
import com.miaopu.shop.utils.Utils;
import com.miaopu.shop.utils.camera.CameraManager;
import com.zzh.horizontal.recyclerview.CardScaleHelper;
import com.zzh.horizontal.recyclerview.SpeedRecyclerView;
import com.zzh.sexual.secret.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * Created by user on 2018/1/2.
 *
 * @date: 2018/1/2
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: diy
 */
public class DIYFragment extends BaseStoreFragment implements SingleImageAdapter.OnClickViewListener {
    RxPermissions mPermissions;
    @BindView(R.id.rv_h_item_ads)
    SpeedRecyclerView rv_h_item_ads;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.toolbar_title)
    public TextView mTitle;

    private SingleImageAdapter mHorizontalAdsAdapter;
    private CardScaleHelper mCardScaleHelper = null;

    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_diy;
    }

    @Override
    protected void initView(View fragment) {
        ButterKnife.bind(this, fragment);
        mTitle.setText("DIY新建");
        mTitle.setTextColor(Color.parseColor("#ffffff"));
        mPermissions = new RxPermissions(this.getActivity());
        fragment.findViewById(R.id.btn).setOnClickListener(v -> {
            if (mPermissions.isGranted(Manifest.permission.CAMERA) && mPermissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE) && mPermissions.isGranted(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                CameraManager.getInst().openCamera(getActivity());
            } else {
                mPermissions.requestEach(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission != null && permission.granted) {
                            CameraManager.getInst().openCamera(getActivity());
                        } else {
                            Utils.toastTips(getActivity(), "缺少必要的权限");
                        }
                    }
                });
            }
        });

    }

    @Override
    protected void initData() {
        mHorizontalAdsAdapter = new SingleImageAdapter(mContext);
        mCardScaleHelper = new CardScaleHelper();
        rv_h_item_ads.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rv_h_item_ads.setAdapter(mHorizontalAdsAdapter);
        mCardScaleHelper.attachToRecyclerView(rv_h_item_ads);
        List<Diybg> list = new ArrayList<>();
        list.add(new Diybg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538128327230&di=b69c2ee07a06770192631736f4d67c14&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F93%2F10%2F44g58PICTnp_1024.jpg"));
        list.add(new Diybg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538128327230&di=8948fd128af950469b36c52461254315&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F15%2F35%2F85%2F86m58PICrvt_1024.jpg"));
        list.add(new Diybg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538128327229&di=c825b3dd422d79a3c87a22f777c8130d&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3D73d16a236b59252db71a15445de36657%2Fb3b7d0a20cf431ad61c7cb004036acaf2edd98f6.jpg"));
        list.add(new Diybg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538128327229&di=56b2d894acc08ef34364bca990e2253f&imgtype=0&src=http%3A%2F%2Fimg.fuwo.com%2Fattachment%2F1606%2F02%2F5a38d31c286311e6a26200163e00254c.jpg"));
        list.add(new Diybg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538128327229&di=cc74281638a7e3b453d1970f4a7f89c7&imgtype=0&src=http%3A%2F%2Fpic16.photophoto.cn%2F20100831%2F0016027965387192_b.jpg"));
        list.add(new Diybg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538128327228&di=93bb28a8051f7edda113a577c65ada04&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F63%2F39%2F27V58PICXAn_1024.jpg"));
        mHorizontalAdsAdapter.addAll(list);
        mHorizontalAdsAdapter.notifyDataSetChanged();
    }

    @Override
    public void setViewListener() {

        mHorizontalAdsAdapter.setClickViewListener(this);
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View view, int position, String path) {
        if (mPermissions.isGranted(Manifest.permission.CAMERA) && mPermissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE) && mPermissions.isGranted(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Intent newIntent = new Intent(mContext, PhotoProcessActivity.class);
            newIntent.setData(Uri.parse(path));
            mContext.startActivity(newIntent);
        } else {
            mPermissions.requestEach(Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Consumer<Permission>() {
                @Override
                public void accept(Permission permission) throws Exception {
                    if (permission != null && permission.granted) {
                        Intent newIntent = new Intent(mContext, PhotoProcessActivity.class);
                        newIntent.setData(Uri.parse(path));
                        mContext.startActivity(newIntent);
                    } else {
                        Utils.toastTips(getActivity(), "缺少必要的权限");
                    }
                }
            });
        }
    }
}
