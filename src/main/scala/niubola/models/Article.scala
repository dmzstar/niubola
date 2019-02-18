package niubola.models

import java.util

import javax.persistence._

import scala.annotation.meta.beanGetter
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

  @BeanProperty
  @(OneToMany @beanGetter)
  @(JoinColumn @beanGetter)(name="category_id")
  var categories:util.List[ArticleCategory] = new util.ArrayList()

}

@Entity
@Table(name="article_category")
class ArticleCategory extends EntityTrait{

  @BeanProperty
  var code:String = _
  @BeanProperty
  var name:String = _
  @BeanProperty
  var remark:String = _

}
