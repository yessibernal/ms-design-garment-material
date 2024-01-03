package com.innter.msdesigngarmentmaterial.controllers;

import com.innter.msdesigngarmentmaterial.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/healthController")
public class healthController {

    @Autowired
    private ResponseUtils responseUtils;

    @GetMapping("/health")
    @PreAuthorize("hasAnyRole ('DESING_READ','ADMIN')")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }
}
