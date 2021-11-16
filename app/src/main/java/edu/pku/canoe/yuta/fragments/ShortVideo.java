package edu.pku.canoe.yuta.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLOnCompletionListener;
import com.pili.pldroid.player.PLOnErrorListener;
import com.pili.pldroid.player.PLOnInfoListener;
import com.pili.pldroid.player.PLOnPreparedListener;
import com.pili.pldroid.player.PLOnVideoSizeChangedListener;
import com.pili.pldroid.player.widget.PLVideoView;

import edu.pku.canoe.yuta.R;

public class ShortVideo extends Fragment implements

        PLOnPreparedListener,

        PLOnInfoListener,

        PLOnCompletionListener,

        PLOnVideoSizeChangedListener,

        PLOnErrorListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_short_video, container, false);
        PLVideoView mVideoView = inflate.findViewById(R.id.VideoView);


        AVOptions options = new AVOptions();
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT,10*1000);
        options.setInteger(AVOptions.KEY_MEDIACODEC,AVOptions.MEDIA_CODEC_SW_DECODE);
        options.setInteger(AVOptions.KEY_LIVE_STREAMING,1);
        options.setInteger(AVOptions.KEY_LOG_LEVEL,0);
        mVideoView.setAVOptions(options);
        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_FIT_PARENT);
        String videoPath = "src/main/java/com/example/demo1/shortvideos/shortvideo1.mp4";
        mVideoView.setVideoPath(videoPath);
        mVideoView.start();

        return inflate;
    }

    @Override
    public void onInfo(int i, int i1) {

    }

    @Override
    public boolean onError(int i) {
        return false;
    }

    @Override
    public void onCompletion() {

    }

    @Override
    public void onVideoSizeChanged(int i, int i1) {

    }

    @Override
    public void onPrepared(int i) {

    }
}