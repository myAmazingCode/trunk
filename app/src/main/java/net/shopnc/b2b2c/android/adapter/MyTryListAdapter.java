package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.TryListBean;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;
import net.shopnc.b2b2c.android.ui.trys.TryGoodReportDetailsActivity;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2017/2/6 14:04.
 */

public class MyTryListAdapter extends RRecyclerAdapter<TryListBean> {

    public MyTryListAdapter(Context context) {
        super(context, R.layout.recyclerview_mytry_item);
    }

    @Override
    public void convert(RecyclerHolder holder, final TryListBean tryListBean) {
        holder.setText(R.id.tvTryState, tryListBean.getShowMemberStateText())
                .setImage(R.id.ivGoodPic, tryListBean.getImageSrc())
                .setText(R.id.tvGoodName, tryListBean.getGoodsName())
                .setText(R.id.tvGoodsSpec, tryListBean.getGoodsFullSpecs())
                .setText(R.id.tvTryTitle, tryListBean.getTrysTypeText());

//        if (tryListBean.getShowMemberAddReportState() == 1) {
//            holder.setVisible(R.id.tvBuyTryGood, true)
//                    .setText(R.id.tvBuyTryGood, "提交试用报告")
//                    .setOnClickListener(R.id.tvBuyTryGood, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                        }
//                    });
//        } else
        if (tryListBean.getShowMemberViewReportState() == 1) {
            holder.setVisible(R.id.tvBuyTryGood, true)
                    .setText(R.id.tvBuyTryGood, "查看试用报告")
                    .setOnClickListener(R.id.tvBuyTryGood, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TryGoodReportDetailsActivity.onStartActivity(context, tryListBean.getTrysId(), tryListBean.getApplyId());
                        }
                    });
        } else if (tryListBean.getShowMemberBuyState() == 1) {
            holder.setVisible(R.id.tvBuyTryGood, true)
                    .setText(R.id.tvBuyTryGood, "购买试用商品")
                    .setOnClickListener(R.id.tvBuyTryGood, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(context, GoodDetailsActivity.class);
                            i.putExtra(GoodDetailsActivity.COMMONID, tryListBean.getCommonId());
                            i.putExtra(GoodDetailsActivity.GOODID, tryListBean.getGoodsId());
                            i.putExtra(GoodDetailsActivity.TRYSTYPE, tryListBean.getTrysType());
                            context.startActivity(i);
                        }
                    });
        } else {
            holder.setVisible(R.id.tvBuyTryGood, false);
        }

    }
}
