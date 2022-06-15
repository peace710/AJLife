package me.peace.javajni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import me.peace.javajni.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'javajni' library on application startup.
    static {
        System.loadLibrary("javajni");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());

        initCallback(url -> {
            tv.append("\n" + url);
            return url.replace("Jni","Android");
        });
    }

    /**
     * A native method that is implemented by the 'javajni' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native void initCallback(Callback c);

    interface Callback{
        String exchange(String url);
    }
}