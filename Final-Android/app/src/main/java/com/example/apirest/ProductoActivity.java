package com.example.apirest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoActivity extends AppCompatActivity {

    ProductoService productoService;
    private EditText txtCantidadTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persona_layout);
        productoService = Apis.getProductoService();

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

        TextView cantidadTotal = findViewById(R.id.cantidadTotal);
        txtCantidadTotal = findViewById(R.id.txtCantidadTotal);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnVolver = findViewById(R.id.btnVolver);
        Button btnEliminar = findViewById(R.id.btnEliminar);
        Button btnMas = findViewById(R.id.btnMas);
        Button btnMenos = findViewById(R.id.btnMenos);

        Bundle bundle = getIntent().getExtras();
        final String id_producto = bundle.getString("ID");
        String nom = bundle.getString("NOMBRE");
        int cant = bundle.getInt("CANTIDAD");
        int cantTot = bundle.getInt("CANTIDAD TOTAL");


        txtId.setText(id_producto);
        txtNombres.setText(nom);
        txtCantidad.setText(String.valueOf(cant));
        txtCantidadTotal.setText(String.valueOf(cantTot));

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

                Intent intent = new Intent(ProductoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProductoActivity.this);
                builder.setTitle("Eliminar Producto")
                        .setMessage("¿Estás seguro de que quieres eliminar este producto?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Eliminar el producto
                                deleteProducto(Integer.valueOf(id_producto));
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
            });




        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el valor de cantidad del EditText
                String cantidadStr = txtCantidad.getText().toString();

                if (!TextUtils.isEmpty(cantidadStr)) {
                    // Convertir la cantidad a un número entero
                    int cantidad = Integer.parseInt(cantidadStr);
                    Log.d("ProductoActivity", "Cantidad obtenida: " + cantidadStr);
                    // Llamar al método sumarProducto con el id_producto y la cantidad
                    sumarProducto(Long.parseLong(id_producto), cantidad);
                    Toast.makeText(ProductoActivity.this, "Cantidad actualizada", Toast.LENGTH_LONG).show();
                } else {
                    // Manejar el caso en el que la cantidad está vacía
                    Toast.makeText(ProductoActivity.this, "Ingrese una cantidad válida", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el valor de cantidad del EditText
                String cantidadStr = txtCantidad.getText().toString();

                if (!TextUtils.isEmpty(cantidadStr)) {
                    // Convertir la cantidad a un número entero
                    int cantidad = Integer.parseInt(cantidadStr);
                    Log.d("ProductoActivity", "Cantidad obtenida: " + cantidadStr);
                    // Llamar al método sumarProducto con el id_producto y la cantidad
                    restarProducto(Long.parseLong(id_producto), cantidad);

                    Toast.makeText(ProductoActivity.this, "Cantidad actualizada", Toast.LENGTH_LONG).show();
                } else {
                    // Manejar el caso en el que la cantidad está vacía
                    Toast.makeText(ProductoActivity.this, "Ingrese una cantidad válida", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addProducto(Producto pr) {
        productoService = Apis.getProductoService();
        Call<Producto> call = productoService.addProducto(pr);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProductoActivity.this, "Se agrego con éxito", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                Log.e("Error:", t.getMessage());
            }
        });
        Intent intent = new Intent(ProductoActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void actualizar(int id_producto) {
        productoService = Apis.getProductoService();
        Call<Producto> call = productoService.actualizar(id_producto);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProductoActivity.this, "Se Actualizó con éxito", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                Log.e("Error:", t.getMessage());
            }
        });
        Intent intent = new Intent(ProductoActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void deleteProducto(long id_producto) {
        productoService = Apis.getProductoService();
        Call<ResponseBody> call = productoService.deleteProducto(id_producto);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProductoActivity.this, "Se Eliminó el registro", Toast.LENGTH_LONG).show();

                    // Mover la parte del Intent aquí, dentro del onResponse
                    Intent intent = new Intent(ProductoActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Error:", t.getMessage());
            }
        });
    }


    private void sumarProducto(long id_producto, int cantidad) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("cantidad", String.valueOf(cantidad));

        // Agregar logs para verificar la llamada
        System.out.println("URL: " + productoService.sumarProducto(id_producto, requestBody).request().url());
        System.out.println("Datos: " + requestBody);


        Call<Producto> call = productoService.sumarProducto(id_producto, requestBody);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                System.out.println("Código de respuesta: " + response.code());

                if (response.isSuccessful()) {
                    Toast.makeText(ProductoActivity.this, "Cantidad agregada", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ProductoActivity.this, "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                Log.e("ProductoActivity", "Error en la llamada: " + t.getMessage(), t);
                // Manejar el error de la llamada
            }
        });
    }

    private void restarProducto(long id_producto, int cantidad) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("cantidad", String.valueOf(cantidad));

        // Agregar logs para verificar la llamada
        System.out.println("URL: " + productoService.sumarProducto(id_producto, requestBody).request().url());
        System.out.println("Datos: " + requestBody);


        Call<Producto> call = productoService.restarProducto(id_producto, requestBody);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                System.out.println("Código de respuesta: " + response.code());

                if (response.isSuccessful()) {
                    Toast.makeText(ProductoActivity.this, "Cantidad restada", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ProductoActivity.this, "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                Log.e("ProductoActivity", "Error en la llamada: " + t.getMessage(), t);
                // Manejar el error de la llamada
            }
        });
    }
}