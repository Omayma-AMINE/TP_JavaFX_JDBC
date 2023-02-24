package ma.enset.javafx.dao;

import ma.enset.javafx.dao.entities.Category;
import ma.enset.javafx.dao.entities.Product;

import java.util.List;

public interface CategoryDao extends Dao<Category>{
    Category findByQuery(String query);
}
