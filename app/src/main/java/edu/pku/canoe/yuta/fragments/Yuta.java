package edu.pku.canoe.yuta.fragments;


import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import edu.pku.canoe.yuta.CameraActivity;
import edu.pku.canoe.yuta.R;


public class Yuta extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_camera, container, false);
        TextView timeTextView = inflate.findViewById(R.id.camera_time);
        timeTextView.setVisibility(View.VISIBLE);
        final Button photoButton = inflate.findViewById(R.id.camera_photo);
        final Button vedioButton = inflate.findViewById(R.id.camera_vedio);
        CameraActivity cameraActivity = new CameraActivity();
        CameraActivity.ButtonOnClickListener onClickListener = cameraActivity.onClickListener;
        photoButton.setOnClickListener(onClickListener);
        vedioButton.setOnClickListener(onClickListener);
        SurfaceView surfaceView = inflate.findViewById(R.id.camera_surfaceview);
        cameraActivity.setSurfaceView(surfaceView);
        DisplayMetrics dm = this.getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        double rate = Math.min(1.0 * width / 720, 1.0 * height / 1280);
        Log.v("size","width=" + width + " height=" + height + " rate=" + rate);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (rate * 720), (int) (rate * 1280));
        layoutParams.setMargins(0, 95, 0, 0);
        surfaceView.setLayoutParams(layoutParams);
        cameraActivity.setSurfaceHolder(surfaceView.getHolder());
        cameraActivity.setTimeTextView(timeTextView);
        cameraActivity.setPhotoButton(photoButton);
        cameraActivity.setVedioButton(vedioButton);
        cameraActivity.initViews();
        cameraActivity.initCamera();
        return inflate;
    }




}

