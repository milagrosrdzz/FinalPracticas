package com.example.apirest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apirest.Model.Producto;

import java.util.List;

public class ProductoAdapter extends ArrayAdapter<Producto> {

    private Context context;
    private List<Producto> productos;


    public ProductoAdapter(@NonNull Context context, int resource, @NonNull List<Producto> objects) {
        super(context, resource, objects);
        this.context=context;
        this.productos=objects;
    }

    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.content_main,parent,false);

        TextView txtId=(TextView)rowView.findViewById(R.id.Id);
        TextView txtNombre=(TextView)rowView.findViewById(R.id.Nombre);
        //TextView txtCantidad=(TextView)rowView.findViewById(R.id.cantidadTotal);
        TextView txtCantidadTotal=(TextView)rowView.findViewById(R.id.cantidadTotal);


        txtId.setText(String.format("ID:%d",productos.get(position).getId_producto()));
        txtNombre.setText(String.format("NOMBRE:%s", productos.get(position).getNombre()));
        //txtCantidad.setText(String.format("CANTIDAD :%s",productos.get(position).getcantidad()));
        txtCantidadTotal.setText(String.format("CANTIDAD TOTAL:%s",productos.get(position).getCantidad_total()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ProductoActivity.class);
                intent.putExtra("ID",String.valueOf(productos.get(position).getId_producto()));
                intent.putExtra("NOMBRE",productos.get(position).getNombre());
                //intent.putExtra("CANTIDAD",productos.get(position).getcantidad());
                intent.putExtra("CANTIDAD TOTAL",productos.get(position).getCantidad_total());

                context.startActivity(intent);
            }
        });
        return rowView;

    }
}
