/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.rosuda.JRI.Rengine;

/**
 *
 * @author Eki
 */
public class NegBinReg {
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
    
    public NegBinReg(){
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
}
