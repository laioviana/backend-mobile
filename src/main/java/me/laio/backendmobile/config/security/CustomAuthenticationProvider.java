package me.laio.backendmobile.config.security;

import me.laio.backendmobile.MessageKey;
import me.laio.backendmobile.Messages;
import me.laio.backendmobile.domain.User;
import me.laio.backendmobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        String password = (String) usernamePasswordAuthenticationToken.getCredentials();
        loginPreConditions(username, password);
        User user = userService.findUserByLogin(username);
        loginPostConditions(user, password);
        return user;
    }

    private void loginPreConditions(String username, String password) {
        if (StringUtils.isEmpty(username)) {
            setHideUserNotFoundExceptions(false);
            throw new UsernameNotFoundException(Messages.getString(MessageKey.USERNAME_NOT_FOUND_MESSAGE));
        }

        if (password == null || StringUtils.isEmpty(password)) {
            throw new BadCredentialsException(Messages.getString(MessageKey.BAD_CREDENTIALS_MESSAGE));
        }
    }

    private void loginPostConditions(User user, String password) {
        if (user.equals(null) || !encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException(Messages.getString(MessageKey.INVALID_USER_OR_PASSWORD));
        }

        if (!user.isEnabled()) {
            throw new BadCredentialsException(Messages.getString(MessageKey.DISABLE_USER_MESSAGE));
        }
    }
}
