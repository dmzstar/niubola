package niubola.admin.faces.users

import niubola.admin.repositories.{AdminUserRepository, CommonRepository}
import niubola.framework.faces.{ModelHelpers, Page}
import niubola.models.User
import org.primefaces.model.{FilterMeta, LazyDataModel, SortOrder}

import java.util
import javax.annotation.PostConstruct
import javax.faces.view.ViewScoped
import javax.inject.{Inject, Named}
import scala.beans.BeanProperty


trait EntityActions[T,R <: CommonRepository[T]]{

  @Inject
  var repo:R = _
  @BeanProperty
  var selected:T = _

  def remove(id:Long) = {
      repo.removeById(id)
  }

  def save() = {
      repo.save(selected)
  }

}

trait EntityActions2[T,R <: CommonRepository[T]]{

  @Inject
  var repo:R = _
  @BeanProperty
  var id:java.lang.Long = _
  @BeanProperty
  var model:T = _

  def load(id:java.lang.Long) = {
    this.id = id
    loadEntity
  }

  def loadEntity = {
      if(id != null){
        model = repo.findBy(id)
      }
  }

  def remove() = {
    repo.remove(model)
  }

  def removeById = {
    repo.removeById(id)
  }

  def save() = {
    repo.save(model)
  }

}

trait EditAction[E,PK] {

}

trait EditPageTrait[T]{

  //@Parameter("id") //This annotation not work right, fixed later. The reason is elProvider not work.
  @BeanProperty
  var id:java.lang.Long = _
  @BeanProperty
  var selected:T = _

}



@ViewScoped
class StatefulModel extends Serializable with ModelHelpers

@Named
class TestPageModel extends StatefulModel with EntityActions[User,AdminUserRepository]{

  @BeanProperty
  var data = lazyModel[User]{
    repo.all().orderDesc("o.id",false)
  }

}


@Named
class TestPage extends Page with EntityActions[User,AdminUserRepository] {

  def Action[T](clazz: => Class[T])(t:T => Unit) = {

  }

  def A = {
    def f = (b:Int) => Unit
  }
  @PostConstruct
  def preRender = {

  }

  def hello = {}

  def edit(id:java.lang.Long) = {
    "edit"
  }

  @BeanProperty
  @Inject
  var model:TestPageModel = _

  @BeanProperty
  var cdata = LazyListModel[User]{ filters =>
    filters.forEach((k,v) => {
      println("cdata " + k)
    })
    val u = new User()
    if(filters.get("id") != null) {
      u.id = java.lang.Long.parseLong(filters.get("id").toString)
    }
    repo.findBy(u)
  }

}

case class LazyQueryModel(ql:String,cql:String=null){



}

case class RQuery[R](){

}


class MyDataModel[T] extends LazyDataModel[T]{

  /**
   *  override def load(first: Int, pageSize: Int, sortField: String, sortOrder: SortOrder, filters: util.Map[String, AnyRef]): util.List[T] = {
   *  load(new PageRequest(first,pageSize,sortField,sortOrder,filters))
   *  }
   */

  override def load(first: Int, pageSize: Int, sortField: String, sortOrder: SortOrder, filters: util.Map[String, FilterMeta]): util.List[T] = {
    load(new PageRequest(first,pageSize,sortField,sortOrder,filters))
  }


  def load(pageRequest: PageRequest):util.List[T] = {

    null
  }

}

class PageRequest(first:Int,pageSize:Int,sortField: String=null, sortOrder: SortOrder=null, filters: util.Map[String, FilterMeta]=null){

}


