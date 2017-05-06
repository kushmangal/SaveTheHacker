package kmzenon.savethehacker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Naray on 06-05-2017.
 */
public class BidActivity extends AppCompatActivity {

    private TextView nametv, msptv, remprodtv, totprodtv;
    private ListView bidlistview;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid);
        nametv = (TextView) findViewById(R.id.bid_agency_name);
        msptv = (TextView) findViewById(R.id.bid_msp);
        bidlistview = (ListView) findViewById(R.id.bidlist);

        remprodtv = (TextView) findViewById(R.id.bid_remainingprod);
        totprodtv = (TextView) findViewById(R.id.bid_totalprod);
        fab = (FloatingActionButton) findViewById(R.id.makebidfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BidActivity.this, MakeBidActivity.class));
            }
        });
    }
}
