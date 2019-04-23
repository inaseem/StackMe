package ali.naseem.stackme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import ali.naseem.stackme.adapters.DrawerTagsAdapter;
import ali.naseem.stackme.adapters.ItemViewModel;
import ali.naseem.stackme.adapters.PagedQuestionAdapter;
import ali.naseem.stackme.datamodels.questions.Item;
import ali.naseem.stackme.models.Interest;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class QuestionListActivity extends AppCompatActivity {

    /**
     * The {@link androidx.viewpager.widget.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * androidx.fragment.app.FragmentStatePagerAdapter.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerTagsAdapter adapter;
    private ArrayList<Interest> interests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawer = findViewById(R.id.drawer);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        mDrawer.setLayoutManager(layoutManager);
        mDrawer.setItemAnimator(itemAnimator);
        mDrawer.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        interests = new ArrayList<>();
        adapter = new DrawerTagsAdapter(interests, this);
        mDrawer.setAdapter(adapter);
        Utils.getInstance().getDatabase().interestDao().getAll()
                .observe(this, new Observer<List<Interest>>() {
                    @Override
                    public void onChanged(List<Interest> interest) {
                        interests.clear();
                        interests.addAll(interest);
                        adapter.notifyDataSetChanged();
                    }
                });
        //mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout, toolbar,
                R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                // Set the title on the action when drawer open
                getSupportActionBar().setTitle("Title");
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.syncState();
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private PagedQuestionAdapter adapter, hotAdapter, defaultAdapter;
        private RecyclerView recyclerView;

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int id) {
            Bundle bundle = new Bundle();
            bundle.putInt("id", id);

            PlaceholderFragment fragment = new PlaceholderFragment();
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_content, container, false);
            recyclerView = rootView.findViewById(R.id.recyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(itemAnimator);
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
            switch (getArguments().getInt("id")) {
                case 1:
                    ali.naseem.stackme.adapters.hot.ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ali.naseem.stackme.adapters.hot.ItemViewModel.class);
                    adapter = new PagedQuestionAdapter(getContext());
                    itemViewModel.itemPagedList.observe(this, new Observer<PagedList<Item>>() {
                        @Override
                        public void onChanged(@Nullable PagedList<Item> items) {
                            adapter.submitList(items);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                    break;
                case 2:
                    ali.naseem.stackme.adapters.week.ItemViewModel hotItemModel = ViewModelProviders.of(this).get(ali.naseem.stackme.adapters.week.ItemViewModel.class);
                    hotAdapter = new PagedQuestionAdapter(getContext());
                    hotItemModel.itemPagedList.observe(this, new Observer<PagedList<Item>>() {
                        @Override
                        public void onChanged(@Nullable PagedList<Item> items) {
                            hotAdapter.submitList(items);
                        }
                    });
                    recyclerView.setAdapter(hotAdapter);
                    break;
                default:
                    ItemViewModel defaultModel = ViewModelProviders.of(this).get(ItemViewModel.class);
                    defaultAdapter = new PagedQuestionAdapter(getContext());
                    defaultModel.itemPagedList.observe(this, new Observer<PagedList<Item>>() {
                        @Override
                        public void onChanged(@Nullable PagedList<Item> items) {
                            defaultAdapter.submitList(items);
                        }
                    });
                    recyclerView.setAdapter(defaultAdapter);
                    break;
            }

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
