package neo.vn.test365home.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.R;


/**
 * Created by QQ on 7/7/2017.
 */

public class AdapterListChildren extends RecyclerView.Adapter<AdapterListChildren.TopicViewHoder> {
    private List<Childrens> listChildren;
    private Context context;
    private ItemClickListener OnIListener;


    public ItemClickListener getOnIListener() {
        return OnIListener;
    }

    public void setOnIListener(ItemClickListener onIListener) {
        OnIListener = onIListener;
    }

    public AdapterListChildren(List<Childrens> listAirport, Context context) {
        this.listChildren = listAirport;
        this.context = context;
    }

    @Override
    public TopicViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sub_user, parent, false);
        return new TopicViewHoder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHoder holder, int position) {
        Childrens airport = listChildren.get(position);
        holder.txt_name.setText(airport.getsUSERNAME());
        if (airport.isChecked()) {
            holder.img_backgrond.setVisibility(View.GONE);
        } else {
            holder.img_backgrond.setVisibility(View.VISIBLE);
        }
        if (position==listChildren.size()-1){
            Glide.with(context).load(R.drawable.add_avata).into(holder.img_backgrond);
            holder.txt_name.setVisibility(View.GONE);
            holder.img_avata.setVisibility(View.GONE);
        }else {
            holder.txt_name.setVisibility(View.VISIBLE);
            holder.img_avata.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return listChildren.size();
    }

    public class TopicViewHoder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.txt_name_item_subuser)
        TextView txt_name;
        @BindView(R.id.img_backgrond)
        CircleImageView img_backgrond;
        @BindView(R.id.img_avata)
        CircleImageView img_avata;

        public TopicViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            OnIListener.onClickItem(getLayoutPosition(), listChildren.get(getLayoutPosition()));
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    public void updateList(List<Childrens> list) {
        listChildren = list;
        notifyDataSetChanged();
    }
}
