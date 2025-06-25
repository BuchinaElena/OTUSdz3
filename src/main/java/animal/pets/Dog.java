package animal.pets;
import animal.Animal;
import data.ColorData;

public class Dog extends Animal {

    public Dog(String id, String name, int age, int weight, ColorData colorData){
        super(id, name,age,weight,colorData);
    }

    @Override
    public void say(){
        System.out.println("Гав");
    }
}
