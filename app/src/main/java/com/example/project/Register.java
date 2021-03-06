package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity implements View.OnClickListener{


    Button register_btn;
    TextView login_txtView;
    EditText editTextName, editTextEmail, editTextPassword, editTextPhone;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_btn=findViewById(R.id.Register_btn);
        login_txtView=findViewById(R.id.login_textview);

        editTextEmail=findViewById(R.id.edit_text_email);
        editTextName=findViewById(R.id.edit_text_name);
        editTextPassword=findViewById(R.id.edit_text_password);
        editTextPhone=findViewById(R.id.edit_text_phone);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        mAuth=FirebaseAuth.getInstance();

        register_btn.setOnClickListener(this);
        login_txtView.setOnClickListener(this);

    }


    @Override
    protected void onStart() {
        super.onStart();
        //already login
        if (mAuth.getCurrentUser()!=null)
        {
            //handle the already login user
        }
    }

    private void registerUser(){
        final String name=editTextName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        final String phone = editTextPhone.getText().toString().trim();

        if (name.isEmpty()) {
            editTextName.setError(getString(R.string.input_error_name));
            editTextName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.input_error_email));
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.input_error_email_invalid));
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.input_error_password));
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError(getString(R.string.input_error_password_length));
            editTextPassword.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editTextPhone.setError(getString(R.string.input_error_phone));
            editTextPhone.requestFocus();
            return;
        }

        if (phone.length() != 11) {
            editTextPhone.setError(getString(R.string.input_error_phone_invalid));
            editTextPhone.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

// if register successful or not
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            //store the additional fields to firebasedatabase

                            User user = new User(
                                    name,
                                    email,
                                    phone
                            );
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    Intent intent;
                                    if (task.isSuccessful()){
                                        Toast.makeText(Register.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();

                                        intent=new Intent(Register.this,MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    }
                                    else {
                                        if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                            Toast.makeText(Register.this, getString(R.string.registration_Fail), Toast.LENGTH_LONG).show();
                                        }}
                                }
                            });
                        }
                        else{
                            Toast.makeText(Register.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }




    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Register_btn:
                registerUser();
                break;

            case R.id.login_textview:
                finish();
                startActivity(new Intent(this,login.class));
                break;
        }
    }
}