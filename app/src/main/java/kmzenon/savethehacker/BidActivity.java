package kmzenon.savethehacker;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Naray on 06-05-2017.
 */
public class BidActivity extends AppCompatActivity {

    private TextView nametv, msptv, remprodtv, totprodtv, croptv;
    private ListView bidlistview;
    private FloatingActionButton fab;
    private String crop, agency, msp, total, remaining, id,vid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid);
        nametv = (TextView) findViewById(R.id.bid_agency_name);
        croptv = (TextView) findViewById(R.id.bid_crop);
        msptv = (TextView) findViewById(R.id.bid_msp);
        bidlistview = (ListView) findViewById(R.id.bidlist);

        Intent intent = getIntent();
        crop = intent.getStringExtra("crop");
        agency = intent.getStringExtra("agency");
        msp = intent.getStringExtra("msp");
        remaining = intent.getStringExtra("remaining");
        total = intent.getStringExtra("total");
        id = intent.getStringExtra("id");
        vid=intent.getStringExtra("vid");

        remprodtv = (TextView) findViewById(R.id.bid_remainingprod);
        totprodtv = (TextView) findViewById(R.id.bid_totalprod);
        fab = (FloatingActionButton) findViewById(R.id.makebidfab);

        nametv.append(agency);
        msptv.append("₹"+msp);
        croptv.append(crop);
        remprodtv.append(remaining+" kgs");
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
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://savethe.pe.hu/postbid.php", new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//                                        Toast.makeText(getApplicationContext(),cid,Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //   Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("vid",vid);
                                params.put("cid",id);
                                params.put("crop",crop);
                                params.put("price",price.getText().toString());
                                params.put("quantity",quantity.getText().toString());
                                return params;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);
                        dialog.dismiss();
//                                Toast.makeText(getApplicationContext(),"User Registered",Toast.LENGTH_SHORT).show();
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
}
