package niubola.admin.repositories

import niubola.models.{Article, ArticleCategory}
import org.apache.deltaspike.data.api.Repository

@Repository
trait AdminArticleRepository extends CommonRepository[Article]


@Repository
trait AdminArticleCategoryRepository extends CommonRepository[ArticleCategory]
