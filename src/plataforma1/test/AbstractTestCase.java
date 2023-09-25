package plataforma1.test;

import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;
import org.apache.wicket.cdi.CdiConfiguration;
import org.apache.wicket.util.tester.WicketTester;

public class AbstractTestCase {
    @Inject
    private TestContext testContext;
    private WicketTester wicketTester;

    protected static void runTestCase(Class<? extends AbstractTestCase> testCase) throws Exception {
        System.setProperty("com.sun.jersey.server.impl.cdi.lookupExtensionInBeanManager", "true");
        TestContainer testContainer = new TestContainer();
        testContainer.start(testCase);
    }

    protected WicketTester getWicketTester() {
        if (wicketTester == null) {
            wicketTester = new WicketTester();
            BeanManager bm = testContext.getBeanManager();
            new CdiConfiguration().setFallbackBeanManager(bm).configure(wicketTester.getApplication());
        }
        return wicketTester;
    }
}
