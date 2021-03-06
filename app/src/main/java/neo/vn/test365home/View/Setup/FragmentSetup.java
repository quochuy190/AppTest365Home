package neo.vn.test365home.View.Setup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import neo.vn.test365home.Adapter.AdapterMenuSetup;
import neo.vn.test365home.Base.BaseFragment;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.Login;
import neo.vn.test365home.Models.ObjMenu;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.SharedPrefs;
import neo.vn.test365home.View.Longin.ActivityLogin;
import neo.vn.test365home.View.Longin.ActivityUpdateInfo;

public class FragmentSetup extends BaseFragment implements View.OnClickListener {
    public static FragmentSetup fragment;
    RecyclerView.LayoutManager mLayoutmanager;
    AdapterMenuSetup adapter;
    List<ObjMenu> lisMenuSetup;
    @BindView(R.id.recycle_setup)
    RecyclerView recycler_setup;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.txt_fullname)
    TextView txt_fullname;
    @BindView(R.id.img_avata_update)
    CircleImageView img_avata_menu;
    Login objLogin;

    public static synchronized FragmentSetup getInstance() {
        if (fragment == null) {
            fragment = new FragmentSetup();
        }
        return (fragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);
        initData();
        init();
        initEvent();
        return view;
    }

    String sUrlAvata;

    private void initData() {
        objLogin = SharedPrefs.getInstance().get(Constants.KEY_LOGININFO, Login.class);
        sUrlAvata = SharedPrefs.getInstance().get(Constants.KEY_URL_MEDIA, String.class);
        if (objLogin != null && objLogin.getsAVARTAR() != null && objLogin.getsAVARTAR().length() > 0)
            Glide.with(this)
                    .load(sUrlAvata + objLogin.getsAVARTAR())
                    .placeholder(R.drawable.icon_family)
                    .into(img_avata_menu);
            // Glide.with(this).load(Config.URL_IMAGE + objLogin.getsAVARTAR()).into(img_avata_menu);
        else
            Glide.with(this).load(R.drawable.icon_family).into(img_avata_menu);
        lisMenuSetup = new ArrayList<>();
        lisMenuSetup.add(new ObjMenu(getString(R.string.txt_setup_sub_user), R.drawable.icon_setup_sub, ""));
        lisMenuSetup.add(new ObjMenu(getString(R.string.txt_account), R.drawable.icon_wallet, ""));
        lisMenuSetup.add(new ObjMenu(getString(R.string.txt_setup_notify), R.drawable.icon_setup_notify, ""));
        lisMenuSetup.add(new ObjMenu(getString(R.string.txt_info), R.drawable.icon_user, ""));
        lisMenuSetup.add(new ObjMenu(getString(R.string.txt_change_pass), R.drawable.icon_change_pass, ""));
        lisMenuSetup.add(new ObjMenu(getString(R.string.txt_guild_app), R.drawable.menu_huong_dan, ""));
        lisMenuSetup.add(new ObjMenu(getString(R.string.txt_share_app), R.drawable.menu_share, ""));
        lisMenuSetup.add(new ObjMenu(getString(R.string.txt_feedback_app), R.drawable.menu_feedback, ""));
        lisMenuSetup.add(new ObjMenu(getString(R.string.txt_support_app), R.drawable.menu_support, ""));
    }

    Login mLogin;

    private void init() {
        mLayoutmanager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        adapter = new AdapterMenuSetup(lisMenuSetup, getContext());
        recycler_setup.setHasFixedSize(true);
        recycler_setup.setLayoutManager(mLayoutmanager);
        recycler_setup.setItemAnimator(new DefaultItemAnimator());
        recycler_setup.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnIListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getContext(), ActivitySetupSubUser.class));
                        break;
                    case 1:
                        startActivity(new Intent(getContext(), ActivityManageAccount.class));
                        break;
                    case 2:
                        startActivity(new Intent(getContext(), ActivitySetupNotify.class));
                        break;
                    case 3:
                        Intent intent = new Intent(getContext(), ActivityUpdateInfo.class);
                        startActivityForResult(intent, Constants.RequestCode.GET_UPDATE_INFO_USER);
                        break;
                    case 4:
                        startActivity(new Intent(getContext(), ActivityChangePassWord.class));
                        break;
                    case 5:
                        //Hướng dẫn sử dụng
                        break;
                    case 6:
                        final String my_package_name = "neo.vn.test365home";  // <- HERE YOUR PACKAGE NAME!!
                        String url = "";
                        mLogin = SharedPrefs.getInstance().get(Constants.KEY_LOGININFO, Login.class);
                        try {
                            Intent i = new Intent(Intent.ACTION_SEND);
                            i.setType("text/plain");
                            i.putExtra(Intent.EXTRA_SUBJECT, "Home365");
                            String sAux = "Tải Home365 tại https://home365.online để con làm bài tập về nhà, rất tuyệt vời.";
                            url = "https://play.google.com/store/apps/details?id=" + my_package_name;
                            sAux = sAux + url + "\n\n";
                            i.putExtra(Intent.EXTRA_TEXT, sAux);
                            startActivity(Intent.createChooser(i, "Home365"));
                        } catch (Exception e) {
                            e.toString();
                        }
                        break;
                    case 7:
                        startActivity(new Intent(getContext(), ActivityFeedback.class));
                        break;
                    case 8:
                        startActivity(new Intent(getContext(), ActivitySupport.class));
                        break;
                }
            }
        });
    }

    private void initEvent() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ActivityLogin.class));
                SharedPrefs.getInstance().put(Constants.KEY_ISLOGIN, false);
                SharedPrefs.getInstance().put(Constants.KEY_LOGININFO, null);
                getActivity().finish();
            }
        });
    }


    @Override
    public void onClick(View view) {

    }

    private void click(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(getActivity(), appCompatActivity.getClass());
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RequestCode.GET_UPDATE_INFO_USER) {
            if (resultCode == getActivity().RESULT_OK) {
                initData();
            }
        }
    }
}
