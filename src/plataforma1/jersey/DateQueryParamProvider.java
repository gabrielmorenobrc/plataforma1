package plataforma1.jersey;

import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;

import javax.ws.rs.ext.Provider;
import java.lang.reflect.Type;


/**

 */
@Provider
public class DateQueryParamProvider implements InjectableProvider<DateQueryParam, Type> {

    @Override
    public ComponentScope getScope() {
        return ComponentScope.PerRequest;
    }

    @Override
    public Injectable getInjectable(ComponentContext cc, DateQueryParam a, Type c) {
        return new DateQueryParamInjectable(a.value());
    }
}