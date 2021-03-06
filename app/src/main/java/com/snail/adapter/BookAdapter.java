package com.snail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.snail.R;
import com.snail.bean.Book;
import com.snail.mvp.news.listener.OnItemClickListener;
import com.snail.mvp.news.listener.OnItemLongClickListener;
import com.snail.utils.ImageLoader;

import java.util.ArrayList;

/**
 * Created by chenzhiwei on 16/10/9.
 */
public class BookAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Book> list;
    private MyViewHolder myViewHolder;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public Book getBook(int position) {
        return list.get(position);
    }

    public BookAdapter(Context context, ArrayList<Book> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R
                .layout.item_books, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            Book book = list.get(position);
            ((MyViewHolder) holder).tv_book_title.setText(book.getTitle());
            ((MyViewHolder) holder).tv_book_info.setText(book.getInfo());
            ImageLoader.getInstance().displayImage(context,book.getCover(),((MyViewHolder) holder).cover);

            final int pos = holder.getLayoutPosition();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView,pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickListener.onItemLongClick(holder.itemView,pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_book_title;
        TextView tv_book_info;
        ImageView cover;

        MyViewHolder(View itemView) {
            super(itemView);
            tv_book_title = (TextView) itemView.findViewById(R.id.text_book_title);
            tv_book_info = (TextView) itemView.findViewById(R.id.text_book_info);
            cover = (ImageView) itemView.findViewById(R.id.img_book_cover);
        }
    }
}
