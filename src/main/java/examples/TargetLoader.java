package examples;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Relevant JVM options are "-XX:+TraceClassLoading" and "-XX:+TraceClassUnloading".
 */
public class TargetLoader extends ClassLoader {

  private final String prefix;

  public TargetLoader(String prefix) {
    super(TargetLoader.class.getClassLoader());
    this.prefix = prefix;
  }

  private static byte[] toByteArray(InputStream in) throws IOException {
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
    if (name.startsWith(prefix)) {
      InputStream in = getResourceAsStream(name.replace('.', '/') + ".class");
      if (in == null) {
        throw new ClassNotFoundException(name);
      }
      byte[] bytes;
      try {
        bytes = toByteArray(in);
      } catch (IOException e) {
        throw new ClassNotFoundException(name, e);
      }
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
