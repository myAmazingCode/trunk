package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.ImageFile;
import net.shopnc.b2b2c.android.bean.OrdersGoodsEvaluateVo;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.custom.MyGridView;
import net.shopnc.b2b2c.android.custom.PhotoBottomDialog;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.custom.dialog.NCDialog;
import net.shopnc.b2b2c.android.custom.dialog.NCDialogConfirm;

import java.util.List;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/8/1 18:36.
 */
public class OrderEvaluateAppendAdapter extends CommonAdapter<OrdersGoodsEvaluateVo> {
    private SparseArray<List<ImageFile>> imageSpaList;   //商品晒图列表
    private SparseArray<String> contentList;   //商品评价内容列表

    private PhotoBottomDialog dialog;
    private NCDialog ncDialog;

    public PhotoBottomDialog getPhotoBottomDialog() {
        return dialog;
    }

    public void setImageSpaList(SparseArray<List<ImageFile>> imageSpaList) {
        this.imageSpaList = imageSpaList;
    }

    public void setContentList(SparseArray<String> contentList) {
        this.contentList = contentList;
    }

    public OrderEvaluateAppendAdapter(Context context){
        super(context, R.layout.activity_order_evaluate_append_goods_item);
        dialog = new PhotoBottomDialog(context);
    }

    @Override
    public void convert(ViewHolder holder, OrdersGoodsEvaluateVo ordersGoodsEvaluateVo) {
        holder.setImage(R.id.ivGoodsPic,ordersGoodsEvaluateVo.getGoodsFullImage())
                .setText(R.id.tvGoodsName,ordersGoodsEvaluateVo.getGoodsName())
                .setText(R.id.tvContent,ordersGoodsEvaluateVo.getEvaluateContent());

        setImageAdapterAndClick(holder, ordersGoodsEvaluateVo);
        setEtAdviceWatcher(holder, ordersGoodsEvaluateVo);

    }

    private void setImageAdapterAndClick(ViewHolder holder, final OrdersGoodsEvaluateVo ordersGoodsVo) {
        MyGridView myGridView = holder.getView(R.id.gv_photo);
        final EvaluatePhotoAdapter photoAdapter = new EvaluatePhotoAdapter(mContext,5);
        myGridView.setAdapter(photoAdapter);
        photoAdapter.setImageFileList(imageSpaList.get(ordersGoodsVo.getEvaluateId()));
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (imageSpaList.get(ordersGoodsVo.getEvaluateId()).size() == 0 || position == imageSpaList.get(ordersGoodsVo.getEvaluateId()).size()) {
                    dialog.setId(ordersGoodsVo.getEvaluateId());
                    dialog.setPhotoAdapter(photoAdapter);
                    dialog.show();
                } else {
                    ncDialog = new NCDialog(mContext);
                    ncDialog.setText1("确认删除该图片?");
                    ncDialog.setOnDialogConfirm(new NCDialogConfirm() {
                        @Override
                        public void onDialogConfirm() {
                            imageSpaList.get(ordersGoodsVo.getEvaluateId()).remove(position);
                            photoAdapter.notifyDataSetChanged();
                        }
                    });
                    ncDialog.showPopupWindow();
                }
            }
        });
    }

    private void setEtAdviceWatcher(ViewHolder holder, final OrdersGoodsEvaluateVo ordersGoodsVo) {
        final EditText etAdvice = holder.getView(R.id.etAdvice);
        etAdvice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                etAdvice.setTag(ordersGoodsVo.getEvaluateId());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() <= 250) {
                    if (ordersGoodsVo.getEvaluateId() == (int) etAdvice.getTag())
                        contentList.put(ordersGoodsVo.getEvaluateId(), etAdvice.getText().toString());
                } else {
                    TToast.showShort(mContext, "评价内容超过限定长度");
                }
            }
        });
    }
}
