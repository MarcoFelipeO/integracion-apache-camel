package com.Integracion.rutas;

import com.Integracion.data.UsuarioMemoria;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRuta extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer://usuario-timer?period=10000") // cada 10 segundos
                .routeId("consumir-usuarios")
                .setHeader("CamelHttpMethod", constant("GET"))
                .to("http://localhost:8080/usuarios")
                .log("Respuesta original: ${body}")
                .unmarshal().json()
                .process(exchange -> {
                    var usuarios = exchange.getIn().getBody(java.util.List.class);
                    usuarios.forEach(obj -> {
                        var usuario = (java.util.Map<String, Object>) obj;
                        String nombre = (String) usuario.get("nombres");
                        if (nombre != null) {
                            usuario.put("nombres", nombre.toUpperCase());
                        }
                    });
                    UsuarioMemoria.setUsuarios(usuarios);
                });
    }
}
