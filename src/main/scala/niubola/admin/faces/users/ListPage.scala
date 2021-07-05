package niubola.admin.faces.users

import niubola.admin.repositories.UserRepository
import niubola.framework.domain.NiuModel
import niubola.framework.faces.Page
import niubola.models.User
import org.ocpsoft.rewrite.annotation.{Matches, Parameter, RequestAction}
import org.ocpsoft.rewrite.el.ELBeanName
import org.ocpsoft.rewrite.faces.annotation.{Deferred, IgnorePostback}

import java.beans.Transient
import java.util
import javax.annotation.PostConstruct
import javax.enterprise.context.{Dependent, RequestScoped}
import javax.faces.view.ViewScoped
import javax.inject.{Inject, Named}
import scala.beans.BeanProperty

@Named("admin_indexPage")
@RequestScoped
class IndexPage


@ViewScoped
@Named("admin_users")
//@Join(path="/admin/users", to="/admin/users/index")
class ListPage extends Page {

  @BeanProperty
  var list = new util.ArrayList[User]()

  @Transient
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

  def remove(id:Long) = {
    listModel.remove(id)
    successMessage("User removed!")
  }

}


@ViewScoped
@ELBeanName("admin_users_indexPage")
@Named("admin_users_indexPage")
//@Join(path="/admin/users/{id}", to="/admin/users/edit.xhtml")
class IndexTestPage extends Page {

  @BeanProperty
  /**
  @Deferred
  @Parameter
  @Matches("[a-z0-9]+")
   */
  var id:Long = _

  @BeanProperty
  var user:User = new User

  @Inject
  var userRepository:UserRepository = _

  /**
  @Deferred
  @RequestAction
  @IgnorePostback
  */
  @IgnorePostback
  override def onload = {

    println(s">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> onload $id")
    //id = Faces.getRequestParameter("")
    user = userRepository.findBy(id)

  }


  def save() = {
    println(s">>>>>>>>>>>>>>>>>>>>>>>>>>>>> save $id")
    user = userRepository.findBy(id)
  }

}


@ViewScoped
@ELBeanName("admin_users_edit")
@Named("admin_users_edit")
//@Join(path="/admin/users/{id}", to="/admin/users/edit.xhtml")
class EditPage extends Page {

  @BeanProperty
  /**
  @Deferred
  @Parameter
  @Matches("[a-z0-9]+")
  */
  var id:Long = _

  @BeanProperty
  var user:User = new User

  @Inject
  var userRepository:UserRepository = _

  @Deferred
  @RequestAction
  @IgnorePostback
  override def onload = {

    println(s">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> onload $id")
    //id = Faces.getRequestParameter("")
    user = userRepository.findBy(id)

  }


  def save() = {
    println(s">>>>>>>>>>>>>>>>>>>>>>>>>>>>> save $id")
    user = userRepository.findBy(id)
    userRepository.save(user)
    successMessage("growl","Success!")
  }

}

@ViewScoped
@ELBeanName("admin_users_showPage")
@Named("admin_users_showPage")
//@Join(path="/admin/users/{id}", to="/admin/users/show.xhtml")
class ShowPage extends Page {

  @BeanProperty
  @Deferred
  @Parameter
  @Matches("[a-z0-9]+")
  var id:Long = _

  @BeanProperty
  @Deferred
  @Parameter("name")
  @Matches("[a-z0-9]+")
  var name:String = _

  @BeanProperty
  var user:User = new User

  @Inject
  var userRepository:UserRepository = _

  @Deferred
  @RequestAction
  @IgnorePostback
  override def onload = {

    name = param("name")
    println(s">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> showPage onload id:$id, name:$name")
    user = userRepository.findBy(id)

  }


  def save() = {
    println(s">>>>>>>>>>>>>>>>>>>>>>>>>>>>> save $id")
    user = userRepository.findBy(id)
  }

}


@ViewScoped
@ELBeanName("admin_users_create")
@Named("admin_users_create")
//@Join(path="/admin/users/create", to="/admin/users/create")
class CreatePage extends Page {



  @BeanProperty
  var user:User = new User

  @Inject
  var userRepository:UserRepository = _

  @Deferred
  @RequestAction
  @IgnorePostback
  def onLoad = {

    //println(s">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> onload $id")
    //user = userRepository.findBy(id)

  }


  def save() = {
    //println(s">>>>>>>>>>>>>>>>>>>>>>>>>>>>> save $id")
    //user = userRepository.findBy(id)
  }

}


@Dependent
class UserListModel extends NiuModel[User,Long] {

  override def entityClass = classOf[User]

}
