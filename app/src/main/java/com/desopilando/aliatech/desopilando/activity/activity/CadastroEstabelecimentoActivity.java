package com.desopilando.aliatech.desopilando.activity.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.desopilando.aliatech.desopilando.R;

public class CadastroEstabelecimentoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_estabelecimento);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Novo Cadastro");
        setSupportActionBar(toolbar);

        Spinner spinner = (Spinner) findViewById(R.id.locais_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locais_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
