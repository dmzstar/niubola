package niubola.mobile.faces.articles

import javax.annotation.PostConstruct
import javax.enterprise.context.RequestScoped
import javax.inject.{Inject, Named}
import niubola.mobile.faces.repositories.MobileArticleRepository
import niubola.models.Article
import org.ocpsoft.rewrite.annotation.Join

import scala.beans.BeanProperty

@Named("mobile_articlePage")
@RequestScoped
@Join(path="/mobile/articles/{id}", to="/mobile/articles/show")
class ShowPage {

}

@Named("mobile_articlePageModel")
@RequestScoped
class ShowPageModel{

    @Inject
    var repo:MobileArticleRepository = _
    var id:java.lang.Long = 1l

    @PostConstruct
    def onCreate = {
      article = repo.findBy(id)
    }

    @BeanProperty
    var article:Article = _

}
