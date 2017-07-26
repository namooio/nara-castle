package nara.castle.domain.castlequery.query;

import nara.share.domain.protocol.NaraQuery;

public class FindCastellansPageQuery implements NaraQuery {
    //
    private int page;
    private int limit;

    public FindCastellansPageQuery() {
        //
    }

    public FindCastellansPageQuery(int page, int limit) {
        //
        this.page = page;
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "FindCastellansPageQuery{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}