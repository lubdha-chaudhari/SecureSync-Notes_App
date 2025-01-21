package com.example.sample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class createnote extends AppCompatActivity {


    EditText mcreatetitleofnote,mcreatecontentofnote;
    FloatingActionButton msavenote;
    FirebaseAuth firebaseauth;
    FirebaseUser firebaseuser;
    FirebaseFirestore firebasefirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createnote);

        mcreatecontentofnote=findViewById(R.id.createcontentofnote);
        msavenote=findViewById(R.id.savenote);
        mcreatetitleofnote=findViewById(R.id.createtitleofnote);

        Toolbar toolbar=findViewById(R.id.toolbarofcreatenote);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseauth=FirebaseAuth.getInstance();
        firebasefirestore=FirebaseFirestore.getInstance();
        firebaseuser=FirebaseAuth.getInstance().getCurrentUser();


        msavenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=mcreatetitleofnote.getText().toString();
                String content=mcreatecontentofnote.getText().toString();
                if(title.isEmpty()||content.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Both fields are Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    DocumentReference documentreference=firebasefirestore.collection("notes").document(firebaseuser.getUid()).collection("myNotes").document();
                    Map<String ,Object> note=new HashMap<>();
                    note.put("title",title);
                    note.put("content",content);

                    documentreference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"Note created successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(createnote.this, notesActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Failed to create note",Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(createnote.this, notesActivity.class));
                        }
                    });

                }
            }
        });











    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}