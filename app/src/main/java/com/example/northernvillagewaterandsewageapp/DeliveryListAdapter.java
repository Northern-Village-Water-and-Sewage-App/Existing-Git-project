package com.example.northernvillagewaterandsewageapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.northernvillagewaterandsewageapp.ObjectClasses.DeliveryList;
import com.example.northernvillagewaterandsewageapp.R;

import java.util.List;

    public class DeliveryListAdapter extends ArrayAdapter<DeliveryList> {

        private Context mContext;
        private int mResource;

        public DeliveryListAdapter(@NonNull Context context, int resource, @NonNull List<DeliveryList> objects) {
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
            String HouseNum = getItem(position).getHouseNum();
            String Service = getItem(position).getService();
            String TimeEstimate = getItem(position).getTimeEstimate();

            int id = Resources.getSystem().getIdentifier("ic_dialog_alert", "drawable", "android");

            DeliveryList deliveryList = new DeliveryList(HouseNum, Service, TimeEstimate);

            TextView houseNumTv = convertView.findViewById(R.id.driverListTV1);
            TextView serviceTv = convertView.findViewById(R.id.driverListTV2);
            TextView timeEstimateTv = convertView.findViewById(R.id.driverListTV3);
            ImageView imageView = convertView.findViewById(R.id.imageViewDriver);

            houseNumTv.setText(HouseNum);
            serviceTv.setText(Service);
            timeEstimateTv.setText(TimeEstimate);
            imageView.setImageResource(id);


            return convertView;
        }
}
