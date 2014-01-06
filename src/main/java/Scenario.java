import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;

public final class Scenario {

  private Scenario() {
  }

  public static void run(String name) throws Exception {
    MemoryPoolMXBean permGenPool = getPermGenPoolMXBean();
    long max = 0;
    for (int i = 0; i < 200000; i++) {
      ClassLoader classLoader = new TargetLoader("targets");
      ((Runnable) classLoader.loadClass(name).newInstance()).run();
      long used = permGenPool.getUsage().getUsed();
      if (used < max) {
        System.out.println("Deallocated");
      }
      max = used;
    }
  }

  private static MemoryPoolMXBean getPermGenPoolMXBean() {
    for (MemoryPoolMXBean pool : ManagementFactory.getMemoryPoolMXBeans()) {
      if (pool.getName().contains("Perm Gen")) {
        return pool;
      }
    }
    throw new IllegalStateException();
  }

}
