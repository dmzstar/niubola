package niubola.framework.domain

import java.util

import niubola.data.QueryModel
import niubola.models.User
import org.primefaces.model.{LazyDataModel, SortOrder}

abstract class NiuModel[T,PK] extends LazyDataModel[T] with QueryModel[T,PK]{


  override def load(first: Int, pageSize: Int, sortField: String, sortOrder: SortOrder, filters: util.Map[String, AnyRef]):util.List[T]= {

    listWithCount(first,pageSize)
    setRowCount(this.total)
    setWrappedData(this.items)
    this.items

  }

}
