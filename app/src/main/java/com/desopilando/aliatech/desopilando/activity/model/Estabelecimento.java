package com.desopilando.aliatech.desopilando.activity.model;

import android.widget.ImageView;

import com.desopilando.aliatech.desopilando.activity.activity.CadastroEstabelecimentoActivity;
import com.desopilando.aliatech.desopilando.activity.config.ConfiguracaoFirebase;
import com.desopilando.aliatech.desopilando.activity.helper.Preferencias;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

/**
 * Created by douglas on 18/10/17.
 */

public class Estabelecimento {

    private String id;
    private String nome;
    private String descricao;
    private String telefone;
    private String promocao;

    public Estabelecimento() {
    }

    public void salvar(){


        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("locais").child(getId()).setValue(this);


    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getPromocao() {
        return promocao;
    }

    public void setPromocao(String promocao) {
        this.promocao = promocao;
    }
}
