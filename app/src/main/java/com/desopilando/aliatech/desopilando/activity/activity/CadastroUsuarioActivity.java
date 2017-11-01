package com.desopilando.aliatech.desopilando.activity.activity;

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
import com.desopilando.aliatech.desopilando.activity.helper.Base64Custom;
import com.desopilando.aliatech.desopilando.activity.helper.Preferencias;
import com.desopilando.aliatech.desopilando.activity.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoSenha;
    private EditText campoRepetirSenha;
    private Button   botaoCadastrar;
    private Usuario usuario;

    private FirebaseAuth autenticacao;

    public FirebaseAuth getAutenticacao() {
        return autenticacao;
    }

    public void setAutenticacao(FirebaseAuth autenticacao) {
        this.autenticacao = autenticacao;
    }

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

                if(campoNome.getText().toString().equals("") || campoEmail.getText().toString().equals("") || campoSenha.getText().toString().equals("") || campoRepetirSenha.getText().toString().equals("")) {

                    Toast.makeText(CadastroUsuarioActivity.this, "Todos os campos são obrigatórios!", Toast.LENGTH_LONG).show();

                } else {

                    usuario = new Usuario();
                    usuario.setNome(campoNome.getText().toString());
                    usuario.setEmail(campoEmail.getText().toString());

                    if (campoSenha.getText().toString().equals(campoRepetirSenha.getText().toString())) {
                        usuario.setSenha(campoSenha.getText().toString());
                        cadastrarUsuario();
                    } else {
                        Toast.makeText(CadastroUsuarioActivity.this, "As senhas digitadas não são iguais!", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }

    private void cadastrarUsuario(){

        //Configurar a autenticação junto ao Firebase
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        //Criar com email e senha
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroUsuarioActivity.this, "Sucesso ao cadastrar usuário", Toast.LENGTH_LONG).show();

                    //Salvar os dados do usuario no banco
                    String identificadorUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                    usuario.setId(identificadorUsuario);
                    usuario.salvar();

                    Preferencias preferencias = new Preferencias(CadastroUsuarioActivity.this);
                    preferencias.salvarDados(identificadorUsuario);

                    abrirLoginUsuario();

                }else{
                    //Tratamento de exceções
                    String erroExcecao = "";
                    try{

                        throw task.getException();

                    }catch (FirebaseAuthWeakPasswordException e){
                        erroExcecao = "Digite uma sennha mais forte, contendo letras e numeros";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = "O e-mail que você digitou não é valido";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcecao = "Já existe um usuário usando esse endereço de e-mail";
                    } catch (Exception e) {
                        erroExcecao = "ao efeturar o cadastro!";
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroUsuarioActivity.this, "Erro: "+erroExcecao, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void abrirLoginUsuario(){

        Intent intent = new Intent(CadastroUsuarioActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

//Problema com o push dessa classe, resolver depois!
