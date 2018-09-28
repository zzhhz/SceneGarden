package com.miaopu.shop.web;

import com.miaopu.shop.ui.model.Balance;
import com.miaopu.shop.ui.model.BrandStory;
import com.miaopu.shop.ui.model.Comment;
import com.miaopu.shop.ui.model.Condition;
import com.miaopu.shop.ui.model.Contractor;
import com.miaopu.shop.ui.model.Count;
import com.miaopu.shop.ui.model.DIYProduct;
import com.miaopu.shop.ui.model.DeliveryList;
import com.miaopu.shop.ui.model.DesWithConsList;
import com.miaopu.shop.ui.model.Design;
import com.miaopu.shop.ui.model.DictArea;
import com.miaopu.shop.ui.model.Diybg;
import com.miaopu.shop.ui.model.Engineer;
import com.miaopu.shop.ui.model.Fee;
import com.miaopu.shop.ui.model.HomeItemPoster;
import com.miaopu.shop.ui.model.Order;
import com.miaopu.shop.ui.model.PersonTag;
import com.miaopu.shop.ui.model.Poster;
import com.miaopu.shop.ui.model.Product;
import com.miaopu.shop.ui.model.RenZheng;
import com.miaopu.shop.ui.model.Resp;
import com.miaopu.shop.ui.model.RespListModel;
import com.miaopu.shop.ui.model.RespModel;
import com.miaopu.shop.ui.model.ShopCar;
import com.miaopu.shop.ui.model.Tags;
import com.miaopu.shop.ui.model.User;
import com.miaopu.shop.ui.model.Works;
import com.miaopu.shop.ui.model.WorksData;
import com.miaopu.shop.ui.model.WrapperModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by user on 2017/6/23.
 *
 * @date: 2017/6/23
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 网络访问接口
 */
public interface ApiService {
    /**
     * 发朋友圈
     * "http://api.yiye.jinantejia.com/cyye/postAction.do?reqCode=savePost"
     *
     * @param picMap
     * @return
     */
    @Multipart
    @POST("postAction.do")
    Observable<ResponseBody> sendCircle(@PartMap Map<String, RequestBody> picMap);

    @Multipart
    @POST("postAction.do?reqCode=savePost")
    Observable<ResponseBody> sendCircle(@Part("reqCode") String savePost, @PartMap Map<String, RequestBody> picMap);

    /**
     * 注册
     */
    @POST("shop/member/register")
    Observable<RespModel<String>> register(@Body RequestBody json);

    /**
     * @return
     */
    @POST("shop/member/queryByPage?pageNo=1&pageSize=15")
    Observable<ResponseBody> register2(@Body RequestBody json);

    /**
     * 订单列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("order/list")
    Observable<RespModel<WrapperModel<Order>>> orderList(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Field("status") String status);

    /**
     * 取消订单
     *
     * @return
     */
    @POST("order/cancel")
    Observable<RespModel<String>> cancelOrder(@Query("id") String id);

    /**
     * 删除订单
     *
     * @return
     */
    @POST("order/delete")
    Observable<RespModel<String>> deleteOrder(@Query("id") String id);

    /**
     * 确认收货
     *
     * @return
     */
    @POST("order/confirm")
    Observable<RespModel<String>> confirmOrder(@Query("id") String id);

    /**
     * 评价订单
     *
     * @return
     */
    @POST("order/evaluate")
    Observable<RespModel<String>> evaluateOrder(@Query("orderId") String orderId, @Query("itemId") String itemId, @Body RequestBody json);

    /**
     * 标签选项值
     *
     * @return
     */
    @POST("dict/list/yhbq")
    Observable<RespListModel<PersonTag>> personTag();

    /**
     * 个人资料  修改和新建
     *
     * @return
     */
    @POST("member/save")
    Observable<RespModel<String>> saveMember(@Body RequestBody json);

    /**
     * 地址管理列表
     *
     * @return
     */
    @POST("shop/memDelivery/getDelivery")
    Observable<RespListModel<DeliveryList>> deliveryList();

    /**
     * 地址管理  保存修改
     *
     * @return
     */
    @POST("shop/memDelivery/save")
    Observable<RespModel<String>> saveDelivery(@Body RequestBody json);

