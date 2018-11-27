package com.fadhlanhawali.alirin.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fadhlanhawali.alirin.R;
import com.fadhlanhawali.alirin.Model.State;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardFragment extends Fragment {

    private static final String TAG =DashboardFragment.class.getSimpleName();
    TextView cahaya,humiditas,suhu,ph;
    Button siram,sinar;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("State");
    State state;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        siram = v.findViewById(R.id.btnSiram);
        sinar = v.findViewById(R.id.btnSinar);
        cahaya = v.findViewById(R.id.txtCahaya);
        humiditas = v.findViewById(R.id.txtHumiditas);
        suhu = v.findViewById(R.id.txtSuhu);
        ph = v.findViewById(R.id.txtPh);


        siram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state.pompa.toString().equals("1"))
                {
                    myRef.child("pompa").setValue(0);
                }else {
                    myRef.child("pompa").setValue(1);
                }

            }
        });

        sinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state.lampu.toString().equals("1"))
                {
                    myRef.child("lampu").setValue(0);
                }else {
                    myRef.child("lampu").setValue(1);
                }
            }
        });

        coboRead();
        return v;
    }

    void coboRead(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                state = dataSnapshot.getValue(State.class);
                cahaya.setText(state.lampu.toString());
                humiditas.setText(state.humidity.toString());
                if(state.pompa.toString().equals("1")){
                    siram.setText("SIRAM (ON)");
                }else
                {
                    siram.setText("SIRAM (OFF)");
                }

                if(state.lampu.toString().equals("1")){
                    sinar.setText("SINAR (ON)");
                }else{
                    sinar.setText("SINAR (OFF)");
                }

                Log.d(TAG, "Durasi Sinar adalah: " + state.lampu);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

    }
}
