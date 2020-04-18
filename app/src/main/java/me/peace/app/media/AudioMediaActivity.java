package me.peace.app.media;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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
            media.play();
            text.setText(play.getText());
        });
        pause.setOnClickListener(v -> {
            media.pause();
            text.setText(pause.getText());
        });
    }

    private void init(){
//        media = new MusicSoundPool();
//        media = new MusicMediaPlayer();
        media = new MusicPlayer();
        media.init(this);
//                    media.setDataSource(R.raw.marvel_studios);
        media.setDataSource(path);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        media.stop();
        media.release();
    }
}
