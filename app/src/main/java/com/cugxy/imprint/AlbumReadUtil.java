package com.cugxy.imprint;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;

/**
 * Created by cugxy on 2018/8/25.
 *
 */

public class AlbumReadUtil {
    private final static String TGA = "AlbumReadUtil";

    static public ArrayList<MarkerInfo> getAllPhotoInfo(Context context) {
        ArrayList<MarkerInfo> result = new ArrayList<MarkerInfo>();

        String[] projection = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE,
        };

        //全部图片
        String where = MediaStore.Images.Media.MIME_TYPE + "=? or "
                + MediaStore.Images.Media.MIME_TYPE + "=? or "
                + MediaStore.Images.Media.MIME_TYPE + "=?";
        //指定格式
        String[] whereArgs = {"image/jpeg", "image/png", "image/jpg"};
        //查询
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, where, whereArgs,
                MediaStore.Images.Media.DATE_MODIFIED + " desc ");
        if (cursor == null) {
            return result;
        }

        while (cursor.moveToNext()) {

        }
        return result;
    }

}
