package com.example.apirest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.apirest.Model.Producto;
import com.example.apirest.Utils.Apis;
import com.example.apirest.Utils.ProductoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarProductoActivity extends AppCompatActivity {


    ProductoService productoService;
    private EditText txtCantidadTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.producto_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configura la barra de acción con el botón de retroceso y el logo
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setIcon(R.drawable.ic_launcher_logo); // Reemplaza ic_launcher_logo con el nombre correcto de tu recurso
        }

        TextView id = findViewById(R.id.Id);
        EditText txtId = findViewById(R.id.txtId);

        TextView nombres = findViewById(R.id.nombres);
        final EditText txtNombres = findViewById(R.id.txtNombres);

        TextView cantidad = findViewById(R.id.cantidadTotal);
        final EditText txtCantidad = findViewById(R.id.txtCantidad);


        Button btnSave = findViewById(R.id.btnSave);
        Button btnVolver = findViewById(R.id.btnVolver);
        Button btnEliminar = findViewById(R.id.btnEliminar);


        Bundle bundle = getIntent().getExtras();
        final String id_producto = bundle.getString("ID");
        String nom = bundle.getString("NOMBRE");
        String cant = bundle.getString("CANTIDAD");

        txtId.setText(id_producto);
        txtNombres.setText(nom);
        txtCantidad.setText(cant);

        if (id_producto.trim().length() == 0 || id_producto.equals("")) {
            id.setVisibility(View.INVISIBLE);
            txtId.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto pr = new Producto();
                pr.setNombre(txtNombres.getText().toString());
                pr.setCantidad(Integer.parseInt(txtCantidad.getText().toString()));

                if (id_producto.trim().length() == 0 || id_producto.equals("")) {
                    addProducto(pr);
                } else {
                    actualizar(Integer.valueOf(id_producto));
                }

                Intent intent = new Intent(AgregarProductoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgregarProductoActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });

    }
    public void addProducto(Producto pr){
        productoService= Apis.getProductoService();
        Call<Producto> call=productoService.addProducto(pr);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if(response.isSuccessful()){
                    Toast.makeText(AgregarProductoActivity.this,"Se agrego con éxito",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(AgregarProductoActivity.this,MainActivity.class);
        startActivity(intent);
    }



    public void actualizar(int id_producto){
        productoService= Apis.getProductoService();
        Call<Producto>call=productoService.actualizar(id_producto);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if(response.isSuccessful()){
                    Toast.makeText(AgregarProductoActivity.this,"Se Actualizó con éxito",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(AgregarProductoActivity.this,MainActivity.class);
        startActivity(intent);
    }

}

