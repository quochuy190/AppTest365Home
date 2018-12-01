package neo.vn.test365home.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.CauhoiDetail;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.StringUtil;


/**
 * Created by QQ on 7/7/2017.
 */

public class AdapterCauhoiNoicau extends RecyclerView.Adapter<AdapterCauhoiNoicau.TopicViewHoder> {
    private List<CauhoiDetail> listAirport;
    private Context context;
    private ItemClickListener OnIListener;


    public ItemClickListener getOnIListener() {
        return OnIListener;
    }

    public void setOnIListener(ItemClickListener onIListener) {
        OnIListener = onIListener;
    }

    public AdapterCauhoiNoicau(List<CauhoiDetail> listAirport, Context context) {
        this.listAirport = listAirport;
        this.context = context;
    }

    @Override
    public TopicViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cauhoi_noicau, parent, false);
        return new TopicViewHoder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHoder holder, int position) {

        CauhoiDetail airport = listAirport.get(position);
        //holder.txt_name.setText(airport.getsMESSAGE());
        initWebview(holder.webview_anwser, StringUtil.convert_html(airport.getsMESSAGE()));

        if (airport.ismLeft()) {
            holder.txt_sott.setText("" + (position + 1));
        }
        if (airport.ismRight()) {
            switch (position) {
                case 0:
                    holder.txt_sott.setText("a");
                    break;
                case 1:
                    holder.txt_sott.setText("b");
                    break;
                case 2:
                    holder.txt_sott.setText("c");
                    break;
                case 3:
                    holder.txt_sott.setText("d");
                    break;
                case 4:
                    holder.txt_sott.setText("e");
                    break;
            }
        }


    }

    @Override
    public int getItemCount() {
        return listAirport.size();
    }

    public class TopicViewHoder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.webview_anwser)
        WebView webview_anwser;
        @BindView(R.id.txt_sott)
        TextView txt_sott;

        public TopicViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // OnIListener.onClickItem(getLayoutPosition(), listAirport.get(getLayoutPosition()));
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    public void updateList(List<CauhoiDetail> list) {
        listAirport = list;
        notifyDataSetChanged();
    }

    private void initWebview(WebView webview_debai, String link_web) {

        webview_debai.getSettings().setJavaScriptEnabled(true);
        webview_debai.getSettings();
        webview_debai.setBackgroundColor(Color.TRANSPARENT);
        WebSettings webSettings = webview_debai.getSettings();
        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
        webSettings.setDefaultFontSize(17);
        /* <html><body  align='center'>You scored <b>192</b> points.</body></html>*/
        String pish = "<html><body  align='center'>";
        String pas = "</body></html>";

        webview_debai.loadDataWithBaseURL("", pish + link_web + pas,
                "text/html", "UTF-8", "");

    }
}
