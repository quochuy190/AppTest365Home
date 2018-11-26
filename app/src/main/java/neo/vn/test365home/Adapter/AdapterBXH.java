package neo.vn.test365home.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.BXH;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.StringUtil;


/**
 * Created by QQ on 7/7/2017.
 */

public class AdapterBXH extends RecyclerView.Adapter<AdapterBXH.TopicViewHoder> {
    private List<BXH> mList;
    private Context context;
    private ItemClickListener OnIListener;

    public ItemClickListener getOnIListener() {
        return OnIListener;
    }

    public void setOnIListener(ItemClickListener onIListener) {
        OnIListener = onIListener;
    }

    public AdapterBXH(List<BXH> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public TopicViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bangxephang, parent, false);
        return new TopicViewHoder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHoder holder, int position) {
        BXH obj = mList.get(position);
        if (position == 0) {
            holder.txt_stt.setText("" + (position + 1));
            Glide.with(context).load(R.drawable.icon_bxh_1).into(holder.img_bg_point);
        } else if (position == 1) {
            holder.txt_stt.setText("" + (position + 1));
            Glide.with(context).load(R.drawable.icon_bxh_2).into(holder.img_bg_point);
        } else if (position == 2) {
            holder.txt_stt.setText("" + (position + 1));
            Glide.with(context).load(R.drawable.icon_bxh_3).into(holder.img_bg_point);
        } else if (position == 3) {
            holder.txt_stt.setText("" + (position + 1));
            Glide.with(context).load(R.drawable.icon_bxh_4).into(holder.img_bg_point);
        } else if (position == 4) {
            holder.txt_stt.setText("" + (position + 1));
            Glide.with(context).load(R.drawable.icon_bxh_5).into(holder.img_bg_point);
        } else if (position == 5) {
            holder.txt_stt.setText("" + (position + 1));
            Glide.with(context).load(R.drawable.icon_bxh_6).into(holder.img_bg_point);
        } else if (position == 6) {
            holder.txt_stt.setText("" + (position + 1));
            Glide.with(context).load(R.drawable.icon_bxh_7).into(holder.img_bg_point);
        } else if (position > 6) {
            holder.txt_stt.setText("" + (position + 1));
            Glide.with(context).load(R.drawable.icon_bxh_7).into(holder.img_bg_point);
        }

        if (obj.getsFULLNAME() != null)
            holder.txt_name.setText(obj.getsFULLNAME());
        if (obj.getsLEVEL_NAME() != null)
            holder.txt_class.setText(obj.getsLEVEL_NAME());
        if (obj.getsSCHOOL_NAME() != null)
            holder.txt_school.setText(obj.getsSCHOOL_NAME());
        if (obj.getsSPEED() != null)
            holder.txt_speed_time.setText(obj.getsSPEED());
        if (obj.getsDTB() != null)
            holder.txt_point.setText(StringUtil.format_point(Float.parseFloat(obj.getsDTB())));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class TopicViewHoder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.txt_name)
        TextView txt_name;
        @BindView(R.id.txt_class)
        TextView txt_class;
        @BindView(R.id.txt_school)
        TextView txt_school;
        @BindView(R.id.txt_speed)
        TextView txt_speed_time;
        @BindView(R.id.txt_point)
        TextView txt_point;
        @BindView(R.id.txt_rank)
        TextView txt_stt;
        @BindView(R.id.img_avata)
        ImageView img_avata;
        @BindView(R.id.img_bg_point)
        ImageView img_bg_point;


        public TopicViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            OnIListener.onClickItem(getLayoutPosition(), mList.get(getLayoutPosition()));
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    public void updateList(List<BXH> list) {
        mList = list;
        notifyDataSetChanged();
    }
}
