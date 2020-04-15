package me.peace.jetpack.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import me.peace.jetpack.R;

public class Navi extends Fragment {
    private Button navi2;
    private Button navi3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navi, container, false);
        navi2 = view.findViewById(R.id.navi2);
        navi3 = view.findViewById(R.id.navi3);
        init();
        return view;
    }

    private void init(){
        //完成fragment切换方式1
        navi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_navi_to_navi2);
            }
        });

        //完成fragment切换方式2
        // navi2.setOnClickListener(Navigation.createNavigateOnClickListener(R.id
        //        .action_navi_to_navi2));

        navi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaviDirections.ActionNaviToNavi3 action = NaviDirections.actionNaviToNavi3();
                action.setName("peace");
                action.setAge(18);
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

}
