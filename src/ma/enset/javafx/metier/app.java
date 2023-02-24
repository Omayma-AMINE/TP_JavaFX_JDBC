package ma.enset.javafx.metier;

import ma.enset.javafx.dao.CategoryDaoImpl;
import ma.enset.javafx.dao.ProductDaoImpl;
import ma.enset.javafx.dao.entities.Category;
import ma.enset.javafx.dao.entities.Product;

public class app {
    public static void main(String[] args) {
        CatalogueService catalogueService = new CatalogueServiceImp(new ProductDaoImpl(),new CategoryDaoImpl());
        Product p = new Product();
        Category c = new Category();
        c.setName("gaming");
        c.setId(1);

        p.setName("HP");
        p.setReference("Ref0001");
        p.setPrice(5000);
        p.setCategory(c);
        catalogueService.addProduct(p);

    }
}
