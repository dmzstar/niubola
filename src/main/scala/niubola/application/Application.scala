package niubola.application

import boot.Tem
import javax.annotation.PostConstruct
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Any
import javax.inject.{Inject, Named}
import javax.persistence.EntityManager
import org.apache.deltaspike.jpa.api.transaction.Transactional
import org.omnifaces.cdi.Startup
import niubola.models.{Article, User}

@Startup
@ApplicationScoped
class Application {
  @Inject
 var my:MyService = _

  @PostConstruct
  def onStartup = {
    println(s"============== application start")
    my.hello
  }

}


@ApplicationScoped
@Transactional
class MyService{

  @Inject
  @Tem
  var em:EntityManager = _

  def hello = {
    for(i <- 1 to 100){
      val user = new User
      user.setUsername(s"u$i")
      em.persist(user)

      val article = new Article
      article.title = "Article" + i
      article.content = "Article content " + i

      em.persist(article)

    }
  }

}
