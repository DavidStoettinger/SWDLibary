package at.fh.winb.swd.libary.searchRequest;

import at.fh.winb.swd.libary.searchRequest.base.SearchRequest;

import java.util.List;

public class FSKSearchRequest extends SearchRequest {
    private long medienID;
    private int fsk;
    private int fsk_fromSearch;

    public FSKSearchRequest(){}

    public FSKSearchRequest(final int page, final int pageSize, final List<String> order){
        super(page, pageSize, order);
    }

    public long getMedienID(){return medienID;}
    public void setMedienID(final long medienID){this.medienID = medienID;}
    public long getFSK_fromMedium(){return fsk;}
    public void setFSK(int fsk){this.fsk = fsk;}
    public int getFsk_fromSearch(){return fsk_fromSearch;}
    public void setFsk_fromSearch(int fsk_fromSearch){this.fsk_fromSearch = fsk_fromSearch;}
}
