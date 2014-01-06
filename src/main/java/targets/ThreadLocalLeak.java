package targets;

public class ThreadLocalLeak implements Runnable {

  private static ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<Object>();

  @Override
  public void run() {
    THREAD_LOCAL.set(new Empty());
  }

}
