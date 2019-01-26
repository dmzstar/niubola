package niubola.admin.faces;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
public class HelloWorld {

    public void hello(){
        System.out.println("=====================");
    }

}
