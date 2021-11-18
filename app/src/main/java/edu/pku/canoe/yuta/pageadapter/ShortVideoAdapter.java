package edu.pku.canoe.yuta.pageadapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.pku.canoe.yuta.R;
import edu.pku.canoe.yuta.fragments.Video;

public class ShortVideoAdapter extends RecyclerView.Adapter<ShortVideoAdapter.ViewHolder> {

    private List<Integer> mShortVideoList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView shortVideoView;

        public ViewHolder(View view) {
            super(view);
            shortVideoView = view.findViewById(R.id.short_video);
        }

    }

    public ShortVideoAdapter(List<Integer> shortList) {
        mShortVideoList = shortList;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.short_video_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        int source = mShortVideoList.get(position);
        holder.shortVideoView.setImageResource(source);
    }

    @Override
    public int getItemCount() {
        return mShortVideoList.size();
    }

    public void setVideoParams(VideoView video, Context context) {
        MediaController mc = new MediaController(context);
        video.setMediaController(mc);
        video.requestFocus();
        try{
            video.start();

        }catch(Exception e){
            e.printStackTrace();
        }
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
            }
        });
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0f, 0f);
                mp.start();
//                        mVideoView.start();
            }
        });
    }
}