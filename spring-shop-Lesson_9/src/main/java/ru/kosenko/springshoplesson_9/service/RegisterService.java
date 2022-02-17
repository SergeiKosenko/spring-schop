package ru.kosenko.springshoplesson_9.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kosenko.springshoplesson_9.model.RegistrationToken;
import ru.kosenko.springshoplesson_9.repository.AuthorityRepository;
import ru.kosenko.springshoplesson_9.repository.RegistrationTokenRepository;
import ru.kosenko.springshoplesson_9.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Service
public class RegisterService  implements UserDetailsService {

    public RegisterService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthorityRepository authorityRepository, RegistrationTokenRepository registrationTokenRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authorityRepository = authorityRepository;
        this.registrationTokenRepository = registrationTokenRepository;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthorityRepository authorityRepository;
    private final RegistrationTokenRepository registrationTokenRepository;
    private final EmailService emailService;

    // TODO Рефактор: вынести sighUp и confirmRegistration RegisterService
    @Transactional
    public String sighUp(String email, String password) {
        boolean userExist = userRepository.findByEmail(email).isPresent();
        if (userExist) {
            throw new IllegalStateException("Пользователь уже существует");
        }
        var user = new ru.kosenko.springshoplesson_9.model.User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEnabled(false);
        user.setAuthorities(Set.of(authorityRepository.findByName("ROLE_USER")));
        userRepository.save(user);

        String tokenUid = UUID.randomUUID().toString();
        registrationTokenRepository.save(new RegistrationToken(tokenUid, LocalDateTime.now().plusMinutes(15), user));

        emailService.createMailMessage(email, tokenUid);

        return tokenUid;
    }

    @Transactional
    public boolean confirmRegistration(String token) {
        var user = registrationTokenRepository.findUserByToken(LocalDateTime.now(), token);
        if (user.isEmpty()) {
            return false;
        }
        user.ifPresent(u -> u.setEnabled(true));
        return true;
    }

}
