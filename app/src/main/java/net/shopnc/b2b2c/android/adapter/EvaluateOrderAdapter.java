package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.ImageFile;
import net.shopnc.b2b2c.android.bean.OrdersGoodsVo;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.custom.MyGridView;
import net.shopnc.b2b2c.android.custom.PhotoBottomDialog;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.custom.dialog.NCDialog;
import net.shopnc.b2b2c.android.custom.dialog.NCDialogConfirm;

import java.util.List;

/**
 * 进入评论页面 商品的显示
 */
public class EvaluateOrderAdapter extends CommonAdapter<OrdersGoodsVo> {
    private PhotoBottomDialog dialog;
    private NCDialog ncDialog;
    private SparseArray<List<ImageFile>> imageSpaList;
    private SparseArray<String> contentList;
    private SparseArray<String> scoreList;
    private SparseArray<Boolean> isAnonymousList;

    public EvaluateOrderAdapter(Context context) {
        super(context, R.layout.activity_order_evaluate_goods_item);
        dialog = new PhotoBottomDialog(context);
    }

    public PhotoBottomDialog getPhotoBottomDialog() {
        return dialog;
    }

    public void setImageSpaList(SparseArray<List<ImageFile>> imageSpaList) {
        this.imageSpaList = imageSpaList;
    }

    public void setContentList(SparseArray<String> contentList) {
        this.contentList = contentList;
    }

    public void setScoreList(SparseArray<String> scoreList) {
        this.scoreList = scoreList;
    }

    public void setIsAnonymousList(SparseArray<Boolean> isAnonymousList) {
        this.isAnonymousList = isAnonymousList;
    }

    @Override
    public void convert(final ViewHolder holder, OrdersGoodsVo ordersGoodsVo) {
        holder.setImage(R.id.ivGoodsPic, ordersGoodsVo.getImageSrc())
                .setText(R.id.tvGoodsName, ordersGoodsVo.getGoodsName());
        setImageAdapterAndClick(holder, ordersGoodsVo);
        setEtAdviceWatcher(holder, ordersGoodsVo);
        setAnonymous(holder, ordersGoodsVo);
        setGoodsRotate(holder, ordersGoodsVo);
    }


    private void setImageAdapterAndClick(ViewHolder holder, final OrdersGoodsVo ordersGoodsVo) {
        MyGridView myGridView = holder.getView(R.id.gv_photo);
        final EvaluatePhotoAdapter photoAdapter = new EvaluatePhotoAdapter(mContext,5);
        myGridView.setAdapter(photoAdapter);
        photoAdapter.setImageFileList(imageSpaList.get(ordersGoodsVo.getGoodsId()));
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (imageSpaList.get(ordersGoodsVo.getGoodsId()).size() == 0 || position == imageSpaList.get(ordersGoodsVo.getGoodsId()).size()) {
                    dialog.setId(ordersGoodsVo.getGoodsId());
                    dialog.setPhotoAdapter(photoAdapter);
                    dialog.show();
                } else {
                    ncDialog = new NCDialog(mContext);
                    ncDialog.setText1("确认删除该图片?");
                    ncDialog.setOnDialogConfirm(new NCDialogConfirm() {
                        @Override
                        public void onDialogConfirm() {
                            imageSpaList.get(ordersGoodsVo.getGoodsId()).remove(position);
                            photoAdapter.notifyDataSetChanged();
                        }
                    });
                    ncDialog.showPopupWindow();
                }
            }
        });
    }

    private void setEtAdviceWatcher(ViewHolder holder, final OrdersGoodsVo ordersGoodsVo) {
        final EditText etAdvice = holder.getView(R.id.etAdvice);
        etAdvice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                etAdvice.setTag(ordersGoodsVo.getGoodsId());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() <= 250) {
                    if (ordersGoodsVo.getGoodsId() == (int) etAdvice.getTag())
                        contentList.put(ordersGoodsVo.getGoodsId(), etAdvice.getText().toString());
                } else {
                    TToast.showShort(mContext, "评价内容超过限定长度");
                }
            }
        });
    }

    private void setAnonymous(ViewHolder holder, final OrdersGoodsVo ordersGoodsVo) {
        LinearLayout rlbtnAnonymity = holder.getView(R.id.rlbtnAnonymity);
        final ImageView btnAnonymity = holder.getView(R.id.btnAnonymity);
        btnAnonymity.setSelected(isAnonymousList.get(ordersGoodsVo.getGoodsId()));
        rlbtnAnonymity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAnonymity.setSelected(!btnAnonymity.isSelected());
                isAnonymousList.put(ordersGoodsVo.getGoodsId(), btnAnonymity.isSelected());
            }
        });
    }

    private void setGoodsRotate(ViewHolder holder, final OrdersGoodsVo ordersGoodsVo) {
        RatingBar rbGoodsExaluate = holder.getView(R.id.rbGoodsExaluate);
        rbGoodsExaluate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating < 1) {
                    ratingBar.setRating(1);
                }
                scoreList.put(ordersGoodsVo.getGoodsId(), String.valueOf(rating).substring(0, 1));
            }
        });
    }

}
