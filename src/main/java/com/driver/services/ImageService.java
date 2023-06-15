package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();

        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);

        blog.getImageList().add(image);
        blogRepository2.save(blog);

        return image;
    }

    public void deleteImage(Integer id){
        blogRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        String[] dimension = screenDimensions.split("X");
        Integer screenX = Integer.parseInt((dimension[0]));
        Integer screenY = Integer.parseInt((dimension[1]));

        Image image = imageRepository2.findById(id).get();

        String[] imageDimension = image.getDimensions().split("X");
        Integer imageX = Integer.parseInt(imageDimension[0]);
        Integer imageY = Integer.parseInt(imageDimension[1]);

        return (screenX/imageX) * (screenY/imageY);


    }
}





