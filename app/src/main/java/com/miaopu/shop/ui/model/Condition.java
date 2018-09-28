package com.miaopu.shop.ui.model;

import java.util.List;

/**
 * Created by ZZH on 2018/2/25.
 *
 * @Date: 2018/2/25
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 商品页，筛选条件
 */
public class Condition {

    private List<String> fitSeason;
    private List<String> fitRegion;

    public List<String> getFitSeason() {
        return fitSeason;
    }

    public void setFitSeason(List<String> fitSeason) {
        this.fitSeason = fitSeason;
    }

    public List<String> getFitRegion() {
        return fitRegion;
    }

    public void setFitRegion(List<String> fitRegion) {
        this.fitRegion = fitRegion;
    }
}
