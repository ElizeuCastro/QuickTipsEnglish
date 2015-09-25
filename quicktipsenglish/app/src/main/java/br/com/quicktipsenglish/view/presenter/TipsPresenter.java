package br.com.quicktipsenglish.view.presenter;

import java.util.List;

import br.com.quicktipsenglish.model.Tip;
import br.com.quicktipsenglish.stub.TaxiTips;
import br.com.quicktipsenglish.view.TipsView;

/**
 * Created by elizeu on 09/09/15.
 */
public class TipsPresenter {

    private TipsView view;

    public TipsPresenter(final TipsView view) {
        this.view = view;
    }

    public void retrieveTips(int type) {
        List<Tip> tips = null;
//        switch (type) {
//            case Tip.Type.TAXI:
//                tips = new TaxiTips().create().list();
//                break;
//            default:
//                break;
//        }
        view.showTips(tips);
    }


}
