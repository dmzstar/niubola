package niubola.admin.faces.users

import java.util

import javax.annotation.PostConstruct
import javax.enterprise.context.{Dependent, RequestScoped}
import javax.inject.{Inject, Named}
import niubola.models.User

import scala.beans.BeanProperty
import niubola.framework.domain.NiuModel
import niubola.framework.faces.Page
import org.ocpsoft.rewrite.annotation.Join
import org.omnifaces.cdi.ViewScoped

@Named("admin_users")
@Join(path="/admin/users", to="/admin/users/index")
class ListPage extends Page {

  @BeanProperty
  var list = new util.ArrayList[User]()
  @Inject
  @BeanProperty
  var listModel:UserListModel = _

  @PostConstruct
  def preRender = {
      listModel.ql = "from User o order by o.id desc"
      listModel.cql = "select count(o) from User o"
  }

  def hello = {
    println("=================== " + listModel.listAll)
  }

}

@Dependent
class UserListModel extends NiuModel[User,Long] {

  override def entityClass = classOf[User]

}
