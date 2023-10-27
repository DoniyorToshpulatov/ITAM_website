package com.example.service;


import com.example.dto.AttachDTO;
import com.example.entity.AttachEntity;
import com.example.exp.AttachException;
import com.example.exp.ItemNotFoundException;
import com.example.repository.AttachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@Service
public class AttachService {


    @Value("${attach.upload.folder}")
    private String attachFolder;
    @Value("${attach.open.url}")
    private String attachOpenUrl;

    @Autowired
    private AttachRepository attachRepository;

    public AttachDTO saveToSystem(MultipartFile file) {
        try {
            // attaches/2022/04/23/UUID.png
            String attachPath = getYmDString(); // 2022/04/23
            String extension = getExtension(file.getOriginalFilename()); // .png....
            String uuid = UUID.randomUUID().toString();
            String fileName = uuid + "." + extension; // UUID.png


            File folder = new File(attachFolder + attachPath);  // attaches/2022/04/23/
            if (!folder.exists()) {
                folder.mkdirs();
            }

            byte[] bytes = file.getBytes();

            Path path = Paths.get(attachFolder + attachPath + "/" + fileName); // attaches/2022/04/23/UUID.png
            Files.write(path, bytes);

            AttachEntity entity = new AttachEntity();
            entity.setId(uuid);
            entity.setOriginalName(file.getOriginalFilename());
            entity.setPath(attachPath);
            entity.setExtension(extension);
            entity.setSize(Files.size(path));
            attachRepository.save(entity);
            AttachDTO attachDTO = toDTO(entity);
            attachDTO.setPath(attachOpenUrl + fileName);
            return attachDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getYmDString() {
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();

        return year + "/" + month + "/" + day; // 2022/04/23
    }





    public String getExtension(String fileName) { // mp3/jpg/npg/mp4.....
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

    private AttachDTO toDTO(AttachEntity entity) {
        AttachDTO attachDTO = new AttachDTO();
        attachDTO.setId(entity.getId());
        attachDTO.setOriginalName(entity.getOriginalName());
        attachDTO.setPath(entity.getPath());
        attachDTO.setSize(entity.getSize());
        return attachDTO;
    }
    public AttachEntity get(String fileName) {
        return attachRepository.findById(fileName).orElseThrow(() -> {
            throw new ItemNotFoundException("Attach not found");
        });
    }
    public byte[] loadImage(String fileName) {
        if (fileName == null || fileName.length() < 1)
            throw  new AttachException("file is null or length less than 1");

        AttachEntity attachEntity = get(fileName);
        String path = attachEntity.getPath();
        byte[] imageInByte;
        BufferedImage originalImage;
        try {
            originalImage = ImageIO.read(new File(attachFolder + path+ "/" + fileName+ "."+attachEntity.getExtension()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, attachEntity.getExtension(), baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public byte[] openGeneral(String fileName) {
        if (fileName == null || fileName.length() < 1)
            throw new AttachException("file is null or length less than 1");

        AttachEntity attachEntity = get(fileName);
        String path = attachEntity.getPath();

        byte[] data;
        try {
            Path file = Paths.get(attachFolder + path + "/" + fileName + "." + attachEntity.getExtension());
            data = Files.readAllBytes(file);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public ResponseEntity<Resource> download(String key) { // images.png
        try {
            AttachEntity entity = get(key);

            if (entity == null) {
                return null;
            }

            String path = entity.getPath() + "/" + key + "." + entity.getExtension();

            Path file = Paths.get(attachFolder + path);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                                "important; filename=\"" + entity.getOriginalName() + "\"")
                        .body(resource);

            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public String delete(String id) {


        Optional<AttachEntity> byId = attachRepository.findById(id);
        if(byId.isEmpty())
            throw new AttachException("id is wrong");

        attachRepository.deleteById(id);
        String path = byId.get().getPath();

        File file = new File(attachFolder + path+ "/" + id+ "."+byId.get().getExtension());
        file.delete();

        return "deleted";
    }

    public PageImpl<AttachDTO> getList(Integer page, Integer size) {
        Page<AttachEntity> all = attachRepository.findAll(PageRequest.of(page, size));
        if(all.isEmpty())
            return null;
        List<AttachDTO> dtoList = new LinkedList<>();
        all.forEach(entity->{
            AttachDTO dto = new AttachDTO();
            dto.setId(entity.getId());
            dto.setSize(entity.getSize());
            dto.setPath(entity.getPath());
            dto.setExtension(entity.getExtension());
            dto.setUrl(attachOpenUrl + entity.getId());
            dto.setOriginalName(entity.getOriginalName());
            dtoList.add(dto);
        });
        PageImpl<AttachDTO> attachDTOList  = new PageImpl(dtoList,PageRequest.of(page,size),all.getTotalElements());
        return attachDTOList;

    }



}
