import bank._

import scroll.internal.support.DispatchQuery
import DispatchQuery._

class BankBenchmark extends Benchmark {

	var bank_var = new Bank

	override def innerBenchmarkLoop(innerIterations : Int) : Boolean = {
		val bank = bank_var
		
		for (from <- bank.getCheckingAccounts) {
			var amount : Float = from.getBalance / innerIterations;
			for (to <- bank.getSavingAccounts) {
				val transaction = new Transaction
				transaction.addPlaysRelation(from, new transaction.Source)
				transaction.addPlaysRelation(to, new transaction.Target)

				transaction partOf bank

				val transactionRole = new bank.TransactionRole
				bank.addPlaysRelation(transaction, transactionRole)
				transactionRole.execute(amount)
			}
		}
		return true;
	}

	def setUp(innerIterations : Int) : Boolean = {
			bank_var = new Bank
			val bank = bank_var

			for (i <- 1 to innerIterations) {
				var p = new Person
				bank.addPlaysRelation(p, new bank.Customer)

			 	var sa = new Account(1000.0f)
				var ca = new Account(1000.0f)

				bank.addPlaysRelation(sa, new bank.SavingsAccount)
				bank.addPlaysRelation(ca, new bank.CheckingsAccount)

				bank.addSavingsAccount(sa)
				bank.addCheckingsAccount(ca)
			}
		return true;
	}

	override def benchmark() : Object = {
		throw new RuntimeException("Should never be reached");
	}

	override def verifyResult(result : Object) : Boolean = {
		throw new RuntimeException("Should never be reached");
	}
}
