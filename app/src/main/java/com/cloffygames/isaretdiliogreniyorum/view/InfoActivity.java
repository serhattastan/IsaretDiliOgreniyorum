package com.cloffygames.isaretdiliogreniyorum.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.cloffygames.isaretdiliogreniyorum.databinding.ActivityInfoBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity {
    private ActivityInfoBinding binding;
    FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    Uri imageData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoBinding.inflate(getLayoutInflater());     //BİNDİNG
        View view = binding.getRoot();
        setContentView(view);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        firebaseStorage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = firebaseStorage.getReference();



        Intent intent = getIntent();
        String wordname = intent.getStringExtra("wordname");
        String imagepathh = intent.getStringExtra("imagepathh");
        String descriptionss = intent.getStringExtra("descriptionss");

        System.out.println(wordname);
        System.out.println(imagepathh);
        System.out.println(descriptionss);

        binding.textView2.setText(wordname.toUpperCase());
        binding.textView.setText(descriptionss);
        Picasso.get().load(imagepathh).into(binding.imageView);
        System.out.println(imagepathh);
    }




}