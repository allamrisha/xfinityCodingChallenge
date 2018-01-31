package com.xfinity.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.xfinity.BuildConfig;
import com.xfinity.R;
import com.xfinity.adapters.TopicsListAdapter;
import com.xfinity.model.Icon;
import com.xfinity.model.RelatedTopicsItem;
import com.xfinity.network.AppController;
import com.xfinity.util.ConnectivityReceiver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.xfinity.util.Constants.SIMPSONS_URL;
import static com.xfinity.util.Constants.SIMPSONS_VERSION_NAME;
import static com.xfinity.util.Constants.WIRE_URL;
import static com.xfinity.util.Constants.WIRE_VERSION_NAME;

/**
 * Created by rashmi on 1/30/2018.
 */

public class TopicsListFragment extends Fragment implements ConnectivityReceiver.ConnectivityReceiverListener {
    private static final String TAG = TopicsListFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;

    private TopicsListAdapter mListAdapter;
    private JsonObjectRequest totalResponse;
    private List<RelatedTopicsItem> mRelatedTopicsItemList;
    private View view;

    public TopicsListAdapter getListAdapter(){
        return mListAdapter;
    }
    public RecyclerView getRecyclerView(){
        return mRecyclerView;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRelatedTopicsItemList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_fragment,container,false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(BuildConfig.FLAVOR.equals("simpsons_flavor")) {
            ((MainActivity) getActivity()).setActionBarTitle(SIMPSONS_VERSION_NAME);
                loadData(SIMPSONS_URL);
            //loadSimpsonsData(S);
        }
        else if(BuildConfig.FLAVOR.equals("wire_flavor")) {
            ((MainActivity) getActivity()).setActionBarTitle(WIRE_VERSION_NAME);
            loadData(WIRE_URL);
           // loadWireData();
        }
        if(AppController.getInstance()!= null)
            AppController.getInstance().addToRequestQueue(totalResponse);
    }


    @Override
    public void onResume() {
        super.onResume();
        AppController.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showToast(isConnected);
    }

    private void showToast(boolean isConnected) {
        String message = "";
        if (!isConnected){
            message  =  "Sorry! Not connected to internet";
            Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
        }
    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showToast(isConnected);
    }

    /*private void loadWireData() {
        // Checking if internet connection exists or not
        checkConnection();
        totalResponse = new JsonObjectRequest(WIRE_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    parseJSON(response);
                }
                catch (Exception e) {
                    System.out.print(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"Volley error response");
            }
        });
    }
*/
    private void loadData(String url) {
        // Checking if internet connection exists or not
        checkConnection();
        totalResponse = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Log.d(TAG,response.toString());
                try {
                    parseJSON(response);
                }
                catch (Exception e) {
                    System.out.print(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"Volley error response");
            }
        });
    }

    private void parseJSON(JSONObject response) throws JSONException {
        JSONArray relatedTopics = response.getJSONArray("RelatedTopics");
        for(int i = 0;i<relatedTopics.length();i++){
            JSONObject item = relatedTopics.getJSONObject(i);
            JSONObject icon2 = item.getJSONObject("Icon");
            String url = icon2.getString("URL");
            Icon icon = new Icon(url);
            String text = item.getString("Text");
            RelatedTopicsItem relatedTopicsItem = new RelatedTopicsItem(text,icon);
            mRelatedTopicsItemList.add(relatedTopicsItem);
        }
        mListAdapter = new TopicsListAdapter(getActivity(),mRelatedTopicsItemList);
        Log.d(TAG+" -list",mRelatedTopicsItemList.toString());
        mRecyclerView.setAdapter(mListAdapter);
        mListAdapter.notifyDataSetChanged();
    }

}
