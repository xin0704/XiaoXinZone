package com.xin.xiaoxinzone.UI.Main.Joke.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xin.xiaoxinzone.Base.BaseRecycleViewAdapter;
import com.xin.xiaoxinzone.Entity.Joke;
import com.xin.xiaoxinzone.R;
import com.xin.xiaoxinzone.Widget.XinCustomImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/15.
 */

public class JokeAdapter extends BaseRecycleViewAdapter<JokeAdapter.JokeViewHolder> {

    private List<Joke.Data> mData = new ArrayList<>();

    private Context context;

    public JokeAdapter(Context context, List<Joke.Data> mData) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public JokeViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_joke, parent, false);
        return new JokeViewHolder(view);
    }

    @Override
    public void onBindHolder(JokeViewHolder holder, final int position) {

        Joke.Data data = mData.get(position);

        if (data.isImg()) {
            //隐藏文本内容容器
            holder.tv_text_joke_content.setVisibility(View.GONE);
            //显示趣图容器
            holder.layout_pic_joke.setVisibility(View.VISIBLE);

            if (TextUtils.isEmpty(data.getContent())) {
                holder.tv_pic_joke_content.setVisibility(View.GONE);
            } else {
                holder.tv_pic_joke_content.setVisibility(View.VISIBLE);
                holder.tv_pic_joke_content.setText(data.getContent());
            }


            if (data.getUrl().endsWith(".gif")){
                //加载趣图
                Glide.with(context)
                        .load(data.getUrl())
                        .asGif()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.icon_placeholder)
                        .into(holder.img_pic_joke);
            }else {
                //加载趣图
                Glide.with(context)
                        .load(data.getUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.icon_placeholder)
                        .into(holder.img_pic_joke);
            }

        } else {
            //显示文本内容容器
            holder.tv_text_joke_content.setVisibility(View.VISIBLE);
            //隐藏趣图容器
            holder.layout_pic_joke.setVisibility(View.GONE);

            if (TextUtils.isEmpty(data.getContent())) {
                holder.tv_text_joke_content.setText("这是一个笑话，哈哈哈哈哈，好不好笑");
            } else {
                holder.tv_text_joke_content.setText(data.getContent());
            }

        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener == null) {
                    return;
                }
                listener.onItemClick(position);
            }
        });


    }

    @Override
    public int getAdapterItemCount() {
        return mData.size();
    }


    public class JokeViewHolder extends RecyclerView.ViewHolder {

        CardView layout;//布局容器

        TextView tv_text_joke_content;//文本笑话的内容

        XinCustomImageView img_pic_joke;//趣图的图片

        TextView tv_pic_joke_content;//趣图的标题

        LinearLayout layout_pic_joke;//趣图的容器


        public JokeViewHolder(View itemView) {
            super(itemView);

            layout = (CardView) itemView.findViewById(R.id.item_joke_layout);
            tv_text_joke_content = (TextView) itemView.findViewById(R.id.item_text_joke_content);
            img_pic_joke = (XinCustomImageView) itemView.findViewById(R.id.item_pic_joke_img);
            tv_pic_joke_content = (TextView) itemView.findViewById(R.id.item_pic_joke_content);
            layout_pic_joke = (LinearLayout) itemView.findViewById(R.id.item_pic_joke_layout);

        }
    }

}
