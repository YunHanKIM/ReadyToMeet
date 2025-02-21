package com.final2.readytomeet.repository.impl;



import com.final2.readytomeet.Mapper.impl.ImageMapper;
import com.final2.readytomeet.dto.ImageUploadDto;
import com.final2.readytomeet.repository.ImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;


@Service
public class ImageRepositoryImpl implements ImageRepository {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public void write(ImageUploadDto dto, MultipartFile file) throws Exception {
        String projectPath = "C:/files/";
        UUID uuid = UUID.randomUUID();

        if(file !=null && !file.isEmpty()) {
            String filename = uuid + "_" + file.getOriginalFilename();
            File saveFile = new File(projectPath, filename);
            file.transferTo(saveFile);
            dto.setFilename(filename);
            dto.setFilepath("/download/" + filename);
        }

        imageMapper.write(dto);
    }
}
