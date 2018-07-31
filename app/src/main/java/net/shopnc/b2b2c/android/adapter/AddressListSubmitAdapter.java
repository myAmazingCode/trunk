package net.shopnc.b2b2c.android.adapter;


import android.content.Context;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.Address;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.interfac.INCOnDel;
import net.shopnc.b2b2c.android.interfac.INCOnEdit;

public class AddressListSubmitAdapter extends CommonAdapter<Address> {

    private boolean deleteShowFlag = true;  //确认订单页跳转过来的无删除等选项
    private int AddressId;

    public AddressListSubmitAdapter(Context context, INCOnDel incOnDel, INCOnEdit incOnEdit, int AddressId) {
        super(context, R.layout.listview_address_submit_item, incOnDel, incOnEdit);
        this.AddressId = AddressId;
    }

    public void setDeleteShowFlag(boolean deleteShowFlag) {
        this.deleteShowFlag = deleteShowFlag;
    }

    @Override
    public void convert(ViewHolder holder, Address address) {
        final String addressId = String.valueOf(address.getAddressId());
        holder.setText(R.id.textAddressName, address.getRealName());
        holder.setText(R.id.textAddressPhone, address.getMobPhone());

        holder.setText(R.id.textAddressAddress, address.getAreaInfo() + "\t" + address.getAddress());

        if (address.getIsDefault() == 1) {
            holder.setVisible(R.id.btnDefaultAddress, true);
        } else {
            holder.setVisible(R.id.btnDefaultAddress, false);
        }


        holder.setPadding(R.id.textAddressAddress, 0, 0, 0, deleteShowFlag ? 0 : 20);

        holder.setOnClickListener(R.id.ivEdit, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incOnEdit.onEdit(addressId);
            }
        });

        if (address.getAddressId() == AddressId && AddressId != -1) {
            holder.setVisible(R.id.ivChoose, true)
                    .setTextColorRes(R.id.textAddressName, R.color.nc_red)
                    .setTextColorRes(R.id.textAddressPhone, R.color.nc_red);
        } else {
            holder.setVisible(R.id.ivChoose, false)
                    .setTextColorRes(R.id.textAddressName, R.color.nc_text_dark)
                    .setTextColorRes(R.id.textAddressPhone, R.color.nc_text_dark);
        }


//        holder.setOnClickListener(R.id.rlDelete, new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                incOnDel.onDel(addressId);
//            }
//        });

    }


}
