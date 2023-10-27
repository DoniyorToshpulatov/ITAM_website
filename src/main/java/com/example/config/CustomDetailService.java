package com.example.config;

import com.example.entity.ProfileEntity;
import com.example.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@RequiredArgsConstructor
public class CustomDetailService implements UserDetailsService {
    @Autowired
    private final ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("keldi");
        ProfileEntity byLogin = profileRepository.findByEmail(email);
        if (byLogin == null)
            throw new UsernameNotFoundException("Bad Credentional");
        return new CustomDetails(byLogin);
    }


}
