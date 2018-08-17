package neo.vn.test365home.Adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Listener.ButtonItemClickListener;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.ObjTuanhoc;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.StringUtil;


/**
 * Created by QQ on 7/7/2017.
 */

public class AdapterListKhobaitap extends RecyclerView.Adapter<AdapterListKhobaitap.TopicViewHoder> {
    private List<ObjTuanhoc> listChildren;
    private Context context;
    private ItemClickListener OnIListener;
    private ButtonItemClickListener buttonItemClickListener;

    public ItemClickListener getOnIListener() {
        return OnIListener;
    }

    public void setOnIListener(ItemClickListener onIListener) {
        OnIListener = onIListener;
    }

    public AdapterListKhobaitap(List<ObjTuanhoc> listAirport, Context context, ButtonItemClickListener buttonItemClickListener) {
        this.listChildren = listAirport;
        this.context = context;
        this.buttonItemClickListener = buttonItemClickListener;
    }

    @Override
    public TopicViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_khobaitap, parent, false);
        return new TopicViewHoder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHoder holder, final int position) {
        ObjTuanhoc airport = listChildren.get(position);
        holder.txt_name.setText(airport.getsWEEK_NAME());
        holder.txt_mota.setText(airport.getsNAME());
        // holder.btnTrangthai.setText(airport.getsTrangthai());
        holder.txt_price.setText(StringUtil.formatNumber(airport.getsPRICE()));
        holder.txt_soluongtai.setText(airport.getsTOTAL_BUY());
        if (airport.getsSTATUS().equals("0")) {
            holder.btnTrangthai.setBackgroundResource(R.drawable.spr_khotuan_chuatai);
            holder.btnTrangthai.setText("Chọn tải");
            holder.btnTrangthai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonItemClickListener.onClickButtonItem(position);
                }
            });
        } else if (airport.getsSTATUS().equals("1")) {
            holder.btnTrangthai.setBackgroundResource(R.drawable.spr_khotuan_dataii);
            holder.btnTrangthai.setText("Đã tải");
        } else if (airport.getsSTATUS().equals("2")) {
            holder.btnTrangthai.setBackgroundResource(R.drawable.spr_khotuan_duoctang);
            holder.btnTrangthai.setText("Được tặng");
        } else if (airport.getsSTATUS().equals("4")) {
            holder.btnTrangthai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonItemClickListener.onClickButtonItem(position);
                }
            });
            holder.btnTrangthai.setBackgroundResource(R.drawable.spr_khotuan_duocchon);
            holder.btnTrangthai.setText("Bỏ chọn");
        }
    }

    @Override
    public int getItemCount() {
        return listChildren.size();
    }

    public class TopicViewHoder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.txt_name)
        TextView txt_name;
        @BindView(R.id.txt_description)
        TextView txt_mota;
        @BindView(R.id.btn_trangthai)
        Button btnTrangthai;
        @BindView(R.id.txt_price)
        TextView txt_price;
        @BindView(R.id.txt_soluongtai)
        TextView txt_soluongtai;
        @BindView(R.id.background_itemkho)
        ConstraintLayout background_itemkho;

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

    public void updateList(List<ObjTuanhoc> list) {
        listChildren = list;
        notifyDataSetChanged();
    }
}
