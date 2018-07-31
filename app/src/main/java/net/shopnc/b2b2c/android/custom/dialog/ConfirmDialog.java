package net.shopnc.b2b2c.android.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.Conform;
import net.shopnc.b2b2c.android.bean.GoodGift;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/9/5 13:57.
 */
public class ConfirmDialog extends Dialog {
    @Bind(R.id.llChoosens)
    ListView llChoosens;

    private Context context;
    private List<Conform> conforms;

    public ConfirmDialog(Context context,List<Conform> conforms) {
        super(context);
        this.context=context;
        this.conforms=conforms;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.follow_bottom_confirm_dialog);
        ButterKnife.bind(this);
        llChoosens.setAdapter(new CommonAdapter<Conform>(context, conforms, R.layout.follow_botton_dialog_item) {
            @Override
            public void convert(ViewHolder holder, final Conform conform) {
                Button btnSortView = holder.getView(R.id.btnSortView);
                btnSortView.setText(conform.getShowText());
                LinearLayout llGift = holder.getView(R.id.llGift);
                if (conform.getIsGift() == 1) {
                    llGift.setVisibility(View.VISIBLE);
                    llGift.removeAllViews();
                    for (GoodGift gift : conform.getGiftVoList()) {
                        AddViewHolder giftHolder = new AddViewHolder(context, R.layout.follow_confirm_dialog_gift_item);
                        giftHolder.setText(R.id.btnSortView, gift.getShowText());
                        llGift.addView(giftHolder.getCustomView());
                    }
                }
            }
        });
    }

    @Override
    public void setOnDismissListener(OnDismissListener listener) {
        super.setOnDismissListener(listener);
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btnSure)
    public void onClick() {
        dismiss();
    }
}
