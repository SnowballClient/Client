package org.golde.snowball.util;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.golde.snowball.Snowball;

public class FileUtil {

	public static boolean copyAsset(String from, File dir) {
		return copyAsset(from, dir, from);
	}
	
	public static boolean copyAsset(String from, File dir, String name) {
		return copy(getFileInside("assets/" + from), dir.getAbsolutePath() + "/" + name);
	}
	
	
	public static InputStream getFileInside(String where) {
		return FileUtil.class.getResourceAsStream("/org/golde/snowball/" + where);
	}
	
	public static boolean copy(InputStream source , String destination) {
        boolean succeess = true;

        Snowball.instance.getLogger().info("Copying ->" + source + "\n\tto ->" + destination);

        try {
            Files.copy(source, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Snowball.instance.getLogger().error("", ex);
            succeess = false;
        }

        return succeess;

    }
	
	public static boolean writeFile(File where, String...lines) {
		try {
			Files.write(where.toPath(), Arrays.asList(lines), Charset.forName("UTF-8"));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static BufferedImage fromBase64(String data) {
		String base64Image = data.split(",")[1];
		byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
		try {
			return ImageIO.read(new ByteArrayInputStream(imageBytes));
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
    /**
     * Extracts a zip file specified by the zipFilePath to a directory specified by
     * destDirectory (will be created if does not exists)
     * @param zipFilePath
     * @param destDirectory
     * @throws IOException
     */
    public static void unzip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                extractFile(zipIn, filePath);
            } else {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }
    /**
     * Extracts a zip entry (file entry)
     * @param zipIn
     * @param filePath
     * @throws IOException
     */
    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[4096];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
	
}

