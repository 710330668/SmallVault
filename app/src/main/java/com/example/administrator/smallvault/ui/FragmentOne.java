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
                    case "电影":
                        Toast.makeText(mActivity, tag, Toast.LENGTH_SHORT).show();
                        //弹出输入框 记录支出多少
                        break;
                    case "2":

                        break;
                    case "3":

                        break;
                    case "4":

                        break;
                    default:

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
