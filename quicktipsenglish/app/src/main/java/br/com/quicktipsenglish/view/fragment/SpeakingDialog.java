package br.com.quicktipsenglish.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import br.com.quicktipsenglish.R;
import br.com.quicktipsenglish.view.component.AnimatedScaleDrawable;

/**
 * Created by elizeu on 08/10/15.
 */
public class SpeakingDialog extends DialogFragment implements View.OnClickListener {

    {
        setStyle(STYLE_NO_TITLE, 0);
    }

    public interface SpeakingListener {
        void onRepeat();
    }

    private SpeakingListener listener;
    private AnimatedScaleDrawable drawable;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle bundle) {
        return inflater.inflate(R.layout.fragment_speaking, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // prepareAnimation();
        bindView();
        //starAnimation();
    }

    private void bindView() {
        imageView = (ImageView) getView().findViewById(R.id.imv);
        imageView.setBackground(ContextCompat
                .getDrawable(getActivity().getApplicationContext(), R.drawable.play_sound));
        imageView.setOnClickListener(this);
    }

    private void prepareAnimation() {
        drawable = new AnimatedScaleDrawable(
                ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.play_sound));
        drawable.setInterpolator(new BounceInterpolator());
        drawable.setInvertTransformation(true);
        drawable.setDuration(500);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imv) {
            if (listener != null) {
                listener.onRepeat();
            }
        }
    }

    public void starAnimation() {
        if (drawable != null) {
            imageView.setBackground(drawable);
            drawable.start();
        }
    }

    public void stopAnimation() {
        if (drawable != null) {
            drawable.stop();
            imageView.setBackground(ContextCompat
                    .getDrawable(getActivity().getApplicationContext(), R.drawable.play_sound));
        }
    }

    public void setListener(final SpeakingListener listener) {
        this.listener = listener;
    }
}
