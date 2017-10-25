package com.desopilando.aliatech.desopilando.activity.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.desopilando.aliatech.desopilando.R;
import com.desopilando.aliatech.desopilando.activity.config.ConfiguracaoFirebase;
import com.desopilando.aliatech.desopilando.activity.helper.Base64Custom;
import com.desopilando.aliatech.desopilando.activity.helper.Preferencias;
import com.desopilando.aliatech.desopilando.activity.model.Estabelecimento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class CadastroEstabelecimentoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spinner;
    private EditText nome;
    private EditText telefone;
    private EditText descricao;
    private EditText promocoes;
    private Button cadastrar;
    private Button abrirGaleria;
    private Button abrirMapas;
    private FirebaseAuth usuarioFirebase;
    private Estabelecimento estabelecimento;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_estabelecimento);

        usuarioFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Novo Cadastro");
        setSupportActionBar(toolbar);

        nome = (EditText) findViewById(R.id.campo_nome_estabelecimento);
        telefone = (EditText) findViewById(R.id.campo_fone_estabelecimento);
        descricao = (EditText) findViewById(R.id.campo_descricao);
        promocoes = (EditText) findViewById(R.id.campo_promocao);
        cadastrar = (Button) findViewById(R.id.botaoCadastrarLugar);
        abrirGaleria = (Button) findViewById(R.id.botaoAbrirGaleria);
        abrirMapas = (Button) findViewById(R.id.botaoAbrirMapas);

        Spinner spinner = (Spinner) findViewById(R.id.locais_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locais_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nome.getText().toString().equals("") || telefone.getText().toString().equals("") || descricao.getText().toString().equals("")){

                    Toast.makeText(CadastroEstabelecimentoActivity.this, "Os campos com * são obrigatórios ", Toast.LENGTH_LONG).show();

                }else{

                    estabelecimento = new Estabelecimento();
                    estabelecimento.setNome(nome.getText().toString());
                    estabelecimento.setTelefone(telefone.getText().toString());
                    estabelecimento.setDescricao(descricao.getText().toString());
                    estabelecimento.setPromocao(promocoes.getText().toString());

                    String identificadorEstabelecimento = Base64Custom.codificarBase64(estabelecimento.getNome());
                    estabelecimento.setId(identificadorEstabelecimento);
                    estabelecimento.salvar();

                }
            }
        });

    }

}
