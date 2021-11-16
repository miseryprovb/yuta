package edu.pku.canoe.yuta.fragments;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.pku.canoe.yuta.R;


public class Video extends Fragment {
    private VideoView video;
    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_video, container, false);
        video = inflate.findViewById(R.id.VideoView1);
        MediaController mc = new MediaController(this.getContext());
        video.setMediaController(mc);
        video.setVideoURI(Uri.parse("android.resource://edu.pku.canoe.yuta/" +R.raw.shortvideo1));
        video.requestFocus();
        try{
            video.start();

        }catch(Exception e){
            e.printStackTrace();
        }
        video.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
            }
        });
        return inflate;
    }
}