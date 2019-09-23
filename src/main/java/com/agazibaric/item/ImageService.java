package com.agazibaric.item;

import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(Long recipeId, MultipartFile file);

    public Resource loadItemImage(Long itemId);

    public void init();

}
