package com.eureca.egressos.controller;

import com.eureca.egressos.controller.documentation.PhotoController;
import com.eureca.egressos.dto.PhotoDto;
import com.eureca.egressos.service.interfaces.PhotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/photos")
public class PhotoControllerImpl implements PhotoController {

    private final PhotoService photoService;

    public PhotoControllerImpl(PhotoService photoService) {
        this.photoService = photoService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<PhotoDto> createPhoto(@RequestBody PhotoDto photoDto) {
        return ResponseEntity.ok(photoService.createPhoto(photoDto));
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<PhotoDto> updatePhoto(@RequestParam UUID id, @RequestBody PhotoDto photoDto) {
        return ResponseEntity.ok(photoService.updatePhoto(id, photoDto));
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePhoto(@RequestParam UUID id) {
        photoService.deletePhoto(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/getById")
    public ResponseEntity<PhotoDto> getPhotoById(@RequestParam UUID id) {
        return ResponseEntity.ok(photoService.getPhotoById(id));
    }

    @Override
    @GetMapping("/getALl")
    public ResponseEntity<List<PhotoDto>> getAllPhotos() {
        return ResponseEntity.ok(photoService.getAllPhotos());
    }

    @Override
    @GetMapping("/getAllByPlaque")
    public ResponseEntity<List<PhotoDto>> getPhotosByPlaqueId(@RequestParam UUID plaqueId) {
        return ResponseEntity.ok(photoService.getPhotosByPlaqueId(plaqueId));
    }
}