package lk.ijse.dep10.app.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import lk.ijse.dep10.app.model.Student;


import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class MainFormController {

    public ToggleGroup Gender;
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnForward;

    @FXML
    private Button btnNewStudent;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnSave;


    @FXML
    private Label lblGender;

    @FXML
    private ListView<String> lstContacts;

    @FXML
    private ListView<String> lstModules;

    @FXML
    private ListView<String> lstSelectedModules;

    @FXML
    private TableView<Student> tblStudentDetails;

    @FXML
    public ComboBox<String> cmbSelectedDegree;

    @FXML
    private RadioButton rdoFemale;

    @FXML
    private RadioButton rdoMale;


    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    private ArrayList<String> contactArray;
    private ArrayList<String> moduleArray;
    private String index = "";
    private String indexDegree = "";
    private String indexNumber = "";

    private int mechanicalCount=0;
    private int civilCount=0;
    private int computerCount=0;
    private int electricalCount=0;
    private int materialCount=0;
    private String selectedDegree="";

    private int studentCount=0;


    public void initialize() {
        tblStudentDetails.getSelectionModel().clearSelection();
        lstModules.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lstSelectedModules.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lstContacts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ObservableList<String> contactList = lstContacts.getItems();
        ObservableList<String> moduleList = lstModules.getItems();
        ObservableList<String> selectedModuleList = lstSelectedModules.getItems();
        ObservableList<Student> studentList = tblStudentDetails.getItems();

        tblStudentDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudentDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudentDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblStudentDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("department"));
        tblStudentDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblStudentDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("modules"));

        txtId.setDisable(true);
        txtName.setDisable(true);
        txtContact.setDisable(true);
        rdoFemale.setDisable(true);
        rdoMale.setDisable(true);
        btnAdd.setDisable(true);
        btnBack.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);
        btnRemove.setDisable(true);
        btnForward.setDisable(true);
        lstContacts.setDisable(true);
        lstModules.setDisable(true);
        lstSelectedModules.setDisable(true);
        cmbSelectedDegree.setDisable(true);


        lstContacts.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnRemove.setDisable(current == null);
        });

        lstModules.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnForward.setDisable(current == null);
        });
        lstSelectedModules.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnBack.setDisable(current == null);
        });
        tblStudentDetails.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnDelete.setDisable(current == null);

            if (current != null) {
                cmbSelectedDegree.setPromptText(selectedDegree);
                cmbSelectedDegree.setDisable(true);

               moduleArray.clear();
               contactArray.clear();
               txtContact.clear();
               txtName.clear();
                contactList.clear();
                moduleList.clear();
                selectedModuleList.clear();

                indexNumber=current.getId();
                selectedDegree=current.department;
                txtName.setText(current.getName());
                txtId.setText(current.getId());
                moduleArray.addAll(current.getModules());
                contactArray.addAll(current.getContact());
                contactList.addAll(current.getContact());
                selectedModuleList.addAll(current.getModules());


                if (rdoFemale.isSelected() || rdoMale.isSelected()) {
                    if (current.getGender() == lk.ijse.dep10.app.util.Gender.MALE){
                        rdoMale.getToggleGroup().selectToggle(rdoMale);
                    }else{
                        rdoFemale.getToggleGroup().selectToggle(rdoFemale);
                    }
                }

                switch (current.department){
                    case "Mechanical":{
                        moduleList.addAll("Thermodynamics","Machine Design","CAD Drawing","Control System","Mechanics","Manufacturing");

                        break;
                    }
                    case "Civil":{
                        moduleList.addAll("Fluid Dynamics","Soil Mechanics","Structural Design","Concrete","Road Design","Environmental Engineering");

                        break;
                    }
                    case "Computer":{
                        moduleList.addAll("Data Structures","Java programming language","DBMS","Design Patterns","OOP Concept","Web Design");

                        break;
                    }
                    case "Material":{
                        moduleList.addAll("Material Properties","Polymer","Ceramics","Material Engineering","Mechanics of Material","Nano Technology");

                        break;
                    }
                    case  "Electrical":{
                        moduleList.addAll("High Voltage","Low Voltage","Machine & Drives","Industrial Automation","Renewable Energy","Theory of Electricity");
                        break;
                    }
                }
                moduleList.removeAll(selectedModuleList);
            }
        });
    }

    @FXML
    void btnNewStudentOnAction(ActionEvent event) {
        ObservableList<String> comboSelectedDegree =  cmbSelectedDegree.getItems();
        comboSelectedDegree.clear();
        cmbSelectedDegree.getItems().addAll("Mechanical Engineering","Civil Engineering","Computer Engineering","Electrical Engineering","Material Engineering");
        cmbSelectedDegree.setPromptText("Select a Degree Programme");
        contactArray=new ArrayList<String>();
        moduleArray=new ArrayList<String>();
        ObservableList<String> selectedModuleList = lstSelectedModules.getItems();
        ObservableList<String> moduleList = lstModules.getItems();
        ObservableList<String> contactList = lstContacts.getItems();

        cmbSelectedDegree.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            moduleList.clear();
            if (current == null)return;
            switch (current){
                case "Mechanical Engineering":{
                    moduleList.addAll("Thermodynamics","Machine Design","CAD Drawing","Control System","Mechanics","Manufacturing");
                    indexDegree = "ME";
                    selectedDegree="Mechanical";
                    break;
                }
                case "Civil Engineering":{
                    moduleList.addAll("Fluid Dynamics","Soil Mechanics","Structural Design","Concrete","Road Design","Environmental Engineering");
                    indexDegree = "CE";
                    selectedDegree="Civil";
                    break;
                }
                case "Computer Engineering":{
                    moduleList.addAll("Data Structures","Java programming language","DBMS","Design Patterns","OOP Concept","Web Design");
                    indexDegree = "CS";
                    selectedDegree="Computer";
                    break;
                }
                case "Material Engineering":{
                    moduleList.addAll("Material Properties","Polymer","Ceramics","Material Engineering","Mechanics of Material","Nano Technology");
                    indexDegree = "MA";
                    selectedDegree="Material";
                    break;
                }
                case  "Electrical Engineering":{
                    moduleList.addAll("High Voltage","Low Voltage","Machine & Drives","Industrial Automation","Renewable Energy","Theory of Electricity");
                    indexDegree = "EE";
                    selectedDegree="Electrical";
                    break;
                }
            }

        });

        txtContact.getStyleClass().remove("invalid");
        txtName.getStyleClass().remove("invalid");
        lstSelectedModules.getStyleClass().remove("invalid");
        lblGender.getStyleClass().remove("invalidGender");

        txtName.clear();
        txtContact.clear();
        txtId.clear();
        contactList.clear();
        selectedModuleList.clear();
        moduleList.clear();

        txtName.setDisable(false);
        txtContact.setDisable(false);
        rdoFemale.setDisable(false);
        rdoMale.setDisable(false);
        btnAdd.setDisable(false);
        btnSave.setDisable(false);
        lstContacts.setDisable(false);
        lstModules.setDisable(false);
        lstSelectedModules.setDisable(false);
        cmbSelectedDegree.setDisable(false);


    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        txtContact.getStyleClass().remove("invalid");
        lstContacts.getStyleClass().remove("invalid");
        ObservableList<String> contactList = lstContacts.getItems();
        ObservableList<Student> studentDetailsList = tblStudentDetails.getItems();

        if (!Pattern.matches("[0-9]{3}+[-]+[0-9]{7}", txtContact.getText())) {
            txtContact.getStyleClass().add("invalid");
            txtContact.selectAll();
            txtContact.requestFocus();
            return;
        }

        if (lstContacts.getSelectionModel() != null) {
            for (String s : contactList) {
                if (txtContact.getText().equals(s)) {
                    txtContact.getStyleClass().add("invalid");
                    txtContact.selectAll();
                    txtContact.requestFocus();
                    return;
                }
            }
        }
        if (studentDetailsList.size()!=0) {
            for (Student student : studentDetailsList) {
                for (String contact : student.getContact()) {
                    if (contact.contains(txtContact.getText())) {
                        new Alert(Alert.AlertType.ERROR, String.format("Contact number: %s already exists", txtContact.getText())).showAndWait();
                        txtContact.getStyleClass().add("invalid");
                        txtContact.selectAll();
                        txtContact.requestFocus();
                        return;
                    }
                }
            }
        }

        contactList.add(txtContact.getText());
        contactArray.add(txtContact.getText());
        txtContact.clear();
        txtContact.requestFocus();

    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        ObservableList<String> contactList = lstContacts.getItems();
        String item = lstContacts.getSelectionModel().getSelectedItem();
        contactList.remove(item);
        txtContact.requestFocus();
        contactArray.remove(item);
        lstContacts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }



    @FXML
    void btnForwardOnAction(ActionEvent event) {

        ObservableList<String> selectedModulesList = lstSelectedModules.getItems();
        ObservableList<String> modulesList = lstModules.getItems();
        moduleArray.add(lstModules.getSelectionModel().getSelectedItem());
        selectedModulesList.add(lstModules.getSelectionModel().getSelectedItem());
        modulesList.remove(lstModules.getSelectionModel().getSelectedItem());

        lstModules.getSelectionModel().clearSelection();

    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        ObservableList<String> selectedModulesList = lstSelectedModules.getItems();
        ObservableList<String> modulesList = lstModules.getItems();

        moduleArray.remove(lstSelectedModules.getSelectionModel().getSelectedItem());
        modulesList.add(lstSelectedModules.getSelectionModel().getSelectedItem());
        selectedModulesList.remove(lstSelectedModules.getSelectionModel().getSelectedItem());

        lstModules.getSelectionModel().clearSelection();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        ObservableList<String> selectedModulesList = lstSelectedModules.getItems();
        ObservableList<String> modulesList = lstModules.getItems();
        ObservableList<String> contactList = lstContacts.getItems();
        ObservableList<String> selectDegreeComboBox = cmbSelectedDegree.getItems();
        Student selectedStudent = tblStudentDetails.getSelectionModel().getSelectedItem();
        ObservableList<Student> studentsList = tblStudentDetails.getItems();

        boolean valid = true;
        String name = txtName.getText().strip();
        txtName.getStyleClass().remove("invalid");
        lstSelectedModules.getStyleClass().remove("invalid");
        lblGender.getStyleClass().remove("invalidGender");
        lstContacts.getStyleClass().remove("invalid");


        if (name.isBlank() || isName(name)) {
            valid = false;
            txtName.getStyleClass().add("invalid");
            txtName.selectAll();
            txtName.requestFocus();

        }
        if (!(rdoFemale.isSelected() || rdoMale.isSelected())) {
            valid = false;
            lblGender.getStyleClass().add("invalidGender");
        }

        if (moduleArray.size() < 3) {
            valid = false;
            lstSelectedModules.getStyleClass().add("invalid");
        }
        if (contactArray.isEmpty()) {
            valid = false;
            lstContacts.getStyleClass().add("invalid");

        }

        if (valid) { // add new student

            tblStudentDetails.setDisable(false);
            if (selectedStudent == null) {

                Optional<ButtonType> optButton = new Alert(Alert.AlertType.CONFIRMATION,
                        "Once you Save, You Can't Change The Department",
                        ButtonType.YES, ButtonType.NO).showAndWait();
                if (optButton.isEmpty() || optButton.get() ==  ButtonType.NO) return;

                lstContacts.getStyleClass().remove("invalid");

                switch (selectedDegree) {
                    case "Mechanical": {
                        indexDegree = "ME";
                        mechanicalCount++;
                        indexNumber = String.format("%s%03d", indexDegree, mechanicalCount);
                        ;
                        break;
                    }
                    case "Civil": {
                        indexDegree = "CE";
                        civilCount++;
                        indexNumber = String.format("%s%03d", indexDegree, civilCount);
                        ;
                        break;
                    }
                    case "Computer": {
                        indexDegree = "CS";
                        computerCount++;
                        indexNumber = String.format("%s%03d", indexDegree, computerCount);
                        ;
                        break;
                    }
                    case "Material": {
                        indexDegree = "MA";
                        materialCount++;
                        indexNumber = String.format("%s%03d", indexDegree, materialCount);
                        ;
                        break;
                    }
                    case "Electrical": {
                        indexDegree = "EE";
                        electricalCount++;
                        indexNumber = String.format("%s%03d", indexDegree, electricalCount);
                        ;
                        break;
                    }
                }
                Student student = new Student(indexNumber, txtName.getText(), contactArray, moduleArray, rdoMale.isSelected() ? lk.ijse.dep10.app.util.Gender.MALE : lk.ijse.dep10.app.util.Gender.FEMALE, selectedDegree);

                studentsList.add(student);
                studentCount++;

            } else {  // Update student details
                Student student = new Student(indexNumber, txtName.getText(), contactArray, moduleArray, rdoMale.isSelected() ? lk.ijse.dep10.app.util.Gender.MALE : lk.ijse.dep10.app.util.Gender.FEMALE, selectedDegree);
                studentsList.add(student);
                tblStudentDetails.getSelectionModel().clearSelection();
                studentsList.remove(selectedStudent);
            }
            btnNewStudent.fire();
        }

    }


    private boolean isName(String name) {
        for (char c : name.toCharArray()) {
            if (!(Character.isLetter(c) || Character.isSpaceChar(c))) return true;
        }
        return false;
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        Optional<ButtonType> optButton = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure to delete this student?",
                ButtonType.YES, ButtonType.NO).showAndWait();
        if (optButton.isEmpty() || optButton.get() ==  ButtonType.NO) return;

        ObservableList<Student> selectedStudent = tblStudentDetails.getItems();
        if(studentCount==0) tblStudentDetails.setDisable(true);
        else tblStudentDetails.setDisable(false);
        tblStudentDetails.getItems().remove(tblStudentDetails.getSelectionModel().getSelectedItem());
        tblStudentDetails.getSelectionModel().clearSelection();
        studentCount--;
        btnNewStudent.fire();

    }

    @FXML
    void lstContactsOnKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) btnRemove.fire();
    }

    @FXML
    void lstModulesOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void lstSelectedModulesOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtContactOnAction(ActionEvent event) {

    }

    public void cmbSelectedDegreeOnAction(ActionEvent event) {

    }
    @FXML



    public void btnNewStudentMouseEntered(MouseEvent event) {

        btnNewStudent.setScaleY(1.05);
        btnNewStudent.setScaleX(1.05);
    }

    public void btnNewStudentMouseExited(MouseEvent event) {
        btnNewStudent.setScaleY(1);
        btnNewStudent.setScaleX(1);
    }

    public void btnAddMouseEntered(MouseEvent event) {
        btnAdd.setScaleY(1.05);
        btnAdd.setScaleX(1.05);
    }

    public void btnAddMouseExited(MouseEvent event) {
        btnAdd.setScaleY(1);
        btnAdd.setScaleX(1);
    }

    public void btnRemoveMouseEntered(MouseEvent event) {
        btnRemove.setScaleY(1.05);
        btnRemove.setScaleX(1.05);
    }

    public void btnRemoveMouseExited(MouseEvent event) {
        btnRemove.setScaleY(1);
        btnRemove.setScaleX(1);
    }

    public void btnForwardMouseExited(MouseEvent event) {
        btnForward.setScaleY(1.05);
        btnForward.setScaleX(1.05);
    }

    public void btnForwardMouseEntered(MouseEvent event) {
        btnForward.setScaleY(1);
        btnForward.setScaleX(1);
    }

    public void btnBackMouseEntered(MouseEvent event) {
        btnBack.setScaleY(1.05);
        btnBack.setScaleX(1.05);
    }

    public void btnBackMouseExited(MouseEvent event) {
        btnBack.setScaleY(1);
        btnBack.setScaleX(1);
    }

    public void btnDeleteMouseExited(MouseEvent event) {
        btnDelete.setScaleY(1.05);
        btnDelete.setScaleX(1.05);
    }

    public void btnDeleteMouseEntered(MouseEvent event) {
        btnDelete.setScaleY(1);
        btnDelete.setScaleX(1);
    }

    public void btnSaveMouseEntered(MouseEvent event) {
        btnSave.setScaleY(1.05);
        btnSave.setScaleX(1.05);
    }
    public void btnSaveMouseExited(MouseEvent event) {
        btnSave.setScaleY(1);
        btnSave.setScaleX(1);
    }
}
