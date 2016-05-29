package com.team.doitnow;

//import com.google.android.gcm.GCMRegistrar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team.doitnow.adapter.User;
import com.team.doitnow.business.UserRepository;
import com.team.doitnow.common.CommonSetting;
import com.team.doitnow.tabsswipe.TabSwipeMainActivity;

public class ConnexionActivity extends ActionBarActivity {
    
    private static final String SENDER_ID = "779086340353";
    private Button connecter;
    private Button inscrire;
    private UserRepository userRepository;
    EditText mail;
    EditText mdp;
    User user = null;
    int tel;
    String monMDP;
    
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

      //action bar
      //CommonSetting.actionBarSetting(this.getActionBar());
        this.getSupportActionBar().hide();

        User userInfos = CommonSetting.getUserBundle(this.getIntent().getExtras());
        if(userInfos != null){
            ((EditText)findViewById(R.id.email)).setText(userInfos.getEmail());
            ((EditText)findViewById(R.id.mdp)).setText(userInfos.getPassword());
        }

        //Connexion part
        mail = (EditText)findViewById(R.id.email);
        mdp = (EditText)findViewById(R.id.mdp);

        inscrire = (Button)findViewById(R.id.inscrire);
        inscrire.setTextColor(Color.WHITE);
        inscrire.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                inscrire.setTextColor(Color.GRAY);
                Intent intent = new Intent(ConnexionActivity.this, InscriptionActivity.class);
                intent.putExtra("email", mail.getText().toString());
                intent.putExtra("password", mdp.getText().toString());
                startActivityForResult(intent,1);
            }
        });
        
        userRepository = new UserRepository(this);
        connecter = (Button)findViewById(R.id.connecter);
        connecter.setOnClickListener(new OnClickListener(){          
            public void onClick(View view){
                String monEmail = mail.getText().toString();
                String monMDP =mdp.getText().toString();
                if(monEmail == null || monEmail.equals("") || !CommonSetting.emailValide(monEmail)){
                    Toast.makeText(getApplicationContext(), "Please, enter a correct email address !!", Toast.LENGTH_SHORT).show();
                }else if( monMDP== null || monMDP.equals("")){
                    Toast.makeText(getApplicationContext(), "Please, enter your password !!", Toast.LENGTH_SHORT).show();
                }else if( CommonSetting.emailValide(monEmail)){
                    userRepository.Open();
                    user = userRepository.GetByMainAttribute(monEmail);
                    //userRepository.Update(user);
                    userRepository.Close();

                    if(user != null && user.getPassword()!= null && user.getPassword().equals(monMDP)){
                        Intent intent = new Intent(ConnexionActivity.this, TabSwipeMainActivity.class);
                        intent = CommonSetting.setUserBundle(user, intent);
                        startActivityForResult(intent,1);
                    }
                    else{; 
                        Toast.makeText(getApplicationContext(), "Wrong Email address or Password  !!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{ 
                    Toast.makeText(getApplicationContext(), "Wrong Email address !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
  
        }
}