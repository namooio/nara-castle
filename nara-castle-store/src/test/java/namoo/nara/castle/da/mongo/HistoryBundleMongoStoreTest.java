package namoo.nara.castle.da.mongo;

import namoo.nara.castle.domain.entity.OpenState;
import namoo.nara.castle.domain.entity.history.*;
import namoo.nara.castle.domain.store.HistoryBundleStore;
import namoo.nara.share.exception.store.NonExistenceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HistoryBundleMongoStoreTest.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = "namoo.nara.castle.da.mongo")
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class HistoryBundleMongoStoreTest {
    //
    @Autowired
    private HistoryBundleStore historyBundleStore;

    @Test
    public void storeCrudTest() {
        //
        // create test
        String id = UUID.randomUUID().toString();
        HistoryBundle history = HistoryBundle.newInstance(id);

        AccountBook accountBook = new AccountBook();
        accountBook.addAccount(new LoginAccount("kchuh@nextree.co.kr", LoginAccount.LoginChannel.Nara, System.currentTimeMillis()));
        accountBook.addAccount(new LoginAccount("11111111", LoginAccount.LoginChannel.Facebook, System.currentTimeMillis()));
        accountBook.addAccount(new LoginAccount("99999999", LoginAccount.LoginChannel.Google, System.currentTimeMillis()));

        MetroBook metroBook = new MetroBook();
        metroBook.addMetro(new ParticipantMetro("M-1", System.currentTimeMillis()));
        metroBook.addMetro(new ParticipantMetro("M-2", System.currentTimeMillis()));
        metroBook.addMetro(new ParticipantMetro("M-3", System.currentTimeMillis()));
        metroBook.addMetro(new ParticipantMetro("M-4", System.currentTimeMillis()));

        CastleStateBook castleStateBook = new CastleStateBook();
        castleStateBook.attachCastleState(new CastleState(OpenState.Ready, OpenState.Open, "Ready -> Open"));
        castleStateBook.attachCastleState(new CastleState(OpenState.Open, OpenState.Suspended, "Open -> Suspended"));
        castleStateBook.attachCastleState(new CastleState(OpenState.Suspended, OpenState.Open, "Suspended -> Open"));
        castleStateBook.attachCastleState(new CastleState(OpenState.Open, OpenState.Closed, "Open -> Closed"));

        history.attachAccountBook(accountBook);
        history.attachMetroBook(metroBook);
        history.attachCastleStateBook(castleStateBook);

        historyBundleStore.create(history);

        // retrieve test
        history = historyBundleStore.retrieve(id);
        accountBook = history.getAccountBook();
        Assert.assertEquals(3, accountBook.findAll().size());

        metroBook = history.getMetroBook();
        Assert.assertEquals(4, metroBook.findAll().size());

        castleStateBook = history.getCastleStateBook();
        Assert.assertEquals(4, castleStateBook.findAll().size());


        // update test
        castleStateBook.attachCastleState(new CastleState(OpenState.Closed, OpenState.Open, "Closed -> Open"));
        historyBundleStore.updateCastleStateBook(history);
        history = historyBundleStore.retrieve(id);
        castleStateBook = history.getCastleStateBook();
        Assert.assertEquals(5, castleStateBook.findAll().size());

        // delete test
        historyBundleStore.delete(id);
        try {
            historyBundleStore.retrieve(id);
        }
        catch (NonExistenceException e) {
            System.out.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }

}
