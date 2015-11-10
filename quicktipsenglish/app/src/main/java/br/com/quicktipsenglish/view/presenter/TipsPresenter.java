package br.com.quicktipsenglish.view.presenter;

import android.speech.tts.TextToSpeech;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import br.com.quicktipsenglish.cache.TipsCache;
import br.com.quicktipsenglish.model.Tip;
import br.com.quicktipsenglish.model.TipItem;
import br.com.quicktipsenglish.view.TipsView;

public class TipsPresenter {

    private TipsView view;
    private String description;
    private Locale locale;
    private String utteranceId;

    public TipsPresenter(final TipsView view) {
        this.view = view;
    }

    public void onViewCreated(int type) {
        final List<Tip> tips = TipsCache.getTips();
        final List<TipItem> items = new ArrayList<>();
        for (final Tip tip : tips) {
            if (tip.getType() == type) {
                items.addAll(tip.getItems());
                break;
            }
        }
        view.bindView();
        view.showTips(items);
        view.setUpTextToSpeech();
    }

    public void prepareToSpeak(final String description, final Locale locale, final String utteranceId) {
        this.description = description;
        this.locale = locale;
        this.utteranceId = utteranceId;
        final HashMap<String, String> params = new HashMap<>();
        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, utteranceId);
        view.speakNow(this.description, this.locale, params);
        view.openSpeakingDialog();
    }

    public void onRepeat() {
        prepareToSpeak(this.description, this.locale, this.utteranceId);
    }

}
