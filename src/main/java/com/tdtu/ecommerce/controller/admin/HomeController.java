package com.tdtu.ecommerce.controller.admin;

import com.tdtu.ecommerce.dto.RacketDto;
import com.tdtu.ecommerce.mapper.ProductMapper;
import com.tdtu.ecommerce.service.impl.RacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class HomeController {
    @Autowired
    RacketService racketService;

    @Autowired
    ProductMapper productMapper;

    @GetMapping("racket/all")
    public ResponseEntity<List<RacketDto>> getAllActiveTrue(){
        return ResponseEntity.ok(racketService.getAll());
    }

    @PostMapping("racket/save/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> save(@RequestBody List<RacketDto> racketDtos){
        racketService.saveAll(racketDtos);
        return ResponseEntity.ok().build();
    }

    @PostMapping("racket/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> save(@RequestBody RacketDto racketDto){
         racketService.save(racketDto);
         return ResponseEntity.ok().build();
    }

    @PostMapping("racket/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
        racketService.delete(id);
        return ResponseEntity.ok().build();
    }
}
