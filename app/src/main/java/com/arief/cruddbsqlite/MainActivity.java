package com.arief.cruddbsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button bTambah;
    private Button bView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bTambah = findViewById(R.id.btn_tambah);
        bView = findViewById(R.id.btn_view);

        // Tombol Tambah
        bTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreateData.class);
                startActivity(i);
            }
        });

        // Tombol View
        bView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewData.class);
                startActivity(i);
            }
        });
    }
}