package common.util;


import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.charset.Charset;

/**
 * @Author Froid_Li
 * @Email 269504518@qq.com
 * @Date 2017/9/12  10:56
 */
public class PidUtil {
    public static void writePid(String path) {
        if (!StringUtils.isEmpty(path)) {
            try {
                File file = new File(path);
                //File file = new File("/Users/tusm/Git/ndt-backend/test.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(file);
                Charset charset = Charset.forName("UTF-8");
                byte[] pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0].getBytes(charset);
                fos.write(pid);
                fos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
