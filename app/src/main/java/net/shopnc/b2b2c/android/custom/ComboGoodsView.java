package net.shopnc.b2b2c.android.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.BookBean;
import net.shopnc.b2b2c.android.util.LoadImage;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/4/27 11:27
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * <p>
 * 推荐组合和优惠套装的商品图
 */
public class ComboGoodsView extends LinearLayout {

    @Bind(R.id.llContainer)
    LinearLayout llContainer;
    @Bind(R.id.ivAdd)
    ImageView mIvAdd;
    @Bind(R.id.ivImage)
    ImageView mIvImage;
    private Context mContext;

    public ComboGoodsView(Context context) {
        super(context);
        init(context);
    }

    public ComboGoodsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ComboGoodsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        mContext = context;
        View view = View.inflate(context, R.layout.combo_goods_view, this);
        ButterKnife.bind(this, view);
    }

    /**
     * 显示图片
     * 隐藏连接的加号
     *
     * @param url
     * @param show 是否显示连接加号
     */
    public void setImage(String url, boolean show) {
        mIvAdd.setVisibility(show ? VISIBLE : GONE);
        LoadImage.loadRemoteImg(mContext, mIvImage, url);
    }

    /**
     * 设置宽度和高度为40*40
     */
    public void setLayoutParams() {
        llContainer.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
    }


}
