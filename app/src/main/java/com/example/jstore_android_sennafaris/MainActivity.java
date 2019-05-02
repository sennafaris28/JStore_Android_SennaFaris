package com.example.jstore_android_sennafaris;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Supplier> listSupplier = new ArrayList<>();
    private ArrayList<Item> listItem = new ArrayList<>();
    private HashMap<Supplier, ArrayList<Item>> childMapping = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listAdapter = new MainListAdapter(MainActivity.this, listSupplier, childMapping);
        expListView.setAdapter(listAdapter);
    }

    protected void refreshList() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse (String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    for (int i = 0; i < jsonResponse.length(); i++) {
                        JSONObject item = jsonResponse.getJSONObject(i);
                        int itemId = item.getInt("id");
                        String itemName = item.getString("name");
                        int itemPrice = item.getInt("price");
                        String itemCategory = item.getString("category");
                        String itemStatus = item.getString("status");

                        JSONObject supplier = item.getJSONObject("supplier");
                        int supplierId = supplier.getInt("id");
                        String supplierName = supplier.getString("name");
                        String supplierEmail = supplier.getString("category");
                        String supplierPhoneNumber = supplier.getString("phoneNumber");

                        JSONObject location = supplier.getJSONObject("location");
                        String locationProvince = supplier.getString("province");
                        String locationDescription = supplier.getString("description");
                        String locationCity = supplier.getString("city");

                        Location location1 = new Location (locationProvince, locationDescription, locationCity);
                        Supplier supplier1 = new Supplier (supplierId, supplierName, supplierEmail, supplierPhoneNumber, location1);
                        Item item1 = new Item (itemId, itemName, itemPrice, itemCategory, itemStatus, supplier1)

                        listSupplier.add(supplier1);
                        listItem.add(item1);

                        childMapping.put(listSupplier.get(i), listItem);


                    }
                } catch(JSONException e) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
                    builder1.setMessage("Wrong Request").create().show();
                }
            }
        };
    }
}
