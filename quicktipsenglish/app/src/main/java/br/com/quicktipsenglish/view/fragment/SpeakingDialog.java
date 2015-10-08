package br.com.quicktipsenglish.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;

import br.com.quicktipsenglish.R;
import br.com.quicktipsenglish.view.component.AnimatedScaleDrawable;

/**
 * Created by elizeu on 08/10/15.
 */
public class SpeakingDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle bundle) {
        return inflater.inflate(R.layout.fragment_speaking, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnimatedScaleDrawable drawable = new AnimatedScaleDrawable(
                ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.vs_micbtn_on));
        drawable.setInterpolator(new BounceInterpolator());
        drawable.setInvertTransformation(true);
        drawable.setDuration(500);
        getView().findViewById(R.id.imv).setBackground(drawable);
        drawable.start();
    }
}
