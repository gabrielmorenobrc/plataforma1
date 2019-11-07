package plataforma1.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class Numerics182 extends AbstractNumerics {

    public Numerics182() {
        init(18, 2);
    }

}