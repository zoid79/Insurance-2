package Provider;

import javax.servlet.http.Cookie; 

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieProvider {

	private static final long accessTokenExpiredTime  = 60 * 30;  // 30분;
    private static final long refreshTokenExpiredTime  = 60 * 60 * 24 * 14;  // 14일;

    public ResponseCookie createRefreshTokenCookie(String refreshToken) {
    	System.out.println("refreshTokenExpiredTime : " + refreshTokenExpiredTime);
        return ResponseCookie.from("refresh-token", refreshToken)
        		.domain("localhost")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(refreshTokenExpiredTime)
//                .sameSite("None")
                .build();
    }
    
    public ResponseCookie createAccessTokenCookie(String accessToken) {
    	System.out.println("accessTokenExpiredTime : " + accessTokenExpiredTime);
        return ResponseCookie.from("access-token", accessToken)
        		.domain("localhost")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(refreshTokenExpiredTime)
                .build();
    }

    public ResponseCookie removeRefreshTokenCookie() {
        return ResponseCookie.from("refresh-token", null)
                .build();
    }

    public Cookie of(ResponseCookie responseCookie) {
    	System.out.println("second : " + (int) responseCookie.getMaxAge().getSeconds());
        Cookie cookie = new Cookie(responseCookie.getName(), responseCookie.getValue());
        cookie.setDomain(responseCookie.getDomain());
        cookie.setPath(responseCookie.getPath());
        cookie.setSecure(responseCookie.isSecure());
        cookie.setHttpOnly(responseCookie.isHttpOnly());
        cookie.setMaxAge((int) responseCookie.getMaxAge().getSeconds());
//        cookie.setDomain("9999");
        
        return cookie;
    }
}