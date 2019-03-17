package niubola.admin.faces.articles

import javax.annotation.PostConstruct
import javax.inject.Named
import niubola.admin.faces.users.EntityActions
import niubola.admin.repositories.{AdminArticleCategoryRepository, AdminArticleRepository}
import niubola.framework.faces.Page
import niubola.models.Article

import scala.beans.BeanProperty

@Named
class ArticleCategoryPages extends Page with EntityActions[Article,AdminArticleCategoryRepository]{

    @BeanProperty
    var content:String = _

    @PostConstruct
    def onCreate = {
      selected = new Article
    }

    def saveListener= {
      content = content.replaceAll("\\r|\\n", "")
      selected.setContent(content)
      save()
    }

}
