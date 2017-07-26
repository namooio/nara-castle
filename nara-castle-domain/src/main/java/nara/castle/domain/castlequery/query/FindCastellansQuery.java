package nara.castle.domain.castlequery.query;

import nara.castle.domain.castlequery.model.KeyAttr;
import nara.share.domain.protocol.NaraQuery;

public class FindCastellansQuery implements NaraQuery {
    //
    private KeyAttr keyAttr;
    private String keyValue;

    private int limit;

    public FindCastellansQuery() {
        //
    }

    public FindCastellansQuery(KeyAttr keyAttr, String keyValue, int limit) {
        //
        this.keyAttr = keyAttr;
        this.keyValue = keyValue;
        this.limit = limit;
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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}