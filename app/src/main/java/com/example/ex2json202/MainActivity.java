package com.example.ex2json202;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> fils = new ArrayList<>();
    ListView ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ls = findViewById(R.id.lst);
        String json = LoadJsonFromRaw(R.raw.filieres);

        try {
            JSONArray arr = new JSONArray(json);

            for(int i=0;i<arr.length();i++){
                JSONObject obj = arr.getJSONObject(i);

                fils.add(obj.getString("code") + " : " + obj.getString("nom") + " - " + obj.getInt("duree"));
            }

            ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1,fils);
            ls.setAdapter(ad);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String LoadJsonFromRaw(int resId) {
        String res = "";
        try {
            int taille = 0;
            InputStream input = getResources().openRawResource(resId);
            taille = input.available();
            byte[] data = new byte[taille];
            input.read(data);
            input.close();
            res = new String(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}