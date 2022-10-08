package ru.tagallteam.hackstarter.application.auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tagallteam.hackstarter.application.auth.domain.Token;
import ru.tagallteam.hackstarter.application.auth.domain.TokenRepository;
import ru.tagallteam.hackstarter.application.auth.mapper.AuthMapper;
import ru.tagallteam.hackstarter.application.auth.model.AuthDto;
import ru.tagallteam.hackstarter.application.auth.model.RegistrationDto;
import ru.tagallteam.hackstarter.application.auth.model.TokenDto;
import ru.tagallteam.hackstarter.application.auth.service.AuthService;
import ru.tagallteam.hackstarter.application.lvl.domain.LvlRepository;
import ru.tagallteam.hackstarter.application.user.domain.User;
import ru.tagallteam.hackstarter.application.user.domain.UserRepository;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;
import ru.tagallteam.hackstarter.integration.modal.WalletDto;
import ru.tagallteam.hackstarter.integration.service.VtbIntegration;
import ru.tagallteam.hackstarter.utils.JwtUtils;
import ru.tagallteam.hackstarter.utils.TokenType;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final TokenRepository tokenRepository;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final JwtUtils jwtUtils;

    private final LvlRepository lvlRepository;

    private final AuthMapper authMapper;

    private final VtbIntegration integration;

    @Override
    public TokenDto authorization(AuthDto authDto) {
        ErrorDescriptor.USER_AUTH_PROBLEM.throwIsFalse(userRepository.existsByEmail(authDto.getLogin()));
        User user = userRepository.getUserByEmail(authDto.getLogin())
                .orElseThrow(ErrorDescriptor.USER_AUTH_PROBLEM::applicationException);
        ErrorDescriptor.USER_AUTH_PROBLEM.throwIsFalse(passwordEncoder.matches(authDto.getPassword(),
                user.getPassword()));
        Token token = new Token();
        token.setToken(jwtUtils.generateToken(TokenType.ACCESS, user.getEmail()));
        token.setUser(user);
        tokenRepository.save(token);
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccessToken(token.getToken());
        tokenDto.setAuthToken(jwtUtils.generateToken(TokenType.AUTH, user.getEmail()));
        return tokenDto;

    }

    @Override
    public TokenDto registration(RegistrationDto registrationDto) {
        ErrorDescriptor.USER_IS_CREATED.throwIsTrue(userRepository.existsByEmail(registrationDto.getEmail()));
        User user = authMapper.convertToUserRegistration(registrationDto);
        user.setLvl(lvlRepository.getById(1L));
        user.setXp(0L);
        WalletDto walletDto = integration.createWallet();
        user.setPrivateKey(walletDto.getPrivateKey());
        user.setPublicKey(walletDto.getPublicKey());
        user = userRepository.save(user);
        Token token = new Token();
        token.setToken(jwtUtils.generateToken(TokenType.ACCESS, user.getEmail()));
        token.setUser(user);
        tokenRepository.save(token);
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccessToken(token.getToken());
        tokenDto.setAuthToken(jwtUtils.generateToken(TokenType.AUTH, user.getEmail()));
        return tokenDto;
    }

    @Override
    public void logout(String accessToken) {
        ErrorDescriptor.USER_TOKEN_ACCESS_NOT_FOUND.throwIsFalse(tokenRepository.existsByToken(accessToken));
        tokenRepository.delete(tokenRepository.getByToken(accessToken)
                .orElseThrow(ErrorDescriptor.USER_LOGOUT_LAST::applicationException));
    }

    @Override
    public TokenDto updateToken(String accessToken) {
        ErrorDescriptor.USER_TOKEN_ACCESS_NOT_FOUND.throwIsFalse(tokenRepository.existsByToken(accessToken));
        Token token = tokenRepository.getByToken(accessToken)
                .orElseThrow(ErrorDescriptor.USER_TOKEN_ACCESS_NOT_FOUND::applicationException);
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccessToken(token.getToken());
        tokenDto.setAuthToken(jwtUtils.generateToken(TokenType.AUTH, token.getUser().getEmail()));
        return tokenDto;
    }
}
