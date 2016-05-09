package com.example.administrator.smallvault.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.smallvault.R;
import com.example.administrator.smallvault.ui.view.XCArcMenuView;

import butterknife.ButterKnife;

/**
 * 跟踪fragment
 */
public class FragmentOne extends Fragment {
    private View mView;
    private MainActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_one, container, false);
        XCArcMenuView view = (XCArcMenuView) mView.findViewById(R.id.arcmenu2);
        view.setOnMenuItemClickListener(new XCArcMenuView.OnMenuItemClickListener() {

            @Override
            public void onClick(View view, int pos) {
                // TODO Auto-generated method stub
                String tag = (String) view.getTag();
                switch (tag){
                    case "yule":
                        Toast.makeText(mActivity, "娱乐", Toast.LENGTH_SHORT).show();
                        //弹出输入框 记录支出多少
                        break;
                    case "gouwu":
                        Toast.makeText(mActivity, "购物", Toast.LENGTH_SHORT).show();
                        break;
                    case "canyin":
                        Toast.makeText(mActivity, "餐饮", Toast.LENGTH_SHORT).show();
                        break;
                    case "yiliao":
                        Toast.makeText(mActivity, "医疗", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "其它", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
        initBar();

        return mView;
    }

    private void initBar() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
