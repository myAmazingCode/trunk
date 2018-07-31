package net.shopnc.b2b2c.android.adapter;


import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.flexbox.FlexboxLayout;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.GoodsEvaluate;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.custom.dialog.ClickBigImageDialog;

import java.util.ArrayList;

/**
 * 作者：Jie on 2016/6/7 14:32
 */

public class EvaluateAdapter extends RRecyclerAdapter<GoodsEvaluate> {
    public EvaluateAdapter(Context context) {
        super(context, R.layout.evaluate_item);
    }

    @Override
    public void convert(RecyclerHolder holder,final GoodsEvaluate goodsEvaluate) {
        holder.setText(R.id.customer_name, goodsEvaluate.getMemberName())
                .setText(R.id.evaluate_date, goodsEvaluate.getEvaluateTimeStr())
                .setImage(R.id.customer_image, goodsEvaluate.getMemberHeadUrl())
                .setText(R.id.evaluate_text, goodsEvaluate.getEvaluateContent1())
                .setText(R.id.evaluate_info, goodsEvaluate.getGoodsFullSpecs());

        //星级显示
        LinearLayout llStar=holder.getView(R.id.llStar);
        llStar.removeAllViews();
        for (int i=0;i<Integer.valueOf(goodsEvaluate.getScores());i++){
            AddViewHolder addViewHolder=new AddViewHolder(context,R.layout.gooddetails_evaluate_star);
            llStar.addView(addViewHolder.getCustomView());
        }

        //评价晒照
        FlexboxLayout llImages = holder.getView(R.id.evaluate_images);
        if (goodsEvaluate.getImage1FullList().isEmpty()) {
            llImages.setVisibility(View.GONE);
        }else {
            llImages.setVisibility(View.VISIBLE);
            llImages.removeAllViews();
            ArrayList<String> imgList=new ArrayList<>();
            imgList.addAll(goodsEvaluate.getImage1FullList());
            for (final String s:imgList){
                AddViewHolder addViewHolder=new AddViewHolder(context,R.layout.view_image);
                addViewHolder.setImage(R.id.ivImg,s);
                addViewHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击查看大图并设置当前位置
                        ClickBigImageDialog dialog = new ClickBigImageDialog(context, goodsEvaluate.getImage1FullList(),goodsEvaluate.getImage1FullList().indexOf(s));
                        dialog.show();
                    }
                });
                llImages.addView(addViewHolder.getCustomView());
            }
        }

        if (Common.isEmpty(goodsEvaluate.getEvaluateContent2())) {
            holder.setVisible(R.id.llMoreEvaluate, false);
        }else{
            holder.setVisible(R.id.llMoreEvaluate, true);
            holder.setText(R.id.more_evaluate_date, goodsEvaluate.getEvaluateAgainTimeStr())
                    .setText(R.id.more_evaluate_text, goodsEvaluate.getEvaluateContent2());

            FlexboxLayout llMoreImgs = holder.getView(R.id.more_evaluate_image);
            if (!goodsEvaluate.getImage2FullList().isEmpty()) {
                llMoreImgs.removeAllViews();
                ArrayList<String> imgList=new ArrayList<>();
                imgList.addAll(goodsEvaluate.getImage2FullList());
                for (final String s:imgList){
                    AddViewHolder addViewHolder=new AddViewHolder(context,R.layout.view_image);
                    addViewHolder.setImage(R.id.ivImg,s);
                    addViewHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //点击查看大图并设置当前位置
                            ClickBigImageDialog dialog=new ClickBigImageDialog(context,goodsEvaluate.getImage2FullList(),goodsEvaluate.getImage2FullList().indexOf(s));
                            dialog.show();
                        }
                    });
                    llMoreImgs.addView(addViewHolder.getCustomView());
                }
            }
        }
    }
}
