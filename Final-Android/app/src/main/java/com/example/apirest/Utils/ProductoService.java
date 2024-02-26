package com.example.apirest.Utils;

import com.example.apirest.Model.Producto;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductoService {

    @GET("listar")
    Call<List<Producto>> getProductos();

    @POST("agregarNuevo")
    Call<Producto>addProducto(@Body Producto producto);

    @POST("usuarios/productos/actualizar/{id_producto}")
    Call<Producto>actualizar(@Path("id_producto") long id_producto);

    @DELETE("/usuarios/productos/eliminar/{id}")
    Call<ResponseBody> deleteProducto(@Path("id") long id);

    @POST("{id_producto}/restar-cantidad/")
    Call<Producto> restarProducto(@Path("id_producto") long id_producto, @Body Map<String, String> requestBody);

    @POST("{id_producto}/sumar-cantidad/")
    Call<Producto> sumarProducto(@Path("id_producto") long id_producto, @Body Map<String, String> requestBody);



}
