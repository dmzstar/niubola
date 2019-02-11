package niubola.admin.faces.users

import niubola.models.User
import org.apache.deltaspike.data.api.{EntityRepository, QueryResult, Repository}

@Repository
trait UserRepository extends EntityRepository[User,java.lang.Long]{

  def findByUsername(name:String):QueryResult[User]

}