    /**
     * 地址管理  删除
     *
     * @return
     */
    @POST("shop/memDelivery/delete")
    Observable<RespModel<String>> deleteDelivery(@Query("id") String id);

    /**
     * 地址管理  设置默认地址
     *
     * @return
     */
    @POST("shop/memDelivery/setDefault")
    Observable<RespModel<String>> setDefaultDelivery(@Query("id") String id);

    /**
     * 全部设计师
     *
     * @return
     */
    @POST("member/queryDesWithCons")
    Observable<RespModel<WrapperModel<DesWithConsList>>> alldesWithConsList(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Body RequestBody json);

    /**
     * 我的设计师
     *
     * @return
     */
    @POST("shop/collection/queryByPage")
    Observable<RespModel<WrapperModel<DesWithConsList>>> collectionDesList(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Body RequestBody json);

    /**
     * 关注施工方或者设计师
     *
     * @return
     */
    @POST("shop/collection/add")
    Observable<RespModel<String>> addCollectionDes(@Body RequestBody json);

    /**
     * 取消关注
     *
     * @return
     */
    @POST("shop/collection/cancel")
    Observable<RespModel<String>> cancelCollectionDes(@Body RequestBody json);

    /**
     * 认证设计师 获取设计师领域
     *
     * @return
     */
    @POST("dict/list/workscategory")
    Observable<RespModel<List<DictArea>>> dictArea();

    /**
     * 认证施工队 获取施工队领域
     *
     * @return
     */
    @POST("dict/list/area")
    Observable<RespModel<List<DictArea>>> shigongduiArea();

    /**
     * 设计师 提交审核
     *
     * @return
     */
    @POST("shop/member/beDesigner")
    Observable<RespModel<String>> beDesigner(@Body RequestBody json);

    /**
     * 施工队 提交审核
     *
     * @return
     */
    @POST("shop/member/beContractor")
    Observable<RespModel<String>> beContractor(@Body RequestBody json);

    /**
     * 认证失败后  获取理由和上传的资料
     *
     * @return
     */
    @POST("memberExt/getByMemberId")
    Observable<RespModel<RenZheng>> getRenzhengExt(@Query("id") String id);

    /**
     * 获取验证码
     * mobile
     *
     * @param json mobile
     * @return
     */
    @POST("sms/send")
    Observable<RespModel<String>> getCode(@Body RequestBody json);

    /**
     * 获取当前用户信息
     *
     * @param
     * @return
     */
    @POST("member/getCurrentInfo")
    Observable<RespModel<User>> getCurrentInfo();

    /**
     * 获取购物车数量
     *
     * @param
     * @return
     */
    @POST("mobile/getCountInfo")
    Observable<RespModel<Count>> getCountInfo();

    /**
     * 获取验证码
     *
     * @param id
     * @return
     */
    @POST("shop/design/get")
    Observable<RespModel<Works>> getWorkDetail(@Query("id") String id);

    /**
     * 获取验证码
     *
     * @param json
     * @return
     */
    @POST("shop/member/login")
    Observable<RespModel<String>> login(@Body RequestBody json);

    /**
     * 获取作品标签
     *
     * @return
     */
    @POST("dict/list/designtag")
    Observable<RespModel<List<Tags>>> getDesignTag();

    /**
     * 获取作品标签
     *
     * @param pageSize
     * @param pageNo
     * @param json
     * @return
     */
    @POST("order/list")
    Observable<RespModel<WrapperModel<Order>>> getOrderList(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Body RequestBody json);

    /**
     * 首页轮播图
     *
     * @return
     */
    @POST("homebanner/queryHomeBanner")
    Observable<RespListModel<Poster>> getHomeBanner();

    /**
     * 首页推荐商品
     *
     * @return
     */
    @POST("product/queryHomeProducts")
    Observable<RespListModel<Product>> getHomeProducts();

    /**
     * 首页推荐作品
     *
     * @return
     */
    @POST("shop/design/queryRecommendDesign")
    Observable<RespListModel<Works>> getHomeWorks();

    /**
     * 首页推荐作品
     *
     * @return
     */
    @POST("category/queryProductCategory")
    Observable<RespListModel<HomeItemPoster>> getHomeCategory();

