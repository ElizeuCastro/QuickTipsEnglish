package br.com.quicktipsenglish.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.quicktipsenglish.R;
import br.com.quicktipsenglish.model.Menu;

public class MenuAdapter extends BaseAdapter {

    private List<Menu> menus;

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
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.menu_item, parent, false);
            final TextView titleBr = (TextView) convertView.findViewById(R.id.titleBr);
            final TextView titleUs = (TextView) convertView.findViewById(R.id.titleUs);
            holder = new Holder(titleBr, titleUs);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.titleBr.setText(menus.get(position).getDescriptionBr());
        holder.titleUs.setText(menus.get(position).getDescriptionUs());
        return convertView;
    }

    static class Holder {

        TextView titleBr;
        TextView titleUs;

        public Holder(final TextView titleBr, final TextView titleUs) {
            this.titleBr = titleBr;
            this.titleUs = titleUs;
        }
    }
}
