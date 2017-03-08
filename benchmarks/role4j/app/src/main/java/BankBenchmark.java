import bank.*;
import net.role4j.*;
import net.role4j.Registry;

public class BankBenchmark extends Benchmark {

	private Bank bank;
	private final static Registry reg = Registry.getRegistry();

	@Override
	public boolean innerBenchmarkLoop(final int innerIterations) {
		try {
			bank.activate();
			for (Account from : bank.getCheckingAccounts()) {
				float amount = from.getBalance() / (float) innerIterations;
				for (Account to : bank.getSavingAccounts()) {
					try {
						Transaction transaction = reg.newCore(Transaction.class);
						transaction.setAccounts(from, to);
						bank.executeTransaction(transaction, amount);
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
				}
			}
			bank.deactivate();
		}
		catch(Throwable e) {
			return false;
		}
		return true;
	}

	public boolean setUp(final int innerIterations) {
		try {
			bank = reg.newCompartment(Bank.class);
			bank.activate();

			for (int i = 0; i < innerIterations; ++i) {
				Person p = reg.newCore(Person.class);
				bank.addCustomer(p);

				Account sa = reg.newCore(Account.class);
				Account ca = reg.newCore(Account.class);
				sa.setBalance(1000.0f);
				ca.setBalance(1000.0f);
				bank.addSavingsAccount(p, sa);
				bank.addCheckingsAccount(p, ca);
			}
			bank.deactivate();
		}
		catch(Throwable e) {
			return false;
		}
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