    /**
     * 品牌故事列表
     *
     * @param pageNo
     * @param pageSize
     * @param json
     * @return
     */
    @POST("shop/article/queryByPageWithMap")
    Observable<RespModel<WrapperModel<BrandStory>>> getBrandStoryList(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Body RequestBody json);

    /**
     * 首页推荐作品
     *
     * @param pageNo
     * @param pageSize
     * @param json
     * @return
     */
    @POST("search/queryByPage")
    Observable<RespModel<WrapperModel<Product>>> getQueryProducts(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Body RequestBody json);

    /**
     * 首页推荐作品
     *
     * @param pageNo
     * @param pageSize
     * @param json
     * @return
     */
    @POST("shop/design/queryDesignByPage")
    Observable<RespModel<WrapperModel<Works>>> getQueryWorks(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Body RequestBody json);

    /**
     * DIY背景列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @POST("dict/queryByParam")
    Observable<RespModel<WrapperModel<Diybg>>> getDiyBgList(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("search_pid") String id);

    /**
     * DIY贴图列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @POST("search/queryImagByPage")
    Observable<RespModel<WrapperModel<DIYProduct>>> getDiyProductList(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Body RequestBody json);

    /**
     * 我的作品列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @POST("shop/design/queryMyDesignByPage")
    Observable<RespModel<WrapperModel<Works>>> getQueryMyWorks(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Body RequestBody json);

    /**
     * 创建作品 获取标签
     */
    @POST("shop/design/queryDict")
    Observable<RespModel<WorksData>> getWorksData();
    /**
     * 创建作品 获取标签
     */
    @POST("dict/list/casestyle")
    Observable<RespModel<List<Tags>>> getCaseStyle();

    /**
     * 创建作品 创建
     */
    @POST("shop/design/save")
    Observable<RespModel<String>> confrimWorksData(@Body RequestBody json);
    /**
     * 创建案例 创建
     */
    @POST("shop/case/save")
    Observable<RespModel<String>> confrimCaseData(@Body RequestBody json);

    /**
     * 我的施工案例
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @POST("shop/case/queryMyCaseByPageWithMap")
    Observable<RespModel<WrapperModel<Works>>> getMyCase(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Body RequestBody json);

    /**
     * 我的施工案例
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @POST("shop/case/queryByPageWithMap")
    Observable<RespModel<WrapperModel<Works>>> queryByPageWithMap(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Body RequestBody json);

    /**
     * 案例详情
     *
     * @param id
     * @return
     */
    @POST("shop/case/get")
    Observable<RespModel<Works>> getCaseDetail(@Query("id") String id);

    /**
     * 设计师详情（他的作品
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @POST("shop/design/queryMyDesignByPage")
    Observable<RespModel<WrapperModel<Works>>> queryMyDesignByPage(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Body RequestBody json);

    /**
     * 我的项目
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @POST("shop/engineering/queryByPage")
    Observable<RespModel<WrapperModel<Engineer>>> getMyEngineer(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Body RequestBody json);

    /**
     * 我的设计作品
     *
     * @return
     */
    @POST("shop/design/queryAll")
    Observable<RespModel<List<Design>>> getAllDesign(@Body RequestBody json);

    /**
     * 所有施工队
     */
    @POST("shop/member/queryAll")
    Observable<RespModel<List<Contractor>>> getAllMember(@Body RequestBody json);

    /**
     * 创建我的项目
     */
    @POST("shop/engineering/save")
    Observable<RespModel<String>> saveEngineer(@Body RequestBody json);

    /**
     * 打赏列表
     */
    @POST("shop/memberFee/queryByPage")
    Observable<RespModel<WrapperModel<Fee>>> getFeeList(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Body RequestBody json);

    /**
     * 商品详情
     *
     * @param id
     * @return
     */
    @POST("search/get")
    Observable<RespModel<Product>> getProductDetail(@Query("id") String id);

    /**
     * 商品评价
     *
     * @return
     */
    @POST("shop/sysComment/list")
    Observable<RespModel<WrapperModel<Comment>>> getProductComment(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Body RequestBody json);

    /**
     * 加入购物车
     *
     * @return
     */
    @POST("shop/shoppingcart/addToCart")
    Observable<RespModel<String>> addShopCar(@Query("number") int number, @Query("productId") String id, @Query("ids") String ids);

