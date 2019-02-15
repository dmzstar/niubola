package niubola.framework.faces

import java.util

import javax.enterprise.context.RequestScoped
import javax.faces.view.ViewScoped
import org.apache.deltaspike.data.api.QueryResult
import org.primefaces.model.{LazyDataModel, SortOrder}


trait ModelHelpers{
  def lazyModel[E](b: => QueryResult[E]) = {
    new LazyDataModel[AnyRef] {

      override def load(first: Int, pageSize: Int, sortField: String, sortOrder: SortOrder, filters: util.Map[String, AnyRef]): util.List[AnyRef] = {
        val result = b
        filters.forEach((k,v) => {
          println(s"============ lazyModel k $k")
        })
        result.maxResults(pageSize)
        result.firstResult(first)
        val list = result.getResultList
        result.clearOrder()//warning: do this or .count() cause error
        val count = result.count()
        setWrappedData(list)
        setRowCount(Integer.parseInt(count + ""))
        list.asInstanceOf[util.List[AnyRef]]
      }
    }
  }
}

@ViewScoped
class Page extends Serializable {

  def lazyModel[E](b: => QueryResult[E]) = {
    new LazyDataModel[AnyRef] {

      override def load(first: Int, pageSize: Int, sortField: String, sortOrder: SortOrder, filters: util.Map[String, AnyRef]): util.List[AnyRef] = {
        val result = b
        filters.forEach((k,v) => {
          println(s"============ lazyModel k $k")
        })
        result.maxResults(pageSize)
        result.firstResult(first)
        val list = result.getResultList
        result.clearOrder()//warning: do this or .count() cause error
        val count = result.count()
        setWrappedData(list)
        setRowCount(Integer.parseInt(count + ""))
        list.asInstanceOf[util.List[AnyRef]]
      }
    }
  }


  def clazyModel[E](b:(util.Map[String, AnyRef]) => QueryResult[E]) = {

    new LazyDataModel[AnyRef] {

      override def load(first: Int, pageSize: Int, sortField: String, sortOrder: SortOrder, filters: util.Map[String, AnyRef]): util.List[AnyRef] = {

        val result = b(filters)
        result.maxResults(pageSize)
        result.firstResult(first)
        val list = result.getResultList
        result.clearOrder()//warning: do this or .count() cause error
        val count = result.count()
        setWrappedData(list)
        setRowCount(Integer.parseInt(count + ""))
        list.asInstanceOf[util.List[AnyRef]]

      }
    }

  }

    def LazyListModel[E](b:(util.Map[String, AnyRef]) => java.util.List[E]) = {

    new LazyDataModel[AnyRef] {

      override def load(first: Int, pageSize: Int, sortField: String, sortOrder: SortOrder, filters: util.Map[String, AnyRef]): util.List[AnyRef] = {

        val result = b(filters)
        val list = result
        setWrappedData(list)
        setRowCount(100)
        list.asInstanceOf[util.List[AnyRef]]

      }
    }

  }

}
