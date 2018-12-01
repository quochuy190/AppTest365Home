package neo.vn.test365home.View.Baitap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.test365home.Base.BaseFragment;
import neo.vn.test365home.R;

public class FragmentComplete extends BaseFragment implements View.OnClickListener {
    public static FragmentComplete fragment;
    @BindView(R.id.btn_exit)
    Button btn_exit;
    public static synchronized FragmentComplete getInstance() {
        if (fragment == null) {
            fragment = new FragmentComplete();
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
        View view = inflater.inflate(R.layout.fragment_complete, container, false);
        ButterKnife.bind(this, view);
        initEvent();
        return view;
    }

    private void initEvent() {
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}
