package com.goodkin.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.goodkin.model.admin.Admin;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AdminDetails implements UserDetails {

    Admin admin;

    //일반로그인
    public AdminDetails(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String getUsername() {
        return admin.getId();
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }


    //계정이 만료되지 않았는지 리턴함 (true: 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있지 않았는 리턴함(true: 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호가 만료되지 않았는지 리턴함 (true : 만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 활성화인지 리턴 (true : 활성화) 
    @Override
    public boolean isEnabled() {
        return true;
    }


    // 계정이 갖고있는 권한 목록을 리턴한다. (권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 우리는 한개만)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();

        collectors.add(() -> {
            return "ROLE_"+admin.getRole().toString();
        });

        return collectors;
    }
    
}
