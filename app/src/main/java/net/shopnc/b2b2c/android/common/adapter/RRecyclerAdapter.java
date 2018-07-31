package net.shopnc.b2b2c.android.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * @Description RRecyclerAdapter封装
 * @Result 1.只有一种item的情况下，缓存的ViewHolder的数目为RecyclerView在滑动过程中所能在一屏内容纳的最大item个数+2
 * 2.有至少两种item显示的情况下，每种item的ViewHolder的缓存个数为单种item在一屏内最大显示个数+1
 * @Author qyf
 *
 * Created 2016/4/14 13:49.
 */
public abstract class RRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerHolder> {
    protected Context context;
    protected List<T> datas;
    protected int layoutId;
    protected int position;
    private List<View> headViews;

    public RRecyclerAdapter(Context context) {
        this.context = context;
    }

    public RRecyclerAdapter(Context context, int layoutId) {
        this.context = context;
        this.layoutId = layoutId;
    }

    public RRecyclerAdapter(Context context, int layoutId, List<T> datas) {
        this.context = context;
        this.layoutId = layoutId;
        this.datas = datas;
    }

    public void setHeadViews(List<View> headViews) {
        this.headViews = headViews;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public int getItemCount() {
        return (datas == null ? 0 : datas.size()) + (headViews == null ? 0 : headViews.size());
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Integer.MIN_VALUE) {
            return RecyclerHolder.get(context, headViews.get(position));
        }
        View itemView = View.inflate(parent.getContext(), layoutId, null);
        return new RecyclerHolder(context, itemView);

    }

    @Override
    public int getItemViewType(int position) {
        if (headViews != null && position < headViews.size()) {
            return Integer.MIN_VALUE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        this.position = position;
        if (getItemViewType(position) != Integer.MIN_VALUE)
            convert(holder, datas.get(position - (headViews != null ? headViews.size() : 0)));
    }

    public abstract void convert(RecyclerHolder holder, T t);


}