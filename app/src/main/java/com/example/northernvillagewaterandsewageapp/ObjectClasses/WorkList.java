package com.example.northernvillagewaterandsewageapp.ObjectClasses;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class WorkList {

    private String TimeStamp;
    //private String Resident;
    private String HouseNum;
    private String TankType;
    private String TimeEstimate;
    private String waterID = "@drawable/water_image2";
    private int sewageID;

    // constructor
    public WorkList(String timeStamp, String houseNum, String tankType, String timeEstimate) {
        TimeStamp = timeStamp;
        HouseNum = houseNum;
        TankType = tankType;
        TimeEstimate = timeEstimate;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getHouseNum() {
        return HouseNum;
    }

    public void setHouseNum(String houseNum) {
        HouseNum = houseNum;
    }

    public String getTankType() {
        return TankType;
    }

    public void setTankType(String tankType) {
        TankType = tankType;
    }

    public String getTimeEstimate() {
        return TimeEstimate;
    }

    public void setTimeEstimate(String timeEstimate) {
        this.TimeEstimate = timeEstimate;
    }

    public String getResourceID() {
        String resourceID = "0";

        switch (getTankType()) {
            case "Water":
                resourceID = "@drawable/water_image2";
                break;
            case "Sewage":
                resourceID = "@drawable/water_image1";
                break;
        }
        return resourceID;
    }

}

                            //    NOT NEEDED ANYMORE...TOOK OUT FROM MANAGER ACTIVITY

    /*private void loadListView() {
        String url = "http://54.201.85.48:32132/get_work_list/";
        final ArrayList<String> worklistListText = new ArrayList<>();
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject e;
                    for (int pos = 0; pos < response.length(); pos++) {
                        String temp = "";
                        e = response.getJSONObject(pos);
                        temp += "Time Stamp: " + e.getString("timestamp") + "\n";
                        temp += "Resident: " + e.getString("username") + "\n";
                        temp += "House Number: " + e.getString("house_number") + "\n";
                        temp += "Tank Type: " + e.getString("tank_type") + "\n";
                        temp += "Time estimate: " + e.getString("estimate");
                        worklistListText.add("\n" + temp + "\n");
                    }
                    arrayAdapter.addAll(worklistListText);
                    worklistListView.setAdapter(arrayAdapter);

                    //makes clicking on an item from the worklist pull up the manager time estimate fragment, with the information it needs to update the database
                    worklistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //need to pass other stuff here to make this work                                                                                                            ************************
                            ManagerTimeEstimateFragment managerTimeEstimateFragment = new ManagerTimeEstimateFragment();
                            managerTimeEstimateFragment.show(getSupportFragmentManager(), "Dialog");
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        mQueue.add(request);
    }*/