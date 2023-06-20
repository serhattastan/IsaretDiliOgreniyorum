package com.cloffygames.isaretdiliogreniyorum.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cloffygames.isaretdiliogreniyorum.adapter.WordsAdapter;
import com.cloffygames.isaretdiliogreniyorum.databinding.ActivitySozlukBinding;
import com.cloffygames.isaretdiliogreniyorum.model.Words;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class SozlukActivity extends AppCompatActivity {

    private ActivitySozlukBinding binding;

    private FirebaseFirestore firebaseFirestore;

    ArrayList<Words> wordsArrayList;
    WordsAdapter wordsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySozlukBinding.inflate(getLayoutInflater());     //BİNDİNG
        View view = binding.getRoot();
        setContentView(view);
//
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        wordsArrayList = new ArrayList<>();

        binding.recyclview.setLayoutManager(new LinearLayoutManager(this));

        firebaseFirestore = FirebaseFirestore.getInstance();

        getData();

        wordsAdapter = new WordsAdapter(wordsArrayList);
        binding.recyclview.setAdapter(wordsAdapter);



    }

    public void Exit_Button(View view) {
        Intent intent = new Intent(SozlukActivity.this, MainActivity.class);
        startActivity(intent);

    }

    private void getData(){

        firebaseFirestore.collection("words").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Toast.makeText(SozlukActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
                if (value != null){

                    for (DocumentSnapshot snapshot: value.getDocuments()){
                        Map<String, Object> data = snapshot.getData();

                        String descriptions = (String) data.get("descriptions");
                        String name = (String) data.get("name");
                        String image_path =(String) data.get("image_path");
                        String word_type =(String) data.get("word_type");


                        Words words = new Words(name,descriptions,word_type,image_path);
                        wordsArrayList.add(words);

                    }

                    wordsAdapter.notifyDataSetChanged();

                }
            }
        });

    }
}