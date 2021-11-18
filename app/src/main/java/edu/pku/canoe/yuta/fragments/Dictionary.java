package edu.pku.canoe.yuta.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import edu.pku.canoe.yuta.R;
import edu.pku.canoe.yuta.Searchout;


public class Dictionary extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.dictionary_layout, container, false);
        EditText searchbar = (EditText)inflate.findViewById(R.id.search_bar);
        Drawable searchIcon = inflate.getResources().getDrawable(R.drawable.search_icon);
        searchIcon.setBounds(-15, 0, 35, 50);
        searchbar.setCompoundDrawables(searchIcon, null, null, null);
        searchbar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Intent intent = new Intent(getActivity(), Searchout.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        return inflate;
    }
}