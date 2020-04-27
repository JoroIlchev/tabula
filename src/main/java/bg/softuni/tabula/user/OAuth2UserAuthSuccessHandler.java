package bg.softuni.tabula.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2UserAuthSuccessHandler implements AuthenticationSuccessHandler {

  @Autowired
  private UserService userService;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {

    if (authentication instanceof OAuth2AuthenticationToken) {
      OAuth2AuthenticationToken oAuth2AuthenticationToken =
          (OAuth2AuthenticationToken)authentication;

      String email =
          oAuth2AuthenticationToken.
              getPrincipal().
              getAttribute("email");

      userService.getOrCreateUser(email);
      //TODO - clean up authentication...
    } else {
      //TODO - should not be here, maybe clean up auth and move on to login page.
    }

  }
}
