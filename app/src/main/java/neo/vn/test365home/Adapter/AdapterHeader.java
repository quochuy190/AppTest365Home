package neo.vn.test365home.Adapter;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.TuanDamua;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.StringUtil;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;


public class AdapterHeader extends BaseAdapter implements StickyListHeadersAdapter {
    private List<TuanDamua> mLis;
    private final Activity context;
    ItemClickListener onClickListener;
    /*public AdapterHeader(ArrayList<BaitapTuan> contacts, Activity context) {
        super(contacts, context);
        this.context = context;
    }*/

    public AdapterHeader(Activity context, List<TuanDamua> mLis, ItemClickListener onClickListener) {
        this.context = context;
        this.mLis = mLis;
        this.onClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        return mLis.size();
    }

    @Override
    public Object getItem(int position) {
        return mLis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_baitap_tuan, null, true);
        ImageView img_sticker = rowView.findViewById(R.id.img_sticker);
        TextView txt_tentuan = rowView.findViewById(R.id.txt_tentuan);
        TextView txt_muctieu_tuan = rowView.findViewById(R.id.txt_muctieu_tuan);
        TextView txt_mota_tuan = rowView.findViewById(R.id.txt_mota_tuan);
        TextView txt_point_tuan = rowView.findViewById(R.id.txt_point_tuan);
        TextView txt_luuy = rowView.findViewById(R.id.txt_luuy);
        Button btn_muathem = rowView.findViewById(R.id.btn_taithem);
        ConstraintLayout background_itemkho = rowView.findViewById(R.id.background_itemkho);
        TuanDamua obj = mLis.get(position);
        txt_tentuan.setText(obj.getsWEEK_NAME());
        if (obj.getsREQUIREMENT() != null && obj.getsREQUIREMENT().length() > 0)
            txt_mota_tuan.setText(Html.fromHtml("Mục tiêu: " + obj.getsREQUIREMENT()));
        if (obj.getsNAME() != null && obj.getsNAME().length() > 0)
            txt_muctieu_tuan.setText(Html.fromHtml(obj.getsNAME()));
        // add thêm 1 phần tử để hiển thị button mua thêm
        if (position != (mLis.size() - 1) && mLis.get(position) != null && mLis.get(position).getsHeaderId() != null
                && mLis.get(position).getsHeaderId().equals("d")) {
            if (obj.getsPOINT() != null && obj.getsPOINT().length() > 0) {
                String s = StringUtil.format_point(Float.parseFloat(obj.getsPOINT()));
                txt_point_tuan.setText(s + " ĐIỂM");
                txt_point_tuan.setVisibility(View.VISIBLE);
            } else
                txt_point_tuan.setVisibility(View.GONE);

        } else txt_point_tuan.setVisibility(View.GONE);
        if (position == (mLis.size() - 1)) {
            btn_muathem.setVisibility(View.VISIBLE);
            img_sticker.setVisibility(View.GONE);
            txt_mota_tuan.setVisibility(View.GONE);
            txt_tentuan.setVisibility(View.GONE);
            txt_luuy.setVisibility(View.VISIBLE);
            background_itemkho.setBackgroundResource(R.color.white);
            btn_muathem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onClickItem(position, mLis.get(position));
                }
            });
        } else {
            background_itemkho.setBackgroundResource(R.color.background_login);
            txt_luuy.setVisibility(View.GONE);
            btn_muathem.setVisibility(View.GONE);
            img_sticker.setVisibility(View.GONE);
            txt_mota_tuan.setVisibility(View.VISIBLE);
            txt_tentuan.setVisibility(View.VISIBLE);
        }

        return rowView;

    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        //setup for header view
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_header, null, true);
        TextView label = (TextView) rowView.findViewById(R.id.txt_header);
        ImageView img_down = (ImageView) rowView.findViewById(R.id.img_down);

        if (position != (mLis.size() - 1)) {
            img_down.setVisibility(View.VISIBLE);
            if (mLis.get(position).getsHeaderId().equals("a")) {
                label.setText("Bài tập đang làm");

            } else if (mLis.get(position).getsHeaderId().equals("b")) {
                label.setText("Bài tập quá hạn");

            } else if (mLis.get(position).getsHeaderId().equals("c")) {
                label.setText("Bài tập chưa làm");

            } else if (mLis.get(position).getsHeaderId().equals("d")) {
                label.setText("Bài tập đã làm hết");

            } else if (mLis.get(position).getsHeaderId().equals("e")) {
                label.setText("Bài tập chưa giao");

            }
        } else {
            label.setVisibility(View.GONE);
            img_down.setVisibility(View.GONE);
        }

        return rowView;

    }

    @Override
    public long getHeaderId(int position) {
        if (mLis.size() > 0 && mLis.get(position).getsHeaderId() != null && position < (mLis.size() - 1)) {
            return mLis.get(position).getsHeaderId().subSequence(0, 1).charAt(0);
        } else return -1;
    }
}
