package niubola.admin.faces.users

import java.util

import javax.annotation.PostConstruct
import javax.enterprise.context.SessionScoped
import javax.faces.model.DataModel
import javax.inject.{Inject, Named}
import org.primefaces.model.{LazyDataModel, SortOrder}
import niubola.models.User
import org.apache.deltaspike.core.api.scope.ViewAccessScoped
import org.apache.deltaspike.data.api.QueryResult

import scala.beans.BeanProperty
import javax.faces.view.ViewScoped

@Named
@ViewScoped
class TestPage extends Serializable {

  def Action[T](clazz: => Class[T])(t:T => Unit) = {

  }

  def A = {
    def f = (b:Int) => Unit
  }

  @Inject
  var r:UserRepository = _

  @PostConstruct
  def preRender = {
    println(s"====================== $r")
  }

  def hello = {}

  @BeanProperty
  var data = lazyModel[User](r.findByUsername("u"))

  def lazyModel[E](b: => QueryResult[E]) = {
    new LazyDataModel[AnyRef] {
      override def load(first: Int, pageSize: Int, sortField: String, sortOrder: SortOrder, filters: util.Map[String, AnyRef]): util.List[AnyRef] = {
        val count = b.count()
        val list = b.getResultList
        setWrappedData(list)
        setRowCount(Integer.parseInt(count + ""))
        list.asInstanceOf[util.List[AnyRef]]
      }
    }
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


