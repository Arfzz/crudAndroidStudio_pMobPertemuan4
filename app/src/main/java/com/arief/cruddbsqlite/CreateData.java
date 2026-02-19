package com.arief.cruddbsqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateData extends AppCompatActivity {

    private EditText edNama;
    private EditText edMerk;
    private EditText edAsal;
    private Button btnSimpan;

    private DbDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        edNama   = findViewById(R.id.nama_barang);
        edMerk   = findViewById(R.id.merk_barang);
        edAsal   = findViewById(R.id.asal_barang);
        btnSimpan = findViewById(R.id.btn_simpan);

        dataSource = new DbDataSource(this);
        dataSource.open();

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanData();
            }
        });
    }

    private void simpanData() {
        String nama = edNama.getText().toString().trim();
        String merk = edMerk.getText().toString().trim();
        String asal = edAsal.getText().toString().trim();

        if (nama.isEmpty() || merk.isEmpty() || asal.isEmpty()) {
            Toast.makeText(this, "Semua field wajib diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        Barang barang = dataSource.createBarang(nama, merk, asal);

        Toast.makeText(
                this,
                "Data tersimpan:\n" +
                        "Nama: " + barang.getNamaBarang() + "\n" +
                        "Merk: " + barang.getMerkBarang() + "\n" +
                        "Asal: " + barang.getAsalNegara(),
                Toast.LENGTH_LONG
        ).show();

        finish(); // kembali ke MainActivity
    }

    @Override
    protected void onDestroy() {
        dataSource.close();
        super.onDestroy();
    }
}