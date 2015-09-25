package br.com.quicktipsenglish.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.quicktipsenglish.R;
import br.com.quicktipsenglish.model.Tip;
import br.com.quicktipsenglish.view.TipsView;
import br.com.quicktipsenglish.view.adapter.TipsAdapter;
import br.com.quicktipsenglish.view.presenter.TipsPresenter;

/**
 * Created by elizeu on 09/09/15.
 */
public class TipsFragment extends Fragment implements TipsView, TipsAdapter.OnClickListener {

    private TipsPresenter presenter;
    private RecyclerView rcvTips;
    private TipsAdapter tipsAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        view = inflater.inflate(R.layout.fragment_tips, container, false);
        presenter = new TipsPresenter(this);
        //@Tip.Type int type = 1;
        presenter.retrieveTips(1);
        return view;
    }

    @Override
    public void showTips(List<Tip> tips) {
        final LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        tipsAdapter = new TipsAdapter(tips, this);
        rcvTips = (RecyclerView) view.findViewById(R.id.rcv_tips);
        rcvTips.setLayoutManager(layoutManager);
        rcvTips.setAdapter(tipsAdapter);
    }

    @Override
    public void speakUs(final Tip tip) {

    }

    @Override
    public void speakBr(final Tip tip) {

    }
}
