package niubola.admin.repositories

import niubola.models.Article
import org.apache.deltaspike.data.api.Repository

@Repository
trait AdminArticleRepository extends CommonRepository[Article]
