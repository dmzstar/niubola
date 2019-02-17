package niubola.models

import javax.persistence.{GeneratedValue, Id}

import scala.beans.BeanProperty

trait EntityTrait {

  @Id
  @GeneratedValue
  @BeanProperty
  protected var id:java.lang.Long = _

}
