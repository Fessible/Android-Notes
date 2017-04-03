package example.com.materialdesigndemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by rhm on 2017/4/1.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    List<Fruit> list;
    Context mContext;

    public FruitAdapter(List<Fruit> list) {
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        final    ViewHolder holder = new ViewHolder(view);
         holder.cardView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 int position = holder.getAdapterPosition();
                 Fruit fruit = list.get(position);
                 Intent intent = new Intent(mContext, FruitActivity.class);
                 intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getFruitName());
                 intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageId());
                 mContext.startActivity(intent);
             }
         });
        return holder;
    }

    //绑定
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Fruit fruit = list.get(position);//获得fruit
        holder.fruitName.setText(fruit.getFruitName());
        //由于图片很大，所以使用了glide来接收图片
        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

        //初始化操作
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            fruitImage = (ImageView) itemView.findViewById(R.id.fruit_image);
            fruitName = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }
}