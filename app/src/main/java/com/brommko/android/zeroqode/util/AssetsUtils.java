package com.brommko.android.zeroqode.util;

import android.content.Context;
import android.content.ContextWrapper;

import java.io.IOException;

import flipagram.assetcopylib.AssetCopier;

/**
 * Created by dragank on 2/27/2018.
 */

public class AssetsUtils {
    public static void copyAssetFolderToFolder(Context activity) {
        try {
                ContextWrapper c = new ContextWrapper(activity);
                new AssetCopier(activity)
                        .withFileScanning()
                        .copy("web", c.getFilesDir());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
