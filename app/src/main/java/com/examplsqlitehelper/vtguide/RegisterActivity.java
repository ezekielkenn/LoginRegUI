
package com.examplsqlitehelper.vtguide;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import com.expose.vinu.custom_font.MyEditText;
//import com.expose.vinu.custom_font.MyTextView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class RegisterActivity extends AppCompatActivity {
    TextView holliday;
    Button signin,getstarted;
    TextInputEditText email,Password,ConfirmPassword;

    FirebaseFirestore db;
    public static  final  String COLLECTION_NAME_KEY = "USERS";
    public static  final  String USERNAME_KEY = "username";
    public static  final  String PASSWORD_KEY = "password";
//    public static  final  String NUMBER_KEY = "phoneNo:";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signin =  findViewById(R.id.LinkRegister);
        getstarted = findViewById(R.id.ButtonLogin);


        email =  findViewById(R.id.textInputEditTextEmail);
        Password =  findViewById(R.id.textInputEditTextPassword);
        ConfirmPassword =  findViewById(R.id.confirmPassword);
//
//        holliday = findViewById(R.id.holliday);



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });

        db = FirebaseFirestore.getInstance();


        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (! email.getText().toString().equals("")
                        && ! Password.getText().toString().equals("")
                        && ! ConfirmPassword.getText().toString().equals(""))

                {

                    if (Password.getText().toString().equals(ConfirmPassword.getText().toString()) )
                    {
                        CollectionReference cities = db.collection(COLLECTION_NAME_KEY);

                        final Users users = new Users();

                        users.setName(email.getText().toString());
                        users.setPassword(Password.getText().toString());
                        db.collection(COLLECTION_NAME_KEY).document(email.getText().toString()).set(users);



                        DocumentReference docRef = db.collection(COLLECTION_NAME_KEY).document();

                        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {



                                if (documentSnapshot.exists())
                                {

                                   /* Users users = documentSnapshot.toObject(Users.class);

                                    if (users.getName().equals(email.getText().toString()))
                                    {
                                        Toast.makeText(MainActivity1.this, "All ready registered", Toast.LENGTH_SHORT).show();
                                    }
*/
                                    Toast.makeText(RegisterActivity.this, "Already registered", Toast.LENGTH_SHORT).show();

                                }

                                else
                                {
                                    users.setName(email.getText().toString());
                                    users.setPassword(Password.getText().toString());
                                    db.collection(COLLECTION_NAME_KEY).document(email.getText().toString()).set(users);

                                    Toast.makeText(RegisterActivity.this, "registration successful", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });

                    }

                    else
                    {
                        Toast.makeText(getApplicationContext(), "Password Mismatch ", Toast.LENGTH_SHORT).show();
                    }
                }


                else
                {
                    Toast.makeText(getApplicationContext(), "Make All field Filled", Toast.LENGTH_SHORT).show();
                }







            }
        });

    }
}