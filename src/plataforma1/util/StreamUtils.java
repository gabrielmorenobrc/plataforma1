package plataforma1.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtils {

    public static void transfer(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[512];
        int r = inputStream.read(buffer, 0, buffer.length);
        while (r > -1) {
            outputStream.write(buffer, 0, r);
            r = inputStream.read(buffer, 0, buffer.length);
        }
    }

    public static byte[] loadResourceBytes(Class baseClass, String name) throws IOException {
        InputStream inputStream = baseClass.getResourceAsStream(name);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            transfer(inputStream, byteArrayOutputStream);
            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } finally {
            inputStream.close();
        }

    }
}
