package com.example.administrator.smallvault.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.smallvault.R;
import com.example.administrator.smallvault.db.entity.SiFangQian;

import java.util.List;

/**
 * Created by Administrator on 2016/5/10.
 */
public class PayListAdapter extends BaseAdapter {

    private Context mContext;
    private List<SiFangQian> list;
    private LayoutInflater mLayoutInflater;
    public PayListAdapter(Context context,List<SiFangQian>list){
        this.mContext=context;
        this.list=list;
        mLayoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=mLayoutInflater.inflate(R.layout.item_paylist,null);
            viewHolder.tv_money=(TextView)convertView.findViewById(R.id.tv_money);
            viewHolder.tv_paywhere=(TextView)convertView.findViewById(R.id.tv_paywhere);
            viewHolder.tv_time=(TextView)convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.tv_money.setText(list.get(position).getMoney());
        viewHolder.tv_paywhere.setText(list.get(position).getPaywhere());
        viewHolder.tv_time.setText(list.get(position).getTime());

        return convertView;
    }

    public  class ViewHolder{
        private TextView tv_money;
        private TextView tv_paywhere;
        private TextView tv_time;
    }
}
