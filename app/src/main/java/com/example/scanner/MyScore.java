package com.example.scanner;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.scanner.databinding.MyScoreBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyScore extends AppCompatActivity {

    MyScoreBinding binding;
    FirebaseDatabase database;
    DatabaseReference reference;
    String uid;
    User user;
    Statistique statistique;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.my_score);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        uid = preferences.getString("uid", null);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User us = dataSnapshot.getValue(User.class);
                if (us == null) return;
                user = us;
                binding.score.setText(String.valueOf(user.getScore()));
                binding.gifts.setText(String.valueOf(user.getGifts()));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        };

        reference.child("USERS").child(uid).addListenerForSingleValueEvent(valueEventListener);

        reference.child("STATISTIQUE").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Statistique sta = dataSnapshot.getValue(Statistique.class);
                if (user == null) return;
                statistique = sta;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        binding.gift1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Integer score = user.getScore();
                        Integer gift1 = user.getGift1();
                        Integer gifts = user.getGifts();
                        if (score >= 200){
                            Integer g1 = statistique.getGift1() + 1;
                            reference.child("STATISTIQUE").child("gift1").setValue(g1);
                            score = score - 200;
                            gift1 = gift1 + 1;
                            gifts = gifts + 1;
                            reference.child("USERS").child(uid).child("score").setValue(score);
                            reference.child("USERS").child(uid).child("gift1").setValue(gift1);
                            reference.child("USERS").child(uid).child("gifts").setValue(gifts);
                            binding.score.setText(String.valueOf(score));
                            binding.gifts.setText(String.valueOf(gifts));
                        }else {
                            Toast.makeText(MyScore.this, "Le score insuffisant !", Toast.LENGTH_LONG).show();
                        }
            }
        });

        binding.gift2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                        if (user == null) return;
                        Integer score = user.getScore();
                        Integer gift2 = user.getGift2();
                        Integer gifts = user.getGifts();
                        if (score >= 150){
                            Integer g2 = statistique.getGift1() + 1;
                            reference.child("STATISTIQUE").child("gift2").setValue(g2);
                            score = score - 150;
                            gift2 = gift2 + 1;
                            gifts = gifts + 1;
                            reference.child("USERS").child(uid).child("score").setValue(score);
                            reference.child("USERS").child(uid).child("gift2").setValue(gift2);
                            reference.child("USERS").child(uid).child("gifts").setValue(gifts);
                            binding.score.setText(String.valueOf(score));
                            binding.gifts.setText(String.valueOf(gifts));
                        }else {
                            Toast.makeText(MyScore.this, "Le score insuffisant !", Toast.LENGTH_LONG).show();
                        }

            }
        });

        binding.gift3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        if (user == null) return;
                        Integer score = user.getScore();
                        Integer gift3 = user.getGift3();
                        Integer gifts = user.getGifts();
                        if (score >= 100){
                            Integer g3 = statistique.getGift1() + 1;
                            reference.child("STATISTIQUE").child("gift3").setValue(g3);
                            score = score - 100;
                            gift3 = gift3 + 1;
                            gifts = gifts + 1;
                            reference.child("USERS").child(uid).child("score").setValue(score);
                            reference.child("USERS").child(uid).child("gift3").setValue(gift3);
                            reference.child("USERS").child(uid).child("gifts").setValue(gifts);
                            binding.score.setText(String.valueOf(score));
                            binding.gifts.setText(String.valueOf(gifts));
                        }else {
                            Toast.makeText(MyScore.this, "Le score insuffisant !", Toast.LENGTH_LONG).show();
                        }
                    }
            });

    }
}
