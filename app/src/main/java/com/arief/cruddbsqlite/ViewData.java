package com.arief.cruddbsqlite;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewData extends ListActivity
        implements AdapterView.OnItemLongClickListener {

    private DbDataSource dataSource;
    private ArrayList<Barang> values;

    private Button editButton;
    private Button delButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        dataSource = new DbDataSource(this);
        dataSource.open();

        values = dataSource.getAllBarang();

        ArrayAdapter<Barang> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        values);

        setListAdapter(adapter);

        ListView lv = getListView();
        lv.setOnItemLongClickListener(this);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Barang barang =
                        (Barang) getListAdapter().getItem(position);
                switchToDetail(barang.getId());
            }
        });
    }

    private void switchToDetail(long id) {
        Barang b = dataSource.getBarang(id);

        Intent i = new Intent(this, ViewSingleData.class);
        Bundle bun = new Bundle();
        bun.putLong("id", b.getId());
        bun.putString("nama", b.getNamaBarang());
        bun.putString("merk", b.getMerkBarang());
        bun.putString("asal", b.getAsalNegara());

        i.putExtras(bun);
        startActivity(i);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent,
                                   View view, int pos, long id) {

        final Barang b =
                (Barang) getListAdapter().getItem(pos);

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view);
        dialog.setTitle("Pilih Aksi");

        editButton = dialog.findViewById(R.id.button_edit_data);
        delButton  = dialog.findViewById(R.id.button_delete_data);

        editButton.setOnClickListener(v -> {
            switchToEdit(b.getId());
            dialog.dismiss();
        });

        delButton.setOnClickListener(v -> {
            dataSource.deleteBarang(b.getId());
            dialog.dismiss();
            recreate(); // refresh list
        });

        dialog.show();
        return true;
    }

    private void switchToEdit(long id) {
        Barang b = dataSource.getBarang(id);

        Intent i = new Intent(this, EditData.class);
        Bundle bun = new Bundle();
        bun.putLong("id", b.getId());
        bun.putString("nama", b.getNamaBarang());
        bun.putString("merk", b.getMerkBarang());
        bun.putString("asal", b.getAsalNegara());

        i.putExtras(bun);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        dataSource.close();
        super.onDestroy();
    }
}