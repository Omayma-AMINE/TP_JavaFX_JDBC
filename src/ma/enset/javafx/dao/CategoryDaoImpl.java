package ma.enset.javafx.dao;

import ma.enset.javafx.dao.entities.Category;
import ma.enset.javafx.dao.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao{
    @Override
    public Category find(long id) {
        Connection connection = ConnexionDBSingleton.getConnection();
        Category c = new Category();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from CATEGORY where ID=?");
            pstm.setLong(1,id);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()){
                c.setId(rs.getLong("ID"));
                c.setName(rs.getString("NAME"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return c;
    }

    public Category findByQuery(String categoryName) {
        Connection connection = ConnexionDBSingleton.getConnection();
        Category c = new Category();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from CATEGORY where NAME=?");
            pstm.setString(1,categoryName);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()){
                c.setId(rs.getLong("ID"));
                c.setName(rs.getString("NAME"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public List<Category> findAll() {
        List<Category> listCategory = new ArrayList<>();
        Connection connection = ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from CATEGORY");
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Category c = new Category();
                c.setId(rs.getLong("ID"));
                c.setName(rs.getString("NAME"));
                listCategory.add(c);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return listCategory;
    }

    @Override
    public void save(Category o) {

        Connection connection = ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("insert into CATEGORY(NAME) values(?)");

            pstm.setString( 1, o.getName());
           pstm.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Category o) {

        Connection connection = ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("delete from CATEGORY where ID=?");
            pstm.setLong(1,o.getId());
            pstm.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category o) {
        Connection connection = ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("update CATEGORY set NAME=? where ID=?");
            pstm.setString(1,o.getName());
            pstm.setLong(2, o.getId());

            pstm.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
