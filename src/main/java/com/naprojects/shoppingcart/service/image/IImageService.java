package com.naprojects.shoppingcart.service.image;

import com.naprojects.shoppingcart.dto.ImageDto;
import com.naprojects.shoppingcart.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImage(List<MultipartFile> file, Long productId);
    void updateImage(MultipartFile file, Long productId);
}
