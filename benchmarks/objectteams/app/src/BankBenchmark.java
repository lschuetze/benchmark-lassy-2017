import bank.Account;
import bank.Bank;
import bank.Person;
import bank.CallinTransaction;
import bank.CalloutTransaction;

public class BankBenchmark extends Benchmark {

	private Bank bank;
	private String optionalArgument;

	@Override
	public boolean innerBenchmarkLoop(final int innerIterations) {
		bank.activate();
		for (Account from : bank.getCheckingAccounts()) {
			float amount = from.getBalance() / (float) innerIterations;
			for (Account to : bank.getSavingAccounts()) {
				if(optionalArgument.equals("CALLIN")) {
					CallinTransaction transaction = new CallinTransaction();
					transaction.activate();
					try {
						transaction.execute(from, to, amount);
					} catch (RuntimeException e) {
						// do nothing
					} finally {
						transaction.deactivate();
					}
				} else {
					CalloutTransaction transaction = new CalloutTransaction();
					transaction.activate();
					try {
						transaction.execute(from, to, amount);
					} catch (RuntimeException e) {
						// do nothing
					} finally {
						transaction.deactivate();
					}
				}
			}
		}
		bank.deactivate();
		return true;
	}

	public boolean setUp(final int innerIterations) {
		bank = new Bank();
		bank.activate();

		for (int i = 0; i < innerIterations; ++i) {
			Person p = new Person();
			bank.addCustomer(p);

			Account sa = new Account(1000.0f);
			Account ca = new Account(1000.0f);
			bank.addSavingsAccount(p, sa);
			bank.addCheckingsAccount(p, ca);
		}
		return true;
	}

	public boolean setUpOptional(String optionalArgument) {
		this.optionalArgument = optionalArgument;
		return true;
	}

	@Override
	public Object benchmark() {
		throw new RuntimeException("Should never be reached");
	}

	@Override
	public boolean verifyResult(Object result) {
		throw new RuntimeException("Should never be reached");
	}
}
