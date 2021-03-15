package com.example.fragmentsample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class Fragment_01 extends Fragment {

    private Activity root;
    private Button button;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        root = (FragmentActivity) getActivity();
//        root.setTitle("frag_01");
        context = container.getContext();

        View view = inflater.inflate(R.layout.activity_frag01, container, false);
        button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"hello..01",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
