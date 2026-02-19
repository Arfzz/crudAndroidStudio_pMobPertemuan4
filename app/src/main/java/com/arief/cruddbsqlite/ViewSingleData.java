package com.arief.cruddbsqlite;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ViewSingleData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single);

        DbDataSource ds = new DbDataSource(this);
        ds.open();

        long id = getIntent().getLongExtra("id", -1);
        Barang b = ds.getBarang(id);

        ((TextView) findViewById(R.id.tv_nama)).setText(b.getNamaBarang());
        ((TextView) findViewById(R.id.tv_merk)).setText(b.getMerkBarang());
        ((TextView) findViewById(R.id.tv_asal)).setText(b.getAsalNegara());

        ds.close();
    }
}