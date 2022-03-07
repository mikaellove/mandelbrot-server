package com.example.mandelbrotserver.controllers;

import com.example.mandelbrotserver.services.MandelbrotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
public class MandelbrotController {

    private final com.example.mandelbrotserver.services.MandelbrotService MandelbrotService;

    @Autowired
    public MandelbrotController(MandelbrotService service) {
        this.MandelbrotService = service;
    }

    @GetMapping("mandel/{height}/{width}/{max}/{ports}")
    public ResponseEntity<byte[]> getTest(
            @PathVariable("height") int height,
            @PathVariable("width") int width,
            @PathVariable("ports") List<Integer> ports,
            @PathVariable("max") int max)
    {
        byte[] imageInByte = MandelbrotService.generateImage(width,height,max,ports);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"image.png")
                        .contentType(MediaType.IMAGE_PNG)
                        .body(imageInByte);
    }
}
