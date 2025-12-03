package pe.edu.upc.menteactiva.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private static final String[] AUTH_WHITELIST ={
            // -- Swagger
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui/**",

            // -- Login
            "/upc/MenteActiva/User/login",
            "/upc/MenteActiva/User/register",
            "/upc/MenteActiva/User/registrar",
            "/upc/MenteActiva/Clients/registrar",
            "/upc/MenteActiva/Professionals/registrar",
            "/upc/MenteActiva/Authority/listartodos"
    };

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Agregar filtro JWT antes de UsernamePasswordAuthenticationFilter
        http.addFilterBefore(
                jwtRequestFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        // Deshabilitar CSRF (necesario para APIs REST)
        http.csrf(AbstractHttpConfigurer::disable);

        // Configurar CORS
        http.cors(Customizer.withDefaults());

        // Configurar autorización de endpoints
        http.authorizeHttpRequests((authz) -> authz
                // Rutas públicas
                .requestMatchers(AUTH_WHITELIST).permitAll()

                // 👥 Usuarios Profesionales (requiere rol PRO o ADMIN)
                .requestMatchers("/upc/MenteActiva/User/UsuariosProfesionales")
                .hasAnyAuthority("ROLE_PROFESSIONAL", "ROLE_ADMIN")

                // 👤 Usuarios Clientes (requiere rol CLIENT o ADMIN)
                .requestMatchers("/upc/MenteActiva/User/UsuariosClientes")
                .hasAnyAuthority("ROLE_CLIENT", "ROLE_ADMIN")

                // 👨‍💻 Operaciones generales sobre usuarios (listar, modificar, eliminar)
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/User/listartodos").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/upc/MenteActiva/User/modificar/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.DELETE, "/upc/MenteActiva/User/eliminar/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/upc/MenteActiva/Appointments/registrar").hasAnyAuthority("ROLE_CLIENT")
                .requestMatchers(HttpMethod.PUT, "/upc/MenteActiva/Appointments/modificar/**").hasAnyAuthority( "ROLE_CLIENT", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/upc/MenteActiva/Appointments/eliminar/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Appointments/listartodos").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Appointments/cliente/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Appointments/proxima/**").hasAnyAuthority(  "ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Appointments/ranking/top-profesionales-todas").hasAnyAuthority(  "ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Appointments/ranking/top-especialidades").hasAnyAuthority(  "ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Appointments/ranking/top-clientes-todas").hasAnyAuthority(  "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/upc/MenteActiva/Authority/registrar").hasAnyAuthority( "ROLE_CLIENT", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/upc/MenteActiva/Authority/modificar/**").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/upc/MenteActiva/Authority/eliminar/**").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Authority/listartodos").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/upc/MenteActiva/Chats/registrar").hasAnyAuthority( "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Chats/listartodos").hasAnyAuthority( "ROLE_ADMIN","ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.PUT, "/upc/MenteActiva/Chats/modificar/**").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/upc/MenteActiva/Chats/eliminar/**").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Chats/cliente/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Clients/listByProfessional/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PROFESSIONAL") // Agregado permiso para el endpoint de pacientes
                .requestMatchers(HttpMethod.POST, "/upc/MenteActiva/Clients/registrar").hasAnyAuthority( "ROLE_ADMIN","ROLE_CLIENT")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Clients/listartodos").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/upc/MenteActiva/Clients/modificar/**").hasAnyAuthority( "ROLE_ADMIN","ROLE_CLIENT")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Clients/**").hasAnyAuthority( "ROLE_ADMIN","ROLE_CLIENT")
                .requestMatchers(HttpMethod.DELETE, "/upc/MenteActiva/Clients/eliminar/**").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/upc/MenteActiva/Consents/registrar").hasAnyAuthority( "ROLE_ADMIN","ROLE_CLIENT")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Consents/listartodos").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/upc/MenteActiva/Consents/modificar/**").hasAnyAuthority( "ROLE_ADMIN","ROLE_CLIENT")
                .requestMatchers(HttpMethod.DELETE, "/upc/MenteActiva/Consents/eliminar/**").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/upc/MenteActiva/Professionals/registrar").hasAnyAuthority( "ROLE_ADMIN","ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Professionals/listartodos").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Professionals/listar/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.PUT, "/upc/MenteActiva/Professionals/modificar/**").hasAnyAuthority( "ROLE_ADMIN","ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.DELETE, "/upc/MenteActiva/Professionals/eliminar/**").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Professionals/CitasPorProfesional").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/upc/MenteActiva/Schedules/registrar").hasAnyAuthority( "ROLE_ADMIN", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Schedules/listartodos").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.PUT, "/upc/MenteActiva/Schedules/modificar/**").hasAnyAuthority( "ROLE_ADMIN", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.DELETE, "/upc/MenteActiva/Schedules/eliminar/**").hasAnyAuthority( "ROLE_ADMIN", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.POST, "/upc/MenteActiva/Session_Summaries/registrar").hasAnyAuthority( "ROLE_ADMIN", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Session_Summaries/listartodos").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/upc/MenteActiva/Session_Summaries/modificar/**").hasAnyAuthority( "ROLE_ADMIN", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.DELETE, "/upc/MenteActiva/Session_Summaries/eliminar/**").hasAnyAuthority( "ROLE_ADMIN", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.POST, "/upc/MenteActiva/Status/registrar").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Status/listartodos").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/upc/MenteActiva/Status/modificar/**").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/upc/MenteActiva/Status/eliminar/**").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/upc/MenteActiva/User_Authority/registrar").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/User_Authority/listartodos").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/upc/MenteActiva/User_Authority/modificar/**").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/upc/MenteActiva/User_Authority/eliminar/**").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/upc/MenteActiva/Video_Progress/registrar").hasAnyAuthority( "ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Video_Progress/listartodos").hasAnyAuthority( "ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Video_Progress/**").hasAnyAuthority( "ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.PUT, "/upc/MenteActiva/Video_Progress/modificar/**").hasAnyAuthority( "ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.DELETE, "/upc/MenteActiva/Video_Progress/eliminar/**").hasAnyAuthority( "ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.POST, "/upc/MenteActiva/Videos/registrar").hasAnyAuthority( "ROLE_ADMIN",  "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Videos/listartodos").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.PUT, "/upc/MenteActiva/Videos/modificar/**").hasAnyAuthority( "ROLE_ADMIN", "ROLE_PROFESSIONAL", "ROLE_CLIENT")
                .requestMatchers(HttpMethod.DELETE, "/upc/MenteActiva/Videos/eliminar/**").hasAnyAuthority( "ROLE_ADMIN", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Videos/VideosMasVistos/**").hasAnyAuthority( "ROLE_ADMIN", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Videos/{professionalId}/top-largos").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Videos/{professionalId}/count").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Videos/search").hasAnyAuthority( "ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Chats/historial/cliente/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Schedules/profesional/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.PUT, "/upc/MenteActiva/Appointments/cancelar/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL")
                .requestMatchers(HttpMethod.GET, "/upc/MenteActiva/Videos/profesional/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PROFESSIONAL")





                // 🔐 Cualquier otra petición requiere autenticación
                .anyRequest().authenticated()
        );

        // Sesiones STATELESS (importante para JWT)
        http.sessionManagement((session) ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

