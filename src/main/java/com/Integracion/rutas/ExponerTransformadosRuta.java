package com.Integracion.rutas;

import com.Integracion.data.UsuarioMemoria;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExponerTransformadosRuta extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("jetty:http://0.0.0.0:8081/usuarios-transformados")
                .routeId("exponer-usuarios-transformados")
                .setHeader("Content-Type", constant("application/json"))
                .process(exchange -> exchange.getMessage().setBody(UsuarioMemoria.getUsuarios()))
                .marshal().json();
    }
}
