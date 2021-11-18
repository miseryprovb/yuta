package edu.pku.canoe.yuta.pageadapter;

import edu.pku.canoe.yuta.R;

public class ShortVideoSource {
    private String sourcePath;
    private String shortVideo;

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getShortVideo() {
        return shortVideo;
    }

    public void setShortVideo(String shortVideo) {
        this.shortVideo = shortVideo;
    }

    public ShortVideoSource(String sourcePath, String shortVideo) {
        this.sourcePath = sourcePath;
        this.shortVideo = shortVideo;
    }
}
