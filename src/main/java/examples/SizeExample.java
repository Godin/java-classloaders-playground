package examples;

import targets.Empty;

import java.lang.management.MemoryPoolMXBean;

public class SizeExample {

  public static void main(String[] args) throws Exception {
    MemoryPoolMXBean permGenPool = Memory.getPermGenPoolMXBean();
    for (int i = 0; i < 20; i++) {
      ClassLoader classLoader = new TargetLoader("targets");

      long before = permGenPool.getUsage().getUsed();
      classLoader.loadClass(Empty.class.getName());
      long after = permGenPool.getUsage().getUsed();

      System.out.println(after - before);
    }
  }

}
