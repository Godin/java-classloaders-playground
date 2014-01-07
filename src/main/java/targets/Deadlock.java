package targets;

public class Deadlock implements Runnable {

  static class BaseClass {
    static ChildClass STATIC = new ChildClass();
  }

  static class ChildClass extends BaseClass {
    void hello() {
      System.out.println("Hello");
    }
  }

  @Override
  public void run() {
    Thread thread = new Thread() {
      @Override
      public void run() {
        BaseClass.STATIC.hello();
      }
    };
    thread.start();
    new ChildClass().hello();
    try {
      thread.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
