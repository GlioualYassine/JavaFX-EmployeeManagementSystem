package com.example.employee;

import com.example.employee.Departement.Departement;
import com.example.employee.Employe.Employe;
import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.control.TableColumn;
import org.w3c.dom.events.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class dashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private Button addEmploye_addBtn;

    @FXML
    private Button addEmploye_clearBtn;

    @FXML
    private TableColumn<Employe, Integer> addEmploye_col_age;

    @FXML
    private TableColumn<Employe, String> addEmploye_col_departement;

    @FXML
    private TableColumn<Employe, Integer> addEmploye_col_employeID;

    @FXML
    private TableColumn<Employe, String> addEmploye_col_nomEmploye;

    @FXML
    private TableColumn<Employe, Double> addEmploye_col_salaire;

    @FXML
    private Button addEmploye_deleteBtn;

    @FXML
    private ComboBox<String> addEmploye_departement;

    @FXML
    private TextField addEmploye_employeAge;

    @FXML
    private TextField addEmploye_employeID;

    @FXML
    private TextField addEmploye_employeNom;

    @FXML
    private TextField addEmploye_employeSalaire;

    @FXML
    private AnchorPane addEmploye_form;

    @FXML
    private TextField addEmploye_search;

    @FXML
    private TableView<Employe> addEmploye_tableview;

    @FXML
    private Button addEmploye_updateBtn;

    @FXML
    private Button addEmployebtn;

   // @FXML
    //private FontAwesomeIcon close;

    @FXML
    private BarChart<String, Number> home_chart;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEmploye;

    @FXML
    private Label homeMasseSalarial;

    @FXML
    private Label homeTotalDepartement;

    @FXML
    private Button homebtn;

    @FXML
    private Button logout;

    @FXML
    private Button minimize;

    @FXML
    private Button salaireBtn;

    @FXML
    private AnchorPane salaire_Employe_form;

    @FXML
    private Label salaire_employe_Dept;

    @FXML
    private TextField salaire_employe_ID;

    @FXML
    private Label salaire_employe_Nom;

    @FXML
    private TextField salaire_employe_Salaire;

    @FXML
    private Button salaire_employe_clearBtn;

    @FXML
    private TableView<?> salaire_employe_tableview;

    @FXML
    private Button salaire_employe_updateBtn;

    @FXML
    private TableColumn<?, ?> salaire_empoye_col_departement;

    @FXML
    private TableColumn<?, ?> salaire_empoye_col_employeID;

    @FXML
    private TableColumn<?, ?> salaire_empoye_col_nom;

    @FXML
    private TableColumn<?, ?> salaire_empoye_col_salaire;

    @FXML
    private Label username;
    @FXML
    private  ComboBox<String> addEmploye_SearchBy ;
    @FXML
    private Label statistique_MasseSalarial;

    @FXML
    private Label statistique_totalDepartement;

    @FXML
    private Label statistique_totalEmploye;
    @FXML
    private Button statistiqueBtn;
    @FXML
    private AnchorPane statistiqueform;
    @FXML
    private TableColumn<Map<String, Object>, Integer> statistique_col_idDept;

    @FXML
    private TableColumn<Map<String, Object>, String> statistique_col_nomDept;

    @FXML
    private TableColumn<Map<String, Object>, Integer> statistique_col_nbEmp;

    @FXML
    private TableColumn<Map<String, Object>, Double> statistique_col_MasseSalariale;

    @FXML
    private TableView<Map<String, Object>> statistique_table_view;
    @FXML
    private Label statistique_max_depart_NB;

    @FXML
    private Label statistique_max_depart_Nom;

    @FXML
    private Label statistique_min_depart_NB;

    @FXML
    private Label statistique_min_depart_Nom;

    /** LOCAL OBJECTS */
    Connection connection = Databse.getConnect() ;
    private ObservableList<Employe> Listemployes = FXCollections.observableArrayList();  ;
    private List<Departement> departements = new ArrayList<>();



    /**  METHODS */
    public void close() {
        System.exit(0);
    }
    public void minimize(){
        System.out.println("trie");
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void Logout(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout ?");
        Optional<ButtonType> optional = alert.showAndWait();
        try {
            if(optional.get().equals(ButtonType.OK)){
                 logout.getScene().getWindow().hide();
                 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                 Stage stage = new Stage(); // create new windows
                 Scene scene = new Scene(fxmlLoader.load()); ; // here load the content in the scene
                 stage.setTitle("LOGIN !"); // title of the window
                 stage.setScene(scene);
                 stage.show();
        }
        } catch (IOException e) {
        throw new RuntimeException(e);
    }
    }
    public void switchForm(ActionEvent event){
        if(event.getSource()==homebtn){
            home_form.setVisible(true);
            addEmploye_form.setVisible(false);
            statistiqueform.setVisible(false);
            salaire_Employe_form.setVisible(false);
            homebtn.setStyle("-fx-background-color: linear-gradient(to bottom right , #B6A6CA , #FFDDE1  );\n");
            addEmployebtn.setStyle("-fx-background-color: transparent;\n");
            salaireBtn.setStyle("-fx-background-color: transparent;\n");
            statistiqueBtn.setStyle("-fx-background-color: transparent;\n");
            TotalEmloyeePresent();
            TotalDepartment();
            TotalMasseSalariale();
            homeChart();
        }
        else if(event.getSource()==addEmployebtn){
            home_form.setVisible(false);
            addEmploye_form.setVisible(true);
            salaire_Employe_form.setVisible(false);
            statistiqueform.setVisible(false);
            addEmployebtn.setStyle("-fx-background-color: linear-gradient(to bottom right , #B6A6CA , #FFDDE1  );\n");
            homebtn.setStyle("-fx-background-color: transparent;\n");
            salaireBtn.setStyle("-fx-background-color: transparent;\n");
            statistiqueBtn.setStyle("-fx-background-color: transparent;\n");
            addEmployeeSearch();
            fillSearchByComboBox();
        }else if(event.getSource()==statistiqueBtn) {
            home_form.setVisible(false);
            addEmploye_form.setVisible(false);
            salaire_Employe_form.setVisible(false);
            statistiqueform.setVisible(true);
            statistiqueBtn.setStyle("-fx-background-color: linear-gradient(to bottom right , #B6A6CA , #FFDDE1  );\n");
            homebtn.setStyle("-fx-background-color: transparent;\n");
            addEmployebtn.setStyle("-fx-background-color: transparent;\n");
            salaireBtn.setStyle("-fx-background-color: transparent;\n");
            StatisticsDeptTableFill();
            setMinDeptEmployee();
            setMaxDeptEmployee();
        }
        else{

            home_form.setVisible(false);
            statistiqueform.setVisible(false);
            addEmploye_form.setVisible(false);
            salaire_Employe_form.setVisible(true);
            salaireBtn.setStyle("-fx-background-color: linear-gradient(to bottom right , #B6A6CA , #FFDDE1  );\n");
            addEmployebtn.setStyle("-fx-background-color: transparent;\n");
            homebtn.setStyle("-fx-background-color: transparent;\n");
            statistiqueBtn.setStyle("-fx-background-color: transparent;\n");

        }

    }
    public void  addEmploye_ShowAllEmploye() {
        // Retrieve data from the database
       // ObservableList<Employe> observableEmployes = FXCollections.observableArrayList();
        String query = "SELECT E.*, D.* FROM EMPLOYEE E JOIN DEPARTEMENT D ON E.refdep = D.IDDEPT";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Employe e = new Employe(
                        resultSet.getInt("idEmp"),
                        resultSet.getString("NomEmp"),
                        resultSet.getInt("age"),
                        resultSet.getDouble("salaire")
                );
                Departement dept = new Departement();
                dept.setIdDept(resultSet.getInt("idDept"));
                dept.setNomDept(resultSet.getString("nomDept"));

                e.setRefDept(dept);
                Listemployes.add(e);
            }
                /** La CellValueFactory est une interface dans JavaFX qui définit une seule méthode appelée call. Cette méthode prend en argument un objet de type CellDataFeatures,
                 *  qui contient des informations sur la cellule actuelle, y compris les données associées à cette cellule. */
                addEmploye_col_employeID.setCellValueFactory(new PropertyValueFactory<>("idEmp"));
                addEmploye_col_nomEmploye.setCellValueFactory(new PropertyValueFactory<>("NomEmp"));
                addEmploye_col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
                addEmploye_col_salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
                addEmploye_col_departement.setCellValueFactory(cellData -> {
                StringProperty departmentName = new SimpleStringProperty();
                // Obtenez l'objet Employe associé à la cellule
                Employe employe = cellData.getValue();  /**  cellData.getValue() récupère l'objet Employe associé à la ligne actuelle de la TableView.
                                                            Cet objet Employe est l'objet de modèle, également appelé "objet propriétaire de cellule"*/
                if (employe != null && employe.getRefDept() != null) {
                    departmentName.set(employe.getRefDept().getNomDept());
                } else {
                    departmentName.set("");
                }

                return departmentName;
            });

            addEmploye_tableview.setItems(Listemployes);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        //return observableEmployes ;
    }
