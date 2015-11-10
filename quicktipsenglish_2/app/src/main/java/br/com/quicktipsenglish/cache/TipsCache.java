package br.com.quicktipsenglish.cache;

import java.util.HashMap;
import java.util.List;

import br.com.quicktipsenglish.model.Menu;
import br.com.quicktipsenglish.model.Tips;
import br.com.quicktipsenglish.model.TipsItem;

public final class TipsCache {

    private static HashMap<Integer, List<TipsItem>> tips = new HashMap<>();
    private static List<Menu> menus;

    public static List<TipsItem> getTips(int type) {
        return tips.get(type);
    }

    public static void setTips(List<Tips> tips) {
        for (Tips tip : tips) {
            TipsCache.tips.put(tip.getId(), tip.getItems());
        }
    }

    public static List<Menu> getMenus() {
        return menus;
    }

    public static void setMenus(List<Menu> menus) {
        TipsCache.menus = menus;
    }
}
