package com.Integracion.data;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UsuarioMemoria {

    private static final List<Object> usuariosTransformados = new CopyOnWriteArrayList<>();

    public static void setUsuarios(List<Object> lista) {
        usuariosTransformados.clear();
        usuariosTransformados.addAll(lista);
    }

    public static List<Object> getUsuarios() {
        return usuariosTransformados;
    }
}
