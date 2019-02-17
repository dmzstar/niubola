package niubola.admin.faces.articles

import javax.inject.Named
import niubola.admin.faces.users.EntityActions
import niubola.admin.repositories.AdminArticleRepository
import niubola.framework.faces.Page
import niubola.models.{Article, User}
import org.omnifaces.util.Faces

import scala.beans.BeanProperty

@Named
class ArticleEditPage extends Page with EntityActions[Article,AdminArticleRepository]{

    @BeanProperty
    var content:String = _

    def saveListener= {
      content = content.replaceAll("\\r|\\n", "")
    }

}
