package net.shopnc.b2b2c.android.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.GoodGift;
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
 * Created 2016/9/5 14:51.
 */
public class GoodGiftDialog extends Dialog {

    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.llChoosens)
    ListView llChoosens;

    private Context context;
    private List<GoodGift> gifts;
    private CommonAdapter<GoodGift> adapter;

    public GoodGiftDialog(Context context) {
        super(context);
        this.context = context;
        adapter=new CommonAdapter<GoodGift>(context, gifts, R.layout.follow_confirm_dialog_gift_item) {
            @Override
            public void convert(ViewHolder holder, final GoodGift gift) {
                holder.setText(R.id.btnSortView,gift.getShowText());
            }
        };
    }

    public void setGifts(List<GoodGift> gifts) {
        this.gifts = gifts;
        adapter.setmDatas(gifts);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.follow_bottom_confirm_dialog);
        ButterKnife.bind(this);
        tvTitle.setText("赠品");
        llChoosens.setAdapter(adapter);
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
