package com.example.northernvillagewaterandsewageapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
            String Resident = getItem(position).getResident();
            String Service = getItem(position).getService();
            String TimeEstimate = getItem(position).getTimeEstimate();

            DeliveryList deliveryList = new DeliveryList(Resident, Service, TimeEstimate);

            TextView residentTv = convertView.findViewById(R.id.driverListTV1);
            TextView serviceTv = convertView.findViewById(R.id.driverListTV2);
            TextView timeEstimateTv = convertView.findViewById(R.id.driverListTV3);

            residentTv.setText(Resident);
            serviceTv.setText(Service);
            timeEstimateTv.setText(TimeEstimate);

            return convertView;
        }
}
