package com.example.com.systemtest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rhm on 2017/7/16.
 */

public class PMAdapter extends ArrayAdapter<PMAppInfo> {

    private  int resourceId;

    public PMAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<PMAppInfo> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        PMAppInfo pmAppInfo = getItem(i);//获取PMAppInfo
        ViewHolder viewHolder;
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.lable = view.findViewById(R.id.t_label);
            viewHolder.icon = view.findViewById(R.id.image);
            viewHolder.pkgname = view.findViewById(R.id.t_pkgname);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.lable.setText(pmAppInfo.getAppLabel());
        viewHolder.icon.setImageDrawable(pmAppInfo.getAppIcon());
        viewHolder.pkgname.setText(pmAppInfo.getPkgName());

        return view;
    }

    class  ViewHolder{
        TextView lable;
        ImageView icon;
        TextView pkgname;
}
}
