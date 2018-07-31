package net.shopnc.b2b2c.android.custom.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.VoucherVo;
import net.shopnc.b2b2c.android.custom.PickerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description pickerview+说明
 * @Author qyf
 *
 * Created 2016/5/5 14:50.
 */
public class NCListDialog {
    Context context;

    View popupView;
    PopupWindow mPopupWindow;
    Button btnCancel;
    Button btnConfirm;

    HashMap<Integer,String> map;
    List<String> datas;

    public NCListDialog(Context context,List<VoucherVo> voucherVos) {
        this.context = context;

        popupView = LayoutInflater.from(context).inflate(R.layout.nc_list_dialog, null);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));

        FrameLayout flBack = popupView.findViewById(R.id.flBack);
        flBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });

        map=new HashMap<>();
        for (VoucherVo voucherVo:voucherVos){
            map.put(voucherVo.getVoucherId(),voucherVo.getVoucherTitle());
        }
        datas=new ArrayList<>(map.values());

        PickerView pvList= popupView.findViewById(R.id.pvList);
        pvList.setData(datas);
//        for (Map.Entry<Integer,String> entry:map.entrySet()){
//            if (entry.getValue().equals(pvList.getCurrentSelected())){
//
//            }
//        }

        btnCancel = popupView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });

        btnConfirm = popupView.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPopupWindow.dismiss();
            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
    }




    public void showPopupWindow(){
        mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }


}
