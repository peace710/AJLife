package me.peace.app.gray;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class GrayLinearLayout extends LinearLayout {
    private Paint mPaint = new Paint();

    public GrayLinearLayout(Context context) {
        this(context,null);
    }

    public GrayLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GrayLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        mPaint.setColorFilter(new ColorMatrixColorFilter(cm));
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.saveLayer(null,mPaint,Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        canvas.restore();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(null,mPaint,Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        canvas.restore();
    }
}
