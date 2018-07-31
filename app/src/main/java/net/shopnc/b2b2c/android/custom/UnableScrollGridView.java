package net.shopnc.b2b2c.android.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class UnableScrollGridView extends GridView
{
  public UnableScrollGridView(Context paramContext)
  {
    super(paramContext);
  }

  public UnableScrollGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, MeasureSpec.makeMeasureSpec(536870911, -2147483648));
  }
}