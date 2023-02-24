package ma.enset.javafx.metier;

import ma.enset.javafx.dao.CategoryDao;
import ma.enset.javafx.dao.ProductDao;
import ma.enset.javafx.dao.entities.Category;
import ma.enset.javafx.dao.entities.Product;

import java.util.List;

public class CatalogueServiceImp implements CatalogueService{

   private ProductDao productDao;
   public CategoryDao categoryDao;

    public CatalogueServiceImp(ProductDao productDao, CategoryDao categoryDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryDao.findAll();
    }

    @Override
    public List<Product> searchProductByQuery(String query) {
        return productDao.findByQuery(query);
    }

    @Override
    public Category findCategoryName(String Name) {
        return categoryDao.findByQuery(Name);
    }

    @Override
    public void addProduct(Product p) {
        productDao.save(p);
    }

    @Override
    public void updateProduct(Product p) {
        productDao.update(p);
    }

    @Override
    public void deleteProduct(Product p) {
        productDao.delete(p);
    }

    @Override
    public void addCategory(Category c) {
        categoryDao.save(c);
    }

    @Override
    public void updateCategory(Category c) {
            categoryDao.update(c);
    }

    @Override
    public void deleteCategory(Category c) {
        categoryDao.delete(c);
    }

}
