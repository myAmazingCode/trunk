package net.shopnc.b2b2c.android.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

//让容器中的组件自动换行

public class LineBreakLayout extends ViewGroup {

	private int VIEW_MARGIN = 6;

	public void setVIEW_MARGIN(int vIEW_MARGIN) {
		VIEW_MARGIN = vIEW_MARGIN;
	}

	public LineBreakLayout(Context context) {
		super(context);
	}

	public LineBreakLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public LineBreakLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	private void measueChile(View child){
		child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
	}
	
	protected void measureOrLayoutChildView(int widthMeasureSpec,boolean measureChild){
		final int count = getChildCount();
		int left = getLeft();
		int right = getRight();
		int row = 0;// which row lay you view relative to parent
		int childRight = left; // right position of child relative to parent
		int childBottom = 0; // bottom position of child relative to parent
		View child = null;
		for (int i = 0; i < count; i++) {
			child = this.getChildAt(i);
			if(measureChild){
				measueChile(child);
			}
			int width = child.getMeasuredWidth();
			int height = child.getMeasuredHeight();
			childRight += width + VIEW_MARGIN;
			childBottom = row * (height + VIEW_MARGIN) + height+VIEW_MARGIN;
			// if it can't drawing on a same line , skip to next line
			if (childRight > right) {
				childRight = width + VIEW_MARGIN + left;
				row++; 
				childBottom = row * (height + VIEW_MARGIN) + height+VIEW_MARGIN;
			}
			if(!measureChild){
				child.layout(childRight - width, childBottom - height, childRight, childBottom);
			}
		}
		if(measureChild){
			int mWidth = MeasureSpec.getSize(widthMeasureSpec); 
			setMeasuredDimension(mWidth, childBottom);
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		measureOrLayoutChildView(widthMeasureSpec, true);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		measureOrLayoutChildView(0, false);
	}
}
