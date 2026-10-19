package com.tarum.io.content;
import com.tarum.graphics.GraphicUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Used for loading/unloading assets for our game,
 * such as the sprites for our renderer.
 */
public class AssetManager {

    public AssetManager(){
    }

    public void prepareAssets(){
        String path = System.getenv("APPDATA")+"/.Tarum/assets/";

        // TODO: CHECK IF ASSETS ARE UNPACKED OR NOT
        // IF NOT -> USE THE ZIP UTILITIES TO UNPACK
        // THE ARCHIVE AND FORMAT THE ASSETS APPROPRIATELY,
        // SUCH AS RESIZING SPRITES*.

        // TODO: REMOVE
        resizeAssets();
    }

    private void resizeAssets(){
        String path = System.getenv("APPDATA")+"/.Tarum/assets/";
        File[] spriteFiles = new File (path+"raw/sprites/ships/").listFiles();

        for (File file : spriteFiles){
            try {

                String targetPath = path+"sprites/ships/";
                File dir = new File (targetPath);
                if (!dir.exists()) dir.mkdirs();

                boolean result = resizeSprite(file, new File (path+"sprites/ships/", file.getName()), 32, 32);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean resizeSprite (File originalImageFile, File targetDest, int targetWidth, int targetHeight) throws IOException {

        BufferedImage img = ImageIO.read(originalImageFile);
        BufferedImage out = GraphicUtils.resizeImage(img, targetWidth, targetHeight);

        boolean result = ImageIO.write(out, "PNG", targetDest);

        return result;
    }

}
