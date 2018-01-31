package com.xfinity.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xfinity.R;
import com.xfinity.model.Topic;

import static com.xfinity.util.Constants.DEFAULT_DESC;
import static com.xfinity.util.Constants.DEFAULT_TITLE;



public class DetailsFragment extends Fragment {

    private TextView titleView;
    private TextView descriptionView;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment,container,false);
        titleView = view.findViewById(R.id.details_title);
        descriptionView = view.findViewById(R.id.details_description);
        imageView = view.findViewById(R.id.details_image);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadDetailsInFragment();
    }

    private void loadDetailsInFragment() {
        Bundle args = getArguments();
        if(args == null){
            titleView.setText(DEFAULT_TITLE);
            descriptionView.setText(DEFAULT_DESC);
        }
        else {
            //set title for ActionBar
            ((MainActivity) getActivity()).setActionBarTitle(args.get(Topic.TITLE).toString());

            String url_image = args.get(Topic.IMAGE_URL).toString();

            titleView.setText(args.get(Topic.TITLE).toString());
            descriptionView.setText(args.get(Topic.DESCRIPTION).toString());
            if(url_image!= null && !url_image.isEmpty() ){
                Picasso.with(getContext())
                        .load(url_image)
                        .into(imageView);
            }else{
                Log.e("Detail","Display Holder Image");
                Picasso.with(getContext())
                        .load(R.mipmap.ic_launcher)
                        .into(imageView);
            }
        }
    }
    public void updateDetails(String mtitle, String mdescription, String image) {
        this.titleView.setText(mtitle);
        descriptionView.setText(mdescription);
        if (!image.isEmpty() && image !=  null) {
            Picasso.with(getContext())
                    .load(image)
                    .into(imageView);
        }else{
            Log.e("Detail","Display Holder Image");
            Picasso.with(getContext())
                    .load(R.mipmap.ic_launcher)
                    .into(imageView);
        }
    }
}
