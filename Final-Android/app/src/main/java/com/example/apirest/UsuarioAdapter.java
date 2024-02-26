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

import com.example.apirest.Model.Usuario;

import java.util.List;

public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    private Context context;
    private  List<Usuario>personas;

    public UsuarioAdapter(@NonNull Context context, int resource, @NonNull List<Usuario> objects) {
        super(context, resource, objects);
        this.context=context;
        this.personas=objects;
    }


    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.content_main,parent,false);

        TextView txtidPersona=(TextView)rowView.findViewById(R.id.Id);
        TextView txtNombre=(TextView)rowView.findViewById(R.id.Nombre);
        //TextView txtEmail=(TextView)rowView.findViewById(R.id.cantidadTotal);


        txtidPersona.setText(String.format("ID:%d",personas.get(position).getId_usuario()));
        txtNombre.setText(String.format("NOMBRE:%s", personas.get(position).getName()));
        //txtEmail.setText(String.format("EMAIL:%s",personas.get(position).getEmail()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(context, UsuarioActivity.class);
               intent.putExtra("ID",String.valueOf(personas.get(position).getId_usuario()));
               intent.putExtra("NOMBRE",personas.get(position).getName());
               intent.putExtra("EMAIL",personas.get(position).getEmail());

                context.startActivity(intent);
            }
        });
        return rowView;

    }

}
