package bank;

public team class CallinTransaction {

	public class Source playedBy Account {

		callin void withDraw(float amount) {

			base.withDraw(amount);
		}

		withDraw <- replace decrease;
	}

	public class Target playedBy Account {
		callin void deposit(float amount) {

			base.deposit(amount);
		}

		deposit <- replace increase;
	}

	// @ImplicitTeamActivation
	public boolean execute(Account f, Account t, float a) {
		f.decrease(a);
		t.increase(a);
		return true;
	}

}
