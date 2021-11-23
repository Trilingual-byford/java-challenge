package jp.co.axa.apidemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // TODO temporary security setting.
        http.authorizeRequests() // 認可に関する設定
                .antMatchers("/api/v1/").permitAll().and().csrf().disable();
        http.authorizeRequests() // 認可に関する設定
                .antMatchers(HttpMethod.POST,"/api/v1/employees").permitAll().and().csrf().disable();
        http.authorizeRequests() // 認可に関する設定
                .antMatchers(HttpMethod.DELETE,"/api/v1/employees/*").permitAll().and().csrf().disable();
        http.authorizeRequests() // 認可に関する設定
                .antMatchers(HttpMethod.PUT,"/api/v1/employees/*").permitAll().and().csrf().disable();
    }
}
