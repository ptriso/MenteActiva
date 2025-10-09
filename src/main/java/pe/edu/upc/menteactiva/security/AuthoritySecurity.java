package pe.edu.upc.menteactiva.security;

import org.springframework.security.core.GrantedAuthority;
import pe.edu.upc.menteactiva.entities.Authority;

public class AuthoritySecurity implements GrantedAuthority {

    private Authority authority;

    public AuthoritySecurity() {
    }

    public AuthoritySecurity(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority.getName();
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "AuthoritySecurity{" +
                "authority=" + authority +
                '}';
    }
}
