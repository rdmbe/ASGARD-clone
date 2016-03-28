/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Eki
 */
public class Variable {
    private StringProperty name = new SimpleStringProperty();
    private StringProperty type = new SimpleStringProperty();

    public Variable() {
    }

    public Variable(String name,String type){
        this.name.setValue(name);
        this.type.setValue(type);
    }
    
    public String getName(){
        return name.get();
    }
    
    public void setName(String name){
        this.name.setValue(name);
    }
    
    public String gettype(){
        return type.get();
    }
    
    public void setType(String type){
        this.type.setValue(type);
    }
}
