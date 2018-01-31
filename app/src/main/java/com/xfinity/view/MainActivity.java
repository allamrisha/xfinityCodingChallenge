package com.xfinity.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.xfinity.R;
import com.xfinity.adapters.TopicsListAdapter;

import static com.xfinity.util.Constants.DETAILS_FRAGMENT;
import static com.xfinity.util.Constants.LIST_FRAGMENT;

public class MainActivity extends AppCompatActivity  implements TopicsListAdapter.TopicsClickListener{

    private static final String TAG=MainActivity.class.getSimpleName();
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.container)!=null){
            mFragmentManager=getSupportFragmentManager();
            mFragmentManager.beginTransaction()
                    .replace(R.id.container,new TopicsListFragment(),LIST_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch (id){
            case R.id.toggle_view:
                TopicsListFragment topicsListFragment=((TopicsListFragment)mFragmentManager.findFragmentByTag(LIST_FRAGMENT));
                //boolean isSwitched= mTopicsListAdapter.toggleItemViewType();
                boolean isSwitched= topicsListFragment.getListAdapter().toggleItemViewType();
                //mRecyclerView.setLayoutManager(isSwitched?new LinearLayoutManager(this):new GridLayoutManager(this,2));
                topicsListFragment.getRecyclerView().setLayoutManager(isSwitched?new LinearLayoutManager(this):new GridLayoutManager(this,2));
                //mRecyclerView.setLayoutManager(isSwitched?new LinearLayoutManager(this):new GridLayoutManager(this,2));
                topicsListFragment.getListAdapter().notifyDataSetChanged();
                if (item.isChecked()) {
                    item.setIcon(getResources().getDrawable(R.drawable.ic_off_toggle));
                    item.setChecked(false);
                }else{
                    item.setIcon(getResources().getDrawable(R.drawable.ic_on_toggle));
                    item.setChecked(true);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTopicItemListener(String title, String description, String image) {

        DetailsFragment detailsFragment=(DetailsFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_detail);
        if(detailsFragment!=null){
            detailsFragment.updateDetails(title,description,image);
        }else {
            DetailsFragment dynamicDetailsFragment=new DetailsFragment();
            Bundle bundle=new Bundle();
            bundle.putString("title",title);
            bundle.putString("description",description);
            bundle.putString("image_url",image);
            dynamicDetailsFragment.setArguments(bundle);
            Log.i(TAG,bundle.toString());
            mFragmentManager.beginTransaction()
                    .replace(R.id.container,dynamicDetailsFragment,DETAILS_FRAGMENT)
                    .addToBackStack(DETAILS_FRAGMENT)
                    .commit();
        }
    }

    /**
     * Sets the title on the action bar to the custom title.
     * This method is called in the details fragments to update the title.
     * @param title is the title to be displayed on the action bar
     */
    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
}
