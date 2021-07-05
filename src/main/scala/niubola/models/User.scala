package niubola.models

import javax.persistence.{Entity, GeneratedValue, Id, Lob, Table}
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

  @BeanProperty
  var password:String = _

  @BeanProperty
  @Lob
  var remark:String = _

}
