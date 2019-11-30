package cn.com.nanfeng.providersecurity.handler;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-23 11:51
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    @Autowired
    private ClientDetailsService clientDetailsService;
    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //从请求头中获取ClientId
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Basic ")){
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }
        String[] tokens = this.extractAndDecodeHeader(header,request);
        String clientId = tokens[0];
        String clientSecret = tokens[1];

        TokenRequest tokenRequest = null;

        //2 通过ClientId 获取 ClientDetails
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        //3 校验ClientId和ClientSecret的正确性
        if (clientDetails == null){
            throw new UnapprovedClientAuthenticationException("clientId"+clientId+"信息不存在");
        }else if (!passwordEncoder.matches(clientSecret,clientDetails.getClientSecret())){
            throw new UnapprovedClientAuthenticationException("clientSecret不正确");
        }else {
            //4通过TokenRequest构造器生成TokenRequest
            tokenRequest = new TokenRequest(new HashMap<>(),clientId,clientDetails.getScope(),"custom");
        }
        //5通过TokenRequest的createOAuth2Request方法获取OAuth2Request;
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        //6通过Authencation和TokenRequest构造出OAuth2Authencation
        OAuth2Authentication auth2Authentication = new OAuth2Authentication(oAuth2Request,authentication);
        //7通过authorizationServerTokenServices生成OAuth@AccessToken
        OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(auth2Authentication);
        //8返回Token
        logger.info("登录成功");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(accessToken));
    }

    private String[] extractAndDecodeHeader(String header,HttpServletRequest request){
        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);

        byte[] decode;

        try {
            decode = Base64.getDecoder().decode(base64Token);
        }catch (IllegalArgumentException var7){
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }
        String token = new String(decode,StandardCharsets.UTF_8);
        int delim = token.indexOf(":");
        if (delim == -1){
            throw new BadCredentialsException("Invalid basic authentication token");
        }else {
            return new String[]{token.substring(0,delim),token.substring(delim+1)};
        }
    }
}
