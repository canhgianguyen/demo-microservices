package com.example.authservice.security;

import com.example.authservice.entity.User;
import com.example.authservice.model.dto.UserSecurityDto;
import com.example.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        if (user != null) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole());
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            grantedAuthorityList.add(grantedAuthority);
            return new UserSecurityDto(user.getId(), user.getUsername(), user.getPassword(), grantedAuthorityList);
        }
        throw new UsernameNotFoundException("Username: " + username + " not found!");
    }
}
