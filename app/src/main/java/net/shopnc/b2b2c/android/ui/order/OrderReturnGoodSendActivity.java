package net.shopnc.b2b2c.android.ui.order;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.RefundItemVo;
import net.shopnc.b2b2c.android.bean.Ship;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class OrderReturnGoodSendActivity extends BaseActivity {

    @Bind(R.id.tvShowInfo)
    TextView tvShowInfo;
    @Bind(R.id.tvShipName)
    TextView tvShipName;
    @Bind(R.id.llShip)
    LinearLayout llShip;
    @Bind(R.id.etBuyerMessage)
    EditText etBuyerMessage;
    @Bind(R.id.btnCommit)
    Button btnCommit;

    private int refundId;
    private RefundItemVo refundItemVo;
    private List<Ship> shipList;
    private int selectedShipId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refundId = getIntent().getIntExtra("refundId", 0);
        setCommonHeader("退货发货");
        requestData();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_order_return_good_send);
    }

    private void requestData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("refundId", refundId + "");
        OkHttpUtil.postAsyn(this,ConstantUrl.URL_ORDER_RETURN_GOOD_SEND, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                refundItemVo = JsonUtil.toBean(data, "refundItemVo", new TypeToken<RefundItemVo>() {
                }.getType());
                shipList = JsonUtil.toBean(data, "shipList", new TypeToken<ArrayList<Ship>>() {
                }.getType());
                tvShowInfo.setText("发货" + refundItemVo.getMaxDayReturnDelay() + "天后，当商家选择未收到则要进行延迟时间操作；如果超过" + refundItemVo.getMaxDayReturnConfirm() + "天不处理按弃货处理，直接由管理员确认退款。");
                tvShipName.setText(shipList.get(0).getShipName());
                selectedShipId = shipList.get(0).getShipId();
            }
        }, params);
    }

    @OnClick({R.id.llShip, R.id.btnCommit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llShip:
                final AlertDialog dialog = new AlertDialog.Builder(context).create();
                View v = LayoutInflater.from(context).inflate(R.layout.follow_bottom_dialog, null);
                ListView listView = v.findViewById(R.id.llChoosens);
                dialog.setView(v);
                dialog.show();
                listView.setAdapter(new CommonAdapter<Ship>(context, shipList, R.layout.follow_botton_dialog_item) {
                    @Override
                    public void convert(ViewHolder holder, final Ship ship) {
                        Button btnSortView = holder.getView(R.id.btnSortView);
                        btnSortView.setText(ship.getShipName());

                        btnSortView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                selectedShipId = ship.getShipId();
                                tvShipName.setText(ship.getShipName());
                                dialog.dismiss();
                            }
                        });
                    }
                });
                break;
            case R.id.btnCommit:
                commitSend();
                break;
        }
    }

    private void commitSend() {
        String shipSn = etBuyerMessage.getText().toString().trim();
        if (shipSn.equals("")) {
            TToast.showShort(context, "请填写物流单号");
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("refundId", refundId + "");
        params.put("shipId", selectedShipId + "");
        params.put("shipSn", shipSn);

        OkHttpUtil.postAsyn(this,ConstantUrl.URL_ORDER_RETURN_GOOD_SEND_SAVE, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                finish();
            }
        }, params);

    }

}
