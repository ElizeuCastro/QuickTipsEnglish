package br.com.quicktipsenglish.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.quicktipsenglish.R;
import br.com.quicktipsenglish.model.TipItem;
import br.com.quicktipsenglish.view.fragment.TipsFragment;

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
        void speakUs(TipItem tip);

        /**
         * Notify speak br option
         */
        void speakBr(TipItem tip);
    }

    private List<TipItem> tips;
    private OnClickListener listener;
    private boolean isSpeaking;

    public TipsAdapter(final List<TipItem> tips, final TipsFragment listener) {
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
        final TipItem tip = tips.get(position);
        holder.txvDescriptionUs.setText(tip.getDescriptionUs());
        holder.txvDescriptionBr.setText(tip.getDescriptionBr());
        defineListenerSpeak(holder, tip);
    }

    private void defineListenerSpeak(ViewHolder holder, final TipItem tip) {
        holder.imvSpeakUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.speakUs(tip);
                    notifyDataSetChanged();
                }
            }
        });
        holder.imvSpeakBr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.speakBr(tip);
                    notifyDataSetChanged();
                }
            }
        });
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txvDescriptionUs;
        private ImageView imvSpeakUs;
        private TextView txvDescriptionBr;
        private ImageView imvSpeakBr;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txvDescriptionUs = (TextView) itemView.findViewById(R.id.txv_description_us);
            this.imvSpeakUs = (ImageView) itemView.findViewById(R.id.imv_speak_us);
            this.txvDescriptionBr = (TextView) itemView.findViewById(R.id.txv_description_br);
            this.imvSpeakBr = (ImageView) itemView.findViewById(R.id.imv_speak_br);
        }

    }


}
