package niubola.data

import javax.persistence.{GeneratedValue, Id}

import scala.beans.BeanProperty

trait UserData {

  @Id
  @GeneratedValue
  @BeanProperty
  var id:Long = _
  @BeanProperty
  var username:String = _

}
