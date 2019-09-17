package cn.com.nanfeng.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerSecurityConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-08 15:03
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServiceConfig extends AuthorizationServerConfigurerAdapter {

    private final static String RESOURCE_ID = "user";
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        //配置一个客户端
        //可以通过授权码获取登录的令牌，也可以通过密码类型获取令牌
        clients.inMemory().withClient("client")//客户端ID
            .authorizedGrantTypes("authorization_code","password","refresh_token")//客户端可以使用的授权类型
            .scopes("all")//允许请求范围
            .secret("secret")//客户端安全码
            .redirectUris("http://localhost:8888");//回调地址
    }

    //配置AuthorizationServer tokenService
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer) throws Exception{
        endpointsConfigurer
                .tokenStore(new InMemoryTokenStore())
                .accessTokenConverter(accessTokenConverter())
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(false);
    }

    //配置JWT
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("secret");
        return converter;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception{
        //允许所有人请求令牌AuthorizationServerSecurityConfigurer
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated")
                .allowFormAuthenticationForClients();
    }
}
