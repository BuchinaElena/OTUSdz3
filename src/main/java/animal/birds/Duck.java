package animal.birds;
import animal.Animal;
import data.AnimalTypeData;
import data.ColorData;

public class Duck extends Animal implements Flying {

    public Duck(String id, String name, int age, int weight, ColorData colorData){
        super(id, AnimalTypeData.valueOf("DUCK"),name,age,weight,colorData);
    }

    @Override
    public void say(){
        System.out.println("Кря");
    }

    @Override
    public void fly() {
        System.out.println("Я лечу!");
    }
}
