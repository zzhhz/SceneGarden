package com.miaopu.shop.ui.base;

import com.zzh.zlibs.base.BaseFragment;

/**
 * Created by user on 2018/1/2.
 *
 * @date: 2018/1/2
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description:
 */
public abstract class BaseStoreFragment extends BaseFragment {
    protected boolean                       isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {
    }

    protected void lazyLoad() {
    }
}
