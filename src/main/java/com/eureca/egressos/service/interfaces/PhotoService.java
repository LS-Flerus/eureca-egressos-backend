package com.eureca.egressos.service.interfaces;

import com.eureca.egressos.dto.PhotoDto;

import java.util.List;
import java.util.UUID;

public interface PhotoService {
    PhotoDto createPhoto(PhotoDto photoDto);
    PhotoDto updatePhoto(UUID id, PhotoDto photoDto);
    void deletePhoto(UUID id);
    PhotoDto getPhotoById(UUID id);
    List<PhotoDto> getAllPhotos();
    List<PhotoDto> getPhotosByPlaqueId(UUID plaqueId);
}