package com.cloffygames.isaretdiliogreniyorum.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.cloffygames.isaretdiliogreniyorum.R;
import com.cloffygames.isaretdiliogreniyorum.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());     //BİNDİNG
        View view = binding.getRoot();
        setContentView(view);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        mAuth = FirebaseAuth.getInstance();


    }


    public void sozluk_button_click(View view){
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        binding.sozlukButton.startAnimation(shake);
        Intent intent = new Intent(MainActivity.this,SozlukActivity.class);           //Start buttonu işlevi: MainActivity-GameActivity arasında geçişi sağlar.
        startActivity(intent);

    }
    public void eslestirme_click(View view){
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        binding.eslestirmeButton.startAnimation(shake);
        Intent intent = new Intent(MainActivity.this, MatchActivity.class);
        startActivity(intent);

    }
    public void exit_button_click(View view){
        this.finishAffinity();
    }
    public void user_panel (View view){

        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Kullanıcı Değiştirme");
        alert.setMessage("Mevcut kullanıcıdan çıkış yapılsın mı?");
        alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mAuth.signOut();

                Intent intentTomain = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intentTomain);
                finish();

            }
        });

        alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();

    }

}