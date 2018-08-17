package neo.vn.test365home.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.DapAn;
import neo.vn.test365home.R;


/**
 * Created by QQ on 7/7/2017.
 */

public class AdapterDapAn extends RecyclerView.Adapter<AdapterDapAn.TopicViewHoder> {
    private List<DapAn> listChildren;
    private Context context;
    private ItemClickListener OnIListener;

    public ItemClickListener getOnIListener() {
        return OnIListener;
    }

    public void setOnIListener(ItemClickListener onIListener) {
        OnIListener = onIListener;
    }

    public AdapterDapAn(List<DapAn> listAirport, Context context) {
        this.listChildren = listAirport;
        this.context = context;
    }

    @Override
    public TopicViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dapan, parent, false);
        return new TopicViewHoder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHoder holder, int position) {
        DapAn obj = listChildren.get(position);
        holder.txt_name.setText(obj.getsMESSAGE());

    }

    @Override
    public int getItemCount() {
        return listChildren.size();
    }

    public class TopicViewHoder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.radioDapAn)
        RadioButton txt_name;

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

    public void updateList(List<DapAn> list) {
        listChildren = list;
        notifyDataSetChanged();
    }
}
