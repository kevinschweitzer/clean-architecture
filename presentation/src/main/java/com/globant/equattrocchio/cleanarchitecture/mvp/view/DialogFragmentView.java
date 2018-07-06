package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.app.DialogFragment;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

public class DialogFragmentView {

    private WeakReference<DialogFragment> fragment;


    public DialogFragmentView(DialogFragment fragment){
        this.fragment = new WeakReference<>(fragment);
    }

    @Nullable
    public DialogFragment getFragment(){
        return fragment.get();
    }
}
