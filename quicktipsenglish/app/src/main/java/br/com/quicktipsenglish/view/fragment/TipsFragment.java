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
import br.com.quicktipsenglish.model.TipsItem;
import br.com.quicktipsenglish.view.TipsView;
import br.com.quicktipsenglish.view.adapter.TipsAdapter;
import br.com.quicktipsenglish.view.presenter.TipsPresenter;

/**
 * Created by elizeu on 09/09/15.
 */
public class TipsFragment extends Fragment implements TipsView, TipsAdapter.OnClickListener, TextToSpeech.OnInitListener {

    private TipsPresenter presenter;
    private SpeakingDialog speakingDialog;
    private RecyclerView rcvTips;
    private TipsAdapter tipsAdapter;
    private TextToSpeech textToSpeech;
    private View view;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle bundle) {
        view = inflater.inflate(R.layout.fragment_tips, container, false);
        presenter = new TipsPresenter(this);
        if (getArguments() != null) {
            presenter.retrieveTips(getArguments().getInt("TYPE_TIP"));
        }
        speakingDialog = new SpeakingDialog();
        return view;
    }

    @Override
    public void showTips(final List<TipsItem> tips) {
        final LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        tipsAdapter = new TipsAdapter(tips, this);
        rcvTips = (RecyclerView) view.findViewById(R.id.rcv_tips);
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
    public void speakUs(final TipsItem tip) {
        openSpeakingDialog();
        speakNow(tip.getDescriptionUs(), Locale.US, "us");
    }

    @Override
    public void speakBr(final TipsItem tip) {
        openSpeakingDialog();
        speakNow(tip.getDescriptionBr(), new Locale("pt"), "br");
    }

    private void speakNow(final String description, final Locale locale, final String utteranceId) {
        final HashMap<String, String> params = new HashMap<>();
        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, utteranceId);
        textToSpeech.setLanguage(locale);
        textToSpeech.speak(description, TextToSpeech.QUEUE_FLUSH, params);
    }

    private void openSpeakingDialog() {
        speakingDialog.setCancelable(false);
        speakingDialog.show(getChildFragmentManager(), SpeakingDialog.class.getName());
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
                    speakingDialog.setCancelable(true);
                    Log.d("TextToSpeech", utteranceId);
                }

                @Override
                public void onError(String utteranceId) {
                    speakingDialog.setCancelable(true);
                    Log.d("TextToSpeech", utteranceId);
                }
            });
        }
    }

}
