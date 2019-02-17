package niubola.models

import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name="article")
class Article extends EntityTrait {

  @BeanProperty
  var title:String = _
  @BeanProperty
  @Lob
  @Basic(fetch = FetchType.LAZY)
  var content:String = _

}
