package com.desopilando.aliatech.desopilando.activity;

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

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoSenha;
    private EditText campoRepetirSenha;
    private Button   botaoCadastrar;
    private Usuario usuario;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        campoNome =         (EditText) findViewById(R.id.campo_nome_cadastro);
        campoEmail =        (EditText) findViewById(R.id.campo_email_cadastro);
        campoSenha =        (EditText) findViewById(R.id.campo_senha_cadastro);
        campoRepetirSenha = (EditText) findViewById(R.id.campo_repetirSenha_cadastro);
        botaoCadastrar =    (Button) findViewById(R.id.botao_cadastrar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuario = new Usuario();
                usuario.setNome(campoNome.getText().toString());
                usuario.setEmail(campoEmail.getText().toString());
                usuario.setSenha(campoSenha.getText().toString());
                cadastrarUsuario();

            }
        });

    }

    private void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroUsuarioActivity.this, "Sucesso ao cadastrar usuário", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(CadastroUsuarioActivity.this, "Erro ao cadastrar usuário", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
