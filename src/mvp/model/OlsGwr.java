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
import mvp.view.OutputView;
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
    private StringProperty shpPath;
    private String text;
    private Rengine r;

    public OlsGwr() {
        this.xCoordValues = FXCollections.observableArrayList();
        this.yCoordValues = FXCollections.observableArrayList();
        this.depVarValues = FXCollections.observableArrayList();
        this.indVarValues = FXCollections.observableArrayList();
        this.indVar = FXCollections.observableArrayList();
        this.depVar = new SimpleStringProperty();
        this.bandwidth = new SimpleStringProperty();
        this.selection = new SimpleStringProperty();
        this.kernel = new SimpleStringProperty();
        this.shpPath = new SimpleStringProperty();

        r = new Rengine(new String[]{"--no-save"}, false, null);
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

    /**
     * @return the shpPath
     */
    public StringProperty getShpPathProperty() {
        return shpPath;
    }

    public String getShpPath() {
        return shpPath.get();
    }

    /**
     * @param shpPath the shpPath to set
     */
    public void setShpPath(String shpPath) {
        this.shpPath.setValue(shpPath);
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

    public void formula() {
        if (r.eval("is.element(\"GWmodel\", installed.packages()[,1])").asBool().isFALSE()) {
            r.eval("install.packages(\"GWmodel\")");
        }

        double[] depVarArray = new double[depVarValues.size()];
        for (int i = 0; i < depVarArray.length; i++) {
            depVarArray[i] = (double) depVarValues.get(i);
        }

        String varNames = "";
        String formulas = depVar.get().replaceAll(" ", ".") + "~";
        String colNames = "\"" + depVar.get().replaceAll(" ", ".") + "\",";
        for (int i = 0; i < indVarValues.size() && i < indVar.size(); i++) {
            varNames = varNames + indVar.get(i).replaceAll(" ", ".").replaceAll("\\(", ".").replaceAll("\\)", ".");
            formulas = formulas + indVar.get(i).replaceAll(" ", ".").replaceAll("\\(", ".").replaceAll("\\)", ".");
            colNames = colNames + "\"" + indVar.get(i).replaceAll(" ", ".").replaceAll("\\(", ".").replaceAll("\\)", ".") + "\"";

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
                    r.assign(indVar.get(i).replaceAll(" ", ".").replaceAll("\\(", ".").replaceAll("\\)", "."), vard);
                    break;
                case "class java.lang.String":
                    String[] vars = new String[indVarValues.get(i).size()];
                    for (int j = 0; j < vars.length; j++) {
                        vars[j] = (String) indVarValues.get(i).get(j);
                    }
                    r.assign(indVar.get(i).replaceAll(" ", ".").replaceAll("\\(", ".").replaceAll("\\)", "."), vars);
                    break;
            }
        }

        System.out.println(r.eval("library(GWmodel)"));
        System.out.println(r.eval("library(sp)"));
        System.out.println("=========================");
        System.out.println(r.assign("depvar", depVarArray));
        System.out.println(r.eval("depvar"));
        r.eval("indvar<- cbind(" + varNames + ")");
        System.out.println(r.eval("indvar"));
        System.out.println("=========================");
        System.out.println(r.eval("dataset <- as.data.frame(cbind(depvar,indvar))"));
        System.out.println(r.eval("colnames(dataset) <- c(" + colNames + ")"));
        System.out.println("=========================");
        if (xCoordValues.get(0).equals("shp") && yCoordValues.get(0).equals("shp")) {
            System.out.println("-GET IN-");
            r.assign("mapFile", shpPath.getValue());
            File rScriptS = new File("src/rscript/uploadMap.r");
            System.out.println(r.eval("source(\"" + rScriptS.toURI().getPath().substring(1) + "\")"));
            System.out.println(r.eval("datacoord <- centroid.reg.f"));
            System.out.println(r.eval("xCoord <- centroid.reg.f[,1]"));
            System.out.println(r.eval("yCoord <- centroid.reg.f[,2]"));
            System.out.println("=========================");
        } else {
            System.out.println("-MANUAL COORDINATE-");
            double[] xCoordArray = new double[xCoordValues.size()];
            double[] yCoordArray = new double[yCoordValues.size()];
            for (int i = 0; i < xCoordValues.size() && i < yCoordValues.size(); i++) {
                xCoordArray[i] = (double) xCoordValues.get(i);
                yCoordArray[i] = (double) yCoordValues.get(i);
            }
            r.assign("xCoord", xCoordArray);
            r.assign("yCoord", yCoordArray);
            System.out.println(r.eval("datacoord <- as.data.frame(cbind(xCoord,yCoord))"));
            System.out.println("=========================");
        }
        System.out.println(r.eval("datas <- SpatialPointsDataFrame(coords = datacoord,data = dataset)"));
        System.out.println(r.assign("formulas", formulas));
        System.out.println("=========================");
        File rScript1 = new File("src/rscript/dMat.r");
        System.out.println(r.eval("source(\"" + rScript1.toURI().getPath().substring(1) + "\")"));
        System.out.println(r.eval("dMat <- dMat(xCoord,yCoord)"));
        System.out.println("=========================");
        File rScript2 = new File("src/rscript/olsGwr.r");
        System.out.println(r.eval("source(\"" + rScript2.toURI().getPath().substring(1) + "\")"));
        System.out.println(r.eval("result <- olsgwr(formulas,datas,\"gaussian\",dMat)"));
        REXP result = r.eval("result$SDF$Intercept");
        System.out.println(result);
        r.eval("data <- as.data.frame(result$SDF)");
        File rScript3 = new File("src/rscript/mapResult.r");

        System.out.println("=========================");
        for (int j = 0; j < indVar.size(); j++) {
            String var = indVar.get(j).replaceAll(" ", ".").replaceAll("\\(", ".").replaceAll("\\)", ".");
            String pathDir = System.getProperty("user.dir").replaceAll("\\\\", "/") + "/src/resources/maps/peta_b" + j + ".png";
            r.assign("var", var);
            r.assign("pathdir", pathDir);
            r.eval("source(\"" + rScript3.toURI().getPath().substring(1) + "\")");
            System.out.println(r.eval("dev.off()"));
            System.out.println(r.eval("capture.output(pathdir)"));
        }
        System.out.println("=========================");

        double[] aa = result.asDoubleArray();
//        
        System.out.println(aa[0]);
//       
        text = "";
        // for(int i=0;i<aa.length;i++){
        for (int j = 0; j < aa.length; j++) {
            text += Double.toString(aa[j]) + "\t";
        }
//            text += "\n";
//            }
    }

    public void formula2() {
        if (r.eval("is.element(\"GWmodel\", installed.packages()[,1])").asBool().isFALSE()) {
            r.eval("install.packages(\"GWmodel\")");
        }

        double[] xCoordArray = new double[xCoordValues.size()];
        double[] yCoordArray = new double[yCoordValues.size()];
        double[] depVarArray = new double[depVarValues.size()];
        for (int i = 0; i < xCoordValues.size() && i < yCoordValues.size(); i++) {
            xCoordArray[i] = (double) xCoordValues.get(i);
            yCoordArray[i] = (double) yCoordValues.get(i);
            depVarArray[i] = (double) depVarValues.get(i);
        }

        String varNames = "";
        String formulas = depVar.get().replaceAll(" ", ".") + "~";
        String colNames = "\"" + depVar.get().replaceAll(" ", ".") + "\",";
        for (int i = 0; i < indVarValues.size() && i < indVar.size(); i++) {
            varNames = varNames + indVar.get(i).replaceAll(" ", ".");
            formulas = formulas + indVar.get(i).replaceAll(" ", ".");
            colNames = colNames + "\"" + indVar.get(i).replaceAll(" ", ".") + "\"";

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
                    r.assign(indVar.get(i).replaceAll(" ", "."), vard);
                    break;
                case "class java.lang.String":
                    String[] vars = new String[indVarValues.get(i).size()];
                    for (int j = 0; j < vars.length; j++) {
                        vars[j] = (String) indVarValues.get(i).get(j);
                    }
                    r.assign(indVar.get(i).replaceAll(" ", "."), vars);
                    break;
            }
        }

        System.out.println(r.eval("library(GWmodel)"));

        System.out.println(r.assign("xCoord", xCoordArray));
        System.out.println(r.assign("yCoord", yCoordArray));
        System.out.println(r.assign("depvar", depVarArray));

        System.out.println(r.eval("indvar <- cbind(" + varNames + ")"));
        System.out.println(r.eval("indvar"));
        System.out.println(r.eval("dataset <- as.data.frame(cbind(depvar,indvar))"));
        System.out.println(r.eval("datacoord <- as.data.frame(cbind(xCoord,yCoord))"));
        System.out.println(r.eval("colnames(dataset) <- c(" + colNames + ")"));
        System.out.println(r.eval("datas <- SpatialPointsDataFrame(coords = datacoord, data = dataset)"));

        System.out.println(r.assign("formulas", formulas));

        r.assign("mapFile", shpPath.getValue());
        File rScriptS = new File("src/rscript/uploadMap.r");
        r.eval("source(\"" + rScriptS.toURI().getPath().substring(1) + "\")");

        File rScript1 = new File("src/rscript/dMat.r");
        System.out.println(r.eval("source(\"" + rScript1.toURI().getPath().substring(1) + "\")"));
        System.out.println(r.eval("dMat <- dMat(xCoord,yCoord)"));

        File rScript2 = new File("src/rscript/olsGwr.r");
        System.out.println(r.eval("source(\"" + rScript2.toURI().getPath().substring(1) + "\")"));
        System.out.println(r.eval("result <- olsgwr(formulas,datas,\"gaussian\",dMat)"));
        REXP result = r.eval("result$SDF$Intercept");
        System.out.println(result);
    }

    public String text() {
        this.text = text;
        return text;
    }
}
