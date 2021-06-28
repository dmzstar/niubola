package niubola.framework.domain

import niubola.data.QueryModel
import org.primefaces.model.{FilterMeta, LazyDataModel, SortOrder}

import java.util

abstract class NiuModel[T,PK] extends LazyDataModel[T] with QueryModel[T,PK]{


  override def load(first: Int, pageSize: Int, sortField: String, sortOrder: SortOrder, filters: util.Map[String, FilterMeta]):util.List[T]= {

    listWithCount(first,pageSize)
    setRowCount(this.total)
    setWrappedData(this.items)
    this.items

  }

}
