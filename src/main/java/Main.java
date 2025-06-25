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

        AnimalTable animalTable = new AnimalTable();

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

                    List<String> animalTypeNames = new ArrayList<>();

                    for(AnimalTypeData animalTypeData: AnimalTypeData.values()){
                        animalTypeNames.add(animalTypeData.name().toLowerCase());
                    }

                    AnimalTypeData animalTypeData = null;
                    while (true) {
                        System.out.printf("Введите тип животного: %s\n", String.join("/", animalTypeNames));
                        String userAnimalTypeData = scanner.next().trim().toLowerCase();

                        if (!animalTypeNames.contains(userAnimalTypeData)) {
                            System.out.println("Данный тип животного не существует. Введите тип заново.");
                            continue;
                        }

                        animalTypeData = AnimalTypeData.valueOf(userAnimalTypeData.toUpperCase());
                        break;
                    }

                    String id = new RandomStringUUID().generatorId();

                    System.out.println("Введите имя животного:");
                    String name = scanner.next();

                    int animalAge = getAnimalAdeWeight("Введите возраст животного:", "Неверный возраст, попробуйте снова.");
                    int animalWeight = getAnimalAdeWeight("Введите вес животного:", "Неверный вес, попробуйте снова.");

                    List<String> animalColor = new ArrayList<>();

                    for(ColorData colorData: ColorData.values()){
                        animalColor.add(colorData.name().toLowerCase());
                    }

                    ColorData colorData = null;
                    while (true) {
                        System.out.printf("Введите цвет животного: %s\n", String.join("/", animalColor));
                        String userAnimalColor = scanner.next();

                        if (!animalColor.contains(userAnimalColor)) {
                            System.out.println("Данный цвет животного не существует. Введите тип заново.");
                            continue;
                        }

                        colorData = ColorData.valueOf(userAnimalColor.toUpperCase());
                        break;
                    }

                    Animal animal = new AnimalFactory(id, name, animalAge, animalWeight, colorData).create(animalTypeData);
                    animals.add(animal);

                    animalTable.updateTable();
                    break;
                }
                case LIST:{
                    for(Animal animal: animals) {

                    animalTable.findAll();

                        System.out.println(animal.toString());
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
}
