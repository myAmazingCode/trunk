package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.ContractDetail;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.SystemHelper;

import java.util.ArrayList;

/**
 * 消保详情适配器
 */
public class ContractDetailGridViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ContractDetail> contractList;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public ContractDetailGridViewAdapter(Context context) {
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

    public void setContractList(ArrayList<ContractDetail> contractList) {
        this.contractList = contractList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.gridview_contract_detail_item, null);
            holder = new ViewHolder();
            holder.ivContractIcon = convertView.findViewById(R.id.ivContractIcon);
            holder.tvContractName = convertView.findViewById(R.id.tvContractName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ContractDetail contractInfo = contractList.get(position);
        imageLoader.displayImage(contractInfo.getCtiIcon(), holder.ivContractIcon, options, animateFirstListener);
        holder.tvContractName.setText(contractInfo.getCtiName());

        return convertView;
    }

    class ViewHolder {
        ImageView ivContractIcon;
        TextView tvContractName;
    }
}
