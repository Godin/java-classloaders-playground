import targets.StaticThreadLocalLeak;

public class StaticThreadLocalExample {

  public static void main(String[] args) throws Exception {
    Scenario.run(StaticThreadLocalLeak.class);
  }

}
