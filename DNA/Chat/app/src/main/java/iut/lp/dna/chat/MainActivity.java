package iut.lp.dna.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import iut.lp.dna.chat.model.User;
import iut.lp.dna.chat.service.ChatService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://home.kahriboo.com:3104/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        chatService = retrofit.create(ChatService.class);

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.nameInput);
                login(editText.getText().toString());
            }
        });
    }

    private void login(String name){
        if (name == null || name.length() == 0 || !name.matches("[A-Za-z0-9]+")){
            new AlertDialog.Builder(this)
                    .setTitle("Erreur")
                    .setMessage("Le nom est obligatoire et dois etre en alphanumérique")
                    .setCancelable(true)
                    .create().show();
            return;
        }
        chatService.login(name).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                    intent.putExtra(ChatActivity.EXTRA_USER_ID, response.body().getId());
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(MainActivity.this, "Une erreur est survenue",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Une erreur est survenue",Toast.LENGTH_SHORT).show();
            }
        });
    }
}