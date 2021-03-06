package com.desopilando.aliatech.desopilando.activity.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.desopilando.aliatech.desopilando.R;
import com.desopilando.aliatech.desopilando.activity.adapter.TabAdapter;
import com.desopilando.aliatech.desopilando.activity.config.ConfiguracaoFirebase;
import com.desopilando.aliatech.desopilando.activity.helper.SlidingTabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FirebaseAuth usuarioAutenticacao;

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioAutenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Desopilando");
        setSupportActionBar(toolbar);

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        viewPager = (ViewPager) findViewById(R.id.vp_pagina);

        //Configurar sliding tabs
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.white));

        //Configurar adapter
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        slidingTabLayout.setViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.item_sair:
                deslogarUsuario();
                return true;
            case  R.id.item_adicionar:
                abrirTelaCadastro();;
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void deslogarUsuario(){

        usuarioAutenticacao.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    public void abrirTelaCadastro(){

        Intent intent2 = new Intent(MainActivity.this, CadastroEstabelecimentoActivity.class);
        startActivity(intent2);

    }
}
