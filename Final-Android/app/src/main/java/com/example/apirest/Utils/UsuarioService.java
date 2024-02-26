package com.example.apirest.Utils;

import com.example.apirest.Model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioService {

    @GET("listar")
    Call<List<Usuario>> getPersonas();

    @POST("agregar")
    Call<Usuario>addPersona(@Body Usuario usuario);

    @POST("actualizar/{id}")
    Call<Usuario>updatePersona(@Body Usuario usuario, @Path("id") int id);

    @POST("eliminar/{id}")
    Call<Usuario>deletePersona(@Path("id")int id);

    @POST("login")
    Call<Usuario>loginUser(@Body Usuario usuario);

}
