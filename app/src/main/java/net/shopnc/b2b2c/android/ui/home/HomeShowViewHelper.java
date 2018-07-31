package net.shopnc.b2b2c.android.ui.home;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.Home3GridViewAdapter;
import net.shopnc.b2b2c.android.adapter.HomeGoodsGridViewAdapter;
import net.shopnc.b2b2c.android.bean.ApiSpecialItem;
import net.shopnc.b2b2c.android.bean.ItemData;
import net.shopnc.b2b2c.android.bean.ItemGoods;
import net.shopnc.b2b2c.android.bean.ItemText;
import net.shopnc.b2b2c.android.circlelibrary.ImageCycleView;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.custom.marqueeview.MarqueeView;
import net.shopnc.b2b2c.android.util.LoadImage;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页界面显示帮助类
 *
 * @author huting
 * @date 2016/5/3
 */
public class HomeShowViewHelper {
    private Context context;
    private MyShopApplication application;
    private LinearLayout homeViewID;
    private ImageCycleView cycleView;
    private List<ItemData> itemDataList;
    private MarqueeView marqueeView;

    public HomeShowViewHelper(Context context, LinearLayout homeViewID, ImageCycleView cycleView) {
        this.context = context;
        this.homeViewID = homeViewID;
        this.cycleView = cycleView;
        application = (MyShopApplication) context.getApplicationContext();
    }

    /**
     * 显示轮播图
     */
    public void showAD(ApiSpecialItem apiSpecialItem) {
        itemDataList = getData(apiSpecialItem.getItemData());
        ArrayList<String> images = new ArrayList<>();
        for (ItemData itemData : itemDataList) {
            images.add(itemData.getImageUrl());
        }

        initAd(images, apiSpecialItem);
    }

    /**
     * 没有轮播图片时调用
     */
    public void showAD() {
        ArrayList<String> images = new ArrayList<String>();
        initAd(images, null);
    }

