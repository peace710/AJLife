package me.peace.app.media;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Executors;

import androidx.appcompat.app.AppCompatActivity;
import me.peace.app.R;
import me.peace.app.media.music.IMedia;
import me.peace.app.media.music.MusicMediaPlayer;
import me.peace.app.media.music.MusicPlayer;
import me.peace.app.media.music.MusicSoundPool;

public class AudioMediaActivity extends AppCompatActivity {

    private String path = "http://192.168.31.252:8081/marvel_studios.mp3";
    private TextView text;
    private Button play;
    private Button pause;
    private IMedia media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        initView();
        init();
    }

    private void initView(){
        text = findViewById(R.id.text);
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);

        play.setOnClickListener(v -> {
            try {
                media.play();
                text.setText(play.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        pause.setOnClickListener(v -> {
            try {
                media.pause();
                text.setText(pause.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void init(){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                //        media = new MusicSoundPool();
                //        media = new MusicMediaPlayer();
                try {
                    media = new MusicPlayer();
                    media.init(AudioMediaActivity.this);
                    //                    media.setDataSource(R.raw.marvel_studios);
                    media.setDataSource(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        media.stop();
        media.release();
    }
}
