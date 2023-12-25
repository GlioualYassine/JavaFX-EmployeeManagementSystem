module com.example.employee {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    //requires fontawesomefx;


    opens com.example.employee to javafx.fxml;
    opens com.example.employee.Employe;
    opens com.example.employee.Departement;
    exports com.example.employee;
}