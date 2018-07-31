package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.TryGoodBean;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.ui.trys.TryGoodDetailsActivity;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2017/2/7 10:01.
 */
public class TryGoodShowAdapter extends RRecyclerAdapter<TryGoodBean> {

    private String moneyRmb;

    public TryGoodShowAdapter(Context context) {
        super(context, R.layout.recyclerview_try_good_show_item);
        moneyRmb = context.getResources().getString(R.string.money_rmb);
    }

    @Override
    public void convert(RecyclerHolder holder, final TryGoodBean tryGoodBean) {
        holder.setImage(R.id.ivGoodImg, tryGoodBean.getImageSrc())
                .setText(R.id.tvGoodName, (tryGoodBean.getTrysType() == 1 ? "邮" : "券") + tryGoodBean.getGoodsName())
                .setText(R.id.tvGoodPrice, moneyRmb + ShopHelper.getPriceString(tryGoodBean.getGoodsPrice()));
        ((TextView) holder.getView(R.id.tvGoodPrice)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        ((TextView) holder.getView(R.id.tvGoodLimit)).setText(Html.fromHtml("限购<font color=\"#ED5968\">" + tryGoodBean.getProvideNum() + "</font>份"));
        ((TextView) holder.getView(R.id.tvGoodName)).setText(tryGoodBean.getTrysType() == 1 ? getImageText("占" + tryGoodBean.getGoodsName(), R.drawable.you) : getImageText("占" + tryGoodBean.getGoodsName(), R.drawable.quan), TextView.BufferType.SPANNABLE);
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TryGoodDetailsActivity.onStartActivity(context,tryGoodBean.getTrysId());
            }
        });
    }

    private SpannableString getImageText(String s, int id) {
        SpannableString spannableString = new SpannableString(s);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
        ImageSpan imageSpan = new ImageSpan(context, bitmap);
        spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
