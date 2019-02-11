package niubola.data

import java.util.{ArrayList, List}

import javax.inject.Inject
import javax.persistence.EntityManager
import org.apache.deltaspike.jpa.api.transaction.Transactional

trait QueryModel[T,PK] {

  var items:List[T] = new ArrayList[T]
  var total:Int = 0
  var item:T = _
  var itemId:PK = _
  def entityClass:Class[T]
  val entityClassName = entityClass.getSimpleName
  val fromQL = s"from $entityClassName o"
  val countQL = s"select count(o) $fromQL"

  var ql = ""
  var cql = ""

  var mapper:AnyRef = _


  @Inject
  var em:EntityManager = _

  def select(t:T) = item = t

  def listWithCount(first:Int,pageSize:Int) = {
    items = em.createQuery(ql).setFirstResult(first).setMaxResults(pageSize).getResultList.asInstanceOf[List[T]]
    total = Integer.parseInt(em.createQuery(cql).getSingleResult.asInstanceOf[Long] + "")
  }

  def listAll:List[T] = {
    em.createQuery(fromQL).getResultList.asInstanceOf[List[T]]
  }

  def countAll:Int = {
    em.createQuery(countQL).getSingleResult.asInstanceOf
  }

  def jpql = {

  }

  @Transactional
  def remove(id:Long) = {
    em.createQuery(s"delete from $entityClassName where id=?1").setParameter(1,id).executeUpdate()
  }

  def itemEntity = {

  }

  class ItemEntity{

    def save = {
      em.persist(item)
    }
    def remove = {
      em.remove(item)
    }

  }

}
