package bank;

import java.util.List;
import java.util.LinkedList;

public team class Bank {
	private List<Customer> customers;
	private List<Account> checkingAccounts;
	private List<Account> savingAccounts;

	precedence SavingsAccount,CheckingsAccount;

	public class Customer playedBy Person {
		private List<Account> accounts;

		public void addAccount(Account account) {
			if (accounts == null) {
				accounts = new LinkedList<>();
			}
			accounts.add(account);
		}
	}

	public List<Account> getSavingAccounts() {
		return savingAccounts;
	}

	public List<Account> getCheckingAccounts() {
		return checkingAccounts;
	}

	public void addCustomer(Person as Customer customer) {
		if (customers == null) {
			customers = new LinkedList<>();
		}
		customers.add(customer);
	}

	public void addCheckingsAccount(Person as Customer c, Account as CheckingsAccount a) {
		c.addAccount(a);
		if (checkingAccounts == null) {
			checkingAccounts = new LinkedList<>();
		}
		checkingAccounts.add(a);
	}

	public void addSavingsAccount(Person as Customer c, Account as SavingsAccount a) {
		c.addAccount(a);
		if (savingAccounts == null) {
			savingAccounts = new LinkedList<>();
		}
		savingAccounts.add(a);
	}

	public class CheckingsAccount playedBy Account {

		private static final float LIMIT = 100.0f;

		callin void limited(float a) {
			// System.out.println("Checking limit...");
			if (a <= LIMIT) {
				base.limited(a);
			} else {
				throw new RuntimeException(a + "is over the limit " + LIMIT);
			}
		}

		void limited(float a) <- replace decrease(float a);

	}

	public class SavingsAccount playedBy Account {

		private static final float FEE = 0.1f;

		callin void withFee(float a) {
			// System.out.println("Calculating fee...");
			base.withFee(a + a * FEE);
		}

		void withFee(float a) <- replace decrease(float a);
	}
}
