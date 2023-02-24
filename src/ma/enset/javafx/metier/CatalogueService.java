package ma.enset.javafx.metier;

import ma.enset.javafx.dao.entities.Category;
import ma.enset.javafx.dao.entities.Product;

import java.util.List;

public interface CatalogueService {
    List<Product> getAllProducts();
    List<Category> getAllCategory();
    List<Product> searchProductByQuery(String query);
    Category findCategoryName(String Name);
    void addProduct( Product p);
    void updateProduct(Product p);
    void deleteProduct(Product p);
    void addCategory(Category c);
    void updateCategory(Category c);
    void deleteCategory (Category c );


}
