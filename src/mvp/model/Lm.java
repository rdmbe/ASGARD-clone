/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp.model;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;

/**
 *
 * @author Eki
 */
public class Lm {
    private StringProperty model;
    private StringProperty depVar;
    private ObservableList<String> indVar;
    private Variable depVari;
    private ObservableList<Variable> indVaris;
    private ObservableList<Object> depVarValues;
    private ObservableList<ObservableList<Object>> indVarValues;
    
    
    private NegBinReg negBinReg;
    private String hasil;
    
    public Lm(){
        this.model = new SimpleStringProperty();
        this.depVar = new SimpleStringProperty();
        this.indVar = FXCollections.observableArrayList();
        
        this.depVarValues = FXCollections.observableArrayList();
        this.indVarValues = FXCollections.observableArrayList();
        
        
        //olsGwr = new OlsGwr();
        //negBinGwr = new NegBinGwr(); 
       
        
        
        
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
    
    public void calculate() throws ClassNotFoundException{
        switch(model.getValue()){
            
            case "Logistic" : 
                break;
            case "Negative-Binomial" : 
                negBinReg = new NegBinReg(); 
                negBinReg.setDepVarValues(depVarValues);
                negBinReg.setDepVar(depVar.getValue());
                negBinReg.setIndVarValues(indVarValues);
                negBinReg.setIndVar(indVar);
        {
            try {
                negBinReg.formula();
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(Lm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Lm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(Lm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (REngineException ex) {
                Logger.getLogger(Lm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (REXPMismatchException ex) {
                Logger.getLogger(Lm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            
            case "Poisson" : break;
        }
    }
    public String hasil(){
        switch(model.getValue()){
            case "OLS" :
        
            break;
                
            case "Negative-Binomial" : 
        hasil = negBinReg.text();
            break;
    }
        return hasil;
}
}
