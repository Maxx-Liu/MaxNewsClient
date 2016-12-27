package com.max.news.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.max.news.R;
import com.max.news.pojo.ChannelInfoBean;
import com.max.news.ui.adapter.viewholder.ViewHolderDefault;

/**
 * @auther MaxLiu
 * @time 2016/12/15
 */

public class HomeTabRecyclerAdapter extends RecyclerView.Adapter<ViewHolderDefault> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ChannelInfoBean.Pagebean mPagebean;

    public HomeTabRecyclerAdapter(Context context,ChannelInfoBean.Pagebean mPagebean){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.mPagebean = mPagebean;
    }

    @Override
    public ViewHolderDefault onCreateViewHolder(ViewGroup parent, int viewType) {
       return new ViewHolderDefault(mLayoutInflater
               .inflate(R.layout.viewholder_default_home,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolderDefault holder, int position) {
        holder.bindView(mContext,mPagebean.getContentlist().get(position));
    }

    @Override
    public int getItemCount() {
        return mPagebean.getMaxResult();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}