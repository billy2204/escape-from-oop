package graphics.animation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

public class ResourceManager {
    private static final Map<String, List<BufferedImage>> cache = new HashMap<>();
    public static List<BufferedImage> loadFrames(String folderPath) {
        if (cache.containsKey(folderPath)) {
            return cache.get(folderPath);
        }

        List<BufferedImage> frames = new ArrayList<>();
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
            
            if (files != null) {
                Arrays.sort(files, (f1, f2) -> compareFileNames(f1.getName(), f2.getName()));
                
                for (File f : files) {
                    try {
                        frames.add(ImageIO.read(f));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
        }

        cache.put(folderPath, frames);
        return frames;
    }
    
    private static int compareFileNames(String n1, String n2) {
        String s1 = n1.replaceAll("\\D", "");
        String s2 = n2.replaceAll("\\D", "");
        if (s1.isEmpty() || s2.isEmpty()) return n1.compareTo(n2);
        return Integer.parseInt(s1) - Integer.parseInt(s2);
    }
}