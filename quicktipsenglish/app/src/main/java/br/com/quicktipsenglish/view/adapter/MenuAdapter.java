package br.com.quicktipsenglish.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

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
                    .inflate(android.R.layout.simple_list_item_activated_1, parent, false);
            TextView title = (TextView) convertView.findViewById(android.R.id.text1);
            holder = new Holder(title);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.title.setText(menus.get(position).getDescription());
        return convertView;
    }

    static class Holder {
        public TextView title;

        public Holder(final TextView title) {
            this.title = title;
        }
    }
}
