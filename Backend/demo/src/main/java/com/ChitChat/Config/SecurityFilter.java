package com.ChitChat.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
public class SecurityFilter {

    private final UserAuthProvider userAuthProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(
                        (request) -> {
                            request.requestMatchers(HttpMethod.GET, "/users/users", "/viewConversations", "/getMessage", "/received", "/user/conversation", "/users/{userId}", "/users/alldetail/{userId}", "/viewReceivedMessage").permitAll()
                                    .requestMatchers(HttpMethod.POST, "/users/adduser", "/conversations", "/saveMessage", "/receivedMessage", "/saveReceivedMessage").permitAll()
                                    .requestMatchers(HttpMethod.PUT, "/users/{userId}", "/users/{userId}/conversation/{conversationId}").permitAll()
                                    .anyRequest().authenticated();
                        }
                );
        return httpSecurity.build();
    }
}
