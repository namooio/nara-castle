package namoo.nara.castle.cp.akka.actor.store.command.castellan;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.share.domain.protocol.NaraCommand;

public class UpdateCastellanCommand implements NaraCommand {
    //
    private Castellan castellan;

    public UpdateCastellanCommand(Castellan castellan) {
        //
        this.castellan = castellan;
    }

    public Castellan getCastellan() {
        return castellan;
    }
}
