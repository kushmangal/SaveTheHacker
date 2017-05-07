package kmzenon.savethehacker;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.spec.RC2ParameterSpec;

/**
 * Created by Naray on 06-05-2017.
 */
public class BidActivity extends AppCompatActivity {

    private TextView nametv, msptv, remprodtv, totprodtv, croptv;
    private FloatingActionButton fab;
    private String crop, agency, msp, total, remaining, id,vid;
    private RecyclerView recyclerView;
    private BidListAdapter bidListAdapter;
    private List<Bid> bidList = new ArrayList<>();
    private ArrayList<String> quantities;
    private ArrayList<String> prices;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid);
        nametv = (TextView) findViewById(R.id.bid_agency_name);
        croptv = (TextView) findViewById(R.id.bid_crop);
        msptv = (TextView) findViewById(R.id.bid_msp);
        getSupportActionBar().setTitle("Bidding");
        recyclerView = (RecyclerView) findViewById(R.id.bidlist);
        quantities = new ArrayList<>();
        prices = new ArrayList<>();

        Intent intent = getIntent();
        crop = intent.getStringExtra("crop");
        agency = intent.getStringExtra("agency");
        msp = intent.getStringExtra("msp");
        total = intent.getStringExtra("total");
        id = intent.getStringExtra("id");
        vid=intent.getStringExtra("vid");

        bidListAdapter = new BidListAdapter(bidList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(bidListAdapter);
        getData();

        totprodtv = (TextView) findViewById(R.id.bid_totalprod);
        fab = (FloatingActionButton) findViewById(R.id.makebidfab);

        nametv.append(agency);
        msptv.append("₹"+msp);
        croptv.append(crop);
        totprodtv.append(total+" kgs");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(BidActivity.this);
                dialog.setContentView(R.layout.bid_view);
                final EditText price = (EditText)dialog.findViewById(R.id.bid_price);
                final EditText quantity = (EditText)dialog.findViewById(R.id.bid_quantity);
                Button onProceed = (Button) dialog.findViewById(R.id.proceed);
                Button onCancel = (Button) dialog.findViewById(R.id.cancel);
                onProceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(MakeBiddingActivity.this,price.getText().toString(),Toast.LENGTH_SHORT).show();
                        String url = "http://savethe.pe.hu/postbid.php?aid="+id+"&crop="+crop+"&vid="+vid+"&price="+price.getText().toString()+"&quantity="+quantity.getText().toString();

                        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//                                        Toast.makeText(getApplicationContext(),cid,Toast.LENGTH_SHORT).show();
                            }
                        },new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //Toast.makeText(mycontext,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                            }
                        });
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);
                        dialog.dismiss();
                           Toast.makeText(getApplicationContext(),"Bid Registered",Toast.LENGTH_SHORT).show();
                    }
                });

                onCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
    public void getData(){
        String url = "http://savethe.pe.hu/centerbidding.php?aid="+id+"&crop="+crop;
        // Toast.makeText(mycontext,"getData",Toast.LENGTH_LONG).show();
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // loading.dismiss();
                // Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(mycontext,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(BidActivity.this);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String response) {
        String price;
        String quantity;
        // Toast.makeText(mycontext,response,Toast.LENGTH_SHORT).show();
        try {
            JSONArray contacts = new JSONArray(response);
            for (int j = 0; j < contacts.length(); j++) {
                JSONObject c = contacts.getJSONObject(j);
                price = c.getString("amount");
                prices.add(price);
                quantity = c.getString("quantity");
                quantities.add(quantity);

            }
            prepare();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void prepare() {
        for (int i = 0; i < prices.size(); i++) {

            String price = prices.get(i);
            String quantity = quantities.get(i);
            Bid a = new Bid(price, quantity);
            bidList.add(a);

        }

        bidListAdapter.notifyDataSetChanged();
    }

}
