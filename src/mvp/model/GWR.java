/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp.model;

/**
 *
 * @author Eki
 */
public class GWR {
    private OLSGWR olsgwr;
    private LogGwr logGwr;
    private NegBinGWR negBinGWR;
    private PoiGWR poiGWR;

    public GWR() {
    }

    public GWR(OLSGWR olsgwr, LogGwr logGwr, NegBinGWR negBinGWR, PoiGWR poiGWR) {
        this.olsgwr = olsgwr;
        this.logGwr = logGwr;
        this.negBinGWR = negBinGWR;
        this.poiGWR = poiGWR;
    }

    public OLSGWR getOlsgwr() {
        return olsgwr;
    }

    public void setOlsgwr(OLSGWR olsgwr) {
        this.olsgwr = olsgwr;
    }

    public LogGwr getLogGwr() {
        return logGwr;
    }

    public void setLogGwr(LogGwr logGwr) {
        this.logGwr = logGwr;
    }

    public NegBinGWR getNegBinGWR() {
        return negBinGWR;
    }

    public void setNegBinGWR(NegBinGWR negBinGWR) {
        this.negBinGWR = negBinGWR;
    }

    public PoiGWR getPoiGWR() {
        return poiGWR;
    }

    public void setPoiGWR(PoiGWR poiGWR) {
        this.poiGWR = poiGWR;
    }
}
