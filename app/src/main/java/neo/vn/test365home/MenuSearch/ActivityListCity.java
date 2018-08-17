package neo.vn.test365home.MenuSearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Adapter.AdapterListCity;
import neo.vn.test365home.App;
import neo.vn.test365home.Base.BaseActivity;
import neo.vn.test365home.Config.Constants;
import neo.vn.test365home.Listener.ItemClickListener;
import neo.vn.test365home.Models.City;
import neo.vn.test365home.Models.District;
import neo.vn.test365home.Models.Schools;
import neo.vn.test365home.R;
import neo.vn.test365home.Untils.KeyboardUtil;
import neo.vn.test365home.Untils.SharedPrefs;


/**
 * Created by QQ on 10/16/2017.
 */

public class ActivityListCity extends BaseActivity implements ImlMenuSearch.View {

    private List<City> mLisAirport;
    private AdapterListCity adapterService;
    @BindView(R.id.recycle_list_service)
    RecyclerView recycle_service;
    RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.edt_search_service)
    EditText edt_search_service;
    List<City> temp;
    String sUserId;
    PresenterMenuSearch mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.fragment_service);
        ButterKnife.bind(this);
        KeyboardUtil.hideSoftKeyboard(this);
        //  initData();
        App.mCity = null;
        mPresenter = new PresenterMenuSearch(this);
        edt_search_service.setVisibility(View.VISIBLE);
        initAppbar();
        init();
        initData();
        initEvent();
    }

    TextView txt_title;

    public void initAppbar() {

        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setVisibility(View.GONE);
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public int setContentViewId() {
        return R.layout.activity_recycleview;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /*  @Nullable
          @Override
          public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
              View view= inflater.inflate(R.layout.fragment_service, container, false);


              view.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                  }
              });
              return view;
          }
      */
    private void initEvent() {
        edt_search_service.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // filter your list from your input
                filter(s.toString());
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });


    }

    void filter(String text) {
        temp.clear();
        for (City d : mLisAirport) {
            if (d.getsPROVINCE_NAME().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                temp.add(d);
            }
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.getsPROVINCE_NAME().contains(text)) {

            }
        }
        //update recyclerview
        adapterService.updateList(temp);
       /* adapterService.setOnIListener(new ItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                App.mDistrict = temp.get(position);
                finish();
            }

            @Override
            public void OnLongItemClickListener(int position) {

            }
        });*/
       adapterService.setOnIListener(new ItemClickListener() {
           @Override
           public void onClickItem(int position, Object item) {
               setResult(RESULT_OK, new Intent());
               App.mCity = (City) item;
               finish();
           }
       });
    }

    private void init() {
        mLisAirport = new ArrayList<>();
        temp = new ArrayList<>();
        adapterService = new AdapterListCity(temp, this);
        mLayoutManager = new GridLayoutManager(this, 1);
        recycle_service.setNestedScrollingEnabled(false);
        recycle_service.setHasFixedSize(true);
        recycle_service.setLayoutManager(mLayoutManager);
        recycle_service.setItemAnimator(new DefaultItemAnimator());
        recycle_service.setAdapter(adapterService);
        adapterService.updateList(temp);
        /*adapterService.setOnIListener(new setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                App.mDistrict = mLisAirport.get(position);
                finish();

            }

            @Override
            public void OnLongItemClickListener(int position) {

            }
        });*/
        adapterService.setOnIListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {
                setResult(RESULT_OK, new Intent());
                App.mCity = (City) item;
                finish();
            }
        });

    }

    private void initData() {
        if (App.mLisCity != null && App.mLisCity.size() > 0) {
            mLisAirport.addAll(App.mLisCity);
            temp.addAll(mLisAirport);
            adapterService.notifyDataSetChanged();
        } else {
            sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERNAME, String.class);
            showDialogLoading();
            mPresenter.api_get_list_city(sUserId);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }




    @Override
    public void show_get_api_error() {
        hideDialogLoading();
    }

    @Override
    public void show_get_list_district(List<District> mList) {
        hideDialogLoading();

    }

    @Override
    public void show_get_list_citys(List<City> mList) {
        hideDialogLoading();
        if (mList != null) {
            mLisAirport.addAll(mList);
            temp.addAll(mLisAirport);
            App.mLisCity.clear();
            App.mLisCity.addAll(mLisAirport);
            adapterService.notifyDataSetChanged();
        }
    }

    @Override
    public void show_get_list_schools(List<Schools> mList) {
        hideDialogLoading();
    }




}
