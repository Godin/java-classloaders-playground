import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TargetLoader extends ClassLoader {

  private final String targetName;
  private final byte[] bytes;

  public TargetLoader(String name, byte[] bytes) {
    super(TargetLoader.class.getClassLoader());
    this.targetName = name;
    this.bytes = bytes;
  }

  public static InputStream getClassData(Class<?> clazz) {
    final String resource = "/" + clazz.getName().replace('.', '/') + ".class";
    return clazz.getResourceAsStream(resource);
  }

  public static byte[] getClassDataAsBytes(Class<?> clazz) throws IOException {
    InputStream in = getClassData(clazz);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    byte[] buffer = new byte[0x100];
    int len;
    while ((len = in.read(buffer)) != -1) {
      out.write(buffer, 0, len);
    }
    in.close();
    return out.toByteArray();
  }

  @Override
  protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
    if (targetName.equals(name)) {
      Class<?> c = defineClass(name, bytes, 0, bytes.length);
      if (resolve) {
        resolveClass(c);
      }
      return c;
    } else {
      return super.loadClass(name, resolve);
    }
  }

}
