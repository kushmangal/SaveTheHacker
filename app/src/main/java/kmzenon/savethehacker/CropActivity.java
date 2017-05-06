package kmzenon.savethehacker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Naray on 06-05-2017.
 */
public class CropActivity  extends AppCompatActivity implements View.OnClickListener {

    private ImageView wheatimg, riceimg, dalimg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);

        wheatimg = (ImageView) findViewById(R.id.wheat_img);
        riceimg = (ImageView) findViewById(R.id.rice_img);
        dalimg = (ImageView) findViewById(R.id.dal_img);

        wheatimg.setOnClickListener(this);
        riceimg.setOnClickListener(this);
        dalimg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==wheatimg){
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.putExtra("crop", "Wheat");
            startActivity(i);
        }

        if(view==riceimg){
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.putExtra("crop", "Rice");
            startActivity(i);
        }
        if(view==dalimg){
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.putExtra("crop", "Dal");
            startActivity(i);
        }
    }
}
