package com.desopilando.aliatech.desopilando.activity.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.desopilando.aliatech.desopilando.R;
import com.desopilando.aliatech.desopilando.activity.adapter.LocalAdapter;
import com.desopilando.aliatech.desopilando.activity.config.ConfiguracaoFirebase;
import com.desopilando.aliatech.desopilando.activity.helper.Base64Custom;
import com.desopilando.aliatech.desopilando.activity.helper.Preferencias;
import com.desopilando.aliatech.desopilando.activity.model.Estabelecimento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocaisFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Estabelecimento> locais;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerLocais;

    public LocaisFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerLocais);
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerLocais);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        locais = new ArrayList<>();

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_locais, container, false);

        listView = (ListView) view.findViewById(R.id.lv_locais);

        /*adapter = new ArrayAdapter(
                getActivity(),
                R.layout.lista_locais,
                locais
        );*/


        adapter = new LocalAdapter(getActivity(), locais);
        listView.setAdapter(adapter);

        Preferencias preferencias = new Preferencias(getActivity());
        String usuarioLogado = preferencias.getIdentificador();
        firebase = ConfiguracaoFirebase.getFirebase()
                .child("locais")
                .child(usuarioLogado);

        valueEventListenerLocais = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Limpar a lista
                locais.clear();

                //Listar locais
                for(DataSnapshot dados: dataSnapshot.getChildren()){
                    Estabelecimento estabelecimento = dados.getValue(Estabelecimento.class);
                    locais.add(estabelecimento);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };



        return view;
    }

}
