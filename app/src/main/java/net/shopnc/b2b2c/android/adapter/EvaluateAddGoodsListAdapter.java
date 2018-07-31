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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.GoodsDetailForEvaluateAdd;
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
 * Created by wj on 2016/1/5.
 * 追加评论商品列表适配器
 */
public class EvaluateAddGoodsListAdapter  extends BaseAdapter{
    private LayoutInflater inflater;
    private Context ctx;
    private PhotoBottomDialog bottomDialog;
    Map<String, String> commentCache = new HashMap<String, String>();
    private NCDialog ncDialog;


    private List<GoodsDetailForEvaluateAdd> goods_detail_forcevaluateadd = new ArrayList<GoodsDetailForEvaluateAdd>();
    //商品图片缓存， 第一层标记商品编号，二层标记照片位置，第三层为图片属性
    private HashMap<String, List<ImageFile>> itemImageBean = new HashMap<String, List<ImageFile>>();


    public EvaluateAddGoodsListAdapter(Context ctx){
        inflater = LayoutInflater.from(ctx);
        this.ctx = ctx;
    }

    public PhotoBottomDialog getBottomDialog(){
        return this.bottomDialog;
    }
    public void setGoodsDetailForEvaluateList(List<GoodsDetailForEvaluateAdd> goods_detail_forcevaluateadd){
        this.goods_detail_forcevaluateadd = goods_detail_forcevaluateadd;
    }
    public void setData( HashMap<String, List<ImageFile>> itemImageBean){
        this.itemImageBean = itemImageBean;
    }
    public  Map<String, String> getCommentCache(){
        return this.commentCache;
    }


    @Override
    public int getCount() {
        return goods_detail_forcevaluateadd.size();
    }

    @Override
    public Object getItem(int i) {
        return goods_detail_forcevaluateadd.size() != 0 ? goods_detail_forcevaluateadd.get(i) : null;
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
            vh.rlbtnAnonymity = view .findViewById(R.id.rlbtnAnonymity);
            vh.llGoodsexaluate = view .findViewById(R.id.llGoodsexaluate);
            vh.etAdvice = view.findViewById(R.id.etAdvice);
            vh.gv_photo = view.findViewById(R.id.gv_photo);
            vh.tvFirstEvaluate = view.findViewById(R.id.tvFirstEvaluate);

            view.setTag(vh);

        }else{
            vh = (ViewHolder) view.getTag();
        }

        final GoodsDetailForEvaluateAdd goodsDetailsForEvaluateAdd = goods_detail_forcevaluateadd.get(i);
        //带缓存的商品图
        Glide.with(ctx)
                .load(goodsDetailsForEvaluateAdd.getGeval_goodsimage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_launcher)  //设置占位图
                .error(R.drawable.ic_launcher)      //加载错误图
                .into(vh.imageGoodsPic);


        vh.imageGoodsPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, GoodsDetailsActivity.class);
                i.putExtra("goods_id", goodsDetailsForEvaluateAdd.getGeval_goodsid());
                ctx.startActivity(i);
            }
        });

        vh.ref = goodsDetailsForEvaluateAdd.getGeval_id();

        //是否匿名
        vh.rlbtnAnonymity.setVisibility(View.GONE);
        //商品名
        vh.textGoodsName.setText(goodsDetailsForEvaluateAdd.getGeval_goodsname());

        //商品评分
        vh.llGoodsexaluate.setVisibility(View.GONE);

        //初次评价
        vh.tvFirstEvaluate.setVisibility(View.VISIBLE);
        vh.tvFirstEvaluate.setText(goodsDetailsForEvaluateAdd.getGeval_content());

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
                    goodsDetailsForEvaluateAdd.setComment(vh.etAdvice.getText().toString());
                }

            }
        });

        //上传照片
        final EvaluatePhotoAdapter adapter = new EvaluatePhotoAdapter(ctx,goodsDetailsForEvaluateAdd.getGeval_id(),itemImageBean);
        vh.gv_photo.setAdapter(adapter);
        vh.gv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int imagePosition, long id) {

                final List<ImageFile> imageBeanCache = itemImageBean.get(goodsDetailsForEvaluateAdd.getGeval_id());

                if (imageBeanCache == null || imagePosition == imageBeanCache.size()) {
                    bottomDialog = new PhotoBottomDialog(ctx, goodsDetailsForEvaluateAdd.getGeval_id(), adapter);
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
        public RelativeLayout rlbtnAnonymity;
        public LinearLayout llGoodsexaluate;
        public EditText etAdvice;
        public GridView gv_photo;
        public String ref;
        public TextView tvFirstEvaluate;
    }
}
