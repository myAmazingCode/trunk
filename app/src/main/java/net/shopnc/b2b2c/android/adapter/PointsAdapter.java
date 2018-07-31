package net.shopnc.b2b2c.android.adapter;

import android.content.Context;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.MemberPoints;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.common.log.LogHelper;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.adapter.PointsAdapter.java
 * @author: Jie
 * @date: 2016-05-31 15:08
 */
public class PointsAdapter extends RRecyclerAdapter<MemberPoints> {

    public PointsAdapter(Context context) {
        super(context, R.layout.ponit_log_item);
    }

    @Override
    public void convert(RecyclerHolder holder, MemberPoints memberPoints) {
        holder.setText(R.id.point_tvDes, memberPoints.getDescription())
                .setText(R.id.point_tvOpe, memberPoints.getOperationStageText())
                .setText(R.id.point_tvTime, memberPoints.getAddTime() + "");
        LogHelper.e("操作", memberPoints.getDescription() + "\n" + memberPoints.getOperationStageText());
        if (memberPoints.getPoints() > 0) {
            holder.setTextColorRes(R.id.point_tvPoint, R.color.nc_red)
                    .setText(R.id.point_tvPoint, "+" + memberPoints.getPoints());
        } else if (memberPoints.getPoints() == 0) {
            holder.setTextColorRes(R.id.point_tvPoint, R.color.nc_green)
                    .setText(R.id.point_tvPoint, "" + memberPoints.getPoints());
        } else {
            holder.setTextColorRes(R.id.point_tvPoint, R.color.nc_green)
                    .setText(R.id.point_tvPoint, "-" + memberPoints.getPoints());
        }
    }
}
