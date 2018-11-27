package com.fadhlanhawali.alirin.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.fadhlanhawali.alirin.Model.Control;
import com.fadhlanhawali.alirin.Model.State;
import com.fadhlanhawali.alirin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingFragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Control");
    DatabaseReference myRef_2 = database.getReference("Control");
    Control control;

    EditText durasiPenyinaran,humiditasMaksimal,waktuPenyiraman_Jam,waktuPenyiraman_Menit,waktuPenyinaran_Jam,waktuPenyinaran_Menit;
    Switch otomatis;
    Button simpan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        durasiPenyinaran = v.findViewById(R.id.editTxtDurasiPenyinaran);
        humiditasMaksimal = v.findViewById(R.id.editTxtHumiditasMaksimal);
        waktuPenyinaran_Jam = v.findViewById(R.id.editTxtWaktuPenyinaran_Jam);
        waktuPenyinaran_Menit = v.findViewById(R.id.editTxtWaktuPenyinaran_Menit);
        waktuPenyiraman_Jam = v.findViewById(R.id.editTxtWaktuPenyiraman_Jam);
        waktuPenyiraman_Menit = v.findViewById(R.id.editTxtWaktuPenyiraman_Menit);
        otomatis = v.findViewById(R.id.switchOtomatis);
        simpan = v.findViewById(R.id.btnSimpan);
        otomatis.setChecked(true);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String waktuPenyiraman = waktuPenyiraman_Jam.getText().toString() + ":" + waktuPenyiraman_Menit.getText().toString();
                String waktuPenyinaran = waktuPenyinaran_Jam.getText().toString() + ":" + waktuPenyinaran_Menit.getText().toString();

                myRef.child("durasi_sinar").setValue(Long.parseLong(durasiPenyinaran.getText().toString()));
                myRef.child("jam_sinar").setValue(waktuPenyinaran);
                myRef.child("jam_siram").setValue(waktuPenyiraman);
                myRef.child("max_humidity").setValue(Long.parseLong(humiditasMaksimal.getText().toString()));

            }
        });



        otomatis.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    myRef.child("automated").setValue(1);
                    otomatis.setChecked(true);
                }else{
                    myRef.child("automated").setValue(0);
                    otomatis.setChecked(false);
                }
            }
        });

        readData();

        return v;
    }

    void readData(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                control = dataSnapshot.getValue(Control.class);
                durasiPenyinaran.setText(control.getDurasi_sinar().toString());
                humiditasMaksimal.setText(control.getMax_humidity().toString());
                String jamSinar = control.getJam_sinar().toString();
                String jamSiram = control.getJam_siram().toString();
                String[] jamSinar_separated = jamSinar.split(":");
                String[] jamSiram_separated = jamSiram.split(":");

                waktuPenyinaran_Jam.setText(jamSinar_separated[0]);
                waktuPenyinaran_Menit.setText(jamSinar_separated[1]);
                waktuPenyiraman_Jam.setText(jamSiram_separated[0]);
                waktuPenyiraman_Menit.setText(jamSiram_separated[1]);
//                if(control.getAutomated().equals(true)){
//                    otomatis.setChecked(true);
//                }else {
//                    otomatis.setChecked(false);
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myRef_2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                otomatis.setChecked(Boolean.getBoolean(control.getAutomated().toString()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}