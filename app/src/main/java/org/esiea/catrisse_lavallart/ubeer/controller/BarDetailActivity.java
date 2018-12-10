package org.esiea.catrisse_lavallart.ubeer.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.esiea.catrisse_lavallart.ubeer.R;
import org.esiea.catrisse_lavallart.ubeer.data.Utils;
import org.esiea.catrisse_lavallart.ubeer.model.Bar;


public class BarDetailActivity extends AppCompatActivity {
    Bar selectedBar;
    TextView title;
    TextView address;
    TextView isOpen;
    TextView rank;
    Context context;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_detail);

        context = getApplicationContext();

        title=(TextView)findViewById(R.id.title);
        address=(TextView)findViewById(R.id.address);
        isOpen=(TextView)findViewById(R.id.isOpen);
        rank=(TextView)findViewById(R.id.rank);
        imageView=(ImageView) findViewById(R.id.imageView);

        selectedBar = (Bar)getIntent().getSerializableExtra("selectedBar");

        title.setText(selectedBar.getName());
        address.setText(selectedBar.getAddress());
        rank.setText(selectedBar.getRank());

        String openString;
        if(selectedBar.getIsOpen().equals("true")){
            openString=getString(R.string.open);
        }else if(selectedBar.getIsOpen().equals("false")){
            openString=getString(R.string.closed);
        }else{
            openString="?";
        }
        isOpen.setText(openString);
        Picasso.with(context)
                .load(selectedBar.getUrl())
                .resize(400,400)
                .into(imageView);
        //imageView.setBackground(Utils.LoadImageFromWebOperations(selectedBar.getUrl()));


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.B:
                startActivity(new Intent(context, SetupActivity.class));
                finish();
                break;

        }
        return true;
    }
}
