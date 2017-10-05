package com.desopilando.aliatech.desopilando.activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.desopilando.aliatech.desopilando.activity.fragment.EmAntaFragment;
import com.desopilando.aliatech.desopilando.activity.fragment.FavoritosFragment;
import com.desopilando.aliatech.desopilando.activity.fragment.LocaisFragment;

/**
 * Created by douglas on 05/10/17.
 */

public class TabAdapter extends FragmentStatePagerAdapter {

    private String[] tituloAbas = {"MEUS LOCAIS", "FAVORITOS", "EM ALTA"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new LocaisFragment();
                break;
            case 1:
                fragment = new FavoritosFragment();
                break;
            case 2:
                fragment = new EmAntaFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return tituloAbas.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tituloAbas[position];
    }
}
