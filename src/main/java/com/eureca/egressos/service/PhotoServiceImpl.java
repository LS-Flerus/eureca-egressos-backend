package com.eureca.egressos.service;

import com.eureca.egressos.dto.PhotoDto;
import com.eureca.egressos.model.PlaqueModel;
import com.eureca.egressos.model.PhotoModel;
import com.eureca.egressos.repository.PhotoRepository;
import com.eureca.egressos.service.interfaces.PhotoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    @Transactional
    public PhotoDto createPhoto(PhotoDto photoDto) {
        PlaqueModel plaque = null;
        if (photoDto.getPlaqueId() != null) {
            plaque = PlaqueModel.builder().id(photoDto.getPlaqueId()).build();
        }

        PhotoModel photo = PhotoModel.builder()
                .photoId(photoDto.getPhotoId())
                .plaque(plaque)
                .mainPhoto(photoDto.isMainPhoto())
                .build();

        photoRepository.save(photo);
        return photo.toDto();
    }

    @Override
    @Transactional
    public PhotoDto updatePhoto(UUID id, PhotoDto photoDto) {
        PhotoModel existing = photoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Photo not found"));

        existing.setPhotoId(photoDto.getPhotoId());

        if (photoDto.getPlaqueId() != null) {
            existing.setPlaque(PlaqueModel.builder().id(photoDto.getPlaqueId()).build());
        } else {
            existing.setPlaque(null);
        }

        return photoRepository.save(existing).toDto();
    }

    @Override
    @Transactional
    public void deletePhoto(UUID id) {
        photoRepository.deleteById(id);
    }

    @Override
    public PhotoDto getPhotoById(UUID id) {
        return photoRepository.findById(id)
                .map(PhotoModel::toDto)
                .orElseThrow(() -> new RuntimeException("Photo not found"));
    }

    @Override
    public List<PhotoDto> getAllPhotos() {
        return photoRepository.findAll().stream()
                .map(PhotoModel::toDto)
                .toList();
    }

    @Override
    public List<PhotoDto> getPhotosByPlaqueId(UUID plaqueId) {
        return photoRepository.findByPlaqueId(plaqueId).stream()
                .map(PhotoModel::toDto)
                .toList();
    }
}
