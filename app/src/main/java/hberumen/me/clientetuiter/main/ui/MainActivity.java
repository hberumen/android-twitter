package hberumen.me.clientetuiter.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.twitter.sdk.android.Twitter;

import butterknife.BindView;
import butterknife.ButterKnife;
import hberumen.me.clientetuiter.LoginActivity;
import hberumen.me.clientetuiter.R;
import hberumen.me.clientetuiter.hashtags.ui.HashTagFragment;
import hberumen.me.clientetuiter.images.ui.ImagesFragment;
import hberumen.me.clientetuiter.main.ui.adapters.MainSectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.container)
    ViewPager container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setUpAdapter();

        setTitle(String.format(getString(R.string.hello_user), "@"+Twitter.getSessionManager().getActiveSession().getUserName()));
    }

    private void setUpAdapter() {
        Fragment[] fragments = new Fragment[]{new ImagesFragment(), new HashTagFragment()};
        String[] titles = new String[]{getString(R.string.main_header_images),
                                        getString(R.string.main_header_hashtags)};
        MainSectionsPagerAdapter adapter = new MainSectionsPagerAdapter(getSupportFragmentManager(),
                                            titles, fragments);

        container.setAdapter(adapter);
        tabs.setupWithViewPager(container);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        Twitter.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
