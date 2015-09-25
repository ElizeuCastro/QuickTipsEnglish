package br.com.quicktipsenglish.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.quicktipsenglish.R;
import br.com.quicktipsenglish.model.Tip;

/**
 * Created by elizeu on 09/09/15.
 */
public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {

    /**
     * Define actions to speak options.
     */
    public interface OnClickListener {
        /**
         * Notify speak us option
         */
        void speakUs(Tip tip);

        /**
         * Notify speak br option
         */
        void speakBr(Tip tip);
    }

    private List<Tip> tips;
    private OnClickListener listener;
    private boolean isSpeaking;

    public TipsAdapter(final List<Tip> tips, OnClickListener listener) {
        this.tips = tips;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int position) {
        final View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Tip tip = tips.get(position);
//        holder.txvTitleUs.setText(tip.getTitleUs());
//        holder.txvDescriptionUs.setText(tip.getDescriptionUs());
//        holder.txvTitleBr.setText(tip.getTitleBr());
//        holder.txvDescriptionBr.setText(tip.getDescriptionBr());
//        defineListenerSpeak(holder, tip);
//        defineStatusSpeakUs(holder, tip);
//        defineStatusSpeakBr(holder, tip);
    }

//    public void onchangeTip(Tip tip) {
//        isSpeaking = Boolean.FALSE;
//        tip.setSpeakingNowBr(isSpeaking);
//        tip.setSpeakingNowUs(isSpeaking);
//        int position = tips.indexOf(tip);
//        notifyItemChanged(position);
//    }
//
//    private void defineListenerSpeak(ViewHolder holder, final Tip tip) {
//        holder.imvSpeakUs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (listener != null && !isSpeaking) {
//                    isSpeaking = Boolean.TRUE;
//                    tip.setSpeakingNowUs(isSpeaking);
//                    listener.speakUs(tip);
//                    notifyDataSetChanged();
//                }
//            }
//        });
//        holder.imvSpeakBr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (listener != null && !isSpeaking) {
//                    isSpeaking = Boolean.TRUE;
//                    tip.setSpeakingNowBr(isSpeaking);
//                    listener.speakBr(tip);
//                    notifyDataSetChanged();
//                }
//            }
//        });
//    }
//
//    private void defineStatusSpeakBr(ViewHolder holder, Tip tip) {
//        if (tip.isSpeakingNowBr()) {
//            holder.imvSpeakBr.setSelected(true);
//        } else {
//            holder.imvSpeakBr.setSelected(false);
//        }
//    }
//
//    private void defineStatusSpeakUs(ViewHolder holder, Tip tip) {
//        if (tip.isSpeakingNowUs()) {
//            holder.imvSpeakUs.setSelected(true);
//        } else {
//            holder.imvSpeakUs.setSelected(false);
//        }
//    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txvTitleUs;
        private TextView txvDescriptionUs;
        private ImageView imvSpeakUs;
        private TextView txvTitleBr;
        private TextView txvDescriptionBr;
        private ImageView imvSpeakBr;


        public ViewHolder(View itemView) {
            super(itemView);
            this.txvTitleUs = (TextView) itemView.findViewById(R.id.txv_title_us);
            this.txvDescriptionUs = (TextView) itemView.findViewById(R.id.txv_description_us);
            this.imvSpeakUs = (ImageView) itemView.findViewById(R.id.imv_speak_us);
            this.txvTitleBr = (TextView) itemView.findViewById(R.id.txv_title_br);
            this.txvDescriptionBr = (TextView) itemView.findViewById(R.id.txv_description_br);
            this.imvSpeakBr = (ImageView) itemView.findViewById(R.id.imv_speak_br);
        }

    }


}
