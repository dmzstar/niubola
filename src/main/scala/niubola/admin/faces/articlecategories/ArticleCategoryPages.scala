package niubola.admin.faces.articles

import javax.annotation.PostConstruct
import javax.inject.Named
import niubola.admin.faces.users.EntityActions
import niubola.admin.repositories.{AdminArticleCategoryRepository, AdminArticleRepository}
import niubola.framework.faces.Page
import niubola.models.{Article, ArticleCategory}

import scala.beans.BeanProperty

@Named
class ArticleCategoryPages extends Page with EntityActions[ArticleCategory,AdminArticleCategoryRepository]{

      def hello = {
        println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>111222")
      }

}
