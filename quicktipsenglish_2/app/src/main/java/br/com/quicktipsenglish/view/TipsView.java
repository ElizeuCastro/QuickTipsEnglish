package br.com.quicktipsenglish.view;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import br.com.quicktipsenglish.model.TipsItem;

public interface TipsView {

    void showTips(List<TipsItem> tips);

    void setUpTextToSpeech();

    void openSpeakingDialog();

    void speakNow(String description, Locale locale, HashMap<String, String> params);

    void bindView();

}