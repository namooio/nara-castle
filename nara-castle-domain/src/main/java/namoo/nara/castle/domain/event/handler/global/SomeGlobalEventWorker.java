package namoo.nara.castle.domain.event.handler.global;

import namoo.nara.share.event.handler.GlobalEventHandler;
import namoo.nara.share.event.type.GlobalEvent;

public class SomeGlobalEventWorker extends GlobalEventHandler<GlobalEvent> {
    //
    public SomeGlobalEventWorker() {
        //
        super(GlobalEvent.class.getName());
    }

    @Override
    public void process(GlobalEvent event) {

    }
}
