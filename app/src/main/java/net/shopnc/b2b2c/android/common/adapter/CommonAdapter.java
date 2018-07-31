package net.shopnc.b2b2c.android.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zhy.autolayout.utils.AutoUtils;

import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.interfac.INCOnDel;
import net.shopnc.b2b2c.android.interfac.INCOnEdit;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private int layoutId;
    protected int position;
    protected INCOnDel incOnDel;
    protected INCOnEdit incOnEdit;

    public CommonAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.layoutId = layoutId;
    }

    protected CommonAdapter(Context context, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
    }

    /***
     * 针对地址列表
     * @param context
     * @param layoutId
     */
    protected CommonAdapter(Context context, int layoutId, INCOnDel incOnDel, INCOnEdit incOnEdit) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.incOnDel = incOnDel;
        this.incOnEdit = incOnEdit;
    }

    public List<T> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent,
                layoutId, position);
        this.position = position;
        convert(holder, getItem(position));

        //新增
        AutoUtils.autoSize(holder.getConvertView());

        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T t);

}
