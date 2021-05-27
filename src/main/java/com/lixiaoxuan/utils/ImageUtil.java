package com.lixiaoxuan.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author lixiaoxuan
 * @description: 图片操作工具类
 * @date 2021/5/27 14:59
 */
public class ImageUtil {

    public static BufferedImage rotateImage(final BufferedImage image, int angle) {
        int w = image.getWidth();
        int h = image.getHeight();
        int type = image.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(angle), w / 2, h / 2);
        graphics2d.drawImage(image, 0, 0, null);
        graphics2d.dispose();
        return img;
    }


}
