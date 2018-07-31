package net.shopnc.b2b2c.android.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter
{
	protected Context mContext;
	protected List<T> mDatas;
	protected LayoutInflater mInflater;
	private int layoutId;

	public CommonAdapter(Context context, List<T> datas, int layoutId)
	{
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


	public List<T> getmDatas() {
		return mDatas;
	}

	public void setmDatas(List<T> mDatas) {
		this.mDatas = mDatas;
	}

	@Override
	public int getCount()
	{
		return mDatas== null ? 0 :mDatas.size();
	}

	@Override
	public T getItem(int position)
	{
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		if(convertView == null){
			convertView = mInflater.inflate(layoutId,null);
		}
		convert(convertView, getItem(position));
		return convertView;
	}

	public abstract void convert(View convertView, T t);

}
