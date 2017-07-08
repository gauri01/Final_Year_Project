package com.gauri.alertsystem;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;
import java.util.Locale;

public class AddPlaceActivity extends AppCompatActivity {

    TextView placeNameText,placeAddressText,placeLatLngText;
    WebView attributionText;
    Button getAlarmButton;
    private  static  final int MY_PERMISSION=101;
    private  static  final int PLACE_PICKER_REQUEST = 1;

    Place place;
    Button btnTask,btnSpeech;
    EditText etWork;
    TextToSpeech tts;
    String talk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        requestPermission();
        initialise();
    }

    private void requestPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION);
            }
        }
    }

    private void initialise() {

        placeNameText = (TextView) findViewById(R.id.tvPlaceName);
        placeAddressText = (TextView) findViewById(R.id.tvPlaceAddress);
        placeLatLngText = (TextView) findViewById(R.id.tvPlaceLatLng);
        attributionText = (WebView) findViewById(R.id.wvAttribution);
        getAlarmButton = (Button) findViewById(R.id.btnPlace);

        getAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder =  new PlacePicker.IntentBuilder();
                try {
                    Intent intent = builder.build(AddPlaceActivity.this);
                    startActivityForResult(intent,PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        btnTask = (Button) findViewById(R.id.btnTask);
        etWork = (EditText) findViewById(R.id.etWork);
        btnTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerForContextMenu(view);
                openContextMenu(view);

            }
        });
        btnSpeech = (Button) findViewById(R.id.btnAlarm);
        btnSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                talk = etWork.getText().toString();
                tts.speak(talk,TextToSpeech.QUEUE_FLUSH,null);

            }
        });

        tts = new TextToSpeech(AddPlaceActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.US);
                }

            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_PERMISSION:
                if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(),"Permission Required",Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PLACE_PICKER_REQUEST)
        {
            if(resultCode == RESULT_OK)
            {
                place = PlacePicker.getPlace(AddPlaceActivity.this,data);
                placeNameText.setText(place.getName());
                placeAddressText.setText(place.getAddress());
                placeLatLngText.setText(place.getLatLng().toString());
                if(place.getAttributions() == null)
                {
                    attributionText.loadData("No Attribution","text/html; charset=utf-8","UTF-8");
                }else {
                    attributionText.loadData(place.getAttributions().toString(),"text/html; charset=utf-8","UTF-8");
                }

            }
        }

    }
    @Override
    protected void onPause() {
        if(tts !=null) {
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_work,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.atm:
                etWork.setText(item.getTitle());
                item.setChecked(true);
                return true;

            case R.id.bank:
                etWork.setText(item.getTitle());
                item.setChecked(true);
                return true;

            case R.id.lunch:
                etWork.setText(item.getTitle());
                item.setChecked(true);
                return true;

            case R.id.buying:
                etWork.setText(item.getTitle());
                return true;

            case R.id.clg:
                etWork.setText(item.getTitle());
                return true;
            case R.id.dinner:
                etWork.setText(item.getTitle());
                return true;
            case R.id.drop:
                etWork.setText(item.getTitle());
                return true;
            case R.id.eat:
                etWork.setText(item.getTitle());
                return true;
            case R.id.exchange:
                etWork.setText(item.getTitle());
                return true;
            case R.id.emergency:
                etWork.setText(item.getTitle());
                return true;
            case R.id.food_parcel:
                etWork.setText(item.getTitle());
                return true;
            case R.id.gym:
                etWork.setText(item.getTitle());
                return true;
            case R.id.hospital:
                etWork.setText(item.getTitle());
                return true;
            case R.id.hostel:
                etWork.setText(item.getTitle());
                return true;
            case R.id.ice_creame:
                etWork.setText(item.getTitle());
                return true;
            case R.id.library:
                etWork.setText(item.getTitle());
                return true;
            case R.id.meeting:
                etWork.setText(item.getTitle());
                return true;
            case R.id.market:
                etWork.setText(item.getTitle());
                return true;
            case R.id.movie:
                etWork.setText(item.getTitle());
                return true;
            case R.id.multiplex:
                etWork.setText(item.getTitle());
                return true;
            case R.id.nursery:
                etWork.setText(item.getTitle());
                return true;
            case R.id.office:
                etWork.setText(item.getTitle());
                return true;
            case R.id.parcel:
                etWork.setText(item.getTitle());
                return true;
            case R.id.temple:
                etWork.setText(item.getTitle());
                return true;
            case R.id.servicing:
                etWork.setText(item.getTitle());
                return true;

            case R.id.shopping:
                etWork.setText(item.getTitle());
                item.setChecked(true);
                return true;
            case R.id.breakfast:
                etWork.setText(item.getTitle());
                item.setChecked(true);
                return true;
            case R.id.fill_petrol:
                etWork.setText(item.getTitle());
                item.setChecked(true);
                return true;
        }
        return super.onContextItemSelected(item);

    }


}
