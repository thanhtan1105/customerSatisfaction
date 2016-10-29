package com.manhnv.indoordirection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.manhnv.indoordirection.model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ManhNV on 10/29/16.
 */

public class RoomAdapter extends ArrayAdapter<Point> {
    private Context mContext;
    private List<Point> mRooms;
    private List<Point> mOriginalItems;


    public RoomAdapter(Context context, int resource, List<Point> objects) {
        super(context, resource, objects);
        mContext = context;
        mRooms = objects;
    }

    public void getFilter(int floor) {

        if (mOriginalItems == null) {
            mOriginalItems = new ArrayList<>(mRooms);
        }

        List<Point> newlist = new ArrayList<>();
        for (int i = 0; i < mOriginalItems.size(); i++) {
            if (mOriginalItems.get(i).getFloor() == floor) {
                newlist.add(mOriginalItems.get(i));
            }
        }
        mRooms = newlist;
        notifyDataSetChanged();

    }


    @Nullable
    @Override
    public Point getItem(int position) {
        return mRooms.get(position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_room, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position==0) {
            viewHolder.txtRoomName.setText("Choose room");
        }else{
            Point room = getItem(position-1);
            viewHolder.txtRoomName.setText(room.getName());
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return mRooms.size();
    }

    public void setRooms(List<Point> rooms) {
        this.mRooms = rooms;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_room, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position==0) {
            viewHolder.txtRoomName.setText("Choose room");
        }else{
            Point room = getItem(position-1);
            viewHolder.txtRoomName.setText(room.getName());
        }
        return convertView;
    }

    private final class ViewHolder {
        TextView txtRoomName;

        ViewHolder(View v) {
            txtRoomName = (TextView) v.findViewById(R.id.txt_spinner_text);
        }
    }
}
