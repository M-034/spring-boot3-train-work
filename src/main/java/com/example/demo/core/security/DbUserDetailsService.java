package com.example.demo.core.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginUser;
import com.example.demo.web.mapper.LoginUserMapper;

import lombok.RequiredArgsConstructor;


/**
 * 本番用
 * DBで管理しているユーザ情報を取得するクラス
 * 
 * application.propertiesファイルに「web.security.db.auth=true」の場合に本クラスが動作する。
 */
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "web.security.db.auth")
public class DbUserDetailsService implements UserDetailsService {

    private final LoginUserMapper mapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 本番向けの強度をデフォルト10から13に変更
        return new BCryptPasswordEncoder(13);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails user = findUserFromDb(username);
        return user;
    }

    /**
     * ユーザ名に合致するユーザを取得する
     */
    private UserDetails findUserFromDb(String username) {
        LoginUser loginUser = mapper.findByUserName(username);

        if (loginUser == null) {
            throw new UsernameNotFoundException(username);
        } else {
            //ユーザロールを付与する
            return User.builder()
            .username(loginUser.getUserName())
            .password(passwordEncoder().encode(loginUser.getPassword()))
            .roles(loginUser.getAuthority())
            .build();
        }
    }  
}
