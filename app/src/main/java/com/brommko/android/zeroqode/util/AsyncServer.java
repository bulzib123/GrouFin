package com.brommko.android.zeroqode.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.AsyncTask;

/**
 * Created by dragank on 2/27/2018.
 */

public class AsyncServer extends AsyncTask<Void, Void, Void> {
    @SuppressLint("StaticFieldLeak")
    private
    Context context;

    public AsyncServer(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ContextWrapper c= new ContextWrapper(context);
        swlib.Swlib.setDirectory(c.getFilesDir().getAbsolutePath());
        swlib.Swlib.runServer();
        return null;
    }
}
