package niubola.mobile.faces

import javax.annotation.PostConstruct
import javax.enterprise.context.RequestScoped
import javax.inject.{Inject, Named}
import niubola.mobile.faces.repositories.MobileArticleRepository
import niubola.models.Article
import org.ocpsoft.rewrite.annotation.Join

import scala.beans.BeanProperty

@Named("mobile_indexPage")
@RequestScoped
@Join(path="/mobile", to="/mobile/index")
class IndexPage {

}

@Named("mobile_indexPageModel")
@RequestScoped
class IndexPageModel{

    @Inject
    var repo:MobileArticleRepository = _

    @PostConstruct
    def onCreate = {
      println("====================== mobile_indexPageModel" + repo)
      articles = repo.findAll(0,20)
      println(articles.size())
    }

    @BeanProperty
    var articles:java.util.List[Article] = _

}
