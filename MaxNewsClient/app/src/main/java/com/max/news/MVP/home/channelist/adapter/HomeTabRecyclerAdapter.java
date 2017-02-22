package com.max.news.MVP.home.channelist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.max.news.MVP.home.channelist.bean.ContentlistBean;
import com.max.news.MVP.home.channelist.bean.Pagebean;
import com.max.news.R;
import com.max.news.MVP.home.channelist.adapter.viewholder.ViewHolderDefault;
import com.max.news.MVP.home.channelist.adapter.viewholder.ViewHolderNoImg;

import java.util.ArrayList;
import java.util.List;


/**
 * @auther MaxLiu
 * @time 2016/12/15
 */

public class HomeTabRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public interface ItemOnClickListener{
        void OnClick(ContentlistBean mContentlistBean);
    }
    private ItemOnClickListener itemOnClickListener;

    private static final int TYPE_DEFAULT = 0;
    private static final int TYPE_NO_IMG = 1;

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int mPage = 1;
    private List<ContentlistBean> mContentlist = new ArrayList<>();


    public HomeTabRecyclerAdapter(Context context,ItemOnClickListener itemOnClickListener) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.itemOnClickListener = itemOnClickListener;
    }

    public void addPagebean(Pagebean mPagebean) {
        if (mContentlist.size() == 0) {
            mContentlist = mPagebean.getContentlist();
            mPage = 1;
        } else {
            if(mPagebean.getCurrentPage() > mPage) {
                mPage++;
                for (ContentlistBean mContent :
                        mPagebean.getContentlist()) {
                    mContentlist.add(mContent);
                }
                notifyDataSetChanged();
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_DEFAULT:
                return new ViewHolderDefault(mLayoutInflater
                        .inflate(R.layout.viewholder_default_home, parent, false));
            case TYPE_NO_IMG:
                return new ViewHolderNoImg(mLayoutInflater
                        .inflate(R.layout.viewholder_no_img, parent, false));
        }
        return new ViewHolderDefault(mLayoutInflater
                .inflate(R.layout.viewholder_default_home, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_DEFAULT) {
            ViewHolderDefault mHolder = (ViewHolderDefault) holder;
            mHolder.bindView(mContext,mContentlist.get(position));
        } else {
            ViewHolderNoImg mHolder = (ViewHolderNoImg) holder;
            mHolder.bindView(mContentlist.get(position));
        }

        if (itemOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemOnClickListener.OnClick(mContentlist.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mContentlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (!mContentlist.get(position).isHavePic()) {
            return TYPE_NO_IMG;
        } else {
            return TYPE_DEFAULT;
        }
    }
}