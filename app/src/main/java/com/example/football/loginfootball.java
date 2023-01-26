package com.example.football;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link loginfootball#newInstance} factory method to
 * create an instance of this fragment.
 */
public class loginfootball extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText etemail;
    private EditText etpassword;
    private Button loginbtn;
    private Button signupbtn;
    private TextView forgotpass;
    private FirebaseAuth mAuth;



    public void func() {
        etemail = getView().findViewById(R.id.emaillogin);
        etpassword = getView().findViewById(R.id.passlogin);
        loginbtn = getView().findViewById(R.id.login);
        forgotpass = getView().findViewById(R.id.forgotpas);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //forgot passfrag
                forgetpass forgetpass = new forgetpass();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.framelaout, forgetpass, forgetpass.getTag()).commit();
            }
        });


        mAuth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;

                email = etemail.getText().toString();

                password = etpassword.getText().toString();


                if (email.trim().isEmpty() || password.trim().isEmpty()) {
                    Toast.makeText(getContext(), "SOMTHING FAILED ! " + "", Toast.LENGTH_SHORT).show();
                    return;
                }
//FIRE BASE CONNECT
                if (mAuth != null) {
                    mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(getContext(), "User signed in successfully.", Toast.LENGTH_SHORT).show();
                            //go to personal page
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "somthing failed", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Intent i=new Intent(getActivity(), detailsplayerActivity.class);
                    startActivity(i);
                }
            }
        });

//THE COUSTUMER HAVE TWO WAYS TO CREATE USER , THE FIRST WAY "IF THERE IS NI ACOUNT OTOMATIC GO THE CREATE USER" , "CLICK BUTTON SIGN UP"


        // NO USER (CREATE USER)
        signupbtn = getView().findViewById(R.id.signu_ploginpae);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupfragment signupfragment = new signupfragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.framelaout, signupfragment, signupfragment.getTag()).commit();
            }
        });

    }
    public loginfootball() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment loginfootball.
     */
    // TODO: Rename and change types and number of parameters
    public static loginfootball newInstance(String param1, String param2) {
        loginfootball fragment = new loginfootball();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loginfootball, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        func();
    }
}