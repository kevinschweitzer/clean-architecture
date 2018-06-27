package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.app.FragmentManager;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class ActivityView {
    private WeakReference<AppCompatActivity> activityRef;

    public ActivityView(AppCompatActivity activity) {
        activityRef = new WeakReference<>(activity);
    }

    @Nullable
    public AppCompatActivity getActivity() {
        return activityRef.get();
    }

    @Nullable
    public Context getContext() {
        return getActivity();
    }

    @Nullable
    public FragmentManager getFragmentManager() {
        AppCompatActivity activity = getActivity();
        return (activity != null) ? activity.getFragmentManager() : null;
    }
}
