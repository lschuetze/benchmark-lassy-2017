package bank;

public class CheckingsAccount extends Account {
  private Account core;
  private static final float LIMIT = 100.0f;

  public CheckingsAccount(Account core) {
    super();
    this.core = core;
    core.addRole(this);
  }

  @Override
  public void decrease(float amount) {
    //System.out.println("limit");
    if (amount <= LIMIT) {
      core.decrease(amount);
    } else {
      throw new RuntimeException(amount + "is over the limit " + LIMIT);
    }
  }

  @Override
  public void increase(float amount) {
    core.increase(amount);
  }

  @Override
  public float getBalance() {
    return core.getBalance();
  }
}
