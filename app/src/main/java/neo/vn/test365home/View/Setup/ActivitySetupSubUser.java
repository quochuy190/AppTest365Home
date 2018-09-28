package neo.vn.test365home.View.Setup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import neo.vn.test365home.Adapter.AdapterListChildrenSetup;
import neo.vn.test365home.App;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.Childrens;
import neo.vn.test365home.R;

public class ActivitySetupSubUser extends BaseActivity {
    @Override
    public int setContentViewId() {
        return R.layout.activity_setup_sub_user;
    }

    RecyclerView.LayoutManager mLayoutManager;
    private List<Childrens> mLisChildren;
    @BindView(R.id.recycle_list_subuser)
    RecyclerView recyleChildren;
    AdapterListChildrenSetup adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLisChildren();
    }

    private void initLisChildren() {
        mLisChildren = new ArrayList<>();
        if (App.mLisChildren != null)
            mLisChildren.addAll(App.mLisChildren);
        adapter = new AdapterListChildrenSetup(mLisChildren, this);
     /*   mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);*/
        mLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyleChildren.setHasFixedSize(true);
        recyleChildren.setLayoutManager(mLayoutManager);
        recyleChildren.setItemAnimator(new DefaultItemAnimator());
        recyleChildren.setAdapter(adapter);

        adapter.setOnIListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {
                Childrens obj = (Childrens) item;
                Intent intent = new Intent(ActivitySetupSubUser.this, ActivityConfigSubUser.class);
                intent.putExtra(Constants.KEY_SEND_CHILDREN, obj);
             //   intent.putExtra(Constants.KEY_SEND_CHILDREN_CONFIG, obj);
                startActivity(intent);
            }
        });
    }
}
