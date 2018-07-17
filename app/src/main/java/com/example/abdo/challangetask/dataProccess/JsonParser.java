package com.example.abdo.challangetask.dataProccess;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    ArrayList<DataEncap> data;

    public ArrayList<DataEncap> JsonProcess(String jsonFile) {

        data = new ArrayList<>();

        try {

            JSONArray mainArray=new JSONArray(jsonFile);

            for (int i = 0; i < mainArray.length(); i++) {

                JSONObject mainObject = mainArray.getJSONObject(i);
                String des=mainObject.getString("productDescription");
                String price=mainObject.getString("price");

                JSONObject Image=mainObject.getJSONObject("image");
                String url=Image.getString("url");
                String height = Image.getString("height");


               DataEncap encap = new DataEncap(des,url,height,price);
                data.add(encap);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<DataEncap> getlist() {
        return data;
    }

}