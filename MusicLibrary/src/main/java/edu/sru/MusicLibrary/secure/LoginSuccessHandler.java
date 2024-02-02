package edu.sru.MusicLibrary.secure;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import edu.sru.MusicLibrary.domain.user.User;
import edu.sru.MusicLibrary.repository.user.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TwoFactorAuthentication twoFactorService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String username = ((UserDetails) authentication.getPrincipal()).getUsername();
		User user = userRepository.findByUsername(username);

		if (user.isTwoFactorEnabled()) {
			// Set a session attribute or some flag to indicate 2FA is required
			twoFactorService.send2FACode(user);
			request.getSession().setAttribute("tempUsername", username);
			// Redirect to the 2FA page
			getRedirectStrategy().sendRedirect(request, response, "/verify2FAPage");
		} else if (!user.isTwoFactorEnabled()) {
			getRedirectStrategy().sendRedirect(request, response, "/homePage");
		} else {
			// Proceed with the default success handler behavior
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}
}
