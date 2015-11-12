package br.com.quicktipsenglish.view.adapter;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.quicktipsenglish.R;
import br.com.quicktipsenglish.model.Menu;

public class MenuAdapter extends BaseAdapter {

    private List<Menu> menus;
    private int itemSelected = -1;

    public MenuAdapter(final List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Object getItem(final int position) {
        return menus.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.menu_item, parent, false);
            final TextView titleBr = (TextView) convertView.findViewById(R.id.titleBr);
            final TextView titleUs = (TextView) convertView.findViewById(R.id.titleUs);
            final LinearLayout container = (LinearLayout) convertView.findViewById(R.id.container);
            holder = new Holder(titleBr, titleUs, container);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.titleBr.setText(menus.get(position).getDescriptionBr());
        holder.titleUs.setText(menus.get(position).getDescriptionUs());
        if (isItemSelected(position)) {
            holder.titleBr.setTextColor(ContextCompat.getColor(parent.getContext(), android.R.color.white));
            holder.titleUs.setTextColor(ContextCompat.getColor(parent.getContext(), android.R.color.white));
        } elcdse {
            holder.titleBr.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.menu_item_color));
            holder.titleUs.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.menu_item_color));
        }
        return convertView;
    }

    private boolean isItemSelected(int position) {
        return position == itemSelected && itemSelected > -1;
    }

    public void changeTitleColorItemSelected(final int position) {
        itemSelected = position;
        notifyDataSetChanged();
    }

    public static class Holder {

        public LinearLayout container;
        public TextView titleBr;
        public TextView titleUs;

        public Holder(final TextView titleBr, final TextView titleUs, final LinearLayout container) {
            this.titleBr = titleBr;
            this.titleUs = titleUs;
            this.container = container;
        }
    }
}
