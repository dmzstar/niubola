package niubola.admin.faces

import javax.annotation.PostConstruct
import javax.enterprise.context.RequestScoped
import javax.inject.{Inject, Named}
import javax.persistence.EntityManager

@Named
@RequestScoped
class HelloBean {

  @Inject
  var entityManager:EntityManager = _

  @PostConstruct
  def onCreate = {
      println(s"===================== Hello!")
  }

  def hello = {
      println("hello")
    println(entityManager)
  }

}



