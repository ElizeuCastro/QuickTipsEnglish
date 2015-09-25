package br.com.quicktipsenglish.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import br.com.quicktipsenglish.R;
import br.com.quicktipsenglish.cache.TipsCache;
import br.com.quicktipsenglish.view.fragment.AboutFragment;
import br.com.quicktipsenglish.view.fragment.NavigationFragment;
import br.com.quicktipsenglish.view.fragment.TipsFragment;
import br.com.quicktipsenglish.view.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity
        implements NavigationFragment.NavigationCallback, MainPresenter.Callback {

    private NavigationFragment drawerFragment;
    private MainPresenter presenter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupActionBar();
        openAbout();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Wait, loading tips...");
        dialog.show();
        presenter = new MainPresenter();
        presenter.load(this, this);
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Quick tips english");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerFragment.navigationIconClicked();
            }
        });
    }

    private void setupDrawer() {
        drawerFragment = (NavigationFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        drawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        openFragment(position);
    }

    private void openAbout() {
        Fragment fragment = Fragment.instantiate(this, AboutFragment.class.getName(), null);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
        ;
    }

    private void openFragment(final int position) {
        final Bundle bundle = new Bundle();
        bundle.putInt("TIP", position);
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
}