    /**
     * 加入购物车
     *
     * @return
     */
    @POST("shop/shoppingcart/addToCart")
    Observable<RespModel<String>> addShopCar(@Query("number") int number, @Query("productId") String id);

    /**
     * 加入购物车，参数number修改
     *
     * @return
     */
    @POST("shop/shoppingcart/updateCart")
    Observable<RespListModel<ShopCar>> updateShopCarNumber(@Query("number") int number, @Query("id") String id, @Query("ids") String ids);

    @POST("shop/shoppingcart/updateCart")
    Observable<RespListModel<ShopCar>> updateShopCarNumber(@Body RequestBody body);

    @POST("shop/shoppingcart/updateCart")
    Observable<RespListModel<ShopCar>> updateShopCarNumber(@Query("number") int number, @Query("id") String id);

    /**
     * 删除购物车
     *
     * @return
     */
    @POST("shop/shoppingcart/delete")
    Observable<RespModel<String>> deleteShopCar(@Body RequestBody body);


    /**
     * 加入购物车
     *
     * @return
     */
    @POST("shop/shoppingcart/cartView")
    Observable<RespListModel<ShopCar>> getShopCarData(@Body RequestBody json);

    /**
     * 加入购物车
     *
     * @return
     */
    @POST("shop/sysComment/save")
    Observable<RespModel<String>> sendComment(@Body RequestBody json);

    /**
     * 打赏
     *
     * @return
     */
    @POST("shop/memberFee/save")
    Observable<RespModel<Fee>> fee(@Body RequestBody json);

    /**
     * 打赏
     * shop/shoppingcart/prepareOrder?refresh=false
     * {"ids":["4cd85ef95a6f405688b3fbc4c54ae3c2","f0c521ce2840499eb8d5b4f62b519c81"]}
     *
     * @return
     */
    @POST("shop/shoppingcart/prepareOrder")
    Observable<RespModel<Balance>> toBalance(@Query("refresh") String refresh, @Body RequestBody json);

    /**
     * 确认订单
     * shop/shoppingcart/prepareOrder?refresh=false
     * {"ids":["4cd85ef95a6f405688b3fbc4c54ae3c2","f0c521ce2840499eb8d5b4f62b519c81"]}
     *
     * @return
     */
    @POST("order/makeOrder")
    Observable<RespModel<String>> confirmBalance(@Body RequestBody json);
    /**
     * 确认订单
     * shop/shoppingcart/prepareOrder?refresh=false
     * {"ids":["4cd85ef95a6f405688b3fbc4c54ae3c2","f0c521ce2840499eb8d5b4f62b519c81"]}
     *
     * @return
     */
    @POST("order/makeOrder")
    Observable<Resp> confirmBalance2(@Body RequestBody json);

    /**
     * 确认订单
     * shop/shoppingcart/prepareOrder?refresh=false
     * {"ids":["4cd85ef95a6f405688b3fbc4c54ae3c2","f0c521ce2840499eb8d5b4f62b519c81"]}
     *
     * @return
     */
    @POST("order/egMakeOrder")
    Observable<RespModel<String>> confirmOfflineBalance(@Body RequestBody json);

    /**
     * 确认订单
     * shop/shoppingcart/prepareOrder?refresh=false
     * {"ids":["4cd85ef95a6f405688b3fbc4c54ae3c2","f0c521ce2840499eb8d5b4f62b519c81"]}
     * search/queryAttr  {"title":"其他"}
     *
     * @return
     */
    @POST("search/queryAttr")
    Observable<RespModel<Condition>> getInitProduct(@Body RequestBody json);


    /**
     * 一键下单
     *
     * @return
     */
    @POST("shop/engineering/queryProductByEngineeringId")
    Observable<RespModel<List<ShopCar>>> queryProductByEngineeringId(@Query("refresh") String refresh, @Query("id") String json);

    /**
     * 一键下单
     *
     * @return
     */
    @POST("order/diyMakeOrder")
    Observable<RespModel<String>> diyMakeOrder(@Body RequestBody json);
    /**
     * 确认施工
     * shop/engineering/updateState
     * @return
     */
    @POST("shop/engineering/updateState")
    Observable<RespModel<String>> engineeringUpdateState(@Body RequestBody json);
}
