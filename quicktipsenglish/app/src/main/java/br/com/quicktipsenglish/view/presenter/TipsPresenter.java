package br.com.quicktipsenglish.view.presenter;

import java.util.ArrayList;
import java.util.List;

import br.com.quicktipsenglish.cache.TipsCache;
import br.com.quicktipsenglish.model.Tip;
import br.com.quicktipsenglish.model.TipsItem;
import br.com.quicktipsenglish.view.TipsView;

public class TipsPresenter {

    private TipsView view;

    public TipsPresenter(final TipsView view) {
        this.view = view;
    }

    public void retrieveTips(int type) {
        final List<Tip> tips = TipsCache.getTips();
        final List<TipsItem> items = new ArrayList<>();
        for (final Tip tip: tips){
            if (tip.getType() == type){
                items.addAll(tip.getItems());
                break;
            }
        }
        view.showTips(items);
        view.setUpTextToSpeech();
    }
}
