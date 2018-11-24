package neo.vn.test365home.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
                .inflate(R.layout.item_bxh, parent, false);
        return new TopicViewHoder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHoder holder, int position) {
        BXH obj = mList.get(position);
        if (position == 0) {
            holder.txt_stt.setText("" + (position + 1));
            holder.txt_stt.setTextColor(context.getResources().getColor(R.color.red));
        } else if (position == 1) {
            holder.txt_stt.setText("" + (position + 1));
            holder.txt_stt.setTextColor(context.getResources().getColor(R.color.orange));
        } else if (position == 2) {
            holder.txt_stt.setText("" + (position + 1));
            holder.txt_stt.setTextColor(context.getResources().getColor(R.color.green));
        } else if (position > 2) {
            holder.txt_stt.setText("" + (position + 1));
            holder.txt_stt.setTextColor(context.getResources().getColor(R.color.blue));
        }


        if (obj.getsFULLNAME() != null)
            holder.txt_name.setText("Họ tên: "+obj.getsFULLNAME());
        if (obj.getsLEVEL_NAME() != null)
            holder.txt_class.setText(obj.getsLEVEL_NAME());
        if (obj.getsSCHOOL_NAME() != null)
            holder.txt_school.setText(obj.getsSCHOOL_NAME());
        if (obj.getsSPEED() != null)
            holder.txt_speed_time.setText("Tốc độ làm bài: " + obj.getsSPEED());
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
        @BindView(R.id.txt_speed_time)
        TextView txt_speed_time;
        @BindView(R.id.txt_point)
        TextView txt_point;
        @BindView(R.id.txt_stt)
        TextView txt_stt;


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
