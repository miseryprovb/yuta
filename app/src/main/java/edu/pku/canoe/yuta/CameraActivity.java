package edu.pku.canoe.yuta;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.pku.canoe.yuta.util.FormatUtil;


public class CameraActivity extends Activity {

    private String tag ="CameraActivity";
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Camera camera;
    private MediaRecorder mediaRecorder;
    private Button photoButton;  //拍照按钮
    private Button vedioButton;  //摄像按钮
    private TextView timeTextView;

    protected boolean isPreview = false; //摄像区域是否准备良好
    private boolean isRecording = true; // true表示没有录像，点击开始；false表示正在录像，点击暂停
    private boolean bool;

    private int hour = 0;
    private int minute = 0;     //计时专用
    private int second = 0;
    public ButtonOnClickListener onClickListener = new ButtonOnClickListener();

    public void setSurfaceView(SurfaceView surfaceView) {
        this.surfaceView = surfaceView;
    }

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public void setPhotoButton(Button photoButton) {
        this.photoButton = photoButton;
    }

    public void setVedioButton(Button vedioButton) {
        this.vedioButton = vedioButton;
    }

    public void setTimeTextView(TextView timeTextView) {
        this.timeTextView = timeTextView;
    }

    private File mRecVedioPath;
    private File mRecAudioFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initCamera();
        initViews();
    }

    private void setCamera(){
        camera = Camera.open();
        //设置Camera的角度/方向
        camera.setDisplayOrientation(90);
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        parameters.setPreviewSize(1280, 720);
        parameters.setPictureSize(1920, 1080);
        parameters.setPictureFormat(ImageFormat.JPEG);// 设置照片的输出格式
        parameters.set("jpeg-quality", 85);// 照片质量
        camera.setParameters(parameters);
        try {
            camera.setPreviewDisplay(surfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //初始化摄像头
    public void initCamera() {
        mRecVedioPath = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/mahc/video/temp/");
        if (!mRecVedioPath.exists()) {
            mRecVedioPath.mkdirs();
        }
        SurfaceHolder cameraSurfaceHolder = surfaceView.getHolder();
        cameraSurfaceHolder.addCallback(new Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    camera = Camera.open();
                    //设置Camera的角度/方向
                    camera.setDisplayOrientation(90);
                    Camera.Parameters parameters = camera.getParameters();
                    List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
                    for(int i=0;i<supportedPreviewSizes.size();i++)
                    {
                        Log.v("preview size", "width="+supportedPreviewSizes.get(i).width+";height="+supportedPreviewSizes.get(i).height);
                    }
                    List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
                    for(int i=0;i<supportedPictureSizes.size();i++)
                    {
                        Log.v("picture size", "width="+supportedPictureSizes.get(i).width+";height="+supportedPictureSizes.get(i).height);
                    }
                    parameters.setPreviewSize(1280, 720);
                    parameters.setPictureSize(1920, 1080);
                    parameters.setPictureFormat(ImageFormat.JPEG);// 设置照片的输出格式
                    parameters.set("jpeg-quality", 85);// 照片质量
                    camera.setParameters(parameters);
                    camera.setPreviewDisplay(holder);
                    isPreview = true;
                    camera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                surfaceHolder = holder;
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                       int height) {
                surfaceHolder = holder;
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.v(tag,"destory");
                if (camera != null) {
                    if (isPreview) {
                        camera.stopPreview();
                        isPreview = false;
                    }
                    camera.release();
                    camera = null; // 记得释放Camera
                }
                surfaceView = null;
                surfaceHolder = null;
                mediaRecorder = null;
            }
        });
    }

    //初始化视图组件
    public void initViews() {
        timeTextView.setVisibility(View.VISIBLE);
        photoButton.setOnClickListener(onClickListener);
        vedioButton.setOnClickListener(onClickListener);
    }

    public class ButtonOnClickListener implements OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.camera_vedio:
                    //点击开始录像
                    if(isRecording){
                        if(camera == null){
                            setCamera();
                            isPreview = true;
                            camera.startPreview();
                        }
                        second = 0;
                        minute = 0;
                        hour = 0;
                        bool = true;
                        if(null==mediaRecorder){
                            mediaRecorder = new MediaRecorder();
                        }else {
                            mediaRecorder.reset();
                        }
                        //mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_1080P));
                        camera.unlock();
                        mediaRecorder.setCamera(camera);
                        //表面设置显示记录媒体（视频）的预览
                        mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());
                        //开始捕捉和编码数据到setOutputFile（指定的文件）
                        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                        //设置在录制过程中产生的输出文件的格式
                        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                        //设置视频编码器，用于录制
                        mediaRecorder.setVideoEncodingBitRate(900*1024);
                        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
                        //设置要捕获的视频的宽度和高度
                        mediaRecorder.setVideoSize(1920, 1080);
                        // 设置要捕获的视频帧速率
                        mediaRecorder.setVideoFrameRate(30);
                        try {
                            mRecAudioFile = File.createTempFile("Vedio", ".mp4",
                                    mRecVedioPath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaRecorder.setOutputFile(mRecAudioFile.getAbsolutePath());
                        mediaRecorder.setOrientationHint(90);
                        try {
                            mediaRecorder.prepare();
                            timeTextView.setVisibility(View.VISIBLE);
                            handler.postDelayed(task, 1000);
                            mediaRecorder.start();
                            Log.v("camera_video", "recorder start");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        isRecording = !isRecording;
                        Log.e(tag, "=====开始录制视频=====");

                        vedioButton.setText("停止录制");
                    }else {
                        //点击停止录像
                        bool = false;
                        mediaRecorder.setOnErrorListener(null);
                        mediaRecorder.setOnInfoListener(null);
                        mediaRecorder.setPreviewDisplay(null);
                        try{
                            mediaRecorder.stop();
                            Log.v("camera_video", "recorder stop");
                            mediaRecorder.release();
                            mediaRecorder = null;
                            camera.stopPreview();
                            camera.release();
                            camera = null;
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        timeTextView.setText("00:00:00");

                        FormatUtil.videoRename(mRecAudioFile);
                        Log.e(tag, "=====录制完成，已保存=====");
                        vedioButton.setText("开始录制");
                        isRecording = !isRecording;
                        try {
                            setCamera();
                            camera.startPreview();
                            isPreview = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case R.id.camera_photo:
                    if (mediaRecorder != null) {
                        try {
                            bool = false;
                            mediaRecorder.stop();
                            Log.v("camera_photo", "recorder stop");
                            camera.stopPreview();
                            camera.release();
                            camera = null;
                            timeTextView.setText("00:00:00");
                            mediaRecorder.release();
                            mediaRecorder = null;
                            FormatUtil.videoRename(mRecAudioFile);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        isRecording = !isRecording;
                        Log.e(tag, "=====录制完成，已保存=====");
                        Toast toastCenter = Toast.makeText(getApplicationContext(), "录制完成，已保存", Toast.LENGTH_SHORT);
                        toastCenter.setGravity(Gravity.CENTER, 0, 0);
                        toastCenter.show();
                        vedioButton.setText("开始录制");
                        try {
                            setCamera();
                            camera.startPreview();
                            isPreview = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (camera != null) {
                        camera.autoFocus(null);
                        camera.takePicture(null, null, new PictureCallback() {
                            @Override
                            public void onPictureTaken(byte[] data, Camera camera) {
                                new SavePictureTask().execute(data);
                                camera.startPreview();
                                Log.e(tag,"=====拍照成功=====");
                            }
                        }); // 拍照

                    }
                    break;
                default:
                    break;
            }
        }
    }
    /*
     * 定时器设置，实现计时
     */
    private Handler handler = new Handler();
    private Runnable task = new Runnable() {
        public void run() {
            if (bool) {
                handler.postDelayed(this, 1000);
                second++;
                if (second >= 60) {
                    minute++;
                    second = second % 60;
                }
                if (minute >= 60) {
                    hour++;
                    minute = minute % 60;
                }
                timeTextView.setText(FormatUtil.format(hour) + ":" + FormatUtil.format(minute) + ":"
                        + FormatUtil.format(second));
            }
        }
    };



    class SavePictureTask extends AsyncTask<byte[], String, String> {
        @Override
        protected String doInBackground(byte[]... params) {
            String path = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/mahc/image";
            File out = new File(path);
            if (!out.exists()) {
                out.mkdirs();
            }
            File picture = new File(path+"/"+new Date().getTime()+".jpg");
            try {
                FileOutputStream fos = new FileOutputStream(picture.getPath());
                fos.write(params[0]);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e(tag, "=====照片保存完成=====");
            return null;
        }
    }
}