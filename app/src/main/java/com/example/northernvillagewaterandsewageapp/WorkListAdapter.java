package com.example.northernvillagewaterandsewageapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.northernvillagewaterandsewageapp.ObjectClasses.WorkList;

import java.util.List;

public class WorkListAdapter extends ArrayAdapter<WorkList> {

    private Context mContext;
    private int mResource;

    public WorkListAdapter(@NonNull Context context, int resource, @NonNull List<WorkList> objects) {
        super(context, resource, objects);
        this.mContext = getContext();
        this.mResource = resource;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        mContext = getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        // getting all the stuff
        String TimeStamp = getItem(position).getTimeStamp();
        String Resident = getItem(position).getResident();
        String HouseNum = getItem(position).getHouseNum();
        String TankType = getItem(position).getTankType();
        String TimeEstimate = getItem(position).getTimeEstimate();
        String image = getItem(position).getResourceID();

        int id = Resources.getSystem().getIdentifier("water_image2.png", "drawable", "android");

        WorkList workList = new WorkList(TimeStamp, Resident, HouseNum, TankType, TimeEstimate);

        TextView timeStampTv = convertView.findViewById(R.id.customAdapterTV1);
        TextView residentTv = convertView.findViewById(R.id.customAdapterTV2);
        TextView houseNumTv = convertView.findViewById(R.id.customAdapterTV3);
        TextView tankTypeTv = convertView.findViewById(R.id.customAdapterTV4);
        TextView timeEstimateTv = convertView.findViewById(R.id.customAdapterTV5);
        ImageView imageView = convertView.findViewById(R.id.imageViewManager);

        timeStampTv.setText(TimeStamp);
        residentTv.setText(Resident);
        houseNumTv.setText(HouseNum);
        tankTypeTv.setText(TankType);
        timeEstimateTv.setText(TimeEstimate);
        imageView.setImageResource(id);

        return convertView;
    }
}
