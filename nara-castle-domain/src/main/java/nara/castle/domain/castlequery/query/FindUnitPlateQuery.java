package nara.castle.domain.castlequery.query;

import nara.castle.domain.castlequery.model.KeyAttr;
import nara.share.domain.protocol.NaraQuery;

public class FindUnitPlateQuery implements NaraQuery {
    //
    private KeyAttr keyAttr;
    private String keyValue;

    public FindUnitPlateQuery() {
        //
    }

    public FindUnitPlateQuery(KeyAttr keyAttr, String keyValue) {
        //
        this.keyAttr = keyAttr;
        this.keyValue = keyValue;
    }

    public KeyAttr getKeyAttr() {
        return keyAttr;
    }

    public void setKeyAttr(KeyAttr keyAttr) {
        this.keyAttr = keyAttr;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }
}
