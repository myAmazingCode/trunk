package net.shopnc.b2b2c.android.adapter;

import android.content.Context;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;

import java.util.List;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2017/2/14 14:12.
 */

public class TryGoodReportDetailsAdapter extends RRecyclerAdapter<String> {

    private List<String> images;

    public TryGoodReportDetailsAdapter(Context context) {
        super(context, R.layout.recyclerview_try_good_report_details_item);
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public void convert(RecyclerHolder holder, String s) {
        holder.setImage(R.id.ivImg,images.get(datas.indexOf(s)))
                .setText(R.id.tvContent,s);
    }
}
