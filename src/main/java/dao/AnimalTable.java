package dao;

import animal.Animal;
import connecter.MySqlConnecter;
import data.AnimalTypeData;
import data.ColorData;
import factory.AnimalFactory;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalTable extends AbsTable implements IntTable{
        public AnimalTable(MySqlConnecter connecter) throws SQLException, IOException {
            super("animals", connecter);
            columns.put("id", "varchar(64) PRIMARY KEY");
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

                try (ResultSet rs = connecter.executeQuery ("SELECT * FROM " + tableName)) {
                    while (rs.next()) {
                        String id = rs.getString("id");
                        String type = rs.getString("type");
                        String name = rs.getString("name");
                        int age = rs.getInt("age");
                        int weight = rs.getInt("weight");
                        String color = rs.getString("color");

                        AnimalFactory animalFactory = new AnimalFactory(id, AnimalTypeData.valueOf(type), name, age, weight, ColorData.getByName(color));
                        animals.add(animalFactory.create());
                    }
                } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return animals;
        }

        @Override
        public Animal findById(String searchId) {
            String id = "";
            String type = "";
            String name = "";
            AnimalFactory animal = null;

            try (ResultSet rs = connecter.executeQuery("SELECT * FROM " + tableName + " WHERE id=" + searchId)) {
                while (rs.next()) {
                    id = rs.getString("id");
                    type = rs.getString("type");
                    name = rs.getString("name");
                    int age = rs.getInt("age");
                    int weight = rs.getInt("weight");
                    String color = rs.getString("color");

                    animal = new AnimalFactory(id, AnimalTypeData.valueOf(type), name, age, weight, ColorData.getByName(color));

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return animal.create();
        }

        public void updateTable(Animal animal) {

                try {
                    String sql = "INSERT INTO " + tableName + " (id, `type`, name, age, weight, color) VALUES ('" + animal.getId() +
                            "', '" + animal.getType() + "', '" + animal.getName() + "', " + animal.getAge() + ", "+ animal.getWeight() + ", '" +
                            animal.getColor() +  "')";
                    System.out.println(sql);
                    connecter.execute(sql);
                } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

}
