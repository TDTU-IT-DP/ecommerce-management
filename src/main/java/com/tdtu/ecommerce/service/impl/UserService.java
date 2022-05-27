package com.tdtu.ecommerce.service.impl;

import com.tdtu.ecommerce.dto.JwtResponse;
import com.tdtu.ecommerce.dto.LoginDto;
import com.tdtu.ecommerce.dto.SignupDto;
import com.tdtu.ecommerce.entity.ERole;
import com.tdtu.ecommerce.entity.Role;
import com.tdtu.ecommerce.entity.User;
import com.tdtu.ecommerce.logger.Logger;
import com.tdtu.ecommerce.repository.RoleRepository;
import com.tdtu.ecommerce.repository.UserRepository;
import com.tdtu.ecommerce.security.config.UserDetailsImpl;
import com.tdtu.ecommerce.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private static final Logger logger = Logger.getInstance();

    /**
     *
     * @param loginDto
     * @return
     */
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        logger.info("Login", loginDto.getUsername() + " - " + loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        logger.info(loginDto.getUsername(), "Success");
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    /**
     *
     * @param signupDto
     * @return
     */
    public ResponseEntity<?> registerUser(@RequestBody SignupDto signupDto) {
        logger.info("Register", signupDto.getUsername() +" - "+ signupDto.getPassword());
        if (userRepository.existsByUsername(signupDto.getUsername())) {
            logger.error(signupDto.getUsername(), "is exists");
            return ResponseEntity
                    .badRequest()
                    .body("Username is exists");
        }

        if (userRepository.existsByEmail(signupDto.getEmail())) {
            logger.error(signupDto.getEmail(), "is exists");
            return ResponseEntity
                    .badRequest()
                    .body("Email is exists");
        }

        // Create new user's account
        User user = new User(signupDto.getUsername(),
                signupDto.getEmail(),
                encoder.encode(signupDto.getPassword()));

        Set<String> strRoles = signupDto.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        logger.info("Register success", signupDto.getUsername() + " - " + signupDto.getPassword() + " - " + signupDto.getEmail());
        return ResponseEntity.ok("User registered successfully!");
    }
}
