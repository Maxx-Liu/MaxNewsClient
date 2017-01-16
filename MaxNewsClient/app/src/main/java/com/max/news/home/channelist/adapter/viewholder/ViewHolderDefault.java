package com.max.news.home.channelist.adapter.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.max.news.R;
import com.max.news.home.channelist.pojo.ChannelInfoBean;
import com.max.news.utils.img.GlideUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther MaxLiu
 * @time 2016/12/17
 */

public class ViewHolderDefault extends RecyclerView.ViewHolder {
    @BindView(R.id.news_photo)
    ImageView mImageView;
    @BindView(R.id.news_title)
    TextView mTitle;
    @BindView(R.id.news_content)
    TextView mContent;
    public ViewHolderDefault(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bindView(Context context,
                         ChannelInfoBean.Pagebean.ContentlistBean mContentlistBean){
        mTitle.setText(mContentlistBean.getTitle());
        mContent.setText(mContentlistBean.getDesc());
        if(mContentlistBean.getImageurls().size() != 0) {
            LinkedTreeMap list = (LinkedTreeMap) mContentlistBean.getImageurls().get(0);
            GlideUtil.loadImageView(context, list.get("url").toString(), mImageView);
        }else{
            mImageView.setVisibility(View.GONE);
            mTitle.setTextColor(Color.BLACK);
        }
    }
}
