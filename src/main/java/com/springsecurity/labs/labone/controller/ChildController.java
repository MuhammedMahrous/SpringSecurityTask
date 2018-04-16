package com.springsecurity.labs.labone.controller;

import com.springsecurity.labs.labone.service.MyService;
import com.springsecurity.labs.labone.service.ServiceInt;
import com.springsecurity.labs.labone.util.SpringContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import java.util.ArrayList;
import java.util.List;

public class ChildController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField role;

    private SecurityContext securityContext;

    public void doSomething(ActionEvent actionEvent) {
        ApplicationContext applicationContext = SpringContext.getApplicationContext();
        ServiceInt myService = (ServiceInt) applicationContext.getBean("myService");

        String userName = username.getText();
        String pass = password.getText();
        String rol = role.getText();
        if (userName == null || pass == null || rol == null) {
            return;
        }
        GrantedAuthority grantedAuthority = new GrantedAuthorityImpl(rol);
        ArrayList<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(grantedAuthority);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(userName, pass, grantedAuthorityList);

        AuthenticationManager authenticationManager = applicationContext.getBean(AuthenticationManager.class);

        try {
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            boolean isAuthenticated = false;
            authentication.isAuthenticated();
            if (authentication.isAuthenticated()) {
                securityContext = new SecurityContextImpl();
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);
                try {
                    myService.doSomething();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Successfully Executed doSomething.");
                    alert.showAndWait();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("ERROR 401.7");
                    alert.setContentText("ACCESS DENIED!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR 401");
                alert.setContentText("NOT AUTHENTICATED!");
                alert.showAndWait();
            }
        } catch (Throwable e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR 401");
            alert.setContentText("NOT AUTHENTICATED!");
            alert.showAndWait();
        }

    }
}
