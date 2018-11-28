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
import neo.vn.test365home.Models.HistoryBalance;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.StringUtil;
import neo.vn.test365home.Untils.TimeUtils;


/**
 * Created by QQ on 7/7/2017.
 */

public class AdapterHistoryBalance extends RecyclerView.Adapter<AdapterHistoryBalance.TopicViewHoder> {
    private List<HistoryBalance> mList;
    private Context context;
    private ItemClickListener OnIListener;


    public ItemClickListener getOnIListener() {
        return OnIListener;
    }

    public void setOnIListener(ItemClickListener onIListener) {
        OnIListener = onIListener;
    }

    public AdapterHistoryBalance(List<HistoryBalance> listAirport, Context context) {
        this.mList = listAirport;
        this.context = context;
    }

    @Override
    public TopicViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_balance, parent, false);
        return new TopicViewHoder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHoder holder, int position) {
        HistoryBalance obj = mList.get(position);
        if (obj.getsDESCRIPTION() != null) {
            holder.txt_total_descrip_history.setText(obj.getsDESCRIPTION());
        }

        if (obj.getsTYPE() != null) {
            if (obj.getsTYPE().equals("2")) {
                holder.txt_total_type_history.setText("Giao dịch: Trừ tiền");
                if (obj.getsAMOUNT() != null) {
                    holder.txt_total_monney_history.setText(" - " + StringUtil.formatNumber(obj.getsAMOUNT()));
                }
                holder.txt_total_monney_history.setTextColor(context.getResources().getColor(R.color.red));
            } else {
                holder.txt_total_type_history.setText("Giao dịch: Cộng tiền");
                if (obj.getsAMOUNT() != null) {
                    holder.txt_total_monney_history.setText(" + " + StringUtil.formatNumber(obj.getsAMOUNT()));
                }
                holder.txt_total_monney_history.setTextColor(context.getResources().getColor(R.color.blue));
            }
        }
        if (obj.getsTRANSACTION_TIME() != null) {
            holder.txt_date_history.setText("Ngày giao dịch: "+TimeUtils.
                    convent_date(obj.getsTRANSACTION_TIME(), "yyy-MM-dd HH:mm:ss",
                            "dd-MM-yyyy - HH:mm"));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class TopicViewHoder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.txt_date_history)
        TextView txt_date_history;
        @BindView(R.id.txt_total_monney_history)
        TextView txt_total_monney_history;
        @BindView(R.id.txt_total_type_history)
        TextView txt_total_type_history;
        @BindView(R.id.txt_total_descrip_history)
        TextView txt_total_descrip_history;

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

}
