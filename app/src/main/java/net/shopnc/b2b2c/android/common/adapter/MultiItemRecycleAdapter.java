package net.shopnc.b2b2c.android.common.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description 多item布局适配器
 * @Author qyf
 *
 * Created 2016/4/15 18:26.
 */
public abstract class MultiItemRecycleAdapter<T> extends RRecyclerAdapter<T> {
    protected MultiRecyclerItemSupport<T> mMultiItemTypeSupport;
    private List<View> headViews;
    private List<View> footViews;
    private HashMap<Integer, View> middleViews;
    protected int position;

    public MultiItemRecycleAdapter(Context context, MultiRecyclerItemSupport<T> multiItemTypeSupport) {
        super(context);
        headViews = new ArrayList<>();
        footViews = new ArrayList<>();
        datas = new ArrayList<>();
        this.mMultiItemTypeSupport = multiItemTypeSupport;
    }

    public void setHeadViews(List<View> headViews) {
        this.headViews = headViews;
    }

    public void setFootViews(List<View> footViews) {
        this.footViews = footViews;
    }

    public void setMiddleViews(HashMap<Integer, View> middleViews) {
        this.middleViews = middleViews;
        for (Integer i : middleViews.keySet()) {
            this.datas.add(i, null);
        }

    }

    @Override
    public int getItemCount() {
        return (datas == null ? 0 : datas.size()) + (headViews == null ? 0 : headViews.size()) + (footViews == null ? 0 : footViews.size());
    }

    @Override
    public int getItemViewType(int position) {
        this.position = position;
        if (position < headViews.size()) {
            return Integer.MIN_VALUE;
        } else if (position >= datas.size() + headViews.size()) {
            return Integer.MAX_VALUE;
        } else {
            if (datas.get(position - headViews.size()) == null) {
                return 0;
            }
            return mMultiItemTypeSupport.getItemViewType(datas.get(position - headViews.size()));
        }
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        for (Integer i : mMultiItemTypeSupport.getLayoutMap().keySet()) {
            if (viewType == i) {
                View itemView = View.inflate(parent.getContext(), mMultiItemTypeSupport.getLayoutMap().get(i), null);
                RecyclerHolder holder = RecyclerHolder.get(context, itemView);
                holder.getConvertView().setTag(mMultiItemTypeSupport.getLayoutMap().get(i));
                return holder;
            } else if (viewType == Integer.MIN_VALUE) {
                return RecyclerHolder.get(context, headViews.get(position));
            } else if (viewType == Integer.MAX_VALUE) {
                return RecyclerHolder.get(context, footViews.get(position - headViews.size() - datas.size()));
            } else if (viewType == 0) {
                return RecyclerHolder.get(context, middleViews.get(position));
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        for (Integer i : mMultiItemTypeSupport.getLayoutMap().keySet()) {
            if (getItemViewType(position) == i) {
                convert(holder, datas.get(position - headViews.size()));
            }
        }
    }

    public abstract void convert(RecyclerHolder holder, T t);
}
