package niubola.admin.faces.users

import niubola.models.User
import org.apache.deltaspike.data.api.{EntityRepository, Query, QueryResult, Repository}
import org.apache.deltaspike.jpa.api.transaction.Transactional

trait CommonRepository[E] extends EntityRepository[E,java.lang.Long]{

  @Transactional
  def removeById(id:Long)

}

@Repository
trait UserRepository extends CommonRepository[User]{

  def findByUsername(name:String):QueryResult[User]
  @Query(value="select o from User o")
  def all():QueryResult[User]

}

@Repository
trait AdminUserRepository extends CommonRepository[User]{

  def findByUsername(name:String):QueryResult[User]
  @Query(value="select o from User o")
  def all():QueryResult[User]

  //def findBy(user:User):QueryResult[User]


}
