package plataforma1.test;


import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.spi.BeanManager;

@RequestScoped
public class TestContext {

    private BeanManager beanManager;

    public BeanManager getBeanManager() {
        return beanManager;
    }

    public void setBeanManager(BeanManager beanManager) {
        this.beanManager = beanManager;
    }
}
