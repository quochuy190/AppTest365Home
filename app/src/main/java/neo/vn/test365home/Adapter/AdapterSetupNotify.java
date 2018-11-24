package neo.vn.test365home.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Listener.SwitchChangeListenner;
import neo.vn.test365home.Models.ItemSetupNotify;
import neo.vn.test365home.R;


/**
 * Created by QQ on 7/7/2017.
 */

public class AdapterSetupNotify extends RecyclerView.Adapter<AdapterSetupNotify.TopicViewHoder> {
    private List<ItemSetupNotify> mList;
    private Context context;
    private ItemClickListener OnIListener;
    private SwitchChangeListenner onSwitchChangeListenner;

    public ItemClickListener getOnIListener() {
        return OnIListener;
    }

    public void setOnIListener(ItemClickListener onIListener) {
        OnIListener = onIListener;
    }

    public AdapterSetupNotify(List<ItemSetupNotify> mList, Context context, SwitchChangeListenner onListennerr) {
        this.mList = mList;
        this.context = context;
        this.onSwitchChangeListenner = onListennerr;
    }

    @Override
    public TopicViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_setup_notify, parent, false);
        return new TopicViewHoder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHoder holder, final int position) {
        ItemSetupNotify obj = mList.get(position);
        holder.txt_title.setText(obj.getsTitle());
        holder.txt_content.setText(obj.getsContent());
        holder.switch_on.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onSwitchChangeListenner.onListennerSwitchChange(position, isChecked);
            }
        });
        if (obj.isOnOff()) {
            holder.switch_on.setChecked(true);
        } else
            holder.switch_on.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class TopicViewHoder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.switch_on)
        SwitchButton switch_on;
        @BindView(R.id.txt_title)
        TextView txt_title;
        @BindView(R.id.txt_content)
        TextView txt_content;

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
