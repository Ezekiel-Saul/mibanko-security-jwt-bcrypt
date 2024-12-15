    package com.mibanko.security_access.config;

    import com.mibanko.security_access.entity.Authority;
    import com.mibanko.security_access.entity.User;
    import com.mibanko.security_access.repo.AuthorityRepo;
    import com.mibanko.security_access.repo.UserRepo;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

    import java.util.Collection;
    import java.util.List;
    import java.util.stream.Collectors;


    public class MyUserDetails implements UserDetails {


        private final User user;
        @Autowired
        private UserRepo userRepo;

        public MyUserDetails(User user) {
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<Authority> authorities = user.getAuthority();
            System.out.println(authorities.toString());
            // Map each Authority object into a GrantedAuthority (which is now handled by Authority implementing GrantedAuthority)
            return authorities.stream()
                    .map(Authority::getAuthority)  // Simply call getAuthority() method
                    .map(SimpleGrantedAuthority::new)  // Convert to SimpleGrantedAuthority
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
           return user.isActive();
        }
    }
