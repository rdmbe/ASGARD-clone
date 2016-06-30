/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp.model;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngine;
import org.rosuda.REngine.REngineException;

/**
 *
 * @author Eki
 */
class NegBinReg {

    private ObservableList<Object> xCoordValues;
    private ObservableList<Object> yCoordValues;
    private ObservableList<Object> depVarValues;
    private ObservableList<ObservableList<Object>> indVarValues;
    private StringProperty depVar;
    private ObservableList<String> indVar;
    private StringProperty bandwidth;
    private StringProperty selection;
    private StringProperty kernel;
    private String text;
    private Rengine engine;

    public NegBinReg() {
        this.xCoordValues = FXCollections.observableArrayList();
        this.yCoordValues = FXCollections.observableArrayList();
        this.depVarValues = FXCollections.observableArrayList();
        this.indVarValues = FXCollections.observableArrayList();
        this.indVar = FXCollections.observableArrayList();
        this.depVar = new SimpleStringProperty();
        this.bandwidth = new SimpleStringProperty();
        this.selection = new SimpleStringProperty();
        this.kernel = new SimpleStringProperty();

    }

    public ObservableList<Object> getXCoordValues() {
        return xCoordValues;
    }

    public void setXCoordValues(ObservableList<Object> xCoordValues) {
        this.xCoordValues = xCoordValues;
    }

    public ObservableList<Object> getYCoordValues() {
        return yCoordValues;
    }

