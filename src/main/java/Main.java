import animal.Animal;
import connecter.MySqlConnecter;
import dao.AnimalTable;
import data.AnimalTypeData;
import data.ColorData;
import data.CommandData;
import factory.AnimalFactory;
import tools.Number;
import tools.RandomStringUUID;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Number number = new Number();

    public static void main(String[] args) throws SQLException, IOException {

        MySqlConnecter connecter = new MySqlConnecter();

        AnimalTable animalTable = new AnimalTable(connecter);

        List<Animal> animals = new ArrayList<>();

        List<String> commandNames = new ArrayList<>();

        for(CommandData commandData: CommandData.values()){
            commandNames.add(commandData.name().toLowerCase());
        }

        while (true){
            System.out.println(String.format( "Введите команду: %s", String.join("/", commandNames)));

            String userCommandConsole = scanner.next().trim().toLowerCase();

            if(!commandNames.contains(userCommandConsole)) {
                System.out.println("Команда неверная, повторите ввод команды");
                continue;
            }

            CommandData userCommand = CommandData.valueOf(userCommandConsole.toUpperCase());

            switch (userCommand) {
                case ADD:{

                    String type = getAnimalTypeNames("Введите тип животного: \n", "Данный тип животного не существует. Введите тип заново.");;

                    String id = new RandomStringUUID().generatorId();

                    System.out.println("Введите имя животного:");
                    String name = scanner.next();

                    int animalAge = getAnimalAdeWeight("Введите возраст животного:", "Неверный возраст, попробуйте снова.");
                    int animalWeight = getAnimalAdeWeight("Введите вес животного:", "Неверный вес, попробуйте снова.");

                    ColorData colorData = getColor("Введите цвет животного: \n","Данный цвет животного не существует. Введите тип заново.");

                    Animal animal = new AnimalFactory(id, AnimalTypeData.valueOf(type.toUpperCase()), name, animalAge, animalWeight, colorData).create();

                    animalTable.createAnimal(animal);
                    break;
                }
                case LIST:{
                    animals = animalTable.findAll();
                    for(Animal animal: animals) {

                        System.out.println(animal.getTableRow());
                    }
                    break;
                }
                case UPDATE:{
                    animals = animalTable.findAll();
                    for(Animal animal: animals) {

                        System.out.println(animal.getTableRow());
                    }

                    System.out.println("Введите id животного: \n");

                    String updateAnimalID = scanner.next();
                    System.out.println(updateAnimalID);

                    String updateAnimalTypeData = getAnimalTypeNames("Введите тип животного: \n", "Данный тип животного не существует. Введите тип заново.");
                    System.out.println(updateAnimalTypeData);

                    System.out.println("Введите новое имя животного:");
                    String updateName = scanner.next();

                    int updateAnimalAge = getAnimalAdeWeight("Введите возраст животного:", "Неверный возраст, попробуйте снова.");
                    int updateAnimalWeight = getAnimalAdeWeight("Введите вес животного:", "Неверный вес, попробуйте снова.");


                    ColorData updateColorData = getColor("Введите цвет животного: \n","Данный цвет животного не существует. Введите тип заново.");
                    System.out.println(updateColorData);

                    Animal animal = new AnimalFactory(updateAnimalID, AnimalTypeData.valueOf(updateAnimalTypeData.toUpperCase()), updateName, updateAnimalAge, updateAnimalWeight, updateColorData).create();

                    animalTable.updateTable(animal, updateAnimalID);

                    break;

                }
                case FILTER:{
                    List<String> animalTypeNames = new ArrayList<>();

                    System.out.printf("Введите тип животного: %s\n", String.join("/", animalTypeNames));
                    String searchType = scanner.next().trim().toLowerCase();
                    animals = animalTable.findByType(searchType);
                    for(Animal animal: animals) {

                        System.out.println(animal.getTableRow());
                    }
                    if (!animals.contains(animals)){
                    System.out.println(String.format("Нет животных типа: %s\n", String.join("/", searchType)));
                    }
                    break;
                }
                case EXIT:{
                    MySqlConnecter.close();
                    System.exit(0);
                }
            }
        }

    }

    private static int getAnimalAdeWeight(String consoleMsg, String errMsg){
        int animalAgeWeight = -1;
        while (true){
            System.out.println(consoleMsg);
            String userAnimalAge = scanner.next();

            if(!number.isNumber(userAnimalAge)) {
                System.out.println(errMsg);
                continue;
            }

            return Integer.parseInt(userAnimalAge);
        }
    }

    private static ColorData getColor(String consoleMsg, String errMsg){
        List<String> animalColor = new ArrayList<>();

        for(ColorData colorData: ColorData.values()){
            animalColor.add(colorData.name().toLowerCase());
        }

        ColorData colorData = null;
        System.out.printf("Существуют следующие цвета: %s\n", String.join("/", animalColor));
        while (true) {
            System.out.printf(consoleMsg);
            String userAnimalColor = scanner.next();

            if (!animalColor.contains(userAnimalColor)) {
                System.out.println(errMsg);
                continue;
            }

            return ColorData.valueOf(userAnimalColor.toUpperCase());
        }
    }

    private static String getAnimalTypeNames(String consoleMsg, String errMsg){
        List<String> animalTypeNames = new ArrayList<>();

        for(AnimalTypeData animalTypeData: AnimalTypeData.values()){
            animalTypeNames.add(animalTypeData.name().toLowerCase());
        }
        AnimalTypeData animalTypeData = null;

        System.out.printf("Существуют следующие типы животных: %s\n", String.join("/", animalTypeNames));
        while (true) {
            System.out.printf(consoleMsg);
            String userAnimalTypeData = scanner.next().trim().toLowerCase();

            if (!animalTypeNames.contains(userAnimalTypeData)) {
                System.out.println(errMsg);
                continue;
            }
        return userAnimalTypeData;
        }
    }
}
