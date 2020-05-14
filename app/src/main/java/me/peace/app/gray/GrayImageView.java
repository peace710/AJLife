package me.peace.app.gray;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class GrayImageView extends AppCompatImageView {
    private Paint mPaint = new Paint();

    public GrayImageView(Context context) {
        this(context,null);
    }

    public GrayImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GrayImageView(Context context, AttributeSet attrs, int defStyleAttr) {
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
}
