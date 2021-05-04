package iut.lp.dna.chat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.security.Permission;
import java.util.Collections;
import java.util.List;

import iut.lp.dna.chat.model.Message;
import iut.lp.dna.chat.model.Position;
import iut.lp.dna.chat.model.User;
import iut.lp.dna.chat.service.ChatService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatActivity extends AppCompatActivity implements LocationListener {
    public static String EXTRA_USER_ID ;
    private Handler handler = new Handler();
    private ChatService chatService;
    private MessageAdaptater messageAdaptater;
    public static final int PERMISSION_ID = 1;
    MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

         messageAdaptater = new MessageAdaptater(this);
        ((ListView) findViewById(R.id.messages)).setAdapter(messageAdaptater);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://home.kahriboo.com:3104/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        chatService = retrofit.create(ChatService.class);
        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = findViewById(R.id.text);

                Message msg = new Message();
                msg.setUser(getIntent().getStringExtra(EXTRA_USER_ID));
                msg.setText(input.getText().toString());
                chatService.sendMessage(msg).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(ChatActivity.this, "Success",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ChatActivity.this, "Une erreur est survenue",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(ChatActivity.this, "Une erreur est survenue",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        startTraking();

        mapView = findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(mapboxMap -> mapboxMap.setStyle(Style.MAPBOX_STREETS, style -> {
            mapboxMap.addMarker(new MarkerOptions().position(new LatLng(2.29458,2.29458)).title("tour eiffel"));
        }));
    }

    @Override
    protected void onResume(){
        super.onResume();
        fetchMessagesInOneSecond();
    }

    private void fetchMessagesInOneSecond(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fetchMessages();
                fetchMessagesInOneSecond();
            }
        },1000);
    }

    private void fetchMessages(){
        chatService.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    messageAdaptater.setUsers(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

        chatService.getMessages(0).enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful()){
                    List<Message> messages = response.body();
                    Collections.reverse(messages);
                    messageAdaptater.setMessage(messages);


                }else {
                    Toast.makeText(ChatActivity.this, "Une erreur est survenue",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(ChatActivity.this, "Une erreur est survenue",Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected  void onPause(){
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Position position = new Position();
        position.setLatitude(location.getLatitude());
        position.setLongitude(location.getLongitude());
        chatService.sendPosition(getIntent().getStringExtra(EXTRA_USER_ID),position).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    private void startTraking(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationManager locationManager =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,100,1,this);
            onLocationChanged(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
        }else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_ID
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResult){
        super.onRequestPermissionsResult(requestCode, permission, grantResult);
        if (requestCode == PERMISSION_ID){
            if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED){
                startTraking();
            }
        }
    }
}
