package me.peace.jetpack.navigation.deeplink;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import me.peace.jetpack.R;

public class DeepLink extends Fragment {
    private TextView text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_deep_link, container, false);
        text = view.findViewById(R.id.text);
        init();
        return view;
    }

    private void init(){
        Bundle bundle = getArguments();
        String params = "empty";
        if (bundle != null){
            params = bundle.getString("params");
            if (TextUtils.isEmpty(params)){
                params = "empty";
            }
        }
        text.setText("params is " + params);
    }
}
