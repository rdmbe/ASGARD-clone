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
public class Regression {
    private OLSReg oLSReg;
    private LogReg logReg;
    private NegBinReg negBinReg;
    private PoiReg poiReg;

    public Regression() {
    }

    public Regression(OLSReg oLSReg, LogReg logReg, NegBinReg negBinReg, PoiReg poiReg) {
        this.oLSReg = oLSReg;
        this.logReg = logReg;
        this.negBinReg = negBinReg;
        this.poiReg = poiReg;
    }

    public OLSReg getoLSReg() {
        return oLSReg;
    }

    public void setoLSReg(OLSReg oLSReg) {
        this.oLSReg = oLSReg;
    }

    public LogReg getLogReg() {
        return logReg;
    }

    public void setLogReg(LogReg logReg) {
        this.logReg = logReg;
    }

    public NegBinReg getNegBinReg() {
        return negBinReg;
    }

    public void setNegBinReg(NegBinReg negBinReg) {
        this.negBinReg = negBinReg;
    }

    public PoiReg getPoiReg() {
        return poiReg;
    }

    public void setPoiReg(PoiReg poiReg) {
        this.poiReg = poiReg;
    }
}
