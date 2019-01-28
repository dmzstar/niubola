package niubola.models

import javax.persistence.{Entity, GeneratedValue, Id, Table}

import scala.beans.BeanProperty

@Entity
@Table(name="sec_user")
class User {

  @Id
  @GeneratedValue
  @BeanProperty
  var id:Long = _
  @BeanProperty
  var username:String = _

}
