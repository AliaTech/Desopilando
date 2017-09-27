package com.desopilando.aliatech.desopilando.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.desopilando.aliatech.desopilando.R;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private Button   botaoLogar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_gradbk);

        email = (EditText) findViewById(R.id.campo_email_login);

    }

    public void abrirCadastroUsuario(View view){

        Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
        startActivity( intent );

    }


}
