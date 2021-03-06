package targets;

public class ThreadLocalInnerClassLeak implements Runnable {

  private ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();

  @Override
  public void run() {
    threadLocal.set(new Inner());
  }

  private class Inner {
  }

}
