package com.jmmunoz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {

    // Idioma por defecto.
    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        return sessionLocaleResolver;
    }

    // Bueno para el testing. El idioma viene por parámetro.
    // Si viene el parámetro se cambia el Locale gracias al parámetro lang.
    // Si no viene el parámetro se usa el header HTTP para saber el idioma.
    //
    // Para cambiar el idioma:
    // http://localhost:8080/?lang=es
    // http://localhost:8080/?lang=de
    // http://localhost:8080/?lang=en_GB
    // http://localhost:8080/?lang=en_US
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    // Lo que nos permite pasar el parámetro del idioma en la cadena URL.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
