package Filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ict.zuul.Common.Constants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import Provider.CookieProvider;

public class AuthFilter extends ZuulFilter {

	static final String FILTER_TYPE = "pre";
	static final int FILTER_ORDER = 1;

	private CookieProvider cookieProvider;
	private RestTemplate restTemplate;

	public AuthFilter() {
		this.cookieProvider = new CookieProvider();
	}

	@Override
	public Object run() throws ZuulException {
		System.out.println("Auth Filter 시작 ----");

		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		HttpServletResponse response = context.getResponse();

		String url = request.getRequestURL().toString();
		this.restTemplate = new RestTemplate();
		

		// 토큰이 없는 경우 로그인하지 않은 사용자로 간주
		if(url.contains("login")) return null; 
		
		if(request.getCookies() == null) {
			System.out.println("Cookie is null !! ");
			context.addZuulRequestHeader("userId", null);
			return null;
		}
		
		
		for(Cookie cookie : request.getCookies()) {
			System.out.println(cookie.getName());
		}
		
		System.out.println("cookies : " + request.getCookies().length);
				
		Map<String, String> cookies = new HashMap<>();
		for (Cookie cookie : request.getCookies()) {cookies.put(cookie.getName(), cookie.getValue());}

		
		if (url.contains("logout")) {
			// 로그아웃시 토큰 전달 -> 근데 이거 로그아웃 두번 되는데
			// 로그아웃도 토큰 검증이 필요한가?
			System.out.println("logout!!");
			context.addZuulRequestHeader("refresh-token", cookies.get("refresh-token"));
			
			this.logout(response);
			
			return "logout";
			
		} 
		
		try {
			System.out.println("start validation token!");
			if (cookies.containsKey("access-token")) {// 엑세스토큰 쿠키가 널이 아니면 검증
				System.out.println("validation access token");
				JSONObject json = new JSONObject();
				json.put("token", cookies.get("access-token"));

				ResponseEntity<String> result = restTemplate.postForEntity(Constants.AUTH_URL + Constants.VALIDATION_ACCESS_URL, json, String.class);
				
				if(result.getStatusCode() == HttpStatus.OK) {
					System.out.println("userId : " + result.getBody().toString());
					context.addZuulRequestHeader("userId", result.getBody().toString());
				} else {
					context.addZuulRequestHeader("userId", null);
//					throw new Exception();
				}

			} else if (cookies.containsKey("refresh-token")) { // 엑세스토큰 쿠키가 널이면 리프레시 토큰 검증 및 엑세스 토큰 재발급
				System.out.println("validation refresh token");
				
				JSONObject json = new JSONObject();
				json.put("token", cookies.get("refresh-token"));
				json.put("browser", "user-agent");
				
				ResponseEntity<HashMap> result = restTemplate.postForEntity(Constants.AUTH_URL + Constants.VALIDATION_RFERESH_URL, json, HashMap.class);
				
				if(result.getStatusCode() == HttpStatus.OK) {
					System.out.println("userId : " + result.getBody().get("userId").toString());
					context.addZuulRequestHeader("userId", result.getBody().get("userId").toString());
					
					ResponseCookie accessTokenCookie = cookieProvider.createAccessTokenCookie(result.getBody().get("access-token").toString());
					Cookie cookie_access = cookieProvider.of(accessTokenCookie);
					
					response.setContentType(MediaType.APPLICATION_JSON_VALUE);
					response.addCookie(cookie_access);
					
					System.out.println("리프레시 토큰 검증 완료");
				} else {
					context.addZuulRequestHeader("userId", null);
//					System.out.println("result error!!" + result.getStatusCode());
//					throw new Exception();					
				}
				
			} else {
				context.addZuulRequestHeader("userId", null);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			// 토큰 초기화하는 코드 같은데 확인 필요
//			this.initialization_token(restTemplate, request);
			return new ResponseEntity<>("Faild validation Access Token", HttpStatus.FORBIDDEN); // 거부 (인증이 안 되니까)
		}
		
		return null;
	}

	@Override
	public boolean shouldFilter() {return true;}
	@Override
	public String filterType() {return FILTER_TYPE;}
	@Override
	public int filterOrder() {return FILTER_ORDER;}
	
	
	private void logout(HttpServletResponse response) {
	    ResponseCookie refreshTokenCookie = cookieProvider.createRefreshTokenCookie("refreshToken");
	    ResponseCookie accessTokenCookie = cookieProvider.createAccessTokenCookie("accessToken");
	    
        Cookie cookie_refresh = cookieProvider.of(refreshTokenCookie);
        Cookie cookie_access = cookieProvider.of(accessTokenCookie);
        
        cookie_refresh.setMaxAge(0);
        cookie_access.setMaxAge(0);
        
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.addCookie(cookie_refresh);
        response.addCookie(cookie_access);
	}

	// 이건 여기서 안 해줘도 auth에서 해주지 않을까? -> 여기서가 좋나?
	private ResponseEntity<String> initialization_token(RestTemplate restTemplate, HttpServletRequest request) {
		
		Map<String, String> cookies = new HashMap<>();
		for (Cookie cookie : request.getCookies()) {
			cookies.put(cookie.getName(), cookie.getValue());
		}
		
		JSONObject json = new JSONObject();
		json.put("token", cookies.get("refresh-token"));
		
		// refresh token 전달 -> user id로 된 모든 row 삭제
		return restTemplate.postForEntity(Constants.AUTH_URL + Constants.INITIALIZATION_TOKEN_URL, json, String.class);
	}

}
