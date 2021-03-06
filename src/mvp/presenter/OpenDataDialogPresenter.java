/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp.presenter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mvp.model.Data;
import mvp.model.Variable;
import mvp.view.MainWindowView;
import mvp.view.OpenDataDialogView;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;

/**
 *
 * @author Eki
 */
public class OpenDataDialogPresenter {

    private final OpenDataDialogView view;
    private final MainWindowView mwview;
    private final Data data;

    public OpenDataDialogPresenter(OpenDataDialogView view, MainWindowView mwview, Data data) {
        this.view = view;
        this.mwview = mwview;
        this.data = data;
        attachEvents();
    }

    private void attachEvents() {
        view.fileBtn.setOnAction(e -> showOpenDialog());
        view.shapeFileBtn.setOnAction(e -> showOpenMapDialog());
        view.okBtn.setOnAction(e -> {
            try {
                parseFile(view.filePathField.getText(), view.colHeaderCb.isSelected());
            } catch (IOException ex) {
                Logger.getLogger(OpenDataDialogPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        view.cancelBtn.setOnAction(e -> view.closeStage());
    }

    private void showOpenDialog() {
        try {
            FileChooser fileDialog = new FileChooser();
            fileDialog.setTitle("Open File");
            fileDialog.setInitialDirectory(new File("C:\\"));
            fileDialog.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Excel Files", "*.xls", "*.xlsx"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            File file = fileDialog.showOpenDialog(stage);
            view.enterField(file.getPath());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showOpenMapDialog() {
        try {
            FileChooser fdS = new FileChooser();
            fdS.setTitle("Open Map File");
            fdS.setInitialDirectory(new File("C:\\"));
            fdS.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Shapefiles", "*.shp"));

            Stage stageS = new Stage();
            stageS.initModality(Modality.APPLICATION_MODAL);
            File fileS = fdS.showOpenDialog(stageS);
            view.enterShpField(fileS.getPath());
            data.setShpPath(fileS.getPath().replaceAll("\\\\", "/"));
        } catch (Exception e) {
            data.setShpPath("empty");
            System.out.println(e.getMessage());
        }
    }

    private void parseFile(String excelFilePath, boolean colHeader) throws IOException {
        if(view.shapeFilePathField.getText().isEmpty()||view.shapeFilePathField.getText().equalsIgnoreCase("Enter the shapefile path here")){
            data.setShpPath("empty");
        }
        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath))) {
            GridBase grid = new GridBase(1000, 100);
            ObservableList<String> listHeader = FXCollections.observableArrayList();

            try (Workbook workbook = new XSSFWorkbook(inputStream)) {
                Sheet firstSheet = workbook.getSheetAt(0);
                data.setRowNumber(firstSheet.getLastRowNum());
                data.setColumnNumber(firstSheet.getRow(0).getLastCellNum());
                Iterator<Row> iterator = firstSheet.iterator();

                ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();
                for (int row = 0; row < grid.getRowCount(); row++) {
                    final ObservableList<SpreadsheetCell> list = FXCollections.observableArrayList();
                    for (int column = 0; column < grid.getColumnCount(); column++) {
                        list.add(SpreadsheetCellType.STRING.createCell(row, column, 1, 1, ""));
                    }
                    rows.add(list);
                }

                if (colHeader) {
                    if (iterator.hasNext()) {
                        Row headerRow = iterator.next();
                        Iterator<Cell> cellIterator = headerRow.cellIterator();
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            listHeader.add(cell.getStringCellValue());
                        }
                    }

                    ObservableList<String> variableType = FXCollections.observableArrayList();
                    for (int i = 0; i < listHeader.size(); i++) {
                        variableType.add(null);
                    }

                    while (iterator.hasNext()) {
                        Row nextRow = iterator.next();
                        Iterator<Cell> cellIterator = nextRow.cellIterator();
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_STRING:
                                    variableType.set(cell.getColumnIndex(), "String");
                                    rows.get(cell.getRowIndex() - 1).set(cell.getColumnIndex(), SpreadsheetCellType.STRING.createCell(cell.getRowIndex() - 1, cell.getColumnIndex(), 1, 1, cell.getStringCellValue()));
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    variableType.set(cell.getColumnIndex(), "Boolean");
                                    rows.get(cell.getRowIndex() - 1).set(cell.getColumnIndex(), SpreadsheetCellType.STRING.createCell(cell.getRowIndex() - 1, cell.getColumnIndex(), 1, 1, String.valueOf(cell.getBooleanCellValue())));
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    variableType.set(cell.getColumnIndex(), "Double");
                                    rows.get(cell.getRowIndex() - 1).set(cell.getColumnIndex(), SpreadsheetCellType.DOUBLE.createCell(cell.getRowIndex() - 1, cell.getColumnIndex(), 1, 1, cell.getNumericCellValue()));
                                    break;
                            }
                        }
                    }

                    ObservableList<Variable> variables = FXCollections.observableArrayList();
                    for (int i = 0; i < listHeader.size() && i < variableType.size(); i++) {
                        Variable variable = new Variable(listHeader.get(i), variableType.get(i));
                        variables.add(variable);
                    }
                    data.setVariables(variables);
                } else if (!colHeader) {
                    while (iterator.hasNext()) {
                        Row nextRow = iterator.next();
                        Iterator<Cell> cellIterator = nextRow.cellIterator();
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_STRING:
                                    rows.get(cell.getRowIndex()).set(cell.getColumnIndex(), SpreadsheetCellType.STRING.createCell(cell.getRowIndex(), cell.getColumnIndex(), 1, 1, cell.getStringCellValue()));
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    rows.get(cell.getRowIndex()).set(cell.getColumnIndex(), SpreadsheetCellType.STRING.createCell(cell.getRowIndex(), cell.getColumnIndex(), 1, 1, String.valueOf(cell.getBooleanCellValue())));
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    rows.get(cell.getRowIndex()).set(cell.getColumnIndex(), SpreadsheetCellType.DOUBLE.createCell(cell.getRowIndex(), cell.getColumnIndex(), 1, 1, cell.getNumericCellValue()));
                                    break;
                            }
                        }
                    }
                }
                grid.setRows(rows);
                mwview.drawTable(listHeader, grid);
            }
        }
        view.closeStage();
    }
}
