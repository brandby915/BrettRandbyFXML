package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Dailyhealthmodel;

/**
 *
 * @author Brett Randby
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
     @FXML
    private Button buttonCreate;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonRead;
    
    @FXML
    private Button buttonFind;

    @FXML
    private Button buttonFind2;
    
    @FXML
    private TextField searchField;

    @FXML
    private Button buttonSearch;
    
    @FXML
    private TableView<Dailyhealthmodel> dailyTable;

    @FXML
    private TableColumn<Dailyhealthmodel, Integer> tableID;

    @FXML
    private TableColumn<Dailyhealthmodel, Integer> carb;

    @FXML
    private TableColumn<Dailyhealthmodel, Integer> fat;

    @FXML
    private TableColumn<Dailyhealthmodel, Integer> protein;

    @FXML
    private TableColumn<Dailyhealthmodel, String> mood;

    @FXML
    private TableColumn<Dailyhealthmodel, String> journal;
    
    private ObservableList<Dailyhealthmodel> dailyData;
    
    @FXML
    private Button advancedButton;
    
    @FXML
    private Button buttonDetails;
    
    @FXML
    private Button buttonDetails2;
    
    //this method draws inspiration from Dr. Billah's actionShowDetailsMethod
    @FXML
    void openDetails(ActionEvent event) throws IOException {
        System.out.println("Clicked");
        
        Dailyhealthmodel selectedDaily = dailyTable.getSelectionModel().getSelectedItem();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DetailedModelView.fxml"));
        
        Parent detailedModelView = loader.load();
        
        Scene tableScene = new Scene (detailedModelView);
        
        DetailedModelController detailedController = loader.getController();
        
        detailedController.initData(selectedDaily);
        
        Stage stage = new Stage();
        stage.setScene(tableScene);
        stage.show();
        

    }
    
    //this method draws inspiration from Dr.Billahs actionShowDetailsInPlace method
    @FXML
    void openDetailsPlace(ActionEvent event) throws IOException {
        System.out.println("Clicked");
        
        Dailyhealthmodel selectedModel = dailyTable.getSelectionModel().getSelectedItem();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DetailedModelView.fxml"));
        
        Parent detailedModelView = loader.load();
        
        Scene tableScene = new Scene(detailedModelView);
        
        DetailedModelController detailedController = loader.getController();
        
        detailedController.initData(selectedModel);
        
        Scene currentScene = ((Node) event.getSource()).getScene();
        
        detailedController.setPreviousScene(currentScene);
        
        Stage stage = (Stage) currentScene.getWindow();
        
        stage.setScene(tableScene);
        stage.show();
    }

    //the following method draws inspiration from Dr. Billah's advanced search method
    @FXML
    void advancedSearch(ActionEvent event) {
        System.out.println("Clicked");
        
        String id = searchField.getId();

    }
    
    public List<Dailyhealthmodel> readByIDAdvanced(int id) {
        Query query = manager.createNamedQuery("Dailyhealthmodel.findByIDAdvanced");
        
        query.setParameter("id", id);
        
        List<Dailyhealthmodel> dailys = query.getResultList();
        for (Dailyhealthmodel daily : dailys) {
            System.out.println(daily.getDailyhealthid());
        }
        return dailys;
    }
        
    
    
    //next method based on the method of setTableData provided by Dr. Billah
    
    public void setTableData(List<Dailyhealthmodel> dailyList) {
        
        dailyList = FXCollections.observableArrayList();
        
        dailyList.forEach(dailyList::add);
        
        dailyTable.setItems(dailyData);
        dailyTable.refresh();
    }

    //The following function uses code from the demo Dr. Billah has provided. It refers to the FXMLDocumentController class
    @FXML
    void createDailyHealth(ActionEvent event) {
        Scanner scnr = new Scanner(System.in);
        
        //reads input from command line
        System.out.println("Enter Daily Health ID.");
        int id = scnr.nextInt();
        
        System.out.println("Enter carbohydrates.");
        int carb = scnr.nextInt();
        
        System.out.println("Enter fat.");
        int fat = scnr.nextInt();
        
        System.out.println("Enter protein.");
        int protein = scnr.nextInt();
        
        System.out.println("Enter mood. (less than 10 characters)");
        String mood = scnr.nextLine();
        
        System.out.println("Enter journal for the day. (less than 100 characters)");
        String journal = scnr.nextLine();
        
        //creating a new dailyhealth instance
        Dailyhealthmodel dailyHealth = new Dailyhealthmodel();
        
        //set properties
        dailyHealth.setDailyhealthid(id);
        dailyHealth.setCarbohydrates(carb);
        dailyHealth.setFat(fat);
        dailyHealth.setProtein(protein);
        dailyHealth.setMood(mood);
        dailyHealth.setJournalentry(journal);
        
        create(dailyHealth);
        

    }
    
    //2 next methods derived from readByNameCGPA
    
    @FXML
    void queryMood(ActionEvent event) {
        Scanner scnr = new Scanner(System.in);
        
        System.out.println("Enter ID");
        int id = scnr.nextInt();
        
        System.out.println("Enter fat");
        int fat = scnr.nextInt();
        
        List<Dailyhealthmodel> models = readByIDAndFat(id, fat);
    }

    @FXML
    void queryProtein(ActionEvent event) {
        Scanner scnr = new Scanner(System.in);
        
        System.out.println("Enter mood");
        String mood = scnr.nextLine();
        
        System.out.println("Enter fat");
        int fat = scnr.nextInt();
        
        List<Dailyhealthmodel> models = readByMoodAndFat(mood, fat);

    }


    //the following method refers to Dr.Billah's deleteStudent method in the demo project.
    @FXML
    void deleteDailyHealth(ActionEvent event) {
        Scanner scnr = new Scanner(System.in);
        
        //read input
        System.out.println("Enter ID.");
        
        int id = scnr.nextInt();
        //Dailyhealthmodel dh = readDailyHealth(id);
        System.out.println("ID number " + id + " will be deleted.");
        //delete(dh);

    }
    
    
    

    //the following method refers to Dr. Billah's readByID method
    @FXML
    void readDailyHealth(ActionEvent event) {
        Scanner scnr = new Scanner(System.in);
        
        System.out.println("Enter ID.");
        int id = scnr.nextInt();
        
        Dailyhealthmodel dh = readDailyHealth(id);

    }

    
    //this method was derived from Dr. Billah's updateStudent method
    @FXML
    void updateDailyHealth(ActionEvent event) {
        Scanner scnr = new Scanner(System.in);
        
        //read input
        System.out.println("Enter ID:");
        int id = scnr.nextInt();
        
        System.out.println("Enter carbs:");
        int carb = scnr.nextInt();
        
        System.out.println("Enter fat:");
        int fat = scnr.nextInt();
        
        System.out.println("Enter protein:");
        int protein = scnr.nextInt();
        
        
        System.out.println("Enter mood:");
        String mood = scnr.nextLine();
        
        System.out.println("Enter journal entry:");
        String journal = scnr.nextLine();
        
        System.out.println("Entry updated.");
        
        Dailyhealthmodel dailyHealth = new Dailyhealthmodel();
        
        dailyHealth.setDailyhealthid(id);
        dailyHealth.setCarbohydrates(carb);
        dailyHealth.setFat(fat);
        dailyHealth.setProtein(protein);
        dailyHealth.setMood(mood);
        dailyHealth.setJournalentry(journal);

        update(dailyHealth);

    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    //the following method dervies information obtained by Dr. Billah's searchByNameAction method
    @FXML
    void searchID(ActionEvent event) {
        System.out.println("Clicked");
        
        String id = searchField.getId();
        
        List<Dailyhealthmodel> daily = readDailyHealth(id);
        
        setTableData(daily);
    }
  
    //lines came from Derby DB Setup + CRUD handout provided by Dr.Billah
    EntityManager manager;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manager = (EntityManager) Persistence.createEntityManagerFactory("BrettRandbyFXMLPU").createEntityManager();
        
        //tableID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        //carb.setCellValueFactory(new PropertyValueFactory<>("Carbohydrates"));
        //fat.setCellValueFactory(new PropertyValueFactory<>("Fat"));
        //protein.setCellValueFactory(new PropertyValueFactory<>("Protein"));
        //mood.setCellFactory(new PropertyValueFactory<>("Mood"));
        //journal.setCellValueFactory(new PropertyValueFactory<>("Journal"));
        
        dailyTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }    
    
    
    //this method was created using Dr. Billah's create method as reference
    public void create(Dailyhealthmodel dailyHealth){
        try{
            manager.getTransaction().begin();
            
            //check
            if (dailyHealth.getDailyhealthid() != null) {
                
                //create dailyhealth
                manager.persist(dailyHealth);
                
                //end transaction
                manager.getTransaction().commit();
                
                System.out.println("Daily Health entry created.");
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }    
    
    //this method was created using Dr.Billahs update method as reference
    public void update(Dailyhealthmodel model) {
        try {
            
            Dailyhealthmodel existingModel = manager.find(Dailyhealthmodel.class, model.getDailyhealthid());
            
            if(existingModel != null) {
                //begin transaction
                manager.getTransaction().begin();
                
                //update
                existingModel.setCarbohydrates(model.getCarbohydrates());
                existingModel.setFat(model.getFat());;
                existingModel.setProtein(model.getProtein());
                existingModel.setMood(model.getMood());
                existingModel.setJournalentry(model.getJournalentry());
                
                //end
                manager.getTransaction().commit();
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    //this method was created using Dr. Billah's delete method as reference.
    public void delete(Dailyhealthmodel dailyHealth) {
        try {
            Dailyhealthmodel existingModel = manager.find(Dailyhealthmodel.class, dailyHealth.getDailyhealthid());
            
            //check
            if (existingModel != null) {
                //begin
                manager.getTransaction().begin();
                
                //remove
                manager.remove(existingModel);
                
                //end
                manager.getTransaction().commit();
            } 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    //this method was gathered from Dr. Billah's readAll method
    public List<Dailyhealthmodel> readAll(Integer id) {
        Query query = manager.createNamedQuery("DailyHealth.findByID");
        
        //setting parameter
        query.setParameter("id", id);
        
        //execute
        List<Dailyhealthmodel> models = query.getResultList();
        for (Dailyhealthmodel dailyHealth: models) {
            System.out.println(dailyHealth.getDailyhealthid() + " Mood: " + dailyHealth.getMood() + " Journal:  " + dailyHealth.getJournalentry()
            + "Carbohydrates: " + dailyHealth.getCarbohydrates() 
            + "Fat: " + dailyHealth.getFat()
            + "Protein: " + dailyHealth.getProtein());
        }
        return models;
    }
    
    //methods derived from Dr. Billahs readByNameAndCGPA method
    public List<Dailyhealthmodel> readByIDAndFat(Integer id, Integer fat) {
    Query query = manager.createNamedQuery("Dailyhealthmodel.findByIDAndFat");
    
    //setting query
    query.setParameter("id", id);
    query.setParameter("fat", fat);
    
    //execute
    List<Dailyhealthmodel> models = query.getResultList();
    for(Dailyhealthmodel model: models) {
        System.out.println(model.getDailyhealthid() + " " + model.getFat());
    }
    return models;
}
    public List<Dailyhealthmodel> readByMoodAndFat(String mood, Integer fat) {
    Query query = manager.createNamedQuery("Dailyhealthmodel.findByMoodAndFat");
        
    query.setParameter("mood", mood);
    query.setParameter("fat", fat);
    
    List<Dailyhealthmodel> models = query.getResultList();
    for(Dailyhealthmodel model: models) {
        System.out.println(model.getDailyhealthid() + " " + model.getMood() + model.getFat());
    }
    return models;
    }

    private List<Dailyhealthmodel> readDailyHealth(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Dailyhealthmodel readDailyHealth(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
