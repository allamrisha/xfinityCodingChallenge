package com.xfinity.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xfinity.R;

/**
 * Created by rashmi on 1/31/2018.
 */

public class DetailsFragment extends Fragment {

    private TextView titleView;
    private TextView descriptionView;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.detail_fragment,container,false);
        titleView=view.findViewById(R.id.details_title);
        descriptionView=view.findViewById(R.id.details_description);
        imageView=view.findViewById(R.id.details_image);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadDetailsInFragment();
    }

    private void loadDetailsInFragment() {
        Bundle args=getArguments();
        if(args==null){
            titleView.setText("TEST TITLE");
            descriptionView.setText("TEST DESC");
        }
        else {
            titleView.setText(args.get("title").toString());
            descriptionView.setText(args.get("description").toString());
            String url_image=args.get("image_url").toString();
            if(url_image!=null || !url_image.isEmpty()){
                Picasso.with(getContext())
                        .load(url_image)
                        .into(imageView);
            }

        }
    }
}
