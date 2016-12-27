package com.max.news.ui.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.max.news.R;
import com.max.news.pojo.ChannelInfoBean;
import com.max.news.utils.GliderUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther MaxLiu
 * @time 2016/12/17
 */

public class ViewHolderDefault extends RecyclerView.ViewHolder {
    @BindView(R.id.item_img)
    ImageView mImageView;
    @BindView(R.id.item_title)
    TextView mTitle;
    @BindView(R.id.item_content)
    TextView mContent;
    public ViewHolderDefault(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bindView(Context context,
                         ChannelInfoBean.Pagebean.ContentlistBean mContentlistBean){
        mTitle.setText(mContentlistBean.getTitle());
        mContent.setText(mContentlistBean.getDesc());
        if(mContentlistBean.getImageurls().size() != 0)
        GliderUtil.loadHttpImage(context,mContentlistBean.getImageurls().get(0).toString(),mImageView);
    }
}
