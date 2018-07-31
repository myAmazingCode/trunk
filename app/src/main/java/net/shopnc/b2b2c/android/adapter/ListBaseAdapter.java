package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import net.shopnc.b2b2c.android.common.MyShopApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用adapter
 * @author 胡婷
 * @param <T>
 * @date 2015年12月1日 下午2:03:47
 */
public class ListBaseAdapter<T> extends BaseAdapter {

	protected LayoutInflater inflater;
	protected List<T> mDatas = new ArrayList<T>();
	protected Context ctx;
	protected MyShopApplication app;
    protected String store_id = "";
	
	public ListBaseAdapter(Context ctx) {
		inflater = LayoutInflater.from(ctx);
		this.ctx = ctx;
		app = (MyShopApplication) this.ctx.getApplicationContext();
	}
	
	@Override
	public int getCount() {
		return mDatas== null ? 0 : mDatas.size();
	}

	@Override
	public T getItem(int position) {
		if (mDatas.size() > position) {
			return mDatas.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 留作以后扩展
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		  return getRealView(position, convertView, parent);
    }

    protected View getRealView(int position, View convertView, ViewGroup parent) {
        //mDatas.remove(position);
        return null;
    }
	
	public void setData(List<T> data) {
        mDatas = data;
        notifyDataSetChanged();
    }

    public void setStoreId(String id) {
        store_id = id;
        notifyDataSetChanged();
    }

    public String getStoreId(){
        return store_id == null ? "" : store_id;
    }
	
	public List<T> getData() {
        return mDatas == null ? (mDatas = new ArrayList<T>()) : mDatas;
    }
	
	public void addData(List<T> data) {
        if (mDatas != null && data != null && !data.isEmpty()) {
            mDatas.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addItem(T obj) {
        if (mDatas != null) {
            mDatas.add(obj);
        }
        notifyDataSetChanged();
    }

    public void addItem(int pos, T obj) {
        if (mDatas != null) {
            mDatas.add(pos, obj);
        }
        notifyDataSetChanged();
    }

    public void removeItem(Object obj) {
        mDatas.remove(obj);
        notifyDataSetChanged();
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }
}
