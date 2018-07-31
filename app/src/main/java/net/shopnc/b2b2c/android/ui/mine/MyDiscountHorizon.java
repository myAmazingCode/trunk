package net.shopnc.b2b2c.android.ui.mine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 优惠券
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.ui.mine.MyDiscountCoupon.java
 * @author: Jie
 * @date: 2016-05-29 18:26
 */
public class MyDiscountHorizon extends LinearLayout {
    private static final String TAG = "MyDiscountCoupon";

    private Paint paint;
    private float gap = 8;
    private float radius = 10;

    private int circleNum;
    private float remain;

    public MyDiscountHorizon(Context context, AttributeSet attr) {
        super(context, attr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain == 0) {
            remain = (w - gap) % (2 * radius + gap);
        }
        circleNum = (int) ((w - gap) / (2 * radius + gap));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < circleNum; i++) {
            float h = gap + radius + remain / 2 + ((gap + radius * 2) * i);
            canvas.drawCircle(h, getHeight(), radius, paint);
//            canvas.drawCircle(getWidth(), h, radius, paint);
        }
    }
}
