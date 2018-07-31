package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.ContractInfo;

import java.util.ArrayList;

/**
 * 地区Spinner适配器
 */
public class ContractGridViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ContractInfo> contractList;

    public ContractGridViewAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return contractList == null ? 0 : contractList.size();
    }

    @Override
    public Object getItem(int position) {
        return contractList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setContractList(ArrayList<ContractInfo> contractList) {
        this.contractList = contractList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.gridview_contract_item, null);
            holder = new ViewHolder();
            holder.btnContract = convertView.findViewById(R.id.btnContract);
            holder.tvContract = convertView.findViewById(R.id.tvContract);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ContractInfo contractInfo = contractList.get(position);
        holder.btnContract.setText(contractInfo.getName());
        holder.tvContract.setText(contractInfo.getId());
        setSelectBtnState(holder.btnContract);

        return convertView;
    }

    /**
     * 设置选择按钮状态
     *
     * @param btn
     */
    private void setSelectBtnState(final Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn.isActivated()) {
                    btn.setActivated(false);
                } else {
                    btn.setActivated(true);
                }
            }
        });
    }

    class ViewHolder {
        Button btnContract;
        TextView tvContract;
    }
}