    public void setYCoordValues(ObservableList<Object> yCoordValues) {
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

    public StringProperty getKernelProperty() {
        return kernel;
    }

    public String getKernel() {
        return kernel.get();
    }

    public void setKernel(String kernel) {
        this.kernel.setValue(kernel);
    }

    public StringProperty getBandwidthProperty() {
        return bandwidth;
    }

    public String getBandwidth() {
        return bandwidth.get();
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth.setValue(bandwidth);
    }

    public StringProperty getSelectionProperty() {
        return selection;
    }

    public String getSelection() {
        return selection.get();
    }

    public void setSelection(String selection) {
        this.selection.setValue(selection);
    }

    public StringProperty getDepVarProperty() {
        return depVar;
    }

    public String getDepVar() {
        return depVar.get();
    }

    public void setDepVar(String depVar) {
        this.depVar.setValue(depVar);
    }

    public ObservableList<String> getIndVar() {
        return indVar;
    }

    public void setIndVar(ObservableList<String> indVar) {
        this.indVar = indVar;
    }

    public void formula() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, REngineException, REXPMismatchException {
        REngine engine = REngine.engineForClass("org.rosuda.REngine.JRI.JRIEngine");
        engine.parseAndEval("install.packages(\"MASS\")");
        engine.parseAndEval("library(MASS)");

//        if(engine.parseAndEval("is.element(\"MASS\", installed.packages()[,1])").isNull()){
//          
//        }
//         if(r.eval("is.element(\"MASS\", installed.packages()[,1])").asBool().isFALSE()){
//            r.eval("install.packages(\"MASS\")");
//        }
        //
        //engine.parseAndEval("library(MASS)");
//        if(r.eval("is.element(\"GWmodel\", installed.packages()[,1])").asBool().isFALSE()){
//            r.eval("install.packages(\"GWmodel\")");
//        }
//        if(r.eval("is.element(\"MASS\", installed.packages()[,1])").asBool().isFALSE()){
//            r.eval("install.packages(\"MASS\")");
//        }
        //double[]xCoordArray = new double[xCoordValues.size()];
        //double[]yCoordArray = new double[yCoordValues.size()];
        double[] depVarArray = new double[depVarValues.size()];
        for (int i = 0; i < depVarValues.size(); i++) {
            //xCoordArray[i] = (double) xCoordValues.get(i);
            //yCoordArray[i] = (double) yCoordValues.get(i);
            depVarArray[i] = (double) depVarValues.get(i);
        }

        String varNames = "";
        String formulas = depVar.get().replaceAll(" ", ".") + "~";
        String colNames = "\"" + depVar.get().replaceAll(" ", ".") + "\",";

        for (int i = 0; i < indVarValues.size() && i < indVar.size(); i++) {
            varNames = varNames + indVar.get(i)
                    .replaceAll(" ", ".").replaceAll("\\(", ".").replaceAll("\\)", ".") //                    .replaceAll("(", ".")
                    //                    .replaceAll(")", ".")
                    ;
            formulas = formulas + indVar.get(i)
                    .replaceAll(" ", ".").replaceAll("\\(", ".").replaceAll("\\)", ".") //                    .replaceAll("(", ".")
                    //                    .replaceAll(")", ".")
                    ;
            colNames = colNames + "\"" + indVar.get(i)
                    .replaceAll(" ", ".").replaceAll("\\(", ".").replaceAll("\\)", ".")
                    //                    .replaceAll("(", ".")
                    //                    .replaceAll(")", ".")
                    + "\"";

            if (i != indVar.size() - 1) {
                varNames = varNames + ",";
                colNames = colNames + ",";
                formulas = formulas + "+";
            }

            switch (indVarValues.get(i).get(0).getClass().toString()) {
                case "class java.lang.Double":
                    double[] vard = new double[indVarValues.get(i).size()];
                    for (int j = 0; j < vard.length; j++) {
                        vard[j] = (double) indVarValues.get(i).get(j);
                    }
                    engine.assign(indVar.get(i)
                            .replaceAll(" ", ".").replaceAll("\\(", ".").replaceAll("\\)", ".") //                            .replaceAll("(", ".")
                            //                            .replaceAll(")", ".")
                            , vard);
                    break;
                case "class java.lang.String":
                    String[] vars = new String[indVarValues.get(i).size()];
                    for (int j = 0; j < vars.length; j++) {
                        vars[j] = (String) indVarValues.get(i).get(j);
                    }
                    engine.assign(indVar.get(i)
                            .replaceAll(" ", ".").replaceAll("\\(", ".").replaceAll("\\)", ".") //                            .replaceAll("(", ".")
                            //                            .replaceAll(")", ".")
                            , vars);
                    break;
            }
        }
        engine.assign("depvar", depVarArray);
        engine.parseAndEval("depvar");
        System.out.println(engine.parseAndEval("depvar").asString());

        engine.parseAndEval("indvar <- cbind(" + varNames + ")");
        System.out.println(engine.parseAndEval("indvar <- cbind(" + varNames + ")").asString());

        engine.parseAndEval("dataset <- as.data.frame(cbind(depvar,indvar))");
       //System.out.println(engine.parseAndEval("dataset").asString());

        engine.parseAndEval("colnames(dataset)<- c(" + colNames + ")");

        engine.assign("formulas", formulas);
        engine.parseAndEval("formulas");
        System.out.println(engine.parseAndEval("formulas").asString());

        engine.parseAndEval("nb.model<-glm.nb(formula=formulas ,data = dataset)");

        org.rosuda.REngine.REXP s = engine.parseAndEval("capture.output(nb.model)");
        String[] output = s.asStrings();
        text = "";
        for (int i = 0; i < output.length; i++) {
            //System.out.println(output[i]);
            text += output[i];
            text += "\n";
        }
        //text = s.asString();

//        String[] output = s.asStrings();
//        text="";
//        for(int i = 0;i < output.length;i++){
//            text += (output[i]);
//        }
//        
//        System.out.println(r.eval("library(GWmodel)"));
        // System.out.println(r.assign("xCoord",xCoordArray));
        //System.out.println(r.assign("yCoord",yCoordArray));
//        System.out.println(r.assign("depvar",depVarArray));
//        System.out.println(r.eval("depvar"));
//        System.out.println(r.eval("indvar <- cbind("+varNames+")"));
//        //System.out.println("indvar");
//        System.out.println(r.eval("dataset <- as.data.frame(cbind(depvar,indvar))"));
//        System.out.println(r.eval("datacoord <- as.data.frame(cbind(xCoord,yCoord))"));
//        System.out.println(r.eval("colnames(dataset) <- c("+colNames+")"));
//        System.out.println(r.eval("library(MASS)"));
//        //System.out.println(r.eval("data <- as.data.frame(cbind(depvar,indvar))"));
//        System.out.println(r.eval("nb.model<-glm.nb(depvar~indvar,data=dataset)"));
//        System.out.println(r.eval("nb.model$coefficients"));
    }

    String text() {
        this.text = text;
        return text;
    }

}
