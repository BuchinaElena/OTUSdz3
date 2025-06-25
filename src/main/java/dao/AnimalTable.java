package dao;

import animal.Animal;
import connecter.MySqlConnecter;
import data.ColorData;
import factory.AnimalFactory;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalTable extends AbsTable implements IntTable{
        public AnimalTable() throws SQLException, IOException {
            super("animals");
            columns.put("id", "bigint PRIMARY KEY AUTO_INCREMENT");
            columns.put("type", "varchar(20)");
            columns.put("name", "varchar(20)");
            columns.put("age", "int");
            columns.put("weight", "int");
            columns.put("color", "varchar(20)");

            create();
            MySqlConnecter manager = new MySqlConnecter();
        }

        @Override
        public List<Animal> findAll() {
            List<Animal> animals = new ArrayList<>();

                try (ResultSet rs = MySqlConnecter.executeQuery ("SELECT * FROM " + tableName)) {
                    while (rs.next()) {
                        String id = rs.getString("id");
                        String type = rs.getString("type");
                        String name = rs.getString("name");
                        int age = rs.getInt("age");
                        int weight = rs.getInt("weight");
                        String color = rs.getColor("color");

                        Animal animal = new AnimalFactory(id, type, name, age, weight, color);
                        //как передать сюда цвет?
                        // конфликт колордата и стринг колор
                        //тут можно было энимал-фактори использовать?
                        animals.add(animal);
                    }
                } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return animals;
        }

        @Override
        public Animal findById(String searchId) {
            Animal animal = new AnimalFactory();

                try (ResultSet rs = MySqlConnecter.executeQuery("SELECT * FROM " + tableName + " WHERE id=" + searchId)) {
                    while (rs.next()) {
                        String id = rs.getString("id");
                        String type = rs.getString("type");
                        String name = rs.getString("name");
                        int age = rs.getInt("age");
                        int weight = rs.getInt("weight");
                        //String color = rs.getColor("color");

                        //animal = new AnimalFactory(id, type, name, age, weight, color);
                    }
                } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return animal;
        }

        public void updateTable(String id, String name, int age, int weight, ColorData colorData) {
            List<Animal> animals = new ArrayList<>();

                try (ResultSet rs = MySqlConnecter.executeQuery("INSERT INTO " + tableName + "(id, type, name, age, weight, color) VALUES(" + id + type + name + age + weight + color + ")")) {

                    while (rs.next()) {
                        String id = rs.getString("id");
                        String type = rs.getString("type");
                        String name = rs.getString("name");
                        int age = rs.getInt("age");
                        int weight = rs.getInt("weight");
                        //String color = rs.

                        Animal animal = new AnimalFactory(id, type, name, age, weight, color);
                        animals.add(animal);
                    }
                } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return animals;
        }

}
