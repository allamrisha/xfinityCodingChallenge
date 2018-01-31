package com.xfinity.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private TopicsListAdapter mTopicsListAdapter;
    private RecyclerView mRecyclerView;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView=findViewById(R.id.recycler_view);
        if(findViewById(R.id.container)!=null){
            Log.v(TAG,"testing Fragment");
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.toggle_view:
                TopicsListFragment topicsListFragment =((TopicsListFragment)mFragmentManager.findFragmentByTag(LIST_FRAGMENT));
                //boolean isSwitched= mTopicsListAdapter.toggleItemViewType();
                boolean isSwitched= topicsListFragment.getListAdapter().toggleItemViewType();
                //mRecyclerView.setLayoutManager(isSwitched?new LinearLayoutManager(this):new GridLayoutManager(this,2));
                topicsListFragment.getRecyclerView().setLayoutManager(isSwitched?new LinearLayoutManager(this):new GridLayoutManager(this,2));
                //mRecyclerView.setLayoutManager(isSwitched?new LinearLayoutManager(this):new GridLayoutManager(this,2));
                topicsListFragment.getListAdapter().notifyDataSetChanged();
                //mTopicsListAdapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTopicItemListener(String title, String description, String image) {

        DetailsFragment detailsFragment=new DetailsFragment();
        Bundle bundle=new Bundle();
        bundle.putString("title",title);
        bundle.putString("description",description);
        bundle.putString("image_url",image);
        detailsFragment.setArguments(bundle);
        Log.i(TAG,bundle.toString());
        mFragmentManager.beginTransaction()
                .replace(R.id.container,detailsFragment,DETAILS_FRAGMENT)
                .addToBackStack(DETAILS_FRAGMENT)
                .commit();

    }
}
