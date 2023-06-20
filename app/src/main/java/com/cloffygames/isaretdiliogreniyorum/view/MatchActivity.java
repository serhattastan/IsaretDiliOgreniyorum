package com.cloffygames.isaretdiliogreniyorum.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.cloffygames.isaretdiliogreniyorum.databinding.ActivityMacthBinding;
import com.cloffygames.isaretdiliogreniyorum.model.Words;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


public class MatchActivity extends AppCompatActivity {

    private ActivityMacthBinding binding;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<Words> wordsArrayList;
    public String true_Word;
    public int score;
    public int health;
    String buttona;
    String buttonb;
    String buttonc;
    String buttond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMacthBinding.inflate(getLayoutInflater());     //BİNDİNG
        View view = binding.getRoot();
        setContentView(view);

        firebaseFirestore = FirebaseFirestore.getInstance();
        wordsArrayList = new ArrayList<>();
        getData();

//        binding.laylay.setVisibility(View.GONE);

        health = 5;
        score = 0;




    }

    public void game_start(){

        // Rastgele bir sayı üretmek için kullanacağımız Random nesnesi
        Random random = new Random();
        // matchArrayList içinden rastgele bir index seç
        int randomIndex = random.nextInt(wordsArrayList.size());

        String image_link = wordsArrayList.get(randomIndex).image_path;
        Picasso.get().load(image_link).into(binding.image);
        true_Word = wordsArrayList.get(randomIndex).name;

        ArrayList<String> choice = new ArrayList<>();
        String choice_add = wordsArrayList.get(randomIndex).name;
        choice.add(choice_add);
        //İlk eklenen değer doğru değerdir.

        //Bu döngü içerisinde choice listesinin içerisine 4 adet daha kelime ekliyorum.
        for (int i = 0; i < 3; i++) {
            randomIndex = random.nextInt(wordsArrayList.size());
            String choice_o = wordsArrayList.get(randomIndex).name;
            choice.add(choice_o);
        }



        binding.buttonA.setText(choice.get(random.nextInt(choice.size())));

        choice.remove(binding.buttonA.getText());
        binding.buttonB.setText(choice.get(random.nextInt(choice.size())));

        choice.remove(binding.buttonB.getText());
        binding.buttonC.setText(choice.get(random.nextInt(choice.size())));

        choice.remove(binding.buttonC.getText());
        binding.buttonD.setText(choice.get(random.nextInt(choice.size())));

        choice.remove(binding.buttonD.getText());
        choice.clear();
        System.out.println(true_Word + " True Word 1");




    }

    public void buttonA_click(View view){
        buttona = (String) binding.buttonA.getText();
        test_sonuç();
        System.out.println(true_Word + " True Word");
        System.out.println(binding.buttonA.getText() +"A");


    }
    public void buttonB_click(View view){
        buttonb = (String) binding.buttonB.getText();
        test_sonuç();
        System.out.println(true_Word + " True Word");
        System.out.println(binding.buttonB.getText() +"B");


    }
    public void buttonC_click(View view){
        buttonc = (String) binding.buttonC.getText();
        test_sonuç();
        System.out.println(true_Word + " True Word");
        System.out.println(binding.buttonC.getText() +"C");


    }
    public void buttonD_click(View view){
        buttond = (String) binding.buttonD.getText();
        test_sonuç();
        System.out.println(true_Word + " True Word");
        System.out.println(binding.buttonD.getText() +"D");


    }


    public void test_sonuç (){

        if (buttona == true_Word){
            score ++;
            binding.scoreText.setText("Skor: "+score);

            game_start();



        }else if (buttonb == true_Word){

            score ++;
            binding.scoreText.setText("Skor: "+score);

            game_start();



        }else if (buttonc == true_Word){

            score ++;
            binding.scoreText.setText("Skor: "+score);

            game_start();


        }else if (buttond == true_Word){

            score ++;
            binding.scoreText.setText("Skor: "+score);

            game_start();


        }else {

            health --;
            binding.healthText.setText("Can: "+health);
            control_health();
            game_start();

        }

    }


    private void getData(){
        firebaseFirestore.collection("words").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot snapshot : task.getResult()) {
                        Map<String, Object> data = snapshot.getData();

                        String descriptions = (String) data.get("descriptions");
                        String name = (String) data.get("name");
                        String image_path =(String) data.get("image_path");
                        String word_type =(String) data.get("word_type");


                        Words words = new Words(name,descriptions,word_type,image_path);
                        wordsArrayList.add(words);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Veriler okunurken hata oluştu", Toast.LENGTH_SHORT).show();
                }

                game_start();
            }
        });
    }


    public void control_health(){
        if (health < 0 || health == 0){

            AlertDialog.Builder alert = new AlertDialog.Builder(MatchActivity.this);
            alert.setTitle("Restart?");
            alert.setMessage("Restart Game?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //restart

                    health = 5;
                    binding.healthText.setText("Can; "+health);
                    score = 0;
                    binding.scoreText.setText("Skor; "+ score );
                    game_start();

                }
            });

            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MatchActivity.this, "Oyun Bitti! Skorunuz; "+score, Toast.LENGTH_SHORT).show();
                    finish();

                }
            });
            alert.show();

        }

    }

}

