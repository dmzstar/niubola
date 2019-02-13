package niubola.framework.faces

import java.util

import javax.enterprise.context.RequestScoped
import javax.faces.view.ViewScoped
import org.apache.deltaspike.data.api.QueryResult
import org.primefaces.model.{LazyDataModel, SortOrder}

@ViewScoped
class Page extends Serializable {

  def lazyModel[E](b: => QueryResult[E]) = {
    new LazyDataModel[AnyRef] {
      override def load(first: Int, pageSize: Int, sortField: String, sortOrder: SortOrder, filters: util.Map[String, AnyRef]): util.List[AnyRef] = {
        val result = b
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
