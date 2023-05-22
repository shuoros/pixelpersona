package io.github.shuoros.pixelpersona.web.rest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AvatarController {

    @GetMapping(path = "/avatar", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] avatar() throws IOException {
        Random rnd = new Random();
        int[][][] pixels = new int[4][4][3];
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                pixels[y][x][0] = rnd.nextInt(255);
                pixels[y][x][1] = rnd.nextInt(255);
                pixels[y][x][2] = rnd.nextInt(255);
            }
        }
        BufferedImage img = new BufferedImage(4, 4, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                Color color = new Color(pixels[y][x][0], pixels[y][x][1], pixels[y][x][2]);
                img.setRGB(x, y, color.getRGB());
            }
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        return baos.toByteArray();
    }
}
