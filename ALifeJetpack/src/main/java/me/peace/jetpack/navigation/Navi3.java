package me.peace.jetpack.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import me.peace.jetpack.R;


public class Navi3 extends Fragment {

    private TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navi3, container, false);
        text = view.findViewById(R.id.text);
        init();
        return view;
    }

    private void init(){
        Navi3Args args = Navi3Args.fromBundle(getArguments());
        String str = args.getName() + "," + args.getAge() + "," + args.getJob();
        text.setText(str);
    }

}
