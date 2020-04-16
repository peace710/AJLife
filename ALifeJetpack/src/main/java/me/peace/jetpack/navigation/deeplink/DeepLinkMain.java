package me.peace.jetpack.navigation.deeplink;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import me.peace.jetpack.R;

public class DeepLinkMain extends Fragment {
    private static final String CHANNEL_ID = "1";
    private static final int NOTIFICATION_ID = 8;
    private static final String CHANNEL_NAME = "deepLink";
    private static final String DESCRIPTION = "deepLink description";

    private Button deepLink;
    private Button send;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deep_link_main, container, false);
        deepLink = view.findViewById(R.id.deep_link);
        send = view.findViewById(R.id.send);
        init();
        return view;
    }

    private void init(){
        deepLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_main_2_link);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
    }

    private void sendNotification(){
        if (getActivity() != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                    importance);
                channel.setDescription(DESCRIPTION);
                NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
                manager.createNotificationChannel(channel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity()
                ,CHANNEL_ID).setSmallIcon(R.mipmap.ic_meizhi_green)
                .setContentTitle("Love & Peace")
                .setContentText("Hello World")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .setAutoCancel(true);

            NotificationManagerCompat managerCompat =
                NotificationManagerCompat.from(getActivity());
            managerCompat.notify(NOTIFICATION_ID,builder.build());
        }
    }

    private PendingIntent getPendingIntent(){
        if (getActivity() != null){
            Bundle bundle = new Bundle();
            bundle.putString("params","peace");
            return Navigation.findNavController(getActivity(),R.id.deep_link)
                .createDeepLink()
                .setGraph(R.navigation.deep_link)
                .setDestination(R.id.deep_link)
                .setArguments(bundle)
                .createPendingIntent();
        }
        return null;
    }
}
