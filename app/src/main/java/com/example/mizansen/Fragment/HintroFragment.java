package com.example.mizansen.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mizansen.R;

public class HintroFragment extends Fragment {
    private TextView title, description;

    private int number = 0;

    public HintroFragment() {
        // Required empty public constructor
    }

    public void setNumber(final int number) {
        this.number = number;

        if (title != null) {
            title.setText(Integer.toString(number));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_intro, container, false);

        title = (TextView) root.findViewById(R.id.number_fragment_title_holder);
        description = (TextView) root.findViewById(R.id.number_fragment_title_holder);

        Log.d("TAG_Hintro", "" + number);
        title.setText(Integer.toString(number));
        description.setText(Integer.toString(number));

        return root;
    }
}