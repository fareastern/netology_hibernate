package ru.netology.netology_hibernate.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/secure")
public class SecureMethodController {

    @GetMapping("/read")
    @Secured("ROLE_READ")
    public String readOnly() {
        return "Читатель";
    }

    @GetMapping("/write")
    @RolesAllowed("ROLE_WRITE")
    public String writeOnly() {
        return "Писатель";
    }

    @GetMapping("/write-or-delete")
    @PreAuthorize("hasRole('WRITE') or hasRole('DELETE')")
    public String writeOrDelete() {
        return "Писатель с расширенными правами";
    }

    @GetMapping("/check-user")
    @PreAuthorize("#username == authentication.principal.username")
    public String checkUser(@RequestParam String username) {
        return "Привет, " + username + "! Вы владелец";
    }
}