    private int convertDpToPixel(Context mContext, int dp) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        return (int) (dp * displayMetrics.density);
    }

    /**
     * 初试化轮播
     *
     * @param images
     */
    private void initAd(ArrayList<String> images, final ApiSpecialItem apiSpecialItem) {
        cycleView.setId(R.id.home_page_id1);
        final ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                LoadImage.loadRemoteImg(context, imageView, imageURL);
            }

            @Override
            public void onImageClick(int position, View imageView) {
                if (apiSpecialItem != null) {
                    itemDataList = getData(apiSpecialItem.getItemData());
                    ItemData itemData = itemDataList.get(position);
                    OnImageViewClick(imageView, itemData.getType(), itemData.getData(), true);
                }
            }
        };
        cycleView.setImageResources(images, mAdCycleViewListener, true);
        cycleView.startImageCycle();
    }


    /**
     * 显示 大图
     *
     * @param apiSpecialItem item information
     */
    public void showHome1(ApiSpecialItem apiSpecialItem) {
        itemDataList = getData(apiSpecialItem.getItemData());
        for (ItemData itemData : itemDataList) {
            AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.tab_home_item_home1);
            addViewHolder.setImage(R.id.ImageViewHome1Imagepic01, itemData.getImageUrl());
            homeViewID.addView(addViewHolder.getCustomView());

            OnImageViewClick(addViewHolder.getView(R.id.ImageViewHome1Imagepic01), itemData.getType(), itemData.getData(), false);
        }
    }


    /**
     * 显示 左1右2
     */
    public void showHome2(ApiSpecialItem apiSpecialItem) {//TODO
        itemDataList = getData(apiSpecialItem.getItemData());
        if(itemDataList == null || itemDataList.size() != 3) {
            return;
        }
        AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.tab_home_item_home2_left);
        addViewHolder.setImage(R.id.ImageViewSquare, itemDataList.get(0).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewRectangle1, itemDataList.get(1).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewRectangle2, itemDataList.get(2).getImageUrl());

        homeViewID.addView(addViewHolder.getCustomView());

        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare), itemDataList.get(0).getType(), itemDataList.get(0).getData(), false);
        LogHelper.e("首页", itemDataList.get(0).getType() + "____" + itemDataList.get(0).getData());
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewRectangle1), itemDataList.get(1).getType(), itemDataList.get(1).getData(), false);
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewRectangle2), itemDataList.get(2).getType(), itemDataList.get(2).getData(), false);
        LogHelper.e("首页", itemDataList.get(0).getType() + "____" + itemDataList.get(1).getData());
        LogHelper.e("首页", itemDataList.get(0).getType() + "____" + itemDataList.get(2).getData());
    }

    /**
     * 显示 列表
     */
    public void showHome3(ApiSpecialItem apiSpecialItem) {
        itemDataList = getData(apiSpecialItem.getItemData());
        AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.tab_home_item_home3);
        Home3GridViewAdapter adapter = new Home3GridViewAdapter(context);

        addViewHolder.setGridAdapter(R.id.gridview, adapter);
        adapter.setmDatas(itemDataList);
        adapter.notifyDataSetChanged();

        homeViewID.addView(addViewHolder.getCustomView());
    }

    /**
     * 显示 左2右1
     *
     * @param apiSpecialItem
     */
    public void showHome4(ApiSpecialItem apiSpecialItem) {//TODO
        itemDataList = getData(apiSpecialItem.getItemData());
        if(itemDataList == null || itemDataList.size() != 3) {
            return;
        }
        AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.tab_home_item_home2_right);
        addViewHolder.setImage(R.id.ImageViewRectangle1, itemDataList.get(0).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewRectangle2, itemDataList.get(1).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewSquare, itemDataList.get(2).getImageUrl());

        homeViewID.addView(addViewHolder.getCustomView());

        OnImageViewClick(addViewHolder.getView(R.id.ImageViewRectangle1), "goods", itemDataList.get(0).getData(), false);
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewRectangle2), "goods", itemDataList.get(1).getData(), false);
        LogHelper.e("首页2", itemDataList.get(0).getType() + "____" + itemDataList.get(0).getData());
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare), itemDataList.get(2).getType(), itemDataList.get(2).getData(), false);
    }


    /**
     * 显示 3个单列（不清楚）
     *
     * @param apiSpecialItem
     */
    public void showHome5(ApiSpecialItem apiSpecialItem) {
        itemDataList = getData(apiSpecialItem.getItemData());
        if(itemDataList == null || itemDataList.size() != 3) {
            return;
        }
        AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.tab_home_item_home5);

        addViewHolder.setImage(R.id.ImageViewSquare1, itemDataList.get(0).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewSquare2, itemDataList.get(1).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewSquare3, itemDataList.get(2).getImageUrl());

        homeViewID.addView(addViewHolder.getCustomView());

        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare1), itemDataList.get(0).getType(), itemDataList.get(0).getData(), false);
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare2), itemDataList.get(1).getType(), itemDataList.get(1).getData(), false);
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare3), itemDataList.get(2).getType(), itemDataList.get(2).getData(), false);
    }

    /**
     * 显示  上边4个按钮
     *
     * @param apiSpecialItem
     */
    public void showHome6(ApiSpecialItem apiSpecialItem) {
        itemDataList = getData(apiSpecialItem.getItemData());
        if(itemDataList == null || itemDataList.size() != 4) {
            return;
        }
        AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.tab_home_item_home6);

        addViewHolder.setImage(R.id.ImageViewSquare1, itemDataList.get(0).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewSquare2, itemDataList.get(1).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewSquare3, itemDataList.get(2).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewSquare4, itemDataList.get(3).getImageUrl());

        homeViewID.addView(addViewHolder.getCustomView());

        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare1), itemDataList.get(0).getType(), itemDataList.get(0).getData(), false);
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare2), itemDataList.get(1).getType(), itemDataList.get(1).getData(), false);
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare3), itemDataList.get(2).getType(), itemDataList.get(2).getData(), false);
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare4), itemDataList.get(3).getType(), itemDataList.get(3).getData(), false);
    }

    /**
     * 显示  单张大图
     *
     * @param apiSpecialItem
     */
    public void showHome7(ApiSpecialItem apiSpecialItem) {
        itemDataList = getData(apiSpecialItem.getItemData());
        for (ItemData itemData : itemDataList) {
            AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.tab_home_item_home7);
            addViewHolder.setImage(R.id.ImageViewHome1Imagepic01, itemData.getImageUrl());
            homeViewID.addView(addViewHolder.getCustomView());

            OnImageViewClick(addViewHolder.getView(R.id.ImageViewHome1Imagepic01), itemData.getType(), itemData.getData(), false);
        }
    }


    /**
     * 显示home8，五列单行小图模块
     *
     * @param apiSpecialItem
     */
    public void showHome8(ApiSpecialItem apiSpecialItem) {
        itemDataList = getData(apiSpecialItem.getItemData());
        if(itemDataList == null || itemDataList.size() != 5) {
            return;
        }
        AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.tab_home_item_home8);

        addViewHolder.setImage(R.id.ImageViewSquare1, itemDataList.get(0).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewSquare2, itemDataList.get(1).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewSquare3, itemDataList.get(2).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewSquare4, itemDataList.get(3).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewSquare5, itemDataList.get(4).getImageUrl());

        homeViewID.addView(addViewHolder.getCustomView());

        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare1), itemDataList.get(0).getType(), itemDataList.get(0).getData(), false);
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare2), itemDataList.get(1).getType(), itemDataList.get(1).getData(), false);
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare3), itemDataList.get(2).getType(), itemDataList.get(2).getData(), false);
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare4), itemDataList.get(3).getType(), itemDataList.get(3).getData(), false);
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare5), itemDataList.get(4).getType(), itemDataList.get(4).getData(), false);
    }

    /**
     * 显示home9，左一右二竖排模块
     *
     * @param apiSpecialItem
     */
    public void showHome9(ApiSpecialItem apiSpecialItem) {
        itemDataList = getData(apiSpecialItem.getItemData());
        if(itemDataList == null || itemDataList.size() != 3) {
            return;
        }
        AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.tab_home_item_home9);
        addViewHolder.setImage(R.id.ImageViewSquare, itemDataList.get(0).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewRectangle1, itemDataList.get(1).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewRectangle2, itemDataList.get(2).getImageUrl());

        homeViewID.addView(addViewHolder.getCustomView());

        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare), itemDataList.get(0).getType(), itemDataList.get(0).getData(), false);
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewRectangle1), itemDataList.get(1).getType(), itemDataList.get(1).getData(), false);
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewRectangle2), itemDataList.get(2).getType(), itemDataList.get(2).getData(), false);
    }

    /**
     * 显示home10，四列单行图片模块
     *
     * @param apiSpecialItem
     */
    public void showHome10(ApiSpecialItem apiSpecialItem) {
        itemDataList = getData(apiSpecialItem.getItemData());
        if(itemDataList == null || itemDataList.size() != 4) {
            return;
        }
        AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.tab_home_item_home10);

        addViewHolder.setImage(R.id.ImageViewSquare1, itemDataList.get(0).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewSquare2, itemDataList.get(1).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewSquare3, itemDataList.get(2).getImageUrl());
        addViewHolder.setImage(R.id.ImageViewSquare4, itemDataList.get(3).getImageUrl());

        homeViewID.addView(addViewHolder.getCustomView());

        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare1), itemDataList.get(0).getType(), itemDataList.get(0).getData(), false);
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare2), itemDataList.get(1).getType(), itemDataList.get(1).getData(), false);
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare3), itemDataList.get(2).getType(), itemDataList.get(2).getData(), false);
        OnImageViewClick(addViewHolder.getView(R.id.ImageViewSquare4), itemDataList.get(3).getType(), itemDataList.get(3).getData(), false);
    }

    /**
     * 显示text，滚动文字模块
     */
    public void showText(ApiSpecialItem apiSpecialItem) {
        final List<ItemText> itemTextList = getTextData(apiSpecialItem.getItemData());
        List<String> textList = new ArrayList<>();
        for (int i = 0; i < itemTextList.size(); i++) {
            textList.add(i, itemTextList.get(i).getText());
        }
        AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.tab_home_item_text);

        marqueeView = addViewHolder.getView(R.id.marqueeView);

        homeViewID.addView(addViewHolder.getCustomView());
        marqueeView.startWithList(textList);

        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                HomeLoadDataHelper.doClick(context, itemTextList.get(position).getType(), itemTextList.get(position).getData());
            }
        });
    }

    /**
     * 开始滚动文字
     */
    public void startTextFlipping() {
        if(marqueeView != null) {
            marqueeView.startFlipping();
        }
    }

    /**
     * 停止文字滚动
     */
    public void stopTextFlipping() {
        if(marqueeView != null) {
            marqueeView.stopFlipping();
        }
    }

    /**
     * 开始图片轮播
     */
    public void startImageCycle() {
        if(cycleView != null) {
            cycleView.startImageCycle();
        }
    }

    /**
     * 停止图片轮播
     */
    public void stopImageCycle() {
        if(cycleView != null) {
            cycleView.stopImageCycle();
        }
    }

    /**
     * 显示商品
     */
    public void showGoods(ApiSpecialItem apiSpecialItem) {
        List<ItemGoods> itemGoodsList = getGoodsData(apiSpecialItem.getItemData());

        AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.tab_home_item_goods);
        HomeGoodsGridViewAdapter adapter = new HomeGoodsGridViewAdapter(context);
        addViewHolder.setGridAdapter(R.id.gridview, adapter);
        adapter.setmDatas(itemGoodsList);
        adapter.notifyDataSetChanged();

        homeViewID.addView(addViewHolder.getCustomView());
    }


    /**
     * 解析json(通用)
     *
     * @param json
     * @return
     */
    public List<ItemData> getData(String json) {
        List<ItemData> itemData = JsonUtil.toBean(json, new TypeToken<List<ItemData>>() {
        }.getType());
        return itemData;
    }

    /**
     * 解析json(通用)
     *
     * @param json
     * @return
     */
    public List<ItemText> getTextData(String json) {
        List<ItemText> itemText = JsonUtil.toBean(json, new TypeToken<List<ItemText>>() {
        }.getType());
        return itemText;
    }

    /**
     * 解析json(商品)
     *
     * @param json
     * @return
     */
    public List<ItemGoods> getGoodsData(String json) {
        List<ItemGoods> itemGoods = JsonUtil.toBean(json, new TypeToken<List<ItemGoods>>() {
        }.getType());
        return itemGoods;
    }


    /**
     * 点击事件
     *
     * @param view
     * @param type keyword 关键字搜索，data中为搜索关键字
     *             special 专题，data中为专题编号
     *             goods 商品，data中为商品编号
     *             store 店铺，data中为店铺编号
     *             category 跳转到分类，data中无数据
     *             cart 跳转到购物车，data中无数据
     *             my 跳转到我的商城，data中无数据
     * @param data
     */
    private void OnImageViewClick(View view, final String type, final String data, final boolean isAd) {

        if (isAd) {//是轮播
            HomeLoadDataHelper.doClick(context, type, data);
        } else {  //不是轮播图
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HomeLoadDataHelper.doClick(context, type, data);
                }
            });
        }
    }

}
