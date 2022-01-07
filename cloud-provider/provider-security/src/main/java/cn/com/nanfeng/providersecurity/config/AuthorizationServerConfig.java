package cn.com.nanfeng.providersecurity.config;

import cn.com.nanfeng.providersecurity.model.vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-30 14:13
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final static Logger logger = LoggerFactory.getLogger(AuthorizationServerConfig.class);

    private final static String SINGINGKEY = "cloud";


    @Resource
    private SecurityConfigProperties properties;
    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private CustomUserDetailService customUserDetailService;


    @Value("${server.accessTokenTimeOut}")
    public int accessTokenTimeOut;
    @Value("${server.refreshTokenTimeOut}")
    public int refreshTokenTimeOut;




    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        /**
         *配置客户端详细信息
         * clientId 用来标识客户端的Id
         * secret 客户端安全码，如果有的话
         * scope 用来限制客户端的访问范围，如果为空（默认） 那么客户端有用全部的访问范围
         * authorizedGrantTypes:此客户端可以使用的授权类型，默认为空
         * authorities;此客户端可以使用的权限
         */
        clients.inMemory()
                .withClient(properties.getClientId())
                .secret(passwordEncoder.encode(properties.getClientSecret()))
                .scopes(properties.getScope())
                //支持的授权模式，共四种，这里配置了三种
                .authorizedGrantTypes("refresh_token","password")
                //超时时间
                .refreshTokenValiditySeconds(refreshTokenTimeOut);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
        /**
         * 配置授权（authorization） 以及令牌（token）的访问端点和令牌服务
         */
        endpoints
                //token储存
                .tokenStore(tokenStore())
                //自定义token生成方式
                .accessTokenConverter(accessTokenConverter())
                //身份认证管理器，主要用于“password”模式
                .authenticationManager(authenticationManager)
                //配合认证身份管理器，检查用户名密码是有效
                .userDetailsService(customUserDetailService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception{
        /**
         * 配置令牌端点（token Endpoint）的安全约束
         */
        security
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }

    /**
     * token存储方式
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }


    /**
     * token存储方式
     * @return
     */
    @Bean
    public AccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter(){
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication){
                final Map<String,Object> additionalInformation = new HashMap<>();
                CustomUserDetails userDetails = (CustomUserDetails)authentication.getUserAuthentication().getPrincipal();
                UserVO user = new UserVO();
                user.setId(userDetails.getUserEntity().getId());
                user.setName(userDetails.getUserEntity().getName());
                user.setEmail(userDetails.getUserEntity().getEmail());
                user.setPhone(userDetails.getUserEntity().getPhone());
                //9logger.info(">>>>>>>>>>>>>生成Token时的user对象：[{}]", JSONUtils.toJSONString(user));
                try {
                    additionalInformation.put("user",objectMapper.writeValueAsString(user));
                    additionalInformation.put("code",200);
                }catch (Exception e){
                    e.printStackTrace();
                }
                ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(additionalInformation);
                Calendar calendar  = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.SECOND,accessTokenTimeOut);
                Date time = calendar.getTime();
                ((DefaultOAuth2AccessToken)accessToken).setExpiration(time);
                return super.enhance(accessToken,authentication);
            }
        };
        //设置签名
        jwtAccessTokenConverter.setSigningKey(SINGINGKEY);
        return jwtAccessTokenConverter;
    }


}
