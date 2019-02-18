package niubola.models

import java.util

import javax.persistence.{GeneratedValue, Id}

import scala.beans.BeanProperty

trait EntityTrait {

  @Id
  @GeneratedValue
  @BeanProperty
  protected var id:java.lang.Long = _

  def jlist[T]():java.util.List[T] = new util.ArrayList[T]()

}
