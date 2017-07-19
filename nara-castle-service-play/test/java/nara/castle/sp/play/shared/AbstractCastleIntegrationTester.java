package nara.castle.sp.play.shared;

import nara.share.test.AbstractIntegrationTester;
import org.junit.Before;

public abstract class AbstractCastleIntegrationTester extends AbstractIntegrationTester {
    //

    @Override
    protected String getResourceUrlPrefix() {
        return "/castle-api";
    }

    @Before
    public void before() throws Exception {
        //
        mongo.initializeData("CA_CASTLE", "/data/castle.json");
        mongo.initializeData("CA_CASTELLAN", "/data/castellan.json");
        mongo.initializeData("N_RESUME_OFFSET", "/data/resume-offset.json");
    }
}
