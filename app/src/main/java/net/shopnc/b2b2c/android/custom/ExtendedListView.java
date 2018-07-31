package net.shopnc.b2b2c.android.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ExtendedListView extends ListView {

    private OnEndOfListListener onEndOfListListener;

    private boolean hasWarned = false;

    public ExtendedListView(Context context) {
        super(context);
        init();
    }

    public ExtendedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExtendedListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            	if(SCROLL_STATE_IDLE == scrollState){
	        		if (hasWarned
	                        || view.getLastVisiblePosition() != view.getCount() - 1
	                        || onEndOfListListener == null)
	                    return;
	
	                hasWarned = true;
	                Object lastItem = view.getItemAtPosition(view.getCount() - 1);
	                if (lastItem != null || view.getCount() == 0)
	                    onEndOfListListener.onEndOfList(lastItem);
            	}
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    @Override
    protected void handleDataChanged() {
        super.handleDataChanged();
        hasWarned = false;
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        hasWarned = false;
    }

    public void setOnEndOfListListener(OnEndOfListListener onEndOfListListener) {
        this.onEndOfListListener = onEndOfListListener;
    }

    public interface OnEndOfListListener<T> {
        void onEndOfList(T lastItem);
    }
}
