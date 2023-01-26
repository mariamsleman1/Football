package com.example.football;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class detailsplayerActivity extends AppCompatActivity {
    private EditText name;
    private EditText Email;
    private EditText birthday;
    private EditText markaz;
    private EditText id;
    private Button finish;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsplayer);
        String etname,etemail,etbirthday,etmarkaz,etid;
        connect();
        etname=name.getText().toString();
        etemail=Email.getText().toString();
        etbirthday=birthday.getText().toString();
        etmarkaz=markaz.getText().toString();
        etid=id.getText().toString();
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtofire(etname,etemail,etbirthday,etmarkaz,etid);
                Toast.makeText(detailsplayerActivity.this, "saved", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addtofire(String etname, String etemail, String etbirthday, String etmarkaz, String etid) {
        Map<String, Object> details = new HashMap<>();
        details.put("name",etname );
        details.put("email ", etemail);
        details.put("birthday", etbirthday);
        details.put("markaz", etmarkaz);
        details.put("id", etid);

        db.collection("details").document("LA")
                .set(details)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

    }


    private void connect() {
        name=findViewById(R.id.namedet);
        Email=findViewById(R.id.EMAILdet);
        birthday=findViewById(R.id.birthday);
        markaz=findViewById(R.id.markaz);
        id=findViewById(R.id.id);
        finish=findViewById(R.id.save);
    }


}