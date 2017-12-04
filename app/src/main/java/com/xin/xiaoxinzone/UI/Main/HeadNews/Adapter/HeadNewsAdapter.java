package com.xin.xiaoxinzone.UI.Main.HeadNews.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xin.xiaoxinzone.Entity.HeadNews;
import com.xin.xiaoxinzone.R;
import com.xin.xiaoxinzone.Utils.ResourceTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/13.
 */

public class HeadNewsAdapter extends RecyclerView.Adapter<HeadNewsAdapter.HeadNewsHolder> {

    private Context context;

    private List<HeadNews.Data> mData = new ArrayList<>();

    private OnHeadNewsItemListener listener;

    public HeadNewsAdapter(Context context, List<HeadNews.Data> mData) {
        this.context = context;
        this.mData = mData;
    }


    @Override
    public HeadNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_head_news, parent, false);

        return new HeadNewsHolder(view);
    }

    @Override
    public void onBindViewHolder(HeadNewsHolder holder, final int position) {

        HeadNews.Data headNews = mData.get(position);

        if (headNews == null) {
            holder.layout.setVisibility(View.GONE);
        } else {
            holder.layout.setVisibility(View.VISIBLE);

            if (TextUtils.isEmpty(headNews.getTitle())) {
                holder.tv_title.setText("这是新闻标题");
            } else {
                holder.tv_title.setText(headNews.getTitle());
            }

            if (TextUtils.isEmpty(headNews.getAuthor_name())) {
                holder.tv_author.setText("这是新闻作者");
                holder.tv_author.setVisibility(View.GONE);
            } else {
                holder.tv_author.setVisibility(View.VISIBLE);
                holder.tv_author.setText(headNews.getAuthor_name());
            }

            if (TextUtils.isEmpty(headNews.getDate())) {
                holder.tv_date.setText("这是新闻发布时间");
                holder.tv_date.setVisibility(View.GONE);
            } else {
                holder.tv_date.setVisibility(View.VISIBLE);
                holder.tv_date.setText(headNews.getDate());
            }

            Glide.with(context)
                    .load(headNews.getThumbnail_pic_s())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(ResourceTools.getDrawable(context,R.mipmap.loading))
                    .error(ResourceTools.getDrawable(context,R.mipmap.loading))
                    .into(holder.img);

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(position);
                    }
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class HeadNewsHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_author;
        TextView tv_date;
        ImageView img;
        LinearLayout layout;

        public HeadNewsHolder(View itemView) {
            super(itemView);

            tv_title = (TextView) itemView.findViewById(R.id.item_head_news_title);
            tv_author = (TextView) itemView.findViewById(R.id.item_head_news_author);
            tv_date = (TextView) itemView.findViewById(R.id.item_head_news_date);
            img = (ImageView) itemView.findViewById(R.id.item_head_news_img);
            layout = (LinearLayout) itemView.findViewById(R.id.item_head_news_layout);

        }
    }

    public interface OnHeadNewsItemListener {
        void onItemClick(int position);
    }

    public void setOnItemListener(OnHeadNewsItemListener listener) {
        this.listener = listener;
    }

}
