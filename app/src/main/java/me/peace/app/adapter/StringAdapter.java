package me.peace.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.peace.app.R;
import me.peace.app.router.Router;

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.StringViewHolder> {
    private static final String TAG = StringAdapter.class.getSimpleName();

    private Context context;
    private List<String> strings;
    private List<Class> target;

    public StringAdapter(Context context, List<String> strings,List<Class> target) {
        this.context = context;
        this.strings = strings;
        this.target = target;
    }

    @NonNull
    @Override
    public StringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StringViewHolder(LayoutInflater.from(context).inflate(R.layout.item_text,parent,
            false));
    }

    @Override
    public void onBindViewHolder(@NonNull StringViewHolder holder, final int position) {
        holder.text.setText(strings.get(position));
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.getInstance().go(context,target.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return strings == null ? 0 : strings.size();
    }

    static class StringViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item;
        private TextView text;

        public StringViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            text = itemView.findViewById(R.id.text);
        }
    }

}
