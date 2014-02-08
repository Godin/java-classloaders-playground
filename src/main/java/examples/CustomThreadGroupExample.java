package examples;

import targets.CustomThreadGroupLeak;

public class CustomThreadGroupExample {

  public static void main(String[] args) throws Exception {
    Scenario.run(CustomThreadGroupLeak.class);
  }

}
