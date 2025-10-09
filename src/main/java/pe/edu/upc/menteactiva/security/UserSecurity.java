package pe.edu.upc.menteactiva.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pe.edu.upc.menteactiva.entities.User;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserSecurity implements UserDetails {

    private User user;

    public UserSecurity() {
    }

    public UserSecurity(User user) {
        this.user = user;
    }

    // IMPORTANTE: Adaptar para tu estructura con User_Authority
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getUser_authority()
                .stream()
                .map(ua -> new AuthoritySecurity(ua.getAuthority()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserSecurity{" +
                "user=" + user +
                '}';
    }
}
