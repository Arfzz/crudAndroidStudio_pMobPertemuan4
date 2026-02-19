package com.arief.cruddbsqlite;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class EditData extends AppCompatActivity {

    private DbDataSource ds;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        EditText nama = findViewById(R.id.et_nama);
        EditText merk = findViewById(R.id.et_merk);
        EditText asal = findViewById(R.id.et_asal);
        Button update = findViewById(R.id.btn_update);

        ds = new DbDataSource(this);
        ds.open();

        id = getIntent().getLongExtra("id", -1);
        Barang b = ds.getBarang(id);

        nama.setText(b.getNamaBarang());
        merk.setText(b.getMerkBarang());
        asal.setText(b.getAsalNegara());

        update.setOnClickListener(v -> {
            ds.updateBarang(id,
                    nama.getText().toString(),
                    merk.getText().toString(),
                    asal.getText().toString());
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        ds.close();
        super.onDestroy();
    }
}