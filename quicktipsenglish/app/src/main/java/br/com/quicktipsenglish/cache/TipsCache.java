package br.com.quicktipsenglish.cache;

import java.util.List;

import br.com.quicktipsenglish.model.Tip;

/**
 * Created by elizeu on 25/09/15.
 */
public final class TipsCache {

    private static List<Tip> tips;

    public static List<Tip> getTips() {
        return tips;
    }

    public static void setTips(List<Tip> tips) {
        TipsCache.tips = tips;
    }
}
