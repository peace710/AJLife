package me.peace.base;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportGray();
        setContentView(offerContentViewId());
        initView();
        initListener();
        init();
        setTitle(this.getClass().getSimpleName());
    }

    protected abstract int offerContentViewId();

    protected abstract void init();

    protected abstract void initView();

    protected abstract void initListener();

    protected void t(@StringRes int resId){
        Toast.makeText(this,resId,Toast.LENGTH_LONG).show();
    }

    protected void t(String text){
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }

    private boolean isGray(){
        return false;
    }

    private void supportGray(){
        if (isGray()){
            Paint paint = new Paint();
            ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(0);
            paint.setColorFilter(new ColorMatrixColorFilter(cm));
            getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE,paint);
        }
    }
}
