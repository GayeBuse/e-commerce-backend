package com.workintech.ecommercebackend.config;



import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration //Bu sınıfın bir yapılandırma sınıfı olduğunu belirtir.
// Spring tarafından tanınan ve yapılandırma işlemlerini gerçekleştirmek için kullanılan bir anotasyondur.
public class Security {
    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder(); //Bu sınıf, kullanıcı parolalarını güvenli bir
        // şekilde kodlamak ve karşılaştırmak için kullanılır. BCryptPasswordEncoder sınıfı,
        // kullanıcı parolalarını BCrypt algoritmasıyla kodlamak için kullanılır.
    }

    @Bean //Spring'e bu metodun dönüş değerini bir bean olarak yönetmesi gerektiğini söyler.
    // Bu sayede Spring, bu AuthenticationManager'ı uygulamanın ihtiyaç duyduğu yerlerde otomatik olarak enjekte edebilir.
//authManager adında bir Bean oluşturuyoruz.
// Bu Bean, Spring Security'nin yetkilendirme işlemlerini
// gerçekleştirmek için kullanılacak AuthenticationManager'ı döndürüyor.
// DaoAuthenticationProvider kullanarak, kullanıcıların kimlik bilgilerini
// ve rollerini bir veritabanından alacak ve parolalarını kontrol edecek
// bir sağlayıcı oluşturuyoruz. Bu sağlayıcı, UserDetailsService ile
// birlikte kullanıcı detaylarını sağlar ve parolaları kodlamak için
// yukarıda oluşturduğumuz passwordEncoder'ı kullanır.
    public AuthenticationManager authManager(UserDetailsService userDetailsService){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }
//CORS, bir kaynağın başka bir kaynaktan veri alabilmesini
// güvenli bir şekilde sağlamak için kullanılır. Özellikle,
// RESTful API'lere yapılan AJAX isteklerinde yaygın olarak
// kullanılır.
    //CORS, tarayıcı ve sunucu arasındaki HTTP başlıkları
// üzerinden çalışır. Tarayıcı, bir CORS isteği yaparken belirli
// HTTP başlıklarını (headers) ekler ve sunucu da buna yanıt
// verirken belirli başlıklar kullanır
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList(" http://localhost:5173/")); //setAllowedOrigins metodu ile belirli kaynaklardan gelen isteklere izin verilir.
        corsConfiguration.setAllowedMethods(Arrays.asList("PUT","GET","POST","DELETE")); //setAllowedMethods metodu ile izin verilen HTTP metodları
        // belirtilir. Örneğin, burada PUT, GET, POST, DELETE metodlarına izin verilmektedir.
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }
//r. Bu bean, uygulamanızdaki belirli URL'lere erişim kontrolünü ve güvenlik yapılandırmalarını tanımlar.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/product/**").permitAll(); // endpointlerine herkese açık erişim izni veriyoruz
                    auth.requestMatchers("/auth/**").permitAll();
                    auth.requestMatchers("/category/**").permitAll();
                    auth.requestMatchers("/order/**").permitAll();
                    auth.requestMatchers("/card/**").hasAnyAuthority("Admin", "User");//(permitAll()). /card/** endpointlerine ise yalnızca "Admin" veya "User" yetkisine sahip kullanıcıların erişmesine izin veriyoruz (hasAnyAuthority("Admin", "User"))
                    auth.requestMatchers("/address/**").permitAll();
                    auth.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults())
                .build();
    }
}//Bu adımların tamamı, Spring Security'nin kullanılacağı bir uygulama için temel yapılandırmayı sağlar. Bu yapılandırma, parola güvenliği, kimlik doğrulama, yetkilendirme ve CORS ayarlarını içerir, böylece uygulamanın güvenliği sağlanmış olur.
