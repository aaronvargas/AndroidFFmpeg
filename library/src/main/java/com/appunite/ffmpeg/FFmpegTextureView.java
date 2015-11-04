package com.appunite.ffmpeg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;

/**
 * Created by aaron on 10/22/15.
 */
public class FFmpegTextureView extends TextureView implements FFmpegDisplay, SurfaceTextureListener {

    private Surface surface;

    public static enum ScaleType {
        CENTER_CROP, CENTER_INSIDE, FIT_XY
    }

    private FFmpegPlayer mMpegPlayer = null;
    private boolean mCreated = false;

    public FFmpegTextureView(Context context) {
        this(context, null, 0);
    }

    public FFmpegTextureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FFmpegTextureView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setSurfaceTextureListener(this);
    }

    @Override
    public void setMpegPlayer(FFmpegPlayer fFmpegPlayer) {
        if (mMpegPlayer != null)
            throw new RuntimeException(
                    "setMpegPlayer could not be called twice");

        this.mMpegPlayer = fFmpegPlayer;
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {

        if (mCreated  == true) {
            onSurfaceTextureDestroyed(surfaceTexture);
        }

        this.surface = new Surface(surfaceTexture);
        mMpegPlayer.render(surface);
        mCreated = true;
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
//        this.mMpegPlayer.renderFrameStop();
        mCreated = false;

        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    public Bitmap getScreenShot() {
        return getBitmap();
    }


}
