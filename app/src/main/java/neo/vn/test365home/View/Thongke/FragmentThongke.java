package neo.vn.test365home.View.Thongke;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import neo.vn.test365home.Base.BaseFragment;
import neo.vn.test365home.R;

public class FragmentThongke extends BaseFragment implements View.OnClickListener {
    public static FragmentThongke fragment;

    public static synchronized FragmentThongke getInstance() {
        if (fragment == null) {
            fragment = new FragmentThongke();
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
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);
        ButterKnife.bind(this, view);
        initEvent();
        return view;
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
