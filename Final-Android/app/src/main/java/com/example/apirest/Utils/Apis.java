package com.example.apirest.Utils;

import android.util.Log;

public class Apis {

    public static final String URL_001="http://192.168.100.4:8080/usuarios/";

    public static UsuarioService getPersonaService(){
        Log.d("Apis", "Creando servicio con la URL: " + URL_001);
        return  Cliente.getClient(URL_001).create(UsuarioService.class);
    }
}
