package com.alten.shop.authentication.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alten.shop.authentication.models.dtos.AuthenticationResponse;
import com.alten.shop.authentication.models.dtos.LoginRequest;
import com.alten.shop.authentication.models.dtos.RegisterRequest;
import com.alten.shop.authentication.services.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(path = "/account", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Inscription")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", useReturnTypeSchema = true) })
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterRequest.class)),
            }) RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping(path = "/token", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Connexion")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", useReturnTypeSchema = true) })
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LoginRequest.class)),
            }) LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }

}
