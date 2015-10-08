package br.com.quicktipsenglish.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import br.com.quicktipsenglish.R;
import br.com.quicktipsenglish.cache.TipsCache;
import br.com.quicktipsenglish.model.Menu;
import br.com.quicktipsenglish.view.adapter.MenuAdapter;
import br.com.quicktipsenglish.view.fragment.AboutFragment;
import br.com.quicktipsenglish.view.fragment.TipsFragment;
import br.com.quicktipsenglish.view.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainPresenter.Callback,
        AdapterView.OnItemClickListener {

    private ListView menus;
    private MainPresenter presenter;
    private ProgressDialog dialog;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        setupActionBar();
        openAbout();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Wait, loading tips...");
        dialog.show();
        presenter = new MainPresenter();
        presenter.load(this, this);
    }

    private void bindView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menus = (ListView) findViewById(R.id.navigation_drawer);
        menus.setOnItemClickListener(this);
    }

    private void setupActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Quick tips english");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                hideOrShowMenu();
            }
        });
    }

    private void hideOrShowMenu() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    private void setupDrawer() {
        menus.setAdapter(new MenuAdapter(TipsCache.getMenus()));
    }

    private void openAbout() {
        Fragment fragment = Fragment.instantiate(this, AboutFragment.class.getName(), null);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
        ;
    }

    private void openFragment(final int type) {
        final Bundle bundle = new Bundle();
        bundle.putInt("TYPE_TIP", type);
        Fragment fragment = Fragment.instantiate(this, TipsFragment.class.getName(), bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onSuccess() {
        dialog.dismiss();
        setupDrawer();
        TipsCache.getTips();
    }

    @Override
    public void onFail() {
        dialog.dismiss();
        Toast.makeText(this, "Tips not found!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
        if (parent != null && parent.getAdapter() != null) {
            final Menu menu = (Menu) parent.getAdapter().getItem(position);
            if (menu != null) {
                hideOrShowMenu();
                changeTitleToolbar(menu.getDescription());
                openFragment(menu.getType());
            }
        }
    }

    private void changeTitleToolbar(final String title) {
        this.toolbar.setTitle(title);
    }
}
