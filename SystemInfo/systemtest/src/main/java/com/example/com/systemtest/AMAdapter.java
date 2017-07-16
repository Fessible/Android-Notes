package com.example.com.systemtest;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rhm on 2017/7/16.
 */

public class AMAdapter extends ArrayAdapter<AMProcessInfo> {
    private  int resourceId;

    public AMAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<AMProcessInfo> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AMProcessInfo processInfo = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.tvPID = view.findViewById(R.id.t_pid);
            viewHolder.tvUID = view.findViewById(R.id.t_uid);
            viewHolder.tvMemorySize = view.findViewById(R.id.t_msize);
            viewHolder.tvProcessName = view.findViewById(R.id.t_proName);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvPID.setText("PID："+processInfo.getPid());
        viewHolder.tvUID.setText("UID: "+processInfo.getUid());
        viewHolder.tvMemorySize.setText("MemSize: "+processInfo.getMemorySize());
        viewHolder.tvProcessName.setText("ProcessName: "+processInfo.getProcessName());
        return view;
    }

    class ViewHolder {
        TextView tvPID;
        TextView tvUID;
        TextView tvMemorySize;
        TextView tvProcessName;
    }
}

