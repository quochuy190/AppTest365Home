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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Adapter.AdapterMenuSetup;
import neo.vn.test365home.Base.BaseFragment;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.ObjMenu;
import neo.vn.test365home.R;
import neo.vn.test365home.View.Longin.ActivityUpdateInfo;

public class FragmentSetup extends BaseFragment implements View.OnClickListener {
    public static FragmentSetup fragment;
    RecyclerView.LayoutManager mLayoutmanager;
    AdapterMenuSetup adapter;
    List<ObjMenu> lisMenuSetup;
    @BindView(R.id.recycle_setup)
    RecyclerView recycler_setup;

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

    private void initData() {
        lisMenuSetup = new ArrayList<>();
        lisMenuSetup.add(new ObjMenu(getString(R.string.txt_info), R.drawable.camera, ""));
        lisMenuSetup.add(new ObjMenu(getString(R.string.txt_setup_notify), R.drawable.camera, ""));
        lisMenuSetup.add(new ObjMenu(getString(R.string.txt_setup_sub_user), R.drawable.camera, ""));
        lisMenuSetup.add(new ObjMenu(getString(R.string.txt_account), R.drawable.camera, ""));
        lisMenuSetup.add(new ObjMenu(getString(R.string.txt_change_pass), R.drawable.camera, ""));

    }

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
                switch (position){
                    case 0:
                        startActivity(new Intent(getContext(), ActivityUpdateInfo.class));
                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:
                        startActivity(new Intent(getContext(), ActivityManageAccount.class));
                        break;
                    case 4:

                        break;
                }
            }
        });
    }

    private void initEvent() {

    }


    @Override
    public void onClick(View view) {

    }

    private void click(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(getActivity(), appCompatActivity.getClass());
        startActivity(intent);
    }
}
