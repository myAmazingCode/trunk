package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/4/28 9:55
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * @description
 */
public class GoodsDetailBean {


    /**
     * goodsAttrVoList : [{"name":"品牌","value":"123123"},{"name":"材质","value":"毛"},{"name":"包装","value":"大包"}]
     * commitment : null
     * protection : <p><span style="color: rgb(255, 0, 0);"><strong>权利声明：</strong></span><br/>商城上的所有商品信息、客户评价、商品咨询、网友讨论等内容，是商城重要的经营资源，未经许可，禁止非法转载使用。</p><p><span style="color: rgb(63, 63, 63);"><strong>注：</strong></span>本站商品信息均来自于合作方，其真实性、准确性和合法性由信息拥有者（合作方）负责。本站不提供任何保证，并不承担任何法律责任。</p><p><br/><span style="color: rgb(255, 0, 0);"><strong>价格说明：</strong></span><br/></p><p><span style="color: rgb(63, 63, 63);"><strong>价格：</strong></span>价格为商品的销售价，是您最终决定是否购买商品的依据。</p><p><span style="color: rgb(63, 63, 63);"><strong>划线价：</strong></span>商品展示的划横线价格为参考价，该价格可能是品牌专柜标价、商品吊牌价或由品牌供应商提供的正品零售价（如厂商指导价、建议零售价等）或该商品在商城平台上曾经展示过的销售价；由于地区、时间的差异性和市场行情波动，品牌专柜标价、商品吊牌价等可能会与您购物时展示的不一致，该价格仅供您参考。</p><p><span style="color: rgb(63, 63, 63);"><strong>折扣：</strong></span>如无特殊说明，折扣指销售商在原价、或划线价（如品牌专柜标价、商品吊牌价、厂商指导价、厂商建议零售价）等某一价格基础上计算出的优惠比例或优惠金额；如有疑问，您可在购买前联系销售商进行咨询。</p><p><span style="color: rgb(63, 63, 63);"><strong>异常问题：</strong></span>商品促销信息以商品详情页“促销”栏中的信息为准；商品的具体售价以订单结算页价格为准；如您发现活动商品售价或促销信息有异常，建议购买前先联系销售商咨询。</p><p><br/></p>
     * goodsMobileBodyVoList : [{"type":"image","value":"http://192.168.1.232/upload/image/96/be/96be3b165623d35544cf48e17909eede.png"},{"type":"image","value":"http://192.168.1.232/upload/image/46/7a/467a48dbe5d6167c15c83a4632ae1c71.png"},{"type":"image","value":"http://192.168.1.232/upload/image/5c/99/5c998582ed0ca619e883f819ce9c2827.png"},{"type":"image","value":"http://192.168.1.232/upload/image/9a/a2/9aa226c7ffb6faf624bc15d1e4e454d8.png"},{"type":"image","value":"http://192.168.1.232/upload/image/95/4c/954c12a17ce7d56ab57233cd079b2f4c.png"},{"type":"image","value":"http://192.168.1.232/upload/image/96/be/96be3b165623d35544cf48e17909eede.png"},{"type":"image","value":"http://192.168.1.232/upload/image/6c/6d/6c6debb7cf5deac9624c06bbd8fac86d.png"},{"type":"image","value":"http://192.168.1.232/upload/image/75/85/75857601749ad0a3b1bc2978f90e9722.png"},{"type":"image","value":"http://192.168.1.232/upload/image/24/15/24151016b1a318c2bdccf2fdfb7a61a1.png"},{"type":"image","value":"http://192.168.1.232/upload/image/a1/f8/a1f88f1211aee6d3ec0cd870af3295d4.png"},{"type":"image","value":"http://192.168.1.232/upload/image/88/9e/889e771fabae9181b14a7462b7616fdc.png"},{"type":"image","value":"http://192.168.1.232/upload/image/39/77/3977a680e4f17005272f44c96c19261f.png"},{"type":"image","value":"http://192.168.1.232/upload/image/61/25/6125a40cfd1def2f0cb0da87b868e827.png"},{"type":"image","value":"http://192.168.1.232/upload/image/95/4c/954c12a17ce7d56ab57233cd079b2f4c.png"},{"type":"image","value":"http://192.168.1.232/upload/image/9a/a2/9aa226c7ffb6faf624bc15d1e4e454d8.png"},{"type":"image","value":"http://192.168.1.232/upload/image/46/7a/467a48dbe5d6167c15c83a4632ae1c71.png"},{"type":"image","value":"http://192.168.1.232/upload/image/5c/99/5c998582ed0ca619e883f819ce9c2827.png"},{"type":"image","value":"http://192.168.1.232/upload/image/02/9f/029ff88f42e3aa23abe65b39c1172a5c.png"}]
     */

    private String commitment;
    private String protection;
    private List<GoodsAttrVoListBean> goodsAttrVoList;
    private List<GoodsMobileBodyVoListBean> goodsMobileBodyVoList;

    public String getCommitment() {
        return commitment;
    }

    public void setCommitment(String commitment) {
        this.commitment = commitment;
    }

    public String getProtection() {
        return protection;
    }

    public void setProtection(String protection) {
        this.protection = protection;
    }

    public List<GoodsAttrVoListBean> getGoodsAttrVoList() {
        return goodsAttrVoList;
    }

    public void setGoodsAttrVoList(List<GoodsAttrVoListBean> goodsAttrVoList) {
        this.goodsAttrVoList = goodsAttrVoList;
    }

    public List<GoodsMobileBodyVoListBean> getGoodsMobileBodyVoList() {
        return goodsMobileBodyVoList;
    }

    public void setGoodsMobileBodyVoList(List<GoodsMobileBodyVoListBean> goodsMobileBodyVoList) {
        this.goodsMobileBodyVoList = goodsMobileBodyVoList;
    }

    public static class GoodsAttrVoListBean {
        /**
         * name : 品牌
         * value : 123123
         */

        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class GoodsMobileBodyVoListBean {
        /**
         * type : image
         * value : http://192.168.1.232/upload/image/96/be/96be3b165623d35544cf48e17909eede.png
         */

        private String type;
        private String value;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