/**
 * Vous configurez la cellValueFactory pour chaque colonne de la TableView.
 * Vous appelez setItems pour spécifier quel ensemble de données (liste d'employés dans votre cas) la TableView doit afficher.
 * Lorsque la TableView a besoin d'afficher des cellules à l'écran (par exemple, lorsque vous affichez la scène), elle appelle la fonction cellValueFactory pour
 * chaque cellule visible pour obtenir les données à afficher dans cette cellule. Cela se produit dynamiquement pendant le rendu.
 * **/

    public void displayUsername(){
        username.setText(getData.username);
    }



    public void getItem(javafx.scene.input.MouseEvent mouseEvent) {
        Integer index = addEmploye_tableview.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
        addEmploye_employeID.setText(addEmploye_col_employeID.getCellData(index).toString());
        addEmploye_employeNom.setText(addEmploye_col_nomEmploye.getCellData(index).toString());
        addEmploye_employeAge.setText(addEmploye_col_age.getCellData(index).toString());
        addEmploye_employeSalaire.setText(addEmploye_col_salaire.getCellData(index).toString());

        //Assurez-vous que getCellData retourne une String
        String cellData = addEmploye_col_departement.getCellData(index);

        // Définissez la valeur du ComboBox avec la chaîne
        addEmploye_departement.setValue(cellData);
    }
    // another way
    public void getItem2Byclick(javafx.scene.input.MouseEvent mouseEvent) {
        Employe employe =  addEmploye_tableview.getSelectionModel().getSelectedItem();
        Integer index = addEmploye_tableview.getSelectionModel().getSelectedIndex();
        if (index <= -1)
            return;
        addEmploye_employeID.setText(Integer.toString(employe.getIdEmp()));
        addEmploye_employeNom.setText(employe.getNomEmp());
        addEmploye_employeAge.setText(Integer.toString(employe.getAge()));
        addEmploye_employeSalaire.setText(Double.toString(employe.getSalaire()));
        addEmploye_departement.setValue(employe.getRefDept().getNomDept());
    }
    public void addEmployeeAdd(){
        String query = "INSERT INTO EMPLOYEE (NomEmp,age,Salaire,refdep) values (?,?,?,?) ;";
        String employeID = addEmploye_employeID.getText();
        String employeNom = addEmploye_employeNom.getText();
        String employeAgeText = addEmploye_employeAge.getText();
        String employeSalaireText = addEmploye_employeSalaire.getText();
        String departementNom = addEmploye_departement.getValue() ;
        Departement departement = new Departement();
        departement.setNomDept(departementNom);
        //System.out.println(departement);
        if(validateInputs()){
            departement = departements.get(departements.indexOf(departement));
            //Employe e = new Employe(Integer.parseInt(employeID),employeNom , Integer.parseInt(employeAgeText),Double.parseDouble(employeSalaireText),departement);
            //if(checkExistEmploye(e)) {
                try {
                    System.out.println("OK");
                    PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, employeNom);
                    preparedStatement.setInt(2, Integer.parseInt(employeAgeText));
                    preparedStatement.setDouble(3, Double.parseDouble(employeSalaireText));
                    preparedStatement.setInt(4, departement.getIdDept());
                    preparedStatement.executeUpdate();
                    // Récupérer le dernier ID inséré
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int lastInsertId = generatedKeys.getInt(1);
                        System.out.println("Last Insert ID: " + lastInsertId);
                        Employe e = new Employe(lastInsertId,employeNom , Integer.parseInt(employeAgeText),Double.parseDouble(employeSalaireText),departement);
                        Listemployes.add(e);
                       // addEmploye_tableview.setItems(Listemployes);
                        addEmploye_tableview.refresh();
                    }

                    addEmployeeClearInputs();
                    System.out.println("ok");
                   // addEmployeeClearInputs();

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
           // }
        }
    }
    public void addEmloyeeUpdate(){
        String query = "UPDATE EMPLOYEE SET NomEmp = ? , age = ? , Salaire = ?  , refdep = ? where idEmp = ?  ";
        String employeID = addEmploye_employeID.getText();
        String employeNom = addEmploye_employeNom.getText();
        String employeAgeText = addEmploye_employeAge.getText();
        String employeSalaireText = addEmploye_employeSalaire.getText();
        String departementNom = addEmploye_departement.getValue() ;
        Departement departement = new Departement();
        departement.setNomDept(departementNom);
        //System.out.println(departement);
        if(validateInputs()){
            departement = departements.get(departements.indexOf(departement));
            Employe e = new Employe(Integer.parseInt(employeID),employeNom , Integer.parseInt(employeAgeText),Double.parseDouble(employeSalaireText),departement);
            //if(checkExistEmploye(e)) {
            try {
                System.out.println("OK");
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, employeNom);
                preparedStatement.setInt(2, Integer.parseInt(employeAgeText));
                preparedStatement.setDouble(3, Double.parseDouble(employeSalaireText));
                preparedStatement.setInt(4, departement.getIdDept());
                preparedStatement.setInt(5, e.getIdEmp());
                preparedStatement.executeUpdate();
                // Mise à jour de l'objet Employe dans la liste
                int index = Listemployes.indexOf(e);
                System.out.println(Listemployes);
                if (index != -1) {
                    Listemployes.set(index, e);
                    System.out.println(Listemployes);
                }
                addEmploye_tableview.refresh();

                //addEmployeeClearInputs();
                System.out.println("ok");
                // addEmployeeClearInputs();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }
    }
    public void addEmployeeDELETE(){
        String query = "DELETE FROM EMPLOYEE where idEmp = ?";
        String employeID = addEmploye_employeID.getText();
        Employe e = new Employe();
        e.setIdEmp(Integer.parseInt(employeID));
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this Employee ?");
        Optional<ButtonType> optional = alert.showAndWait();
            if(optional.get().equals(ButtonType.OK)) {
                try {
                    System.out.println("OK");
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, e.getIdEmp());
                    preparedStatement.executeUpdate();
                    // Mise à jour de l'objet Employe dans la liste
                    int index = Listemployes.indexOf(e);
                    if (index != -1) {
                        Listemployes.remove(index);
                        System.out.println(Listemployes);
                    }
                    addEmploye_tableview.refresh();

                    //addEmployeeClearInputs();
                    System.out.println("ok");
                    // addEmployeeClearInputs();

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
//    public void addEmployeeSearch(){
//        FilteredList<Employe> filteredList = new FilteredList<>(Listemployes);
//        addEmploye_search.textProperty().addListener((Observable,oldValue,newValue)->{
//            filteredList.setPredicate(predicateEmploye->{
//                if(newValue == null ||newValue.isEmpty()){
//                    return true ;
//                }
//                String searchKey = newValue.toLowerCase() ;
//                //System.out.println(searchKey);
//                if(Integer.toString(predicateEmploye.getIdEmp()).contains(searchKey)){
//                    return true ;
//                }
//                else if(predicateEmploye.getNomEmp().contains(searchKey)){
//
//                    return true ;
//                }
//                else if(predicateEmploye.getRefDept().getNomDept().contains(searchKey)){
//                    System.out.println("ok1");
//                    return true ;
//                }
//                else
//                    return false  ;
//            });
//        });
//        System.out.println(filteredList);
//        SortedList<Employe>sortedList = new SortedList<>(filteredList);
//        sortedList.comparatorProperty().bind(addEmploye_tableview.comparatorProperty());
//        addEmploye_tableview.setItems(sortedList);
//    }
//public void addEmployeeSearch() {
//    System.out.println("Key pressed");
//
//    FilteredList<Employe> filteredList = new FilteredList<>(Listemployes);
//
//
//    addEmploye_search.textProperty().addListener((observable, oldValue, newValue) -> {
//        filteredList.setPredicate(employe -> {
//            if (newValue == null || newValue.isEmpty()) {
//                return true;
//            }
//
//            String lowerCaseFilter = newValue.toLowerCase();
//
//            if (Integer.toString(employe.getIdEmp()).contains(lowerCaseFilter)) {
//                return true;
//            } else if (employe.getNomEmp().toLowerCase().contains(lowerCaseFilter)) {
//                return true;
//            } else if (employe.getRefDept().getNomDept().toLowerCase().contains(lowerCaseFilter)) {
//                return true;
//            }
//
//            return false;
//        });
//        addEmploye_tableview.setItems(filteredList);
//        // Rafraîchit la TableView pour refléter les changements
//        addEmploye_tableview.refresh();
//    });
//}
public void addEmployeeSearch() {
    System.out.println("Key pressed");

    FilteredList<Employe> filteredList = new FilteredList<>(Listemployes);

    addEmploye_search.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredList.setPredicate(employe -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();
            String selectedSearchOption = addEmploye_SearchBy.getValue();  // Récupérez la valeur du ComboBox

            switch (selectedSearchOption) {
                case "ID":
                    return Integer.toString(employe.getIdEmp()).contains(lowerCaseFilter);
                case "Nom":
                    return employe.getNomEmp().toLowerCase().contains(lowerCaseFilter);
                case "Département":
                    return employe.getRefDept().getNomDept().toLowerCase().contains(lowerCaseFilter);
                default:
                    return true;  // Si aucune option n'est sélectionnée, affichez tous les éléments
            }
        });

        addEmploye_tableview.setItems(filteredList);
        // Rafraîchit la TableView pour refléter les changements
        addEmploye_tableview.refresh();
    });
}
    public void TotalEmloyeePresent(){
        String query = "SELECT count(*) as NB from Employee";
        int nbr = 0 ;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next())
                nbr = resultSet.getInt("NB");
            home_totalEmploye.setText(Integer.toString(nbr));
            statistique_totalEmploye.setText(Integer.toString(nbr));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void TotalDepartment(){
        String query = "SELECT count(*) as NB from departement";
        int nbr = 0 ;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next())
                nbr = resultSet.getInt("NB");
            System.out.println(nbr);
            homeTotalDepartement.setText(Integer.toString(nbr));
            statistique_totalDepartement.setText(Integer.toString(nbr));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void TotalMasseSalariale(){
        String query = "SELECT sum(Salaire) as SOMME from employee";
        double SOMME = 0 ;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next())
                SOMME = resultSet.getInt("SOMME");
            homeMasseSalarial.setText(Double.toString(SOMME)+"   MAD");
            statistique_MasseSalarial.setText(Double.toString(SOMME)+"   MAD");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void homeChart(){
        home_chart.getData().clear();
        String query = "SELECT D.nomDept , count(idEmp) from Employee E , Departement D where E.refdep = D.idDept group by D.nomDept  ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            XYChart.Series chart = new XYChart.Series();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                chart.getData().add(new XYChart.Data<>(resultSet.getString(1),resultSet.getInt(2)));
            }
            home_chart.getData().add(chart);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void StatisticsDeptTableFill() {
        String query = "SELECT Count(idEmp) as NB , SUM(Salaire) as SOMME , D.idDept ,D.nomDept FROM EMPLOYEE E JOIN DEPARTEMENT D ON E.refdep = D.IDDEPT GROUP BY D.idDept, D.nomDept";
        ObservableList<Map<String, Object>> departs = FXCollections.observableArrayList();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Map<String, Object> departmentData = new HashMap<>();
                departmentData.put("idDept", resultSet.getInt("idDept"));
                departmentData.put("nomDept", resultSet.getString("nomDept"));
                departmentData.put("nombreEmployes", resultSet.getInt("NB"));
                departmentData.put("masseSalariale", resultSet.getDouble("SOMME"));

                departs.add(departmentData);
            }

            statistique_col_idDept.setCellValueFactory(cellData -> new SimpleIntegerProperty((Integer) ((Map<String, Object>) cellData.getValue()).get("idDept")).asObject());
            statistique_col_nomDept.setCellValueFactory(cellData -> new SimpleStringProperty(((Map<String, Object>) cellData.getValue()).get("nomDept").toString()));
            statistique_col_nbEmp.setCellValueFactory(cellData -> new SimpleIntegerProperty(Integer.parseInt(((Map<String, Object>) cellData.getValue()).get("nombreEmployes").toString())).asObject());
            statistique_col_MasseSalariale.setCellValueFactory(cellData -> new SimpleDoubleProperty(Double.parseDouble(((Map<String, Object>) cellData.getValue()).get("masseSalariale").toString())).asObject());


            // Lier la liste à la TableView
            statistique_table_view.setItems(departs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
public void setMinDeptEmployee(){
        String query = "select D.nomDept , count(idEmp)as NB from EMPLOYEE E JOIN DEPARTEMENT D ON E.refdep = D.IDDEPT GROUP BY D.idDept, D.nomDept HAVING NB <= ALL (select count(idEmp)as NB from EMPLOYEE E JOIN DEPARTEMENT D ON E.refdep = D.IDDEPT GROUP BY D.idDept, D.nomDept)";
    try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            statistique_min_depart_Nom.setText(resultSet.getString("nomDept"));
            statistique_min_depart_NB.setText(Integer.toString(resultSet.getInt("NB")));
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
    public void setMaxDeptEmployee(){
        String query = "select D.nomDept , count(idEmp)as NB from EMPLOYEE E JOIN DEPARTEMENT D ON E.refdep = D.IDDEPT GROUP BY D.idDept, D.nomDept HAVING NB >= ALL (select count(idEmp)as NB from EMPLOYEE E JOIN DEPARTEMENT D ON E.refdep = D.IDDEPT GROUP BY D.idDept, D.nomDept)";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                statistique_max_depart_Nom.setText(resultSet.getString("nomDept"));
                statistique_max_depart_NB.setText(Integer.toString(resultSet.getInt("NB")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public void StatisticsDeptTableFill(){
//        String query = "SELECT Count(idEmp) as NB , SUM(Salaire) as SOMME , D.idDept ,D.nomDept FROM EMPLOYEE E JOIN DEPARTEMENT D ON E.refdep = D.IDDEPT GROUP BY D.idDept, D.nomDept";
//        ObservableList<Departement> departs = FXCollections.observableArrayList();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            while(resultSet.next()){
//                Departement d = new Departement(
//                        resultSet.getInt("IDDEPT"),
//                        resultSet.getString("nomDept")
//                );
//            }
//            statistique_col_idDept.setCellValueFactory(new PropertyValueFactory<>("idDept"));
//            statistique_col_nomDept.setCellValueFactory(new PropertyValueFactory<>("nomDept"));
//
//            while (resultSet.next()) {
//                Employe e = new Employe(
//                        resultSet.getInt("idEmp"),
//                        resultSet.getString("NomEmp"),
//                        resultSet.getInt("age"),
//                        resultSet.getDouble("salaire")
//                );
//                Departement dept = new Departement();
//                dept.setIdDept(resultSet.getInt("idDept"));
//                dept.setNomDept(resultSet.getString("nomDept"));
//
//                e.setRefDept(dept);
//                Listemployes.add(e);
//            }
//            /** La CellValueFactory est une interface dans JavaFX qui définit une seule méthode appelée call. Cette méthode prend en argument un objet de type CellDataFeatures,
//             *  qui contient des informations sur la cellule actuelle, y compris les données associées à cette cellule. */
//            addEmploye_col_employeID.setCellValueFactory(new PropertyValueFactory<>("idEmp"));
//            addEmploye_col_nomEmploye.setCellValueFactory(new PropertyValueFactory<>("NomEmp"));
//            addEmploye_col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
//            addEmploye_col_salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
//            addEmploye_col_departement.setCellValueFactory(cellData -> {
//                StringProperty departmentName = new SimpleStringProperty();
//                // Obtenez l'objet Employe associé à la cellule
//                Employe employe = cellData.getValue();  /**  cellData.getValue() récupère l'objet Employe associé à la ligne actuelle de la TableView.
//                 Cet objet Employe est l'objet de modèle, également appelé "objet propriétaire de cellule"*/
//                if (employe != null && employe.getRefDept() != null) {
//                    departmentName.set(employe.getRefDept().getNomDept());
//                } else {
//                    departmentName.set("");
//                }
//
//                return departmentName;
//            });
//
//            addEmploye_tableview.setItems(Listemployes);
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//    }






    //    private Boolean validateInputs(){
//        if(
//           addEmploye_employeNom.getText().isBlank()||
//           addEmploye_employeAge.getText().isBlank()||
//           addEmploye_employeSalaire.getText().isBlank()||
//           addEmploye_departement.getValue() == null
//        ){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Message");
//            alert.setHeaderText(null);
//            alert.setContentText("Please fill all blank fields");
//            alert.showAndWait();
//            return false ;
//        }
//
//        return true ;
//    }
    public void refreshList(){
        addEmploye_tableview.refresh();
    }
    private Boolean validateInputs() {
    String employeNom = addEmploye_employeNom.getText();
    String employeAgeText = addEmploye_employeAge.getText();
    String employeSalaireText = addEmploye_employeSalaire.getText();

    if (employeNom.isBlank() || employeAgeText.isBlank() || employeSalaireText.isBlank() || addEmploye_departement.getValue() == null) {
        showAlert("Please fill all blank fields");
        return false;
    }

    try {
        int employeAge = Integer.parseInt(employeAgeText);
        if (employeAge < 0) {
            showAlert("Age must be a positive integer");
            return false;
        }
    } catch (NumberFormatException e) {
        showAlert("Invalid age format. Please enter a valid integer for age.");
        return false;
    }

    try {
        double employeSalaire = Double.parseDouble(employeSalaireText);
        if (employeSalaire < 0) {
            showAlert("Salary must be a positive number");
            return false;
        }
    } catch (NumberFormatException e) {
        showAlert("Invalid salary format. Please enter a valid number for salary.");
        return false;
    }

    return true;
}

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private Boolean checkExistEmploye(Employe e){
        if(Listemployes.contains(e)){
            showAlert("Employe Already Exists");
            return false;
        }
        return true ;
    }

    public void addEmployeeClearInputs(){
        addEmploye_employeID.setText("");
        addEmploye_employeNom.setText("");
        addEmploye_employeAge.setText("");
        addEmploye_employeSalaire.setText("");
        addEmploye_departement.getSelectionModel().clearSelection();
    }
    public void fillDepartementComboBox(){
            String query = "SELECT * FROM departement";
        try {
            Statement statement= connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                addEmploye_departement.getItems().add(resultSet.getString("NomDept"));
                Departement departement = new Departement(resultSet.getInt("idDept"),resultSet.getString("NomDept"));
                departements.add(departement);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void fillSearchByComboBox(){
        List<String> columnNames = Arrays.asList("ID", "Nom", "Département");
        ObservableList<String> searchOptions = FXCollections.observableArrayList(columnNames);
        addEmploye_SearchBy.setItems(searchOptions);
        addEmploye_SearchBy.getSelectionModel().selectFirst();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //addEmployeeShowListData();
        displayUsername();
        addEmploye_ShowAllEmploye();
        fillDepartementComboBox();
        addEmployeeSearch();
        fillSearchByComboBox();
        TotalEmloyeePresent();
        TotalDepartment();
        TotalMasseSalariale();
        homeChart();
        StatisticsDeptTableFill();
        setMinDeptEmployee();
        setMaxDeptEmployee();
    }
}
