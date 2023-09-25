package plataforma1.services;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;

public abstract class TransactionalInterceptor {

    @AroundInvoke
    public Object aroundInvoke(InvocationContext ic) throws Exception {
        EntityManager em = getEntityManager();

        boolean act = !em.getTransaction().isActive();
        if (act) {
            em.getTransaction().begin();
        }
        try {
            Object result = ic.proceed();
            if (act) {
                em.getTransaction().commit();
            }
            return result;
        } catch (Exception e) {
            if (act) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            }
            throw e;
        }
    }

    protected abstract EntityManager getEntityManager();
}
