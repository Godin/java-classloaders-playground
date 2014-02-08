package examples;

import targets.ThreadLocalInnerClassLeak;

public class ThreadLocalInnerClassExample {

  public static void main(String[] args) throws Exception {
    Scenario.run(ThreadLocalInnerClassLeak.class);
  }

}
