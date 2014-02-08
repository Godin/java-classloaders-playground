package targets;

public class CustomThreadGroupLeak implements Runnable {

  @Override
  public void run() {
    new CustomThreadGroup();
  }

  private static class CustomThreadGroup extends ThreadGroup {
    public CustomThreadGroup() {
      super("CustomThreadGroup");
    }
  }

}
