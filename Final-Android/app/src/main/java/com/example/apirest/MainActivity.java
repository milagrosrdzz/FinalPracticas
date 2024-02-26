package com.example.apirest;

import android.content.Intent;
import android.os.Bundle;

import com.example.apirest.Model.Producto;
import com.example.apirest.Model.Usuario;
import com.example.apirest.Utils.Apis;
import com.example.apirest.Utils.ProductoService;
import com.example.apirest.Utils.UsuarioService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    UsuarioService usuarioService;
    ProductoService productoService;
    List<Usuario> listUsuario =new ArrayList<>();
    List<Producto> listProducto = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Para mostrar el botón de retroceso
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Configura el logo en la barra de herramientas
        getSupportActionBar().setIcon(R.drawable.ic_launcher_logo);

        listView = findViewById(R.id.listView);
        /*listPersons();*/
        listProdu();

        FloatingActionButton fab = findViewById(R.id.fabe);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this, AgregarProductoActivity.class);
               intent.putExtra("ID","");
               intent.putExtra("NOMBRE","");
               intent.putExtra("TOTAL","");
               startActivity(intent);
            }
        });

    }

    public void listPersons(){
        usuarioService = Apis.getPersonaService();
        Call<List<Usuario>>call= usuarioService.getPersonas();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()) {
                    listUsuario = response.body();
                    listView.setAdapter(new UsuarioAdapter(MainActivity.this,R.layout.content_main, listUsuario));
                }
            }
            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
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
                    listView.setAdapter(new ProductoAdapter(MainActivity.this,R.layout.content_main, listProducto));
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
