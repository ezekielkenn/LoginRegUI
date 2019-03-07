package com.examplsqlitehelper.vtguide;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

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

import java.util.List;


public class LoginActivity extends AppCompatActivity{

    Button bt_add, bt_show;
    FirebaseFirestore db;
    TextView tv_details;
    List<Users> Users_LIST;
    TextView holliday;
    TextView create;
    TextView  getstarted;
    TextInputLayout username;
    TextInputLayout password;


    public static final String COLLECTION_NAME_KEY = "USERS";
    public static final String USERNAME_KEY = "username";
    public static final String PASSWORD_KEY = "password";
//    public static final String NUMBER_KEY = "phoneNo:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
//        Toolbar toolbar = findViewById( R.id.toolbar );
//        setSupportActionBar( toolbar );
//        getSupportActionBar().hide();
//        FloatingActionButton fab =  findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        DrawerLayout drawer = findViewById( R.id.drawer_layout );
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
//        drawer.setDrawerListener( toggle );
//        toggle.syncState();
//
//        NavigationView navigationView = findViewById( R.id.nav_view );
//        navigationView.setNavigationItemSelectedListener(this);

        db = FirebaseFirestore.getInstance();

        create = findViewById( R.id.LinkRegister );
        getstarted = findViewById( R.id.btn_signup );
//        holliday =findViewById(R.id.holliday);

        username = findViewById( R.id.textInputLayoutEmail );
        password = findViewById( R.id.textInputLayoutPassword );


        create.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent( LoginActivity.this, RegisterActivity.class );
                startActivity( it );
            }
        } );

        // Access a Cloud Firestore instance from your Activity

        getstarted.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bt_add.getText().toString().equals( "" ) && !bt_add.getText().toString().equals( "" )) {

                    DocumentReference docRef = db.collection( COLLECTION_NAME_KEY ).document( bt_add.getText().toString() );
                    docRef.get().addOnSuccessListener( new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            if (documentSnapshot.exists()) {


                                Users user = documentSnapshot.toObject( Users.class );

                                if (user.getPassword().equals( bt_add.getText().toString() )) {

                                    Toast.makeText( getApplicationContext(), "welcome", Toast.LENGTH_SHORT ).show();
                                    Intent it = new Intent( LoginActivity.this, NavigationActivity.class );
                                    startActivity( it );
                                } else {
                                    Toast.makeText( LoginActivity.this, "Passsword Mismatching", Toast.LENGTH_SHORT ).show();
                                }


                            } else {

                                Toast.makeText( getApplicationContext(), "Check your Username ", Toast.LENGTH_SHORT ).show();
                            }

                        }
                    } );
                } else {


                    Toast.makeText( LoginActivity.this, "Username or Password Cannot be Empty", Toast.LENGTH_SHORT ).show();
                }


            }
        } );
    }

    public void buttonClick(View view) {
    }
}
