package com.desopilando.aliatech.desopilando.activity.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.desopilando.aliatech.desopilando.R;

public class CadastroEstabelecimentoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spinner;
    private EditText nome;
    private EditText telefone;
    private EditText descricao;
    private EditText promocoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_estabelecimento);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Novo Cadastro");
        setSupportActionBar(toolbar);

        nome = (EditText) findViewById(R.id.campo_nome_estabelecimento);
        telefone = (EditText) findViewById(R.id.campo_fone_estabelecimento);
        descricao = (EditText) findViewById(R.id.campo_descricao);
        promocoes = (EditText) findViewById(R.id.campo_promocao);

        Spinner spinner = (Spinner) findViewById(R.id.locais_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locais_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
