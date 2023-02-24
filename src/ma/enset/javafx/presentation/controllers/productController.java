package ma.enset.javafx.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.javafx.dao.CategoryDaoImpl;
import ma.enset.javafx.dao.ProductDaoImpl;
import ma.enset.javafx.dao.entities.Category;
import ma.enset.javafx.dao.entities.Product;
import ma.enset.javafx.metier.CatalogueService;
import ma.enset.javafx.metier.CatalogueServiceImp;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class productController implements Initializable {
   @FXML
   private TextField textNom;
   @FXML
   private TextField textReference;
   @FXML
   private TextField textPrix ;
   @FXML
   private TextField textFind;
   @FXML
   private ComboBox<Category> comboCategorie ;
   @FXML
   private ListView<Product> listViewProduct ;
   @FXML
   private TableView<Product> tableViewProduct ;

   @FXML private TableColumn<Long,Product> columnID;

    @FXML private TableColumn<String,Product> columnName;
    @FXML private TableColumn<String, Product> columnReference;
    @FXML private TableColumn<Float, Product> columnPrice;
    @FXML private TableColumn<Category, Product> columnCategory;
    @FXML private CatalogueService catalogueService;

    ObservableList<Product> data = FXCollections.observableArrayList();


    private Product selectedProduct ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            tableViewProduct.setItems(data);
            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
            columnReference.setCellValueFactory(new PropertyValueFactory<>("reference"));
            columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
            catalogueService = new CatalogueServiceImp(new ProductDaoImpl(),new CategoryDaoImpl());
            comboCategorie.getItems().addAll(catalogueService.getAllCategory());
            loadData();

            textFind.textProperty().addListener((observableValue, s, t1) -> {
                data.clear();
                data.addAll(catalogueService.searchProductByQuery(t1));
            });


                   tableViewProduct.getSelectionModel().selectedItemProperty().addListener((observableValue, product, t1) -> {
                       selectedProduct = observableValue.getValue();
                       if(selectedProduct!=null) {

                           textNom.setText(selectedProduct.getName());
                           textReference.setText(selectedProduct.getReference());
                           textPrix.setText(String.valueOf(selectedProduct.getPrice()));

                          comboCategorie.valueProperty().setValue(selectedProduct.getCategory());

                       }
                   });
    }

    private void loadData(){
        data.clear();
        data.addAll(catalogueService.getAllProducts());
    }
    private void clearView(){
        textNom.clear();
        textReference.clear();
        textPrix.clear();
        comboCategorie.valueProperty().setValue(null);
    }
    public void addProduct(){
            Product p = new Product();
            p.setName(textNom.getText());
            p.setReference(textReference.getText());
            p.setPrice(Float.parseFloat(textPrix.getText()));
            p.setCategory(comboCategorie.getSelectionModel().getSelectedItem());

            catalogueService.addProduct(p);

            loadData();
            clearView();

    }
    public void deleteProduct(){
       Product p =  tableViewProduct.getSelectionModel().getSelectedItem();
       catalogueService.deleteProduct(p);

       loadData();
       clearView();

    }

    public void updateProduct(){
        selectedProduct.setName(textNom.getText());
        selectedProduct.setPrice(Float.parseFloat(textPrix.getText()));
        selectedProduct.setReference(textReference.getText());
        selectedProduct.setCategory(catalogueService.findCategoryName(String.valueOf(comboCategorie.getSelectionModel().getSelectedItem())));
        catalogueService.updateProduct(selectedProduct);

        loadData();
        clearView();
    }



}
