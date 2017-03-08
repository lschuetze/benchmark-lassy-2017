package bank
import scroll.internal.annotations.Role
import scroll.internal.support.DispatchQuery
import DispatchQuery._
import scroll.internal.Compartment
import scroll.internal.graph.CachedScalaRoleGraph

import scala.collection.mutable.MutableList

case class Bank() extends Compartment {

  override val plays = new CachedScalaRoleGraph(false)

  var checkingAccounts = MutableList[Account]()
  var savingAccounts = MutableList[Account]()
  implicit var dd: DispatchQuery = DispatchQuery.empty

  def getCheckingAccounts() : MutableList[Account] = checkingAccounts
  def getSavingAccounts() : MutableList[Account] = savingAccounts

  def addSavingsAccount(acc : Account) = {
    savingAccounts = savingAccounts :+ acc
  }

  def addCheckingsAccount(acc : Account) = {
    checkingAccounts = checkingAccounts :+ acc
  }

    @Role case class Customer() {
      var accounts = MutableList[Accountable]()

      def addAccount(acc: Accountable): Unit = {
        accounts = accounts :+ acc
      }
    }

    @Role case class CheckingsAccount() extends Decreasable {
      def decrease(amount: Float): Unit = {
        // println("CheckingsAccount:decrease")
        dd = From(_.isInstanceOf[Account]).
          To(_.isInstanceOf[CheckingsAccount]).
          Through(anything).
          // so we won't calling decrease() recursively on this
          Bypassing(_.isInstanceOf[CheckingsAccount])
        val _ = +this decrease amount
      }
    }

    @Role case class SavingsAccount() extends Increasable {
      private def transactionFee(amount: Float): Float = amount * 0.1f

      def increase(amount: Float): Unit = {
        // println("SavingsAccount.increase")
        dd = From(_.isInstanceOf[Account]).
          To(_.isInstanceOf[SavingsAccount]).
          Through(anything).
          // so we won't calling increase() recursively on this
          Bypassing(_.isInstanceOf[SavingsAccount])
        val _ = +this increase (amount - transactionFee(amount))
      }
    }

    @Role case class TransactionRole() {
      def execute(amount : Float): Unit = {
        // println("TransactionRole.execute")
        dd = From(_.isInstanceOf[Transaction]).
          To(_.isInstanceOf[TransactionRole]).
          Through(anything).
          // so we won't calling execute() recursively on this
          Bypassing(_.isInstanceOf[TransactionRole])
        val _ = +this execute(amount)
      }
    }

  }
