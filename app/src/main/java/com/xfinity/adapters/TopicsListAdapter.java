package com.xfinity.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xfinity.R;
import com.xfinity.model.RelatedTopicsItem;

import java.util.List;

import static com.xfinity.util.Constants.GRID;
import static com.xfinity.util.Constants.LIST;

/**
 * Created by risha on 1/30/2018.
 */
public class TopicsListAdapter extends RecyclerView.Adapter<TopicsListAdapter.CustomViewHolder>{

    private List<RelatedTopicsItem> mRelatedTopicsItemList;
    private Context mContext;
    private boolean isToggleView =  true;
    private String title,description;
    private TopicsClickListener mTopicsClickListener;

    //Custom interface to pass data between the fragments.
    public interface TopicsClickListener{
        void onTopicItemListener(String title,String description,String image);
    }

    public TopicsListAdapter(Context context, List<RelatedTopicsItem> relatedTopicsItemList) {
        mContext = context;
        mRelatedTopicsItemList  =  relatedTopicsItemList;
        mTopicsClickListener = (TopicsClickListener)context;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView titleView;
        public ImageView mImageView;
        public CustomViewHolder(View itemView) {
            super(itemView);
            titleView  = itemView.findViewById(R.id.text_title);
            mImageView = itemView.findViewById(R.id.image_simpsons_list);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RelatedTopicsItem relatedTopicsItem = mRelatedTopicsItemList.get(getAdapterPosition());
                    String[] titleText = relatedTopicsItem.getText().split("-");
                    title = titleText[0];
                    description = titleText[1];
                    mTopicsClickListener.onTopicItemListener(title,description,relatedTopicsItem.getIcon().getURL());
                }
            });
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == LIST){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item,parent,false);
        }
        return new CustomViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if(isToggleView)
            return LIST;
        else
            return GRID;
    }


    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        RelatedTopicsItem relatedTopicsItem = mRelatedTopicsItemList.get(position);
        getTitleAndDescription(relatedTopicsItem);
        holder.titleView.setText(title);
        if(relatedTopicsItem.getIcon().getURL() == null || relatedTopicsItem.getIcon().getURL().isEmpty()){
            holder.mImageView.setImageResource(R.mipmap.ic_launcher);
        }else {
            Picasso.with(mContext)
                    .load(relatedTopicsItem.getIcon().getURL())
                    .into(holder.mImageView);
        }
    }

    public boolean toggleItemViewType () {
        isToggleView =  !isToggleView;
        return isToggleView;
    }

    private void getTitleAndDescription(RelatedTopicsItem relatedTopicsItem) {
        String[] titleText = relatedTopicsItem.getText().split("-");
        title = titleText[0];
        description = titleText[1];
    }
    public void animate(RecyclerView.ViewHolder viewHolder) {
        @SuppressLint("ResourceType")
        final Animation animAnticipateOvershoot  =  AnimationUtils.loadAnimation(mContext, R.transition.bounce);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

    @Override
    public int getItemCount() {
        return mRelatedTopicsItemList.size();
    }

}
