package com.example.mandelbrotserver.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/mandelbrot")
public class MandelbrotController {
    @GetMapping("/{hej}")
    public ResponseEntity<byte[]> getMandelbrotImage(){



        return ResponseEntity.ok().build();
    }
}
