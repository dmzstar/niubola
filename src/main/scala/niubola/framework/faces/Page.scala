package niubola.framework.faces

import org.apache.deltaspike.data.api.QueryResult
import org.omnifaces.util.Messages
import org.primefaces.model.{FilterMeta, LazyDataModel, SortOrder}

import java.util
import javax.faces.view.ViewScoped


trait ModelHelpers{
  def lazyModel[E](b: => QueryResult[E]) = {
    new LazyDataModel[AnyRef] {

      override def load(first: Int, pageSize: Int, sortField: String, sortOrder: SortOrder, filters: util.Map[String, FilterMeta]): util.List[AnyRef] = {
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

      override def load(first: Int, pageSize: Int, sortField: String, sortOrder: SortOrder, filters: util.Map[String, FilterMeta]): util.List[AnyRef] = {
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


  def clazyModel[E](b:(util.Map[String, FilterMeta]) => QueryResult[E]) = {

    new LazyDataModel[AnyRef] {

      override def load(first: Int, pageSize: Int, sortField: String, sortOrder: SortOrder, filters: util.Map[String, FilterMeta]): util.List[AnyRef] = {

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

    def LazyListModel[E](b:(util.Map[String, FilterMeta]) => java.util.List[E]) = {

    new LazyDataModel[AnyRef] {

      override def load(first: Int, pageSize: Int, sortField: String, sortOrder: SortOrder, filters: util.Map[String, FilterMeta]): util.List[AnyRef] = {

        val result = b(filters)
        val list = result
        setWrappedData(list)
        setRowCount(100)
        list.asInstanceOf[util.List[AnyRef]]

      }
    }

  }


  def successMessage(id:String,message:String) = {
    Messages.addInfo(id,message)
  }

  def successMessage(message:String) = {
    Messages.addInfo("",message)
  }

}
