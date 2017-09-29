package com.desopilando.aliatech.desopilando.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.desopilando.aliatech.desopilando.R;
import com.desopilando.aliatech.desopilando.activity.config.ConfiguracaoFirebase;
import com.desopilando.aliatech.desopilando.activity.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private Button   botaoLogar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_gradbk);

        email = (EditText) findViewById(R.id.campo_email_login);
        senha = (EditText) findViewById(R.id.campo_senha_login);
        botaoLogar = (Button) findViewById(R.id.botao_entrar_login);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(email.getText().toString().equals("") || senha.getText().toString().equals("")){

                    Toast.makeText(LoginActivity.this, "Informe e-mail e senha!", Toast.LENGTH_LONG).show();

                }else {
                    usuario = new Usuario();
                    usuario.setEmail(email.getText().toString());
                    usuario.setSenha(senha.getText().toString());
                    validarLogin();
                }

            }
        });

    }

    private void validarLogin(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    abrirTelaPrincipal();
                    Toast.makeText(LoginActivity.this, "Logado com sucesso", Toast.LENGTH_LONG).show();

                }else{

                    String erroExcecao = "";

                    try{

                       throw task.getException();

                    } catch (FirebaseAuthInvalidUserException e) {
                        erroExcecao = "Essa conta de usuário não existe ou está desativada!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = "Verifique seu e-mail e senha!";
                    } catch (Exception e) {
                        erroExcecao = "Não foi possível fazer o login!";
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this, "Erro: "+erroExcecao, Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    private void abrirTelaPrincipal(){

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    public void abrirCadastroUsuario(View view){

        Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
        startActivity( intent );

    }


}
