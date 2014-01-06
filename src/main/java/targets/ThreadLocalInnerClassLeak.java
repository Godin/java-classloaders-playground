package targets;

public class ThreadLocalInnerClassLeak implements Runnable {

  private ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<Object>();

  @Override
  public void run() {
    THREAD_LOCAL.set(new Inner());
  }

  private class Inner {
  }

}
