package plataforma1.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class Numerics182 extends AbstractNumerics {

    public Numerics182() {
        init(18, 2);
    }

}