package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.Address;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.interfac.INCOnDel;
import net.shopnc.b2b2c.android.interfac.INCOnEdit;

/**
 * 地址列表adapter
 *
 * @author huting
 * @date 2016/4/11
 */
public class AddressListAdapter extends CommonAdapter<Address> {

    private boolean deleteShowFlag = true;  //确认订单页跳转过来的无删除等选项

    public AddressListAdapter(Context context, INCOnDel incOnDel, INCOnEdit incOnEdit) {
        super(context, R.layout.listview_address_item, incOnDel, incOnEdit);
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

        holder.setVisible(R.id.rlEdit, deleteShowFlag);
        holder.setVisible(R.id.rlDelete, deleteShowFlag);
        holder.setPadding(R.id.textAddressAddress, 0, 0, 0, deleteShowFlag ? 0 : 20);

        holder.setImageGrey(R.id.ivEdit, R.drawable.nc_icon_edit);
        holder.setImageGrey(R.id.ivDelete, R.drawable.nc_icon_del);

        holder.setOnClickListener(R.id.rlEdit, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incOnEdit.onEdit(addressId);
            }
        });


        holder.setOnClickListener(R.id.rlDelete, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incOnDel.onDel(addressId);
            }
        });

    }
}