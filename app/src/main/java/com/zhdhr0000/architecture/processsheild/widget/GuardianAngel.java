package com.zhdhr0000.architecture.processsheild.widget;

import android.os.Looper;
import android.util.Log;

/**
 * Created by win7 on 2017/3/6.
 */

public class GuardianAngel {
    private static GuardianAngel guardianAngel = new GuardianAngel();
    private static boolean guarded = false;
    private static Thread.UncaughtExceptionHandler sUncaughtExceptionHandler;
    private static ExceptionHandler sExceptionHandler = new ExceptionHandler() {
        @Override
        public void onException(Throwable throwable) {
            Log.e("GuardianAngel", null, throwable);
        }
    };


    public static void protect() {
        protect(null);
    }

    public static void protect(ExceptionHandler exceptionHandler) {
        if (guarded) {
            return;
        }
        if (exceptionHandler != null) {
            sExceptionHandler = exceptionHandler;
        }
        sUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                sExceptionHandler.onException(e);
            }
        });
        guarded = true;
        while (guarded) {
            try {
                Looper.getMainLooper().loop();
            } catch (Throwable e) {
                sExceptionHandler.onException(e);
            }
        }
    }

    public interface ExceptionHandler {
        void onException(Throwable throwable);
    }
}
