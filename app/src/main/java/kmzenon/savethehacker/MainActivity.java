package kmzenon.savethehacker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AgencyListAdapter agencyListAdapter;
    private List<Agency> agencyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String title = intent.getStringExtra("crop");
        getSupportActionBar().setTitle(title);
        recyclerView = (RecyclerView) findViewById(R.id.agencylist);

        agencyListAdapter = new AgencyListAdapter(agencyList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(agencyListAdapter);

        prepareAgencyData();
    }

    public void getData()
    {
        //loading = ProgressDialog.show(mycontext,"Please wait...","Fetching...",false,false);

        String url = "http://savethe.pe.hu/production.php";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(mycontext);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String response) {
        String pro = "";
        String bar = "";
        String pr = "";
        String image = "";
        String rat = "";
        String id = "";
        try {
            JSONArray contacts = new JSONArray(response);
            for (int j = 0; j < contacts.length(); j++) {
                JSONObject c = contacts.getJSONObject(j);
                pro = c.getString("district");
                arrayList.add(pro);
                bar = c.getString("msp");
                arrayList1.add(bar);
                image = c.getString("crop");
                arrayList2.add(image);
                pr = c.getString("sum(crop.quantity)");
                arrayList3.add(pr);
                centerid=c.getString("cid");
                arrayList4.add(centerid);


            }
            prepare();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */


    /**
     * Adding few albums for testing
     */
    private void prepare() {
        for (int i = 0; i < arrayList.size(); i++) {

            String product = arrayList.get(i);
            String bar = arrayList1.get(i);
            String imgsrc = arrayList2.get(i);
            String price = arrayList3.get(i);
            String id= arrayList4.get(i);
            Crop a = new Crop(product, bar,"http://kmzenon.pe.hu/app/wheat.jpg",price,id,company);
            cropList.add(a);

        }

        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    private void prepareAgencyData(){

        Agency agency = new Agency("Neduvaasal", 120, "400 quintals", "50 quintals");
        agencyList.add(agency);
        agencyListAdapter.notifyDataSetChanged();

    }

}