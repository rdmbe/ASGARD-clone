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
public class Data {
    private StringProperty path = new SimpleStringProperty();
    private ObservableList<Variable> variables = FXCollections.observableArrayList();
    private int rowNumber;
    private int columnNumber;
    
    public Data(){
        
    }
    
    public Data(String path,ObservableList<Variable>variables){
        this.path.setValue(path);
        this.variables = variables;
    }
    
    public StringProperty getPathProperty(){
        return path;
    }
    
    public String getPath() {
        return path.get();
    }
    
    public void setPath(String path){
        this.path.setValue(path);
    }
    
    public void setName(String name){
        this.path.setValue(name);
    }
    
    public ObservableList<Variable> getVariables(){
        return variables;
    }
    
    public void setVariables(ObservableList<Variable>variables){
        this.variables = variables;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }
    
    
}
