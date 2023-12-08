package com.senai.Mobili.Services;

import com.senai.Mobili.Repositories.ParceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    ParceiroRepository parceiroRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = parceiroRepository.findByEmail(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario não encontrado");
        }
        return usuario;
    }
}
