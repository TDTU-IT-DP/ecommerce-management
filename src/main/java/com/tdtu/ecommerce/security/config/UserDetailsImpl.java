package com.tdtu.ecommerce.security.config;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private UserDetailsImpl(UserBuilder userBuilder) {
        this.id = userBuilder.id;
        this.username = userBuilder.username;
        this.email = userBuilder.email;
        this.password = userBuilder.password;
        this.authorities = userBuilder.authorities;
    }

//  public static UserDetailsImpl build(User user) {
//    List<GrantedAuthority> authorities = user.getRoles().stream()
//        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
//        .collect(Collectors.toList());
//
//    return new UserDetailsImpl(
//        user.getId(),
//        user.getUsername(),
//        user.getEmail(),
//        user.getPassword(),
//        authorities);
//  }

    public static class UserBuilder{
        private Long id;

        private String username;

        private String email;

        private String password;

        private Collection<? extends GrantedAuthority> authorities;

        public UserBuilder(String username, String password, Collection<? extends GrantedAuthority> authorities){
            this.username = username;
            this.password = password;
            this.authorities = authorities;
        }

        public UserBuilder id(Long id){
            this.id = id;
            return this;
        }

        public UserBuilder email(String email){
            this.email = email;
            return this;
        }

        public UserDetailsImpl build(){
            return new UserDetailsImpl(this);
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
