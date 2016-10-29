package com.hackathon.util;

import com.hackathon._config.AppConfigKeys;
import com.hackathon.constant.IContanst;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by HienTQSE60896 on 9/29/2016.
 */
public class StoreFileUtils {
    private static String PATH = AppConfigKeys.getInstance().getApiPropertyValue("file.store.path");

    public static String storeFile(String nameFile, InputStream fileStore) {
        try {
            String fileName = PATH + File.separator + String.format("%s.%s", nameFile, IContanst.EXTENSION_FILE_IMAGE);
            File file = new File(fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            } else {
                if (file.exists()) {
                    file.delete();
                }
            }

            BufferedImage bufferedImage = ImageIO.read(fileStore);
            ImageIO.write(bufferedImage, IContanst.EXTENSION_FILE_IMAGE, file);
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
