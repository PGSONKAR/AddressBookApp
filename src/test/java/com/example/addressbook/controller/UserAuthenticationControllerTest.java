package com.example.addressbook.controller;

import com.example.addressbook.dto.*;
import com.example.addressbook.exception.UserException;
import com.example.addressbook.interfaces.IUserAuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserAuthenticationControllerTest {

    @InjectMocks
    private UserAuthenticationController userAuthenticationController;

    @Mock
    private IUserAuthenticationService userAuthenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register() throws Exception {
        UserAuthenticationDTO userDTO = new UserAuthenticationDTO();
        userDTO.setEmail("test@example.com");

        when(userAuthenticationService.register(any(UserAuthenticationDTO.class)))
                .thenReturn(userDTO);

        ResponseEntity<ResponseDTO<?>> response = userAuthenticationController.register(userDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User details is submitted!", response.getBody().getMessage());
    }

    @Test
    void login() throws UserException {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("test@example.com");
        loginDTO.setPassword("password");

        when(userAuthenticationService.login(any(LoginDTO.class)))
                .thenReturn("mockSessionToken");

        ResponseEntity<ResponseDTO<?>> response = userAuthenticationController.login(loginDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login successfully!!", response.getBody().getMessage());
    }

    @Test
    void resetPassword() throws UserException {
        ResetPasswordDTO resetPasswordDTO = new ResetPasswordDTO();
        resetPasswordDTO.setNewPassword("newPassword");
        resetPasswordDTO.setConfirmPassword("newPassword");

        when(userAuthenticationService.resetPassword(any(String.class), any(ResetPasswordDTO.class)))
                .thenReturn("Password reset successful");

        ResponseEntity<ResponseDTO<?>> response = userAuthenticationController.resetPassword("mockResetToken", resetPasswordDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Password reset successfully!!", response.getBody().getMessage());
    }

    @Test
    void forgotPassword() throws UserException {
        ForgotPasswordDTO forgotPasswordDTO = new ForgotPasswordDTO();
        forgotPasswordDTO.setEmail("test@example.com");

        when(userAuthenticationService.forgotPassword(any(ForgotPasswordDTO.class)))
                .thenReturn("Forgot password email sent");

        ResponseEntity<ResponseDTO<?>> response = userAuthenticationController.forgotPassword(forgotPasswordDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Forgot password successfully!!", response.getBody().getMessage());
    }

    @Test
    void changePassword() throws UserException {
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        changePasswordDTO.setNewPassword("newPassword");
        changePasswordDTO.setConfirmPassword("newPassword");

        when(userAuthenticationService.changePassword(any(String.class), any(ChangePasswordDTO.class)))
                .thenReturn("Password changed successfully");

        ResponseEntity<ResponseDTO<?>> response = userAuthenticationController.changePassword("mockSessionToken", changePasswordDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Password changed successfully!!", response.getBody().getMessage());
    }
}