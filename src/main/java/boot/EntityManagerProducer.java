package boot;

import org.omnifaces.cdi.Startup;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.*;

@Startup
@ApplicationScoped
public class EntityManagerProducer {

    @PostConstruct
    public void onCreate(){
        emf = Persistence.createEntityManagerFactory("default");
    }

    @PersistenceUnit(name = "default")
    private EntityManagerFactory emf;

    @Produces
    @RequestScoped// you can also make this @RequestScoped
    public EntityManager create() {
        return emf.createEntityManager();
    }

    public void close(@Disposes EntityManager em) {
        System.out.println("============ close ");
        if (em.isOpen()) {
            em.close();
        }
    }
}