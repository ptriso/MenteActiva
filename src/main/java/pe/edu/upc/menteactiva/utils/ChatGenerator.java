package pe.edu.upc.menteactiva.utils;
import pe.edu.upc.menteactiva.entities.Clients;
import pe.edu.upc.menteactiva.entities.Profesionals;

import java.util.List;
import java.util.Random;

public class ChatGenerator {

    private static final List<String> templates = List.of(
            """
            Paciente (%CLIENTE%): Hola doctor, ya me uno a la sesión.
            Doctor (%PROFESIONAL%): Perfecto, te estaba esperando.
            Doctor (%PROFESIONAL%): Recuerda respirar profundo y relajarte.
            Paciente (%CLIENTE%): Gracias doctor, lo haré.
            Doctor (%PROFESIONAL%): Excelente, revisa los videos que te mandé.
            Paciente (%CLIENTE%): Claro doctor, muchas gracias.
            """,

            """
            Paciente (%CLIENTE%): Doctor, ya estoy conectado.
            Doctor (%PROFESIONAL%): Muy bien, empecemos cuando estés listo.
            Doctor (%PROFESIONAL%): Hoy revisaremos tus avances emocionales.
            Paciente (%CLIENTE%): Sí doctor, he estado practicando lo conversado.
            Doctor (%PROFESIONAL%): Me alegra oír eso.
            Paciente (%CLIENTE%): Gracias doctor, me ha ayudado muchísimo.
            """,

            """
            Paciente (%CLIENTE%): Doctor, ya ingresé a la sesión.
            Doctor (%PROFESIONAL%): Qué bueno, comencemos entonces.
            Doctor (%PROFESIONAL%): Hoy quisiera reforzar tus técnicas de calma.
            Paciente (%CLIENTE%): Gracias doctor, me parecen útiles.
            Doctor (%PROFESIONAL%): Sigue así, vas muy bien.
            Paciente (%CLIENTE%): Muchas\u00a0gracias doctor.
            """,

            """
            Paciente (%CLIENTE%): Hola doctor, ¿me escucha?
            Doctor (%PROFESIONAL%): Sí, todo correcto.
            Doctor (%PROFESIONAL%): Hoy revisaremos tus progresos.
            Paciente (%CLIENTE%): Me parece bien doctor.
            Doctor (%PROFESIONAL%): Excelente, avancemos.
            Paciente (%CLIENTE%): Muchas gracias por su apoyo.
            """,

            """
            Paciente (%CLIENTE%): Doctor, ya estoy listo.
            Doctor (%PROFESIONAL%): Genial, comencemos.
            Doctor (%PROFESIONAL%): Hablaremos sobre tus detonantes de estrés.
            Paciente (%CLIENTE%): Sí doctor, los he identificado.
            Doctor (%PROFESIONAL%): Muy bien, es un paso enorme.
            Paciente (%CLIENTE%): Gracias de verdad doctor.
            """
    );

    public static String generarChat(Clients cliente, Profesionals profesional) {

        Random random = new Random();
        String bloque = templates.get(random.nextInt(templates.size()));

        return bloque.replace("%CLIENTE%", cliente.getName())
                .replace("%PROFESIONAL%", profesional.getName());
    }
}
