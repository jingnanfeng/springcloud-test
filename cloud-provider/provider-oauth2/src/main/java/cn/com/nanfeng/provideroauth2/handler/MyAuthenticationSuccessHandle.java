package cn.com.nanfeng.provideroauth2.handler;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
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
 * @date 2019-09-24 21:13
 */
@Component
public class MyAuthenticationSuccessHandle implements AuthenticationSuccessHandler {

    private static final Logger logger =  LoggerFactory.getLogger(MyAuthenticationSuccessHandle.class);

    @Resource
    private ClientDetailsService clientDetailsService;
    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //从请求头中获取ClientId
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Basic ")){
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }
        String[] tokens = this.extractAndDecodeHeader(header,request);
        String clientId = tokens[0];
        String clientSecret = tokens[1];

        TokenRequest tokenRequest = null;
        //通过ClientDetailsService获取ClientDetails
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        //校验ClientId和ClientSecret的正确性
        if (clientDetails == null){
            throw new UnapprovedClientAuthenticationException("clientId:"+clientId+"对应的信息不正确");
        }else  if (!StringUtils.equals(clientDetails.getClientSecret(),clientSecret)){
            throw new UnapprovedClientAuthenticationException("clientSecret不正确");
        }else {
            //通过TokenRequest构造器生成TokenRequest
            tokenRequest = new TokenRequest(new HashMap<>(),clientId,clientDetails.getScope(),"custom");
        }
        //通过TokenRequest的createOAuth2Request获取OAuth2Request
        OAuth2Request auth2Request = tokenRequest.createOAuth2Request(clientDetails);
        //通过Authentication和OauthRequest构造出OAuth2Authentication
        OAuth2Authentication Auth2Authentication = new OAuth2Authentication(auth2Request,authentication);
        //通过AuthorizationServerTokenService生成OAuth2AccessToken
        OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(Auth2Authentication);
        //返回toekn
        logger.info("登录成功");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(token));
    }


    private String[] extractAndDecodeHeader(String header,HttpServletRequest request){
        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decode;
        try {
            decode = Base64.getDecoder().decode(base64Token);
        }catch (IllegalArgumentException e){
            throw new BadCredentialsException("Filed to decode basic authentication token");
        }
        String token = new String(decode,StandardCharsets.UTF_8);
        int delim = token.indexOf(":");
        if (delim == -1){
            throw new BadCredentialsException("Incalid basic authentication token");
        }else {
            return new String[]{token.substring(0,delim),token.substring(delim+1)};
        }
    }
}
