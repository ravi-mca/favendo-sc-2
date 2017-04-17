package com.favendo.user.ws.rest.builder;

import com.favendo.commons.domain.Role;
import com.favendo.user.ws.rest.dto.AuthenticationFailureDto;
import com.favendo.user.ws.rest.dto.AuthenticationSuccessDto;
import com.favendo.user.ws.rest.dto.RoleDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationBuilder {

    public AuthenticationSuccessDto buildAuthenticationSuccess(List<Role> roles, String token) {
        AuthenticationSuccessDto authenticationSuccessDTO = new AuthenticationSuccessDto();
        authenticationSuccessDTO.setAccessToken(token);
        authenticationSuccessDTO.setRoles(roles.stream().map(this::buildRoles).collect(Collectors.toList()));
        return authenticationSuccessDTO;
    }

    public AuthenticationFailureDto buildAuthenticationFailure(String errorMessage) {
        AuthenticationFailureDto authenticationFailureDto = new AuthenticationFailureDto();
        authenticationFailureDto.setErrorMesesag(errorMessage);
        return authenticationFailureDto;
    }

    private RoleDto buildRoles(Role roles) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRole(roles.getRoleName());
        return roleDto;
    }
}
