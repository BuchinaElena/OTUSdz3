package animal.pets;
import animal.Animal;
import data.ColorData;

public class Cat extends Animal {

    public Cat(String id, String name, int age, int weight, ColorData colorData){
        super(id, name,age,weight,colorData);
    }

    @Override
    public void say(){
        System.out.println("Мяу");
    }

}
