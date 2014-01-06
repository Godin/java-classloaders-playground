package targets;

public class ThreadLocalStaticInnerClass implements Runnable {

  private ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();

  @Override
  public void run() {
    threadLocal.set(new Inner());
  }

  private static class Inner {
  }

}
