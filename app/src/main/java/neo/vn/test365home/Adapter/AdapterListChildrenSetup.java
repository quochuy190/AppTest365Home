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
import de.hdodenhof.circleimageview.CircleImageView;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.R;


/**
 * Created by QQ on 7/7/2017.
 */

public class AdapterListChildrenSetup extends RecyclerView.Adapter<AdapterListChildrenSetup.TopicViewHoder> {
    private List<Childrens> listChildren;
    private Context context;
    private ItemClickListener OnIListener;


    public ItemClickListener getOnIListener() {
        return OnIListener;
    }

    public void setOnIListener(ItemClickListener onIListener) {
        OnIListener = onIListener;
    }

    public AdapterListChildrenSetup(List<Childrens> listAirport, Context context) {
        this.listChildren = listAirport;
        this.context = context;
    }

    @Override
    public TopicViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_children, parent, false);
        return new TopicViewHoder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHoder holder, int position) {
        Childrens obj = listChildren.get(position);

        if (obj.getsFULLNAME()!=null)
            holder.txt_fullname.setText(obj.getsFULLNAME());

        if (obj.getsUSERNAME()!=null)
            holder.txt_user.setText(obj.getsUSERNAME());

        if (obj.getsID_LEVEL()!=null)
            holder.txt_class.setText(obj.getsLEVEL_NAME());

  /*      if (obj.getsFULLNAME()!=null)
            holder.txt_fullname.setText(obj.getsFULLNAME());

        if (obj.getsFULLNAME()!=null)
            holder.txt_fullname.setText(obj.getsFULLNAME());*/

    }

    @Override
    public int getItemCount() {
        return listChildren.size();
    }

    public class TopicViewHoder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.txt_school)
        TextView txt_school;
        @BindView(R.id.txt_fullname)
        TextView txt_fullname;
        @BindView(R.id.txt_class)
        TextView txt_class;
        @BindView(R.id.txt_user)
        TextView txt_user;
        @BindView(R.id.txt_pass)
        TextView txt_pass;
        @BindView(R.id.img_avata_children)
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
