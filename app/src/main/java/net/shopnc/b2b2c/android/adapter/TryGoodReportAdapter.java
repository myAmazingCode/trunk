package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.View;

import com.google.android.flexbox.FlexboxLayout;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.TryGoodReportBean;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.custom.dialog.ClickBigImageDialog;
import net.shopnc.b2b2c.android.ui.trys.TryGoodReportDetailsActivity;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2017/2/13 17:37.
 */

public class TryGoodReportAdapter extends RRecyclerAdapter<TryGoodReportBean> {
    public TryGoodReportAdapter(Context context) {
        super(context, R.layout.recyclerview_try_good_report);
    }

    @Override
    public void convert(RecyclerHolder holder, final TryGoodReportBean tryGoodReportBean) {
        holder.setImage(R.id.ivMemberImg, tryGoodReportBean.getAvatarUrl())
                .setText(R.id.tvMemberName, tryGoodReportBean.getMemberNameEncrypt())
                .setText(R.id.tvTime, tryGoodReportBean.getReportTime())
                .setText(R.id.tvGoodName, tryGoodReportBean.getGoodsFullName())
                .setText(R.id.tvSuggest, tryGoodReportBean.getSuggest());
        FlexboxLayout flexboxLayout = holder.getView(R.id.flImgs);
        flexboxLayout.removeAllViews();
        if (tryGoodReportBean.getImageList() != null && tryGoodReportBean.getImageList().size() > 0) {
            for (final String s : tryGoodReportBean.getImageList()) {
                AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.view_image);
                addViewHolder.setImage(R.id.ivImg, s);
                addViewHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击查看大图并设置当前位置
                        ClickBigImageDialog dialog = new ClickBigImageDialog(context, tryGoodReportBean.getImageList(), tryGoodReportBean.getImageList().indexOf(s));
                        dialog.show();
                    }
                });
                flexboxLayout.addView(addViewHolder.getCustomView());
            }
        }
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TryGoodReportDetailsActivity.onStartActivity(context, tryGoodReportBean.getTrysId(), tryGoodReportBean.getApplyId());
//                ((AppCompatActivity) context).finish();
            }
        });
    }
}
