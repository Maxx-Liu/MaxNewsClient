package com.max.news.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.max.news.R;

import butterknife.BindView;

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
    }

    public void bindView(String title,String content,String img){
        mTitle.setText(title);
        mContent.setText(content);
    }
}
