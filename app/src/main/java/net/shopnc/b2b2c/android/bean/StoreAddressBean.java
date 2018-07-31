package net.shopnc.b2b2c.android.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanshuo on 2017/10/10.
 */

public class StoreAddressBean implements Parcelable {

    /**
     * addr_list : [{"area_id":"55","area_info":"天津 天津市 和平区","area_name":"和平区","chain_address":"和平区滨江道","chain_id":"1","chain_name":"和平旗舰店","chain_phone":"02288888888","shopnc_chain_price":"699.00"},{"area_id":"60","area_info":"天津 天津市 红桥区","area_name":"红桥区","chain_address":"天津市红桥区湘潭路22号","chain_id":"2","chain_name":"红桥湘潭路分店","chain_phone":"022-25555555","shopnc_chain_price":"699.00"},{"area_id":"60","area_info":"天津 天津市 红桥区","area_name":"红桥区","chain_address":"天津市红桥区丁字估一号路201号","chain_id":"3","chain_name":"红桥丁字估分店","chain_phone":"022-54874444","shopnc_chain_price":"699.00"},{"area_id":"60","area_info":"天津 天津市 红桥区","area_name":"红桥区","chain_address":"天津市红桥区西青道4号（民族中学对过）","chain_id":"4","chain_name":"红桥区西青道分店","chain_phone":"022-20345547","shopnc_chain_price":"699.00"},{"area_id":"58","area_info":"天津 天津市 南开区","area_name":"南开区","chain_address":"天津市南开区红旗路220号慧谷大厦","chain_id":"5","chain_name":"南开红旗路店","chain_phone":"022-65444444","shopnc_chain_price":"699.00"},{"area_id":"57","area_info":"天津 天津市 河西区","area_name":"河西区","chain_address":"天津市河西区围堤道90号（中环）","chain_id":"6","chain_name":"河西区围堤道分店","chain_phone":"022-25555555","shopnc_chain_price":"699.00"},{"area_id":"56","area_info":"天津 天津市 河东区","area_name":"河东区","chain_address":"天津市河东区东兴路与津趟路交口","chain_id":"7","chain_name":"天津河东旗舰店","chain_phone":"022-45784545","shopnc_chain_price":"699.00"},{"area_id":"59","area_info":"天津 天津市 河北区","area_name":"河北区","chain_address":"天津市河北区真理道与海门路交口","chain_id":"8","chain_name":"河北区海门路店","chain_phone":"022-45784545","shopnc_chain_price":"699.00"},{"area_id":"61","area_info":"天津 天津市 塘沽区","area_name":"塘沽区","chain_address":"天津市塘沽区大港油田1号","chain_id":"9","chain_name":"塘沽分店","chain_phone":"022-45784545","shopnc_chain_price":"699.00"},{"area_id":"64","area_info":"天津 天津市 东丽区","area_name":"东丽区","chain_address":"天津市东丽区张贵庄1排1号","chain_id":"10","chain_name":"东丽总店","chain_phone":"022-20345547","shopnc_chain_price":"699.00"},{"area_id":"65","area_info":"天津 天津市 西青区","area_name":"西青区","chain_address":"天津市西青区西青道240号","chain_id":"11","chain_name":"天津市西青分店","chain_phone":"022-20345547","shopnc_chain_price":"699.00"}]
     * baidu_city :
     */

    private String baidu_city;
    private List<AddrListBean> addr_list;

    public String getBaidu_city() {
        return baidu_city;
    }

    public void setBaidu_city(String baidu_city) {
        this.baidu_city = baidu_city;
    }

    public List<AddrListBean> getAddr_list() {
        return addr_list;
    }

    public void setAddr_list(List<AddrListBean> addr_list) {
        this.addr_list = addr_list;
    }

    public static class AddrListBean implements Parcelable {
        /**
         * area_id : 55
         * area_info : 天津 天津市 和平区
         * area_name : 和平区
         * chain_address : 和平区滨江道
         * chain_id : 1
         * chain_name : 和平旗舰店
         * chain_phone : 02288888888
         * shopnc_chain_price : 699.00
         */

        private String area_id;
        private String area_info;
        private String area_name;
        private String chain_address;
        private String chain_id;
        private String chain_name;
        private String chain_phone;
        private String shopnc_chain_price;

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getArea_info() {
            return area_info;
        }

        public void setArea_info(String area_info) {
            this.area_info = area_info;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getChain_address() {
            return chain_address;
        }

        public void setChain_address(String chain_address) {
            this.chain_address = chain_address;
        }

        public String getChain_id() {
            return chain_id;
        }

        public void setChain_id(String chain_id) {
            this.chain_id = chain_id;
        }

        public String getChain_name() {
            return chain_name;
        }

        public void setChain_name(String chain_name) {
            this.chain_name = chain_name;
        }

        public String getChain_phone() {
            return chain_phone;
        }

        public void setChain_phone(String chain_phone) {
            this.chain_phone = chain_phone;
        }

        public String getShopnc_chain_price() {
            return shopnc_chain_price;
        }

        public void setShopnc_chain_price(String shopnc_chain_price) {
            this.shopnc_chain_price = shopnc_chain_price;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.area_id);
            dest.writeString(this.area_info);
            dest.writeString(this.area_name);
            dest.writeString(this.chain_address);
            dest.writeString(this.chain_id);
            dest.writeString(this.chain_name);
            dest.writeString(this.chain_phone);
            dest.writeString(this.shopnc_chain_price);
        }

        public AddrListBean() {
        }

        protected AddrListBean(Parcel in) {
            this.area_id = in.readString();
            this.area_info = in.readString();
            this.area_name = in.readString();
            this.chain_address = in.readString();
            this.chain_id = in.readString();
            this.chain_name = in.readString();
            this.chain_phone = in.readString();
            this.shopnc_chain_price = in.readString();
        }

        public static final Creator<AddrListBean> CREATOR = new Creator<AddrListBean>() {
            @Override
            public AddrListBean createFromParcel(Parcel source) {
                return new AddrListBean(source);
            }

            @Override
            public AddrListBean[] newArray(int size) {
                return new AddrListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.baidu_city);
        dest.writeList(this.addr_list);
    }

    public StoreAddressBean() {
    }

    protected StoreAddressBean(Parcel in) {
        this.baidu_city = in.readString();
        this.addr_list = new ArrayList<AddrListBean>();
        in.readList(this.addr_list, AddrListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<StoreAddressBean> CREATOR = new Parcelable.Creator<StoreAddressBean>() {
        @Override
        public StoreAddressBean createFromParcel(Parcel source) {
            return new StoreAddressBean(source);
        }

        @Override
        public StoreAddressBean[] newArray(int size) {
            return new StoreAddressBean[size];
        }
    };
}
