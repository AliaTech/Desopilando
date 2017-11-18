package com.desopilando.aliatech.desopilando.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.desopilando.aliatech.desopilando.R;
import com.desopilando.aliatech.desopilando.activity.model.Estabelecimento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglas on 17/11/2017.
 */

public class LocalAdapter extends ArrayAdapter<Estabelecimento> {

    private ArrayList<Estabelecimento> estabelecimentos;
    private Context context;

    public LocalAdapter(@NonNull Context c, @NonNull ArrayList<Estabelecimento> objects) {
        super(c,0, objects);
        this.estabelecimentos = objects;
        this.context = c;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        if(estabelecimentos != null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.lista_locais, parent, false);

            TextView nomeLocal = (TextView) view.findViewById(R.id.tv_nome);
            TextView enderecoLocal = (TextView) view.findViewById(R.id.tv_email);

            Estabelecimento estabelecimento = estabelecimentos.get(position);
            nomeLocal.setText(estabelecimento.getNome());
            enderecoLocal.setText((estabelecimento.getDescricao()));

        }

        return view;

    }
}
