package com.miaopu.shop.ui.model;

import java.util.List;

/**
 * Created by ZZH on 2018/2/24.
 *
 * @Date: 2018/2/24
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description:
 */
public class ConfirmOrder {

    /**
     * carts : [{"id":"4cd85ef95a6f405688b3fbc4c54ae3c2","productId":"f2f8f2291c9040918ff5af13080a96a4","productVersion":39,"number":2}]
     * deliveryId : fbee9af884f945369ea7b3d9da5c1e14
     */

    private String deliveryId;
    private String engineeringId;
    private List<CartsBean> carts;

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public List<CartsBean> getCarts() {
        return carts;
    }

    public void setCarts(List<CartsBean> carts) {
        this.carts = carts;
    }

    public String getEngineeringId() {
        return engineeringId;
    }

    public void setEngineeringId(String engineeringId) {
        this.engineeringId = engineeringId;
    }

    public static class CartsBean {
        /**
         * id : 4cd85ef95a6f405688b3fbc4c54ae3c2
         * productId : f2f8f2291c9040918ff5af13080a96a4
         * productVersion : 39
         * number : 2
         */

        private String id;
        private String productId;
        private int productVersion;
        private int number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public int getProductVersion() {
            return productVersion;
        }

        public void setProductVersion(int productVersion) {
            this.productVersion = productVersion;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}
