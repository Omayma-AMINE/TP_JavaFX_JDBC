package ma.enset.javafx.dao;

import ma.enset.javafx.dao.entities.Product;

import java.util.List;

public interface ProductDao extends Dao<Product>{

    List<Product> findByQuery(String query);

}
