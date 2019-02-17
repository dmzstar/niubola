package niubola.mobile.faces.repositories

import niubola.admin.repositories.CommonRepository
import niubola.models.Article
import org.apache.deltaspike.data.api.Repository

@Repository
trait MobileArticleRepository extends CommonRepository[Article]{

}
