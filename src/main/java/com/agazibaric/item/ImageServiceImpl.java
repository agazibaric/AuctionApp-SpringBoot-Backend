package com.agazibaric.item;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ItemRepo itemRepo;

    @Value("${item.image.root.dir}")
    private String ROOT_DIR;

    private static final Path IMAGE_DIR = Paths.get("images/item/");

    @Override
    public void saveImageFile(Long itemId, MultipartFile file) {
        Item item = itemRepo.findById(itemId).get();
        String imageName = ItemUtil.getFullImageName(item, file.getOriginalFilename());

        try {
            BufferedImage imBuff = ImageIO.read(file.getInputStream());
            BufferedImage rescaledImg = Scalr.resize(imBuff, 200);
            ImageIO.write(rescaledImg, "png", IMAGE_DIR.resolve(imageName).toFile());
            //Files.copy(rescaledImg, IMAGE_DIR.resolve(imageName));

        } catch (IOException e) {
            System.out.println(".......................IOException............................");
            e.printStackTrace();
            return;
        }

        item.getImages().add(imageName);
        itemRepo.save(item);
    }

    @Override
    public Resource loadItemImage(Long itemId) {
        Item item = itemRepo.findById(itemId).get();
        List<String> images = item.getImages();
        if (images.isEmpty()) return null;

        String imageName = images.get(0);
        Path imageFile = IMAGE_DIR.resolve(imageName);
        try {
            Resource resource = new UrlResource(imageFile.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(IMAGE_DIR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
