package com.miaopu.shop.ui.activity.recommend;

import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.miaopu.shop.ui.activity.works.adapter.GuigeAdapter;
import com.miaopu.shop.ui.base.BaseStoreFragment;
import com.zzh.sexual.secret.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2018/1/17.
 *
 * @date: 2018/1/17
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 产品参数
 */
public class ProduceFragment extends BaseStoreFragment {
    @BindView(R.id.tv_zhiwupinming)
    TextView tv_zhiwupinming;
    @BindView(R.id.tv_color)
    TextView tv_color;
    @BindView(R.id.tv_shangjia)
    TextView tv_shangjia;
    @BindView(R.id.tv_huaqi)
    TextView tv_huaqi;
    @BindView(R.id.tv_zhiwupinming2)
    TextView tvZhiwupinming2;
    @BindView(R.id.tv_wuzhongpinming)
    TextView tvWuzhongpinming;
    @BindView(R.id.tv_zhiwuleibie)
    TextView tvZhiwuleibie;
    @BindView(R.id.tv_zhiwugongneng)
    TextView tvZhiwugongneng;
    @BindView(R.id.tv_shiyingweihzi)
    TextView tvShiyingweihzi;
    @BindView(R.id.tv_shiyingdiyu)
    TextView tvShiyingdiyu;
    @BindView(R.id.tv_shiyingjijie)
    TextView tvShiyingjijie;
    @BindView(R.id.tv_yizainandu)
    TextView tvYizainandu;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_produce;
    }

    @Override
    protected void initView(View fragment) {
        ButterKnife.bind(this, fragment);
    }

    @Override
    protected void initData() {
        adapter = new GuigeAdapter(mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setViewListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    public void bindData(String pinming, String color, String shelvesTime,
                         String flowerTime, String pinzhong, String leibie,
                         String gongneng,String norms,
                         String weizhi, String diyu, String jijie,
                         String nandu) {

        tv_zhiwupinming.setText("植物品名：" + pinming);
        tv_color.setText("颜色：" + color);
        tv_shangjia.setText("上架周期：" + shelvesTime);
        tv_huaqi.setText("花期：" + flowerTime);
        JsonToMap(norms);
        tvZhiwupinming2.setText(pinming);
        tvWuzhongpinming.setText(pinzhong);
        tvZhiwuleibie.setText(leibie);
        tvZhiwugongneng.setText(gongneng);
        tvShiyingweihzi.setText(weizhi);
        tvShiyingdiyu.setText(diyu);
        tvShiyingjijie.setText(jijie);
        tvYizainandu.setText(nandu);
    }

    private List<Map<String, String>> guigeList = new ArrayList<>();
    private GuigeAdapter adapter;
    private void JsonToMap(String str){
        Gson gson = new Gson();
        Map<String, String> map = new HashMap<>();
        map = gson.fromJson(str, map.getClass());
        Map<String, String> mapStr ;
        Iterator<String> iter = map.keySet().iterator();
        while(iter.hasNext()){
            mapStr = new HashMap<>();
            String key=iter.next();
            String value = map.get(key);
            mapStr.put(key, value);
            guigeList.add(mapStr);
        }
        adapter.setDataList(guigeList);
        adapter.notifyDataSetChanged();
    }
}
