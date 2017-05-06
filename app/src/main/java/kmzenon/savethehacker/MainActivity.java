package kmzenon.savethehacker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

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
        agencyListAdapter = new AgencyListAdapter();
        prepareAgencyData();
    }

    private void prepareAgencyData(){
        Agency agency = new Agency("Neduvaasal", 120, "400 quintals", "50 quintals");
        agencyList.add(agency);
        agencyListAdapter.notifyDataSetChanged();
    }

}
