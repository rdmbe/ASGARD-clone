/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp.model;

import java.io.File;
import java.util.Arrays;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

/**
 *
 * @author Eki
 */
public class OlsGwr {
    
    private ObservableList<Object> xCoordValues;
    private ObservableList<Object> yCoordValues;
    private ObservableList<Object> depVarValues;
    private ObservableList<ObservableList<Object>> indVarValues;
    private StringProperty depVar;
    private ObservableList<String> indVar;
    private StringProperty bandwidth;
    private StringProperty selection;
    private StringProperty kernel;
    
    private Rengine r;
    
    public OlsGwr(){
        this.xCoordValues = FXCollections.observableArrayList();
        this.yCoordValues = FXCollections.observableArrayList();
        this.depVarValues = FXCollections.observableArrayList();
        this.indVarValues = FXCollections.observableArrayList();
        this.indVar = FXCollections.observableArrayList();
        this.depVar = new SimpleStringProperty();
        this.bandwidth = new SimpleStringProperty();
        this.selection = new SimpleStringProperty();
        this.kernel = new SimpleStringProperty();
        
        r = new Rengine(new String[]{"--no-save"}, false, null);
    }
    
    public ObservableList<Object> getXCoordValues(){
        return xCoordValues;
    }
    
    public void setXCoordValues(ObservableList<Object> xCoordValues){
        this.xCoordValues = xCoordValues;
    }
    
    public ObservableList<Object> getYCoordValues(){
        return yCoordValues;
    }
    
    public void setYCoordValues(ObservableList<Object> yCoordValues){
        this.yCoordValues = yCoordValues;
    }
    
    public ObservableList<Object> getDepVarValues(){
        return depVarValues;
    }
    
    public void setDepVarValues(ObservableList<Object> depVarValues){
        this.depVarValues = depVarValues;
    }
    
    public ObservableList<ObservableList<Object>> getIndVarValues(){
        return indVarValues;
    }
    
    public void setIndVarValues(ObservableList<ObservableList<Object>> indVarValues){
        this.indVarValues = indVarValues;
    }
    
    public StringProperty getKernelProperty(){
        return kernel;
    }
    
    public String getKernel(){
        return kernel.get();
    }
    
    public void setKernel(String kernel){
        this.kernel.setValue(kernel);
    }
    
    public StringProperty getBandwidthProperty(){
        return bandwidth;
    }
    
    public String getBandwidth(){
        return bandwidth.get();
    }
    
    public void setBandwidth(String bandwidth){
        this.bandwidth.setValue(bandwidth);
    }
    
    public StringProperty getSelectionProperty(){
        return selection;
    }
    
    public String getSelection(){
        return selection.get();
    }
    
    public void setSelection(String selection){
        this.selection.setValue(selection);
    }
    
    public StringProperty getDepVarProperty(){
        return depVar;
    }
    
    public String getDepVar(){
        return depVar.get();
    }
    
    public void setDepVar(String depVar){
        this.depVar.setValue(depVar);
    }
    
    public ObservableList<String> getIndVar(){
        return indVar;
    }
    
    public void setIndVar(ObservableList<String> indVar){
        this.indVar = indVar;
    }
    
    public void formula(){
        if(r.eval("is.element(\"GWmodel\", installed.packages()[,1])").asBool().isFALSE()){
            r.eval("install.packages(\"GWmodel\")");
        }
        
        double[]xCoordArray = new double[xCoordValues.size()];
        double[]yCoordArray = new double[yCoordValues.size()];
        double[]depVarArray = new double[depVarValues.size()];
        for(int i = 0;i < xCoordValues.size() && i < yCoordValues.size();i++){
            xCoordArray[i] = (double) xCoordValues.get(i);
            yCoordArray[i] = (double) yCoordValues.get(i);
            depVarArray[i] = (double) depVarValues.get(i);
        }
        
        String varNames = "";
        String formulas = depVar.get()+"~";
        String colNames = "\""+depVar.get()+"\",";
        for(int i = 0;i < indVarValues.size() && i < indVar.size();i++){
            varNames = varNames + indVar.get(i);
            formulas = formulas + indVar.get(i);
            colNames = colNames + "\"" + indVar.get(i) + "\"";
            
            if(i != indVar.size()-1){
                varNames = varNames + ",";
                colNames = colNames + ",";
                formulas = formulas + "+";
            }

            switch(indVarValues.get(i).get(0).getClass().toString()){
                case "class java.lang.Double" :
                    double[] vard = new double[indVarValues.get(i).size()];
                    for(int j = 0;j < vard.length;j++){
                        vard[j] = (double) indVarValues.get(i).get(j);
                    }
                    r.assign(indVar.get(i), vard);
                    break;
                case "class java.lang.String" :
                    String[] vars = new String[indVarValues.get(i).size()];
                    for(int j = 0;j < vars.length;j++){
                        vars[j] = (String) indVarValues.get(i).get(j);
                    }
                    r.assign(indVar.get(i), vars);
                    break;
            }
        }
        
        System.out.println(r.eval("library(GWmodel)"));
        
        System.out.println(r.assign("xCoord",xCoordArray));
        System.out.println(r.assign("yCoord",yCoordArray));
        System.out.println(r.assign("depvar",depVarArray));
        
        System.out.println(r.eval("indvar <- cbind("+varNames+")"));
        
        System.out.println(r.eval("dataset <- as.data.frame(cbind(depvar,indvar))"));
        System.out.println(r.eval("datacoord <- as.data.frame(cbind(xCoord,yCoord))"));
        System.out.println(r.eval("colnames(dataset) <- c("+colNames+")"));
        System.out.println(r.eval("datas <- SpatialPointsDataFrame(coords = datacoord, data = dataset)"));
        
        System.out.println(r.assign("formulas", formulas));
        
        File rScript1 = new File("src/rscript/dMat.r");
        System.out.println(r.eval("source(\""+rScript1.toURI().getPath().substring(1)+"\")"));
        System.out.println(r.eval("dMat <- dMat(xCoord,yCoord)"));
        
        File rScript2 = new File("src/rscript/olsGwr.r");
        System.out.println(r.eval("source(\""+rScript2.toURI().getPath().substring(1)+"\")"));
        System.out.println(r.eval("result <- olsgwr(formulas,datas,\"gaussian\",dMat)"));
        REXP result = r.eval("result$SDF$Intercept");
        System.out.println(result);
    }
}