package com.naprojects.shoppingcart.controller;

import com.naprojects.shoppingcart.dto.ImageDto;
import com.naprojects.shoppingcart.exceptions.ResourceNotFoundException;
import com.naprojects.shoppingcart.model.Image;
import com.naprojects.shoppingcart.response.ApiResponse;
import com.naprojects.shoppingcart.service.image.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/images")
public class ImageController {
    private final IImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImage(@RequestParam List<MultipartFile> files,@RequestParam Long productId){
        try {
            List<ImageDto> imageDtos = imageService.saveImage(files,productId);
            return ResponseEntity.ok(new ApiResponse("Upload success!",imageDtos));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Upload failed!",e.getMessage()));
        }
    }

    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException{
        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1,(int)image.getImage().length()));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+image.getFileName()+"\"")
                .body(resource);
    }

    @PutMapping("/image/{imageId}/update")
    public ResponseEntity<ApiResponse> updateImage(@PathVariable Long imageId, @RequestBody MultipartFile file){
        try{
            Image image = imageService.getImageById(imageId);
            if(image!=null){
                imageService.updateImage(file,imageId);
                return ResponseEntity.ok(new ApiResponse("Updated!",null));
            }
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("update failed",HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/image/{imageId}/delete")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long imageId){
        try{
            Image image = imageService.getImageById(imageId);
            if(image!=null){
                imageService.deleteImageById(imageId);
                return ResponseEntity.ok(new ApiResponse("Deleted!",null));
            }
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("delete failed",HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
