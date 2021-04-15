package com.example.playground.drawcolor;

import android.graphics.Bitmap;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

class BitmapScaleTransForm extends BitmapTransformation {
    private static final String ID = "com.example.playground.drawcolor.BitmapScaleTransForm";
    private static byte[] ID_BYTES = null;

    @Override
    public Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        if (toTransform.getWidth() == outWidth && toTransform.getHeight() == outHeight) {
            return toTransform;
        }

        return Bitmap.createScaledBitmap(toTransform, outWidth, outHeight, /*filter=*/ true);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof BitmapScaleTransForm;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        if (ID_BYTES == null) {
            try {
                ID_BYTES = ID.getBytes(STRING_CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        messageDigest.update(ID_BYTES);
    }
}
