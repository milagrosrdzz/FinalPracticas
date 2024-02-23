package com.example.apirest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apirest.Model.Usuario;
import com.example.apirest.Utils.Apis;
import com.example.apirest.Utils.UsuarioService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioActivity extends AppCompatActivity {
    UsuarioService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persona_layout);

        TextView idper=(TextView)findViewById(R.id.Id);
        EditText txtId=(EditText)findViewById(R.id.txtId);

        TextView nombres=(TextView)findViewById(R.id.nombres);
        final EditText txtNombres=(EditText)findViewById(R.id.txtNombres);

        final EditText txtPass=(EditText)findViewById(R.id.txtPass);

        TextView email=(TextView)findViewById(R.id.email);
        final EditText txtEmail=(EditText)findViewById(R.id.txtEmail);

        Button btnSave=(Button)findViewById(R.id.btnSave);
        Button btnVolver=(Button)findViewById(R.id.btnVolver);
        Button btnEliminar=(Button)findViewById(R.id.btnEliminar);


        Bundle bundle=getIntent().getExtras();
        final String id = bundle.getString("ID");
        String nom=bundle.getString("NOMBRE");
        String mail=bundle.getString("EMAIL");
        String contra=bundle.getString("PASSWORD");

        txtId.setText(id);
        txtNombres.setText(nom);
        txtEmail.setText(mail);
        txtPass.setText(contra);
        if(id.trim().length()==0||id.equals("")){
            idper.setVisibility(View.INVISIBLE);
            txtId.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario p=new Usuario();
                p.setName(txtNombres.getText().toString());
                p.setEmail(txtEmail.getText().toString());
                p.setPassword(txtPass.getText().toString());
                if(id.trim().length()==0||id.equals("")){
                    addPersona(p);
                    Intent intent =new Intent(UsuarioActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    updatePersona(p,Integer.valueOf(id));
                    Intent intent =new Intent(UsuarioActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePersona(Integer.valueOf(id));
                Intent intent =new Intent(UsuarioActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(UsuarioActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void addPersona(Usuario p){
        service= Apis.getPersonaService();
        Call<Usuario>call=service.addPersona(p);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    Toast.makeText(UsuarioActivity.this,"Se agrego con éxito",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(UsuarioActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void updatePersona(Usuario p, int id){
        service= Apis.getPersonaService();
        Call<Usuario>call=service.updatePersona(p,id);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    Toast.makeText(UsuarioActivity.this,"Se Actualizó con éxito",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(UsuarioActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void deletePersona(int id){
        service=Apis.getPersonaService();
        Call<Usuario>call=service.deletePersona(id);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    Toast.makeText(UsuarioActivity.this,"Se Elimino el registro",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(UsuarioActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
