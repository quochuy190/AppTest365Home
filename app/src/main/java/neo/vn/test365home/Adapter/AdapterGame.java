package neo.vn.test365home.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Listener.SwitchChangeListenner;
import neo.vn.test365home.Models.Game;
import neo.vn.test365home.R;


/**
 * Created by QQ on 7/7/2017.
 */

public class AdapterGame extends RecyclerView.Adapter<AdapterGame.TopicViewHoder> {
    private List<Game> lisGame;
    private Context context;
    private ItemClickListener OnIListener;
    private SwitchChangeListenner onSwitchChangeListenner;

    public ItemClickListener getOnIListener() {
        return OnIListener;
    }

    public void setOnIListener(ItemClickListener onIListener) {
        OnIListener = onIListener;
    }

    public SwitchChangeListenner getOnSwitchChangeListenner() {
        return onSwitchChangeListenner;
    }

    public void setOnSwitchChangeListenner(SwitchChangeListenner onSwitchChangeListenner) {
        this.onSwitchChangeListenner = onSwitchChangeListenner;
    }

    public AdapterGame(List<Game> lisGame, Context context) {
        this.lisGame = lisGame;
        this.context = context;
    }

    @Override
    public TopicViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_games, parent, false);
        return new TopicViewHoder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHoder holder, final int position) {
        Game obj = lisGame.get(position);
        holder.txt_name.setText(obj.getsName());
        holder.img_avata.setImageResource(obj.getiAvata());
        if (obj.isOff()) {
            holder.switch_on.setChecked(true);
        } else holder.switch_on.setChecked(false);
        holder.switch_on.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onSwitchChangeListenner.onListennerSwitchChange(position, isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lisGame.size();
    }

    public class TopicViewHoder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.txt_name)
        TextView txt_name;
        @BindView(R.id.img_avata)
        ImageView img_avata;
        @BindView(R.id.switch_on)
        SwitchButton switch_on;

        public TopicViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            OnIListener.onClickItem(getLayoutPosition(), lisGame.get(getLayoutPosition()));
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    public void updateList(List<Game> list) {
        lisGame = list;
        notifyDataSetChanged();
    }
}
