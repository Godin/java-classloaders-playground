package examples;

import java.lang.management.MemoryPoolMXBean;

public final class Scenario {

  private Scenario() {
  }

  public static void run(Class<? extends Runnable> clazz) throws Exception {
    MemoryPoolMXBean permGenPool = Memory.getPermGenPoolMXBean();
    long max = 0;
    for (int i = 0; i < 200000; i++) {
      ClassLoader classLoader = new TargetLoader("targets");
      ((Runnable) classLoader.loadClass(clazz.getName()).newInstance()).run();
      long used = permGenPool.getUsage().getUsed();
      if (used < max) {
        System.out.println("Deallocated");
      }
      max = used;
    }
  }

}
