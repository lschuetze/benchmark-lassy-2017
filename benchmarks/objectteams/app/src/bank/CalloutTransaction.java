package bank;

public team class CalloutTransaction {

	 public class Source playedBy Account {

	 void withDraw(float amount) -> void decrease(float amount);
	 }

	 public class Target playedBy Account {
	 void deposit(float amount) -> void increase(float amount);
	 }

	// @ImplicitTeamActivation
	public boolean execute(Account as Source f, Account as Target t, float a) {
		f.withDraw(a);
		t.deposit(a);
		return true;
	}

}
