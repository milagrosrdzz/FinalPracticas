package com.example.apirest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Producto {

    @SerializedName("id_producto")
    @Expose
    private Long id_producto;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("cantidad")
    @Expose
    private int cantidad;
    @SerializedName("cantidad_total")
    @Expose
    private int cantidad_total;


    public Producto(Long id_producto, String nombre, int cantidad, int cantidad_total) {
        super();
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.cantidad_total = cantidad_total;
    }

    public Producto() {

    }

    public long getId_producto() {
        if (id_producto != null) {
            return id_producto.longValue();
        } else {
            return 0; // O cualquier valor predeterminado que desees asignar en caso de que sea nulo
        }
    }
    public void setId_producto(long id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre(){return nombre;}
    public void setNombre (String nombre){this.nombre = nombre;}

    public int getcantidad(){return cantidad;}

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setCantidad_total(int cantidad_total){
        this.cantidad_total = cantidad_total;
    }

    public int getCantidad_total() {
        return cantidad_total;
    }
}
