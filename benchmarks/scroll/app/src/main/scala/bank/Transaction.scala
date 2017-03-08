package bank

import scroll.internal.annotations.Role
import scroll.internal.support.DispatchQuery
import DispatchQuery._
import scroll.internal.Compartment
import scroll.internal.graph.CachedScalaRoleGraph

case class Transaction() extends Compartment {

  override val plays = new CachedScalaRoleGraph(false)

  def execute(amount : Float) : Unit = {
    one[Source]().withDraw(amount)
    one[Target]().deposit(amount)
  }

  @Role case class Source() {
    def withDraw(m: Float): Unit = {
      // println("Source.withDraw")
      val _ = +this decrease m
    }
  }

  @Role case class Target() {
    def deposit(m: Float): Unit = {
      // println("Target.deposit")
      val _ = +this increase m
    }
  }
}
