package com.max.news.MVP.home.channelist.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.max.news.R;
import com.max.news.MVP.home.channelist.pojo.ChannelInfoBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther MaxLiu
 * @time 2016/12/17
 */

public class ViewHolderNoImg extends RecyclerView.ViewHolder {
    @BindView(R.id.news_title)
    TextView mTitle;
    @BindView(R.id.news_content)
    TextView mContent;
    public ViewHolderNoImg(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bindView(Context context,
                         ChannelInfoBean.Pagebean.ContentlistBean mContentlistBean){
        mTitle.setText(mContentlistBean.getTitle());
        mContent.setText(mContentlistBean.getDesc());
    }
}
