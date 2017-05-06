package kmzenon.savethehacker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

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
    private ArrayList<String> name;
    private ArrayList<Integer> msp;
    private ArrayList<Integer> total;
    private ArrayList<Integer> remaining;
    private ArrayList<Integer> id;
    String c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String crop = intent.getStringExtra("crop");
        c = crop;
        getSupportActionBar().setTitle(crop);
        recyclerView = (RecyclerView) findViewById(R.id.agencylist);

        name = new ArrayList<>();
        msp = new ArrayList<>();
        total = new ArrayList<>();
        remaining = new ArrayList<>();
        id = new ArrayList<>();

        agencyListAdapter = new AgencyListAdapter(agencyList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(agencyListAdapter);
        getData();

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                final View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    final int i = rv.getChildAdapterPosition(child);
                    //name, msp, total, remaining, id, c
                    String nametemp = name.get(i);
                    String msptemp = msp.get(i).toString();
                    String totaltemp = total.get(i).toString();
                    String remainingtemp = remaining.get(i).toString();
                    String idtemp = id.get(i).toString();
                    Intent intent = new Intent(MainActivity.this, BidActivity.class);
                    intent.putExtra("crop", c);
                    intent.putExtra("agency", nametemp);
                    intent.putExtra("msp", msptemp);
                    intent.putExtra("total", totaltemp);
                    intent.putExtra("remaining", remainingtemp);
                    intent.putExtra("id", idtemp);

                    startActivity(intent);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    public void getData() {
        //loading = ProgressDialog.show(mycontext,"Please wait...","Fetching...",false,false);

        String url = "http://savethe.pe.hu/production.php?crop=" + c;
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

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        String jname = "";
        int jmsp = 0;
        int jtot = 0;
        int jrem = 0;
        int agen = 0;
        try {
            JSONArray contacts = new JSONArray(response);
            for (int j = 0; j < contacts.length(); j++) {
                JSONObject c = contacts.getJSONObject(j);
                jname = c.getString("name");
                name.add(jname);
                jmsp = Integer.parseInt(c.getString("msp"));
                msp.add(jmsp);
                jtot = Integer.parseInt(c.getString("sum(crop.quantity)"));
                total.add(jtot);
                jrem = jtot - Integer.parseInt(c.getString("sum(bid.quantity)"));
                remaining.add(jrem);
                agen = Integer.parseInt(c.getString("id"));
                id.add(agen);
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
        for (int i = 0; i < name.size(); i++) {

            String vname = name.get(i);
            int vmsp = msp.get(i);
            int vtot = total.get(i);
            int vrem = remaining.get(i);
            int vgen = id.get(i);
            Agency a = new Agency(vname, vmsp, vtot, vrem, vgen);
            agencyList.add(a);
        }
        agencyListAdapter.notifyDataSetChanged();
    }


}