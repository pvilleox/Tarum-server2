package com.tarum.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphicUtils {

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        // Create a new blank BufferedImage with the target dimensions
        // Use TYPE_INT_ARGB to preserve transparency (PNGs) or TYPE_INT_RGB for smaller size (JPGs)
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);

        // Create a Graphics2D object to draw onto the new image
        Graphics2D graphics2D = resizedImage.createGraphics();

        // Configure rendering hints for high-quality scaling
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // TODO: Remove ?
        graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        // Draw the original image scaled into the new dimensions
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);

        // Clean up graphics resources
        graphics2D.dispose();

        return resizedImage;
    }

}
