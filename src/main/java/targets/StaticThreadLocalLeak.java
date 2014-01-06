package targets;

public class StaticThreadLocalLeak implements Runnable {

  private static ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<Object>();

  @Override
  public void run() {
    THREAD_LOCAL.set(new Empty());
  }

}
