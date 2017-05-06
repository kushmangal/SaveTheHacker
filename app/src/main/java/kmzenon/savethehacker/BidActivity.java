package kmzenon.savethehacker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Naray on 06-05-2017.
 */
public class BidActivity extends AppCompatActivity {

    private TextView nametv, msptv, remprodtv, totprodtv, croptv;
    private ListView bidlistview;
    private FloatingActionButton fab;
    private String crop, agency, msp, total, remaining, id;

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
                Intent intent1 = new Intent(BidActivity.this, MakeBidActivity.class);
                //intent1.putExtra("crop", crop);
                //intent1.putExtra("agency", agency);
                //intent1.putExtra("msp", msp);
                //intent1.putExtra("total", total);
                //intent1.putExtra("id", id);
                startActivity(intent1);
            }
        });
    }
}
