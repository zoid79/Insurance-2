package Filter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import Provider.CookieProvider;


public class PostFilter extends ZuulFilter{

	
	static final String FILTER_TYPE = "post";
	static final int FILTER_ORDER = 2;
	
//	static final String EUREKA_URL = "http://localhost:7777/eureka/";
//	static final String CUSTOMER_SERVICE = "customer";
//	static final String VALIDATION_TOKEN_URL = "/validation/token";
	
	private CookieProvider cookieProvider;
	
    public PostFilter() {
    	this.cookieProvider = new CookieProvider();
    }
	

	@Override
	public Object run() throws ZuulException {
		
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		HttpServletResponse response = context.getResponse();

		
		String url = request.getRequestURL().toString();
		if(!url.contains("login")) return "notlogin";
		
		
		System.out.println("login end / post filter --");
		
//		System.out.println(response.get);
		
		
	    try (final InputStream responseDataStream = context.getResponseDataStream()) {
	    	JSONObject response_json = null;
	    	JSONParser parser = new JSONParser();

	    	if(responseDataStream == null) {return null;}

	        String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));


//	        context.setResponseBody(responseData);
	        response_json = (JSONObject)parser.parse(responseData);
	        System.out.println("response_json : " + response_json);
	        HashMap<String, String> map = new HashMap<>();
	        System.out.println("!!!");
	        map.put("customer", response_json.toString()); // 오류 지점
//	        map.put("deviceId", response_json.get("deviceId").toString());
	        context.setResponseBody(response_json.toString());
	        context.setResponseBody((new JSONObject(response_json)).toJSONString());
		    
		    System.out.println("쿠키설정 시작!");
	        // 쿠키 설정
		    ResponseCookie refreshTokenCookie = cookieProvider.createRefreshTokenCookie(response_json.get("refreshToken").toString());
		    ResponseCookie accessTokenCookie = cookieProvider.createAccessTokenCookie(response_json.get("accessToken").toString());
//		    ResponseCookie refreshTokenCookie = cookieProvider.createRefreshTokenCookie("refreshToken");

		    
	        Cookie cookie_refresh = cookieProvider.of(refreshTokenCookie);
	        Cookie cookie_access = cookieProvider.of(accessTokenCookie);
	        
	        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//	        response.addHeader("Access-Control-Allow-Credentials", "true");
//	        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
	        response.addCookie(cookie_refresh);
	        response.addCookie(cookie_access);
	        
	        
//	        response.setHeader("Set-Cookie", refreshTokenCookie.toString());
//	        response.setHeader("Set-Cookie", accessTokenCookie.toString());
	        System.out.println("쿠키 추가 완료!");
	        System.out.println("device Id : " + response_json.get("deviceId").toString());
	        
//	        Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
//	        boolean firstHeader = true;
//
//	        for(String header: headers){
//	            if(firstHeader){
//	                response.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s", header, "SameSite=None"));
//	                firstHeader = false;
//	                continue;
//	            }
//	            response.addHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s", header, "SameSite=None"));
//	        }
	        
//	        System.out.println(cookie_access.getMaxAge());
	    } catch (Exception e) {
	    	e.getStackTrace().toString();		
	    }
//        System.out.println("생성된 쿠키 값 : " + cookie.getValue());
		
		return null;
	}

	@Override
	public boolean shouldFilter() {return true;}
	@Override
	public String filterType() {return FILTER_TYPE;}
	@Override
	public int filterOrder() {return FILTER_ORDER;}


}
