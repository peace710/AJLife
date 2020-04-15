package me.peace.jetpack.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import me.peace.jetpack.R;

public class Navi extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navi, container, false);
        //完成fragment切换方式1
        view.findViewById(R.id.navi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_navi_to_navi2);
            }
        });
        //完成fragment切换方式2
//        view.findViewById(R.id.navi).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_navi_to_navi2));
        return view;
    }

}
