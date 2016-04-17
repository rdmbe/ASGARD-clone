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

/**
 *
 * @author Eki
 */
public class Gwr {
    private StringProperty model;
    private StringProperty xCoord;
    private StringProperty yCoord;
    private StringProperty kernel;
    private StringProperty bandwidth;
    private StringProperty selection;
    private StringProperty depVar;
    private ObservableList<String> indVar;
    private Variable xCoords;
    private Variable yCoords;
    private Variable depVari;
    private ObservableList<Variable> indVaris;
    
    private ObservableList<Object> xCoordValues;
    private ObservableList<Object> yCoordValues;
    private ObservableList<Object> depVarValues;
    private ObservableList<ObservableList<Object>> indVarValues;
    
    private OlsGwr olsGwr;
    
    public Gwr(){
        this.model = new SimpleStringProperty();
        this.xCoord = new SimpleStringProperty();
        this.yCoord = new SimpleStringProperty();
        this.kernel = new SimpleStringProperty();
        this.bandwidth = new SimpleStringProperty();
        this.selection = new SimpleStringProperty();
        this.depVar = new SimpleStringProperty();
        this.indVar = FXCollections.observableArrayList();
        
        this.xCoordValues = FXCollections.observableArrayList();
        this.yCoordValues = FXCollections.observableArrayList();
        this.depVarValues = FXCollections.observableArrayList();
        this.indVarValues = FXCollections.observableArrayList();
        
        olsGwr = new OlsGwr();
    }
    
    public StringProperty getModelProperty(){
        return model;
    }
    
    public String getModel(){
        return model.get();
    }
    
    public void setModel(String model){
        this.model.setValue(model);
    }
    
    public StringProperty getXCoordProperty(){
        return xCoord;
    }
    
    public String getXCoord(){
        return xCoord.get();
    }
    
    public void setXCoord(String xCoord){
        this.xCoord.setValue(xCoord);
    }
    
    public StringProperty getYCoordProperty(){
        return yCoord;
    }
    
    public String getYCoord(){
        return yCoord.get();
    }
    
    public void setYCoord(String yCoord){
        this.yCoord.setValue(yCoord);
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

    public ObservableList<Object> getxCoordValues() {
        return xCoordValues;
    }

    public void setxCoordValues(ObservableList<Object> xCoordValues) {
        this.xCoordValues = xCoordValues;
    }

    public ObservableList<Object> getyCoordValues() {
        return yCoordValues;
    }

    public void setyCoordValues(ObservableList<Object> yCoordValues) {
        this.yCoordValues = yCoordValues;
    }

    public ObservableList<Object> getDepVarValues() {
        return depVarValues;
    }

    public void setDepVarValues(ObservableList<Object> depVarValues) {
        this.depVarValues = depVarValues;
    }

    public ObservableList<ObservableList<Object>> getIndVarValues() {
        return indVarValues;
    }

    public void setIndVarValues(ObservableList<ObservableList<Object>> indVarValues) {
        this.indVarValues = indVarValues;
    }
    
    public void calculate(){
        switch(model.getValue()){
            case "OLS" : 
                olsGwr.setXCoordValues(xCoordValues);
                olsGwr.setYCoordValues(yCoordValues);
                olsGwr.setKernel(kernel.getValue());
                olsGwr.setBandwidth(bandwidth.getValue());
                olsGwr.setSelection(bandwidth.getValue());
                olsGwr.setDepVarValues(depVarValues);
                olsGwr.setDepVar(depVar.getValue());
                olsGwr.setIndVarValues(indVarValues);
                olsGwr.setIndVar(indVar);
                olsGwr.formula();
                break;
            case "Logistic" : break;
            case "Negative-Binomial" : break;
            case "Poisson" : break;
        }
    }
}
