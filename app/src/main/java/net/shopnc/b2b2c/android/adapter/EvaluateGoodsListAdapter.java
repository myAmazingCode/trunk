package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.GoodsDetailForEvaluate;
import net.shopnc.b2b2c.android.bean.ImageFile;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.custom.NCDialog;
import net.shopnc.b2b2c.android.custom.PhotoBottomDialog;
import net.shopnc.b2b2c.android.ncinterface.INCOnDialogConfirm;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wj on 2015/12/24.
 * 评价的商品列表适配器
 */
public class EvaluateGoodsListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context ctx;
    Map<String, Integer> rateCache = new HashMap<String, Integer>();
    Map<String, String> commentCache = new HashMap<String, String>();
    Map<String, String> anonyCache =  new HashMap<String, String>();
    private PhotoBottomDialog bottomDialog;
    private NCDialog ncDialog;


    private List<GoodsDetailForEvaluate> goods_detail_forcevaluate = new ArrayList<GoodsDetailForEvaluate>();
    //商品图片缓存， 第一层标记商品编号，二层标记照片位置，第三层为图片属性
    private HashMap<String, List<ImageFile>> itemImageBean = new HashMap<String, List<ImageFile>>();


    public EvaluateGoodsListAdapter(Context ctx){
        inflater = LayoutInflater.from(ctx);
        this.ctx = ctx;
    }

    public PhotoBottomDialog getBottomDialog(){
        return this.bottomDialog;
    }
    public void setGoodsDetailForEvaluateList(List<GoodsDetailForEvaluate> goods_detail_forcevaluate){
        this.goods_detail_forcevaluate = goods_detail_forcevaluate;
    }
    public void setData( HashMap<String, List<ImageFile>> itemImageBean){
        this.itemImageBean = itemImageBean;
    }

    public  Map<String, Integer> getRateCache(){
        return this.rateCache;
    }
    public  Map<String, String> getCommentCache(){
        return this.commentCache;
    }

    public Map<String, String> getAnonyCache(){
        return this.anonyCache;
    }

    @Override
    public int getCount() {
        return goods_detail_forcevaluate.size();
    }

    @Override
    public Object getItem(int i) {
        return goods_detail_forcevaluate.size() != 0 ? goods_detail_forcevaluate.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final ViewHolder vh;
        if (view == null) {
            vh = new ViewHolder();
            view  = inflater.inflate(R.layout.listview_evaluate_item, null);
            vh.imageGoodsPic = view.findViewById(R.id.imageGoodsPic);
            vh.textGoodsName = view .findViewById(R.id.textGoodsName);
            vh.btnAnonymity = view .findViewById(R.id.btnAnonymity);
            vh.rb_goodsexaluate = view .findViewById(R.id.rb_goodsexaluate);
            vh.etAdvice = view.findViewById(R.id.etAdvice);
            vh.gv_photo = view.findViewById(R.id.gv_photo);

            view.setTag(vh);

        }else{
            vh = (ViewHolder) view.getTag();
        }

        final GoodsDetailForEvaluate goodsDetailsForEvaluate = goods_detail_forcevaluate.get(i);
        //带缓存的商品图
        Glide.with(ctx)
                .load(goodsDetailsForEvaluate.getGoods_image_url())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_launcher)  //设置占位图
                .error(R.drawable.ic_launcher)      //加载错误图
                .into(vh.imageGoodsPic);


        vh.imageGoodsPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, GoodsDetailsActivity.class);
                i.putExtra("goods_id",goodsDetailsForEvaluate.getGoods_id());
                ctx.startActivity(i);
            }
        });

        vh.ref = goodsDetailsForEvaluate.getRec_id();

        //是否匿名
        vh.btnAnonymity.setSelected(goodsDetailsForEvaluate.isSelected());
        vh.btnAnonymity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goodsDetailsForEvaluate.isSelected()) {
                    goodsDetailsForEvaluate.setIsSelected(false);
                    goodsDetailsForEvaluate.setAnony("0");
                   anonyCache.put(vh.ref,"0");
                } else {
                    goodsDetailsForEvaluate.setIsSelected(true);
                    goodsDetailsForEvaluate.setAnony("1");
                    anonyCache.put(vh.ref, "1");
                }
                notifyDataSetChanged();
            }
        });

        //商品名
        vh.textGoodsName.setText(goodsDetailsForEvaluate.getGoods_name());

        //商品评分
        goodsDetailsForEvaluate.setScore(vh.rb_goodsexaluate.getRating());
        //TODO 数据被复用
        vh.rb_goodsexaluate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating == 0) {
                    ratingBar.setRating(1);
                    goodsDetailsForEvaluate.setScore(1);
                    rateCache.put(vh.ref, 1);
                } else {
                    goodsDetailsForEvaluate.setScore(rating);
                    rateCache.put(vh.ref, (int) rating);
                }

            }
        });


        //评价内容
        vh.etAdvice.setFilters(new InputFilter[]{new InputFilter.LengthFilter(150)});
        vh.etAdvice.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                if (s.length() == 150) {
                    T.showShort(ctx, "文字内容长度超出限制");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 150) {
                    T.showShort(ctx, "文字内容长度超出限制");
                } else {
                    commentCache.put(vh.ref, vh.etAdvice.getText().toString());
                    goodsDetailsForEvaluate.setComment(vh.etAdvice.getText().toString());
                }

            }
        });

        //上传照片
        final EvaluatePhotoAdapter adapter = new EvaluatePhotoAdapter(ctx,goodsDetailsForEvaluate.getRec_id(),itemImageBean);
        vh.gv_photo.setAdapter(adapter);
        vh.gv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int imagePosition, long id) {

                final List<ImageFile> imageBeanCache = itemImageBean.get(goodsDetailsForEvaluate.getRec_id());

                if (imageBeanCache == null || imagePosition == imageBeanCache.size()) {
                    bottomDialog = new PhotoBottomDialog(ctx, goodsDetailsForEvaluate.getRec_id(), adapter);
                    bottomDialog.show();
                } else {

                    ncDialog = new NCDialog(ctx);
                    ncDialog.setText1("确认删除该图片?");
                    ncDialog.setOnDialogConfirm(new INCOnDialogConfirm() {
                        @Override
                        public void onDialogConfirm() {
                            imageBeanCache.remove(imagePosition);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    ncDialog.showPopupWindow();
                }

            }
        });

        return view;


    }


    class ViewHolder{
        public ImageView imageGoodsPic;
        public TextView textGoodsName;
        public ImageButton btnAnonymity;
        public RatingBar rb_goodsexaluate;
        public EditText etAdvice;
        public GridView gv_photo;
        public String ref;
    }

}
