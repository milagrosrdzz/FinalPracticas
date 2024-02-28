package com.example.apirest;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.apirest.Model.Producto;

import com.example.apirest.Utils.Apis;
import com.example.apirest.Utils.ProductoService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaActivity extends AppCompatActivity {
    ProductoService productoService;
    List<Producto> listProducto = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_logo);
        setSupportActionBar(toolbar);

        // Para mostrar el botón de retroceso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Configura el logo en la barra de herramientas
        getSupportActionBar().setIcon(R.drawable.ic_launcher_logo);

        listView = findViewById(R.id.listView);

        listProdu();

        FloatingActionButton fab = findViewById(R.id.fabe);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListaActivity.this, AgregarProductoActivity.class);
                intent.putExtra("ID","");
                intent.putExtra("NOMBRE","");
                intent.putExtra("TOTAL","");
                startActivity(intent);
            }
        });

    }


    public void listProdu(){
        productoService = Apis.getProductoService();
        Call<List<Producto>> call= productoService.getProductos();
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if(response.isSuccessful()) {
                    listProducto = response.body();
                    listView.setAdapter(new ProductoAdapter(ListaActivity.this,R.layout.content_main, listProducto));
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // Aquí puedes manejar la acción del menú
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == android.R.id.home) {
            // Manejar el botón de retroceso (icono de la barra)
            Intent intent = new Intent(ListaActivity.this, MainActivity.class);
            startActivity(intent);
            finish();  // Opcional: cierra la actividad actual si no quieres volver a ella con el botón de retroceso
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
