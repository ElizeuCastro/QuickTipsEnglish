package br.com.quicktipsenglish.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.quicktipsenglish.R;
import br.com.quicktipsenglish.model.Tip;

public class NavigationFragment extends Fragment {

    private NavigationCallback callback;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listView = (ListView) inflater.inflate(
                R.layout.fragment_navigation, container, false);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
        listView.setAdapter(new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                new String[]{"teste"}));

        return listView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        callback = (NavigationCallback) getActivity();
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        view = getActivity().findViewById(fragmentId);
        this.drawerLayout = drawerLayout;
    }

    private void selectItem(int position) {
        if (listView != null) {
            listView.setItemChecked(position, true);
        }
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(view);
        }
        if (callback != null) {
            callback.onNavigationDrawerItemSelected(position);
        }
    }

    public void navigationIconClicked() {
        drawerLayout.openDrawer(view);
    }

    public interface NavigationCallback {
        void onNavigationDrawerItemSelected(int position);
    }
}
