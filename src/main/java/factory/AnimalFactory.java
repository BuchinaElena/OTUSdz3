package factory;

import animal.Animal;
import animal.birds.Duck;
import animal.pets.Cat;
import animal.pets.Dog;
import data.AnimalTypeData;
import data.ColorData;

public class AnimalFactory {

    private String id = "";
    private AnimalTypeData type = null;
    private String name = "";
    private int age = -1;
    private int weight = -1;
    private ColorData color = null;

    public AnimalFactory(String id, AnimalTypeData type,  String name, int age, int weight, ColorData colorData){
        this.id = id;
        this.type = type;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = colorData;
    }

    public Animal create(){
        switch (type){
            case CAT: {
               return new Cat(id, name, age, weight, color);
            }
            case DOG: {
                return new Dog(id, name, age, weight, color);
            }
            case DUCK: {
                return new Duck(id, name, age, weight, color);
            }

        }

        throw new RuntimeException(String.format("Animal %s not supported", type.name().toLowerCase()));
    }
}
