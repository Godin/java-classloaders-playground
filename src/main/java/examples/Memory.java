package examples;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;

public final class Memory {

  private Memory() {
  }

  public static MemoryPoolMXBean getPermGenPoolMXBean() {
    for (MemoryPoolMXBean pool : ManagementFactory.getMemoryPoolMXBeans()) {
      if (pool.getName().contains("Perm Gen")) {
        return pool;
      }
    }
    throw new IllegalStateException("Perm Gen Pool not found");
  }

}
