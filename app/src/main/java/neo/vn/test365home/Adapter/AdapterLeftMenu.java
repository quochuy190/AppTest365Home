package neo.vn.test365home.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import neo.vn.test365home.Config.Config;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.R;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;


public class AdapterLeftMenu extends BaseAdapter implements StickyListHeadersAdapter {
    private List<Childrens> mLis;
    private final Activity context;
    ItemClickListener onClickListener;
    /*public AdapterHeader(ArrayList<BaitapTuan> contacts, Activity context) {
        super(contacts, context);
        this.context = context;
    }*/

    public AdapterLeftMenu(Activity context, List<Childrens> mLis, ItemClickListener onClickListener) {
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
        View rowView = inflater.inflate(R.layout.item_left_menu, null, true);
        CircleImageView img_avata = rowView.findViewById(R.id.img_avata);
        TextView txt_name = rowView.findViewById(R.id.txt_name);
        TextView txt_class = rowView.findViewById(R.id.txt_class);
        TextView txt_school = rowView.findViewById(R.id.txt_school);
        Childrens obj = mLis.get(position);
        if (obj.getsAVATAR() != null && obj.getsAVATAR().length() > 0) {
            String sUrl = Config.URL_IMAGE+obj.getsAVATAR();
            Glide.with(context).load(sUrl)
                    .placeholder(R.drawable.icon_avata).into(img_avata);
        } else {
            Glide.with(context).load(R.drawable.icon_avata)
                    .placeholder(R.drawable.icon_avata).into(img_avata);
        }
        if (obj.getsFULLNAME() != null)
            txt_name.setText(obj.getsFULLNAME());
        if (obj.getsSCHOOL_NAME() != null)
            txt_school.setText(obj.getsSCHOOL_NAME());
        if (obj.getsCLASS() != null)
            txt_class.setText(obj.getsCLASS());
        return rowView;

    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        //setup for header view
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_header_leftmenu, null, true);
        TextView label = (TextView) rowView.findViewById(R.id.txt_header);
        if (mLis.get(position).getsHeaderId().equals("a")) {
            label.setText("KHỐI 1");
        } else if (mLis.get(position).getsHeaderId().equals("b")) {
            label.setText("KHỐI 2");
        } else if (mLis.get(position).getsHeaderId().equals("c")) {
            label.setText("KHỐI 3");
        } else if (mLis.get(position).getsHeaderId().equals("d")) {
            label.setText("KHỐI 4");
        } else if (mLis.get(position).getsHeaderId().equals("e")) {
            label.setText("KHỐI 5");

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
