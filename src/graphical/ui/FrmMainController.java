/*
 * 
 *  * (c) kiboy5
 *  * 3rd Year BS in Computer Science @ Holy Angel University
 */
package graphical.ui;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FrmMainController implements Initializable {

    @FXML private Label lblXi1;
    @FXML private Label lblXi;
    @FXML private Label lblXU;
    @FXML private Label lblXL;
    @FXML private ToggleButton bisection;
    @FXML private ToggleButton newtonRaphson;
    @FXML private ToggleButton secant;
    @FXML private ToggleButton modifiedSecant;
    @FXML private Button calc;
    @FXML private TextField function;
    @FXML private TextField approxError;
    @FXML private TextField iteration;
    @FXML private TextField txtXU;
    @FXML private TextField txtXL;
    @FXML private TextField txtXi;
    @FXML private TextField txtXi1;
    @FXML private CheckBox approxEnable;
    @FXML private CheckBox iterEnable;
    @FXML private TableView table;
    private int translate=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table.getColumns().clear();
    }
    
    @FXML
    private void onApproxClick(){
        if(approxError.isEditable()){
            approxError.setEditable(false);
            approxError.setText("");
            approxError.setStyle("-fx-border-color: #FF6D6D;-fx-border-radius: 5px");
        }
        else{
            approxError.setEditable(true);
            approxError.setStyle("-fx-border-color: green;-fx-border-radius: 5px");
        }
    }
    
    @FXML
    private void onIterClick(){
        if(iteration.isEditable()){
            iteration.setEditable(false);
            iteration.setText("");
            iteration.setStyle("-fx-border-color: #FF6D6D;-fx-border-radius: 5px");
        }
        else{
            iteration.setEditable(true);
            iteration.setStyle("-fx-border-color: green;-fx-border-radius: 5px");
        }
    }
    
    @FXML
    private void onBisectionClick(){
        getBracketingUI();
        //initializeBisection();
    }
    @FXML
    private void onNewtonClick(){
        getOpenUI();
    }
    @FXML
    private void onSecantClick(){
        getOpenUI();
    }
    @FXML
    private void onMSecantClick(){
        getOpenUI();
    }
    @FXML
    private void onCalcClick(){
        try{
            if(txtXi.getText().equals(txtXi1.getText())
                    && secant.isSelected())
                throw new Exception("Both values cannot be the same. Would result to division by zero");
            else if(bisection.isSelected())
                initializeBisection();
            else if(newtonRaphson.isSelected())
                initializeNewtonRaphson();
            else if(modifiedSecant.isSelected())
                initializeModifiedSecant();
            else if(secant.isSelected())
                initializeSecant();
        }catch(Exception e){
            showError(e);
        }
    }
    private void showError(Exception e){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error in inputs!");
        String errorMsg = "";
        if(function.getText().isEmpty())
            errorMsg += "Invalid function\n";
        if(approxError.getText().isEmpty() && approxEnable.isSelected())
            errorMsg += "Invalid Approximate Error\n";
        if(iteration.getText().isEmpty() && iterEnable.isSelected())
            errorMsg += "Invalid iteration\n";
        if(txtXi.getText().isEmpty() || txtXi1.getText().isEmpty() ||
                txtXU.getText().isEmpty() || txtXL.getText().isEmpty())
            errorMsg+= "Invalid Inputs";
        alert.setHeaderText(errorMsg);
        alert.setContentText(e.getMessage());
        alert.getDialogPane().setExpandableContent(printError(e));
        alert.showAndWait();
    }
    private GridPane printError(Exception ex){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        return expContent;
    }
    private int set;
    private void getBracketingUI(){
        if(set==0 || set == 1){
            translate = bisection.isSelected()?300:-300;
            TranslateTransition t = new TranslateTransition(Duration.millis(200), lblXL);
            t.setToX(translate);
            t.play();
            t = new TranslateTransition(Duration.millis(200), txtXL);
            t.setToX(translate);
            t.play();
            t = new TranslateTransition(Duration.millis(200), lblXU);
            t.setToX(translate);
            t.play();
            t = new TranslateTransition(Duration.millis(200), txtXU);
            t.setToX(translate);
            t.play();
            set=1;
        }
        else if(set==2){
            translate = -300;
            TranslateTransition t = new TranslateTransition(Duration.millis(200), lblXi);
            t.setToX(translate);
            t.play();
            t = new TranslateTransition(Duration.millis(200), lblXi1);
            t.setToX(translate);
            t.play();
            t = new TranslateTransition(Duration.millis(200), txtXi);
            t.setToX(translate);
            t.play();
            t = new TranslateTransition(Duration.millis(200), txtXi1);
            t.setToX(translate);
            t.play();
            set = 1;
            getBracketingUI();
        }
    }
    
    private void getOpenUI(){
        if(set==0 || set == 2){
            translate = (newtonRaphson.isSelected()|| secant.isSelected() || modifiedSecant.isSelected())
                ?300:-300;
            TranslateTransition t = new TranslateTransition(Duration.millis(200), lblXi);
            t.setToX(translate);
            t.play();
            if(secant.isSelected() || translate<0){
                t = new TranslateTransition(Duration.millis(200), lblXi1);
                t.setToX(translate);
                t.play();
            }
            else{
                t = new TranslateTransition(Duration.millis(200), lblXi1);
                t.setToX(-translate);
                t.play();
            }
            t = new TranslateTransition(Duration.millis(200), txtXi);
            t.setToX(translate);
            t.play();
            if(secant.isSelected() || translate<0){
                t = new TranslateTransition(Duration.millis(200), txtXi1);
                t.setToX(translate);
                t.play();
            }
            else{
                t = new TranslateTransition(Duration.millis(200), txtXi1);
                t.setToX(-translate);
                t.play();
            }
            set=2;
        }
        else if(set==1){
            translate = -300;
            TranslateTransition t = new TranslateTransition(Duration.millis(200), lblXL);
            t.setToX(translate);
            t.play();
            t = new TranslateTransition(Duration.millis(200), txtXL);
            t.setToX(translate);
            t.play();
            t = new TranslateTransition(Duration.millis(200), lblXU);
            t.setToX(translate);
            t.play();
            t = new TranslateTransition(Duration.millis(200), txtXU);
            t.setToX(translate);
            t.play();
            set=2;
            getOpenUI();
        }
    }
    private void initializeBisection(){
        TableColumn iter = new TableColumn("Iterations");
        TableColumn xl = new TableColumn("xl");
        TableColumn xr = new TableColumn("xr");
        TableColumn xu = new TableColumn("xu");
        TableColumn ea = new TableColumn("Ea%");
        iter.setCellValueFactory(new PropertyValueFactory<>("iter"));
        xl.setCellValueFactory(new PropertyValueFactory<>("xu"));
        xr.setCellValueFactory(new PropertyValueFactory<>("xr"));
        xu.setCellValueFactory(new PropertyValueFactory<>("xl"));
        ea.setCellValueFactory(new PropertyValueFactory<>("ea"));
        table.getColumns().clear();
        uiDataRetriever dr = new uiDataRetriever(function.getText().replaceAll("\\s+", ""), iteration.getText(), 
                approxError.getText(), txtXL.getText(), txtXU.getText());
        ObservableList<Data> data =FXCollections.observableArrayList();
        table.setItems(data);
        dr.getData();
        for(int i = 0; i < dr.iteration; i++)
            data.add(
                    new Data(i+1, 
                    dr.xl.get(i),
                    dr.xr.get(i),
                    dr.xu.get(i),
                    dr.ea.get(i)
            ));
        
        
        table.getColumns().addAll(iter, xl, xr, xu, ea);
    }
    private void initializeNewtonRaphson(){
        TableColumn iter = new TableColumn("Iterations");
        TableColumn xi1 = new TableColumn("xi+1");
        TableColumn ea = new TableColumn("Ea%");
        iter.setCellValueFactory(new PropertyValueFactory<>("iter"));
        xi1.setCellValueFactory(new PropertyValueFactory<>("xi1"));
        ea.setCellValueFactory(new PropertyValueFactory<>("ea"));
        table.getColumns().clear();
        uiDataRetriever dr = new uiDataRetriever(function.getText().replaceAll("\\s+", ""), iteration.getText(), 
                approxError.getText(), txtXi.getText(), 2);
        ObservableList<Data> data =FXCollections.observableArrayList();
        table.setItems(data);
        dr.getData();
        for(int i = 0; i < dr.iteration; i++)
            data.add(
                    new Data(i+1, 
                    dr.xi1.get(i),
                    dr.ea.get(i)
            ));
        table.getColumns().addAll(iter, xi1, ea);   
    }
    private void initializeModifiedSecant(){
        TableColumn iter = new TableColumn("Iterations");
        TableColumn xi1 = new TableColumn("xi+1");
        TableColumn ea = new TableColumn("Ea%");
        iter.setCellValueFactory(new PropertyValueFactory<>("iter"));
        xi1.setCellValueFactory(new PropertyValueFactory<>("xi1"));
        ea.setCellValueFactory(new PropertyValueFactory<>("ea"));
        table.getColumns().clear();
        uiDataRetriever dr = new uiDataRetriever(function.getText().replaceAll("\\s+", ""), iteration.getText(), 
                approxError.getText(), txtXi.getText(), 4);
        ObservableList<Data> data =FXCollections.observableArrayList();
        table.setItems(data);
        dr.getData();
        for(int i = 0; i < dr.iteration; i++)
            data.add(
                    new Data(i+1, 
                    dr.xi1.get(i),
                    dr.ea.get(i)
            ));
        table.getColumns().addAll(iter, xi1, ea);
        
    }
    private void initializeSecant(){
        TableColumn iter = new TableColumn("Iterations");
        TableColumn xi1 = new TableColumn("xi+1");
        TableColumn ximin1 = new TableColumn("xi-1");
        TableColumn ea = new TableColumn("Ea%");
        iter.setCellValueFactory(new PropertyValueFactory<>("iter"));
        xi1.setCellValueFactory(new PropertyValueFactory<>("xi1"));
        ximin1.setCellValueFactory(new PropertyValueFactory<>("ximin1"));
        ea.setCellValueFactory(new PropertyValueFactory<>("ea"));
        table.getColumns().clear();
        uiDataRetriever dr = new uiDataRetriever(function.getText().replaceAll("\\s+", ""), iteration.getText(), 
                approxError.getText(), txtXi.getText(), txtXi1.getText(), 3);
        ObservableList<Data> data =FXCollections.observableArrayList();
        table.setItems(data);
        dr.getData();
        for(int i = 0; i < dr.iteration; i++)
            data.add(
                    new Data(i+1, 
                    dr.xi1.get(i),
                    dr.ximin1.get(i),
                    dr.ea.get(i)
            ));
        table.getColumns().addAll(iter, xi1, ximin1, ea);
        
    }
}
