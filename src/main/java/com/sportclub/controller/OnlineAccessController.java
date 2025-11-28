package com.sportclub.controller;

import com.sportclub.model.OnlineAccess;
import com.sportclub.service.OnlineAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/online-access")
@RequiredArgsConstructor
public class OnlineAccessController {

    private final OnlineAccessService onlineAccessService;

    @PostMapping("/create")
    public ResponseEntity<OnlineAccess> create(@RequestBody OnlineAccess access) {
        return ResponseEntity.ok(onlineAccessService.create(access));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OnlineAccess> getById(@PathVariable Long id) {
        return ResponseEntity.ok(onlineAccessService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OnlineAccess>> getAll() {
        return ResponseEntity.ok(onlineAccessService.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OnlineAccess> update(
            @PathVariable Long id,
            @RequestBody OnlineAccess access) {
        return ResponseEntity.ok(onlineAccessService.update(id, access));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        onlineAccessService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
