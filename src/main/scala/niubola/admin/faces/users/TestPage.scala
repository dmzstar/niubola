package niubola.admin.faces.users

import java.util

import javax.annotation.PostConstruct
import javax.inject.{Inject, Named}
import org.primefaces.model.{LazyDataModel, SortOrder}
import niubola.models.User
import org.apache.deltaspike.data.api.{EntityRepository, QueryResult}

import scala.beans.BeanProperty
import niubola.framework.faces.Page
import org.apache.deltaspike.jpa.api.transaction.Transactional


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

  @BeanProperty
  var data = lazyModel[User]{
    repo.all().orderDesc("o.id",false)
  }



}

case class LazyQueryModel(ql:String,cql:String=null){



}

case class RQuery[R](){

}


class MyDataModel[T] extends LazyDataModel[T]{

  override def load(first: Int, pageSize: Int, sortField: String, sortOrder: SortOrder, filters: util.Map[String, AnyRef]): util.List[T] = {



    load(new PageRequest(first,pageSize,sortField,sortOrder,filters))
  }

  def load(pageRequest: PageRequest):util.List[T] = {

    null
  }

}

class PageRequest(first:Int,pageSize:Int,sortField: String=null, sortOrder: SortOrder=null, filters: util.Map[String, AnyRef]=null){

}


