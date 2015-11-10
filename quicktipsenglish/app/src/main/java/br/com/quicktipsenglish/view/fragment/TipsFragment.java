package br.com.quicktipsenglish.view.fragment;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import br.com.quicktipsenglish.R;
import br.com.quicktipsenglish.model.TipItem;
import br.com.quicktipsenglish.view.TipsView;
import br.com.quicktipsenglish.view.adapter.TipsAdapter;
import br.com.quicktipsenglish.view.presenter.TipsPresenter;

/**
 * Created by elizeu on 09/09/15.
 */
public class TipsFragment extends Fragment implements TipsView, TipsAdapter.OnClickListener,
        TextToSpeech.OnInitListener, SpeakingDialog.SpeakingListener {

    private TipsPresenter presenter;
    private SpeakingDialog speakingDialog;
    private RecyclerView rcvTips;
    private TipsAdapter tipsAdapter;
    private TextToSpeech textToSpeech;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle bundle) {
        return inflater.inflate(R.layout.fragment_tips, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new TipsPresenter(this);
        if (getArguments() != null) {
            presenter.onViewCreated(getArguments().getInt("TYPE_TIP"));
        }
    }

    @Override
    public void bindView() {
        final Fragment fragment = getFragmentManager()
                .findFragmentByTag(SpeakingDialog.class.getName());
        if (fragment != null) {
            this.speakingDialog = (SpeakingDialog) fragment;
        } else {
            speakingDialog = new SpeakingDialog();
        }
        speakingDialog.setListener(this);
    }

    @Override
    public void showTips(final List<TipItem> tips) {
        final LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        tipsAdapter = new TipsAdapter(tips, this);
        rcvTips = (RecyclerView) getView().findViewById(R.id.rcv_tips);
        rcvTips.setLayoutManager(layoutManager);
        rcvTips.setAdapter(tipsAdapter);
    }

    @Override
    public void setUpTextToSpeech() {
        if (getActivity() != null) {
            textToSpeech = new TextToSpeech(getActivity(), this);
        }
    }

    @Override
    public void speakUs(final TipItem tip) {
        presenter.prepareToSpeak(tip.getDescriptionUs(), Locale.US, "us");
    }

    @Override
    public void speakBr(final TipItem tip) {
        presenter.prepareToSpeak(tip.getDescriptionBr(), new Locale("pt"), "br");
    }

    @Override
    public void speakNow(final String description, final Locale locale, final HashMap<String, String> params) {
        textToSpeech.setLanguage(locale);
        textToSpeech.speak(description, TextToSpeech.QUEUE_FLUSH, params);
    }

    @Override
    public void openSpeakingDialog() {
        speakingDialog.setCancelable(false);
        if (!speakingDialog.isAdded()) {
            speakingDialog.show(getChildFragmentManager(), SpeakingDialog.class.getName());
        } else {
            speakingDialog.starAnimation();
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String utteranceId) {
                    Log.d("TextToSpeech", utteranceId);
                }

                @Override
                public void onDone(String utteranceId) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            speakingDialog.setCancelable(true);
                            speakingDialog.stopAnimation();
                        }
                    });
                    Log.d("TextToSpeech", utteranceId);
                }

                @Override
                public void onError(String utteranceId) {
                    onDone(utteranceId);
                    Log.d("TextToSpeech", utteranceId);
                }
            });
        }
    }

    @Override
    public void onRepeat() {
        presenter.onRepeat();
    }

}
