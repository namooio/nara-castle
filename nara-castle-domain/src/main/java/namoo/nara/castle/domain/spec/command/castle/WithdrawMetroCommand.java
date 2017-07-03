package namoo.nara.castle.domain.spec.command.castle;

import namoo.nara.share.domain.protocol.NaraCommand;

public class WithdrawMetroCommand implements NaraCommand {
    //
    private String castleId;
    private String metroId;
    private String civilianId;

    public WithdrawMetroCommand(String castleId, String metroId, String civilianId) {
        //
        this.castleId = castleId;
        this.metroId = metroId;
        this.civilianId = civilianId;
    }

    public String getCastleId() {
        return castleId;
    }

    public String getMetroId() {
        return metroId;
    }

    public String getCivilianId() {
        return civilianId;
    }
}
