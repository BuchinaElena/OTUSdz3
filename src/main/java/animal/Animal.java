package animal;
import data.AnimalTypeData;
import data.ColorData;

public abstract class Animal {

    private String id = "";
    private AnimalTypeData type = null;
    private String name = "";
    private int age = -1;
    private int weight = -1;
    private ColorData color = null;

    public Animal(String id, AnimalTypeData type, String name, int age, int weight, ColorData colorData){
        this.id = id;
        this.type = type;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = colorData;
    }

    public String getId() {
        return id;
    }

    public AnimalTypeData getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public ColorData getColor() {
        return color;
    }

    public void say(){
        System.out.println("Я говорю");
    }

    public void go(){
        System.out.println("Я иду");
    }

    public void drink(){
        System.out.println("Я пью");
    }

    public void eat(){
        System.out.println("Я ем");
    }


    public String toString(){
        return String.format("Привет! Меня зовут %s, мне %d %s, я вешу %d кг, мой цвет - %s", name,age, getYearPadej(),weight,color.getName());
    }

    private String getYearPadej(){
        if(age>=11 && age<=14){
            return "лет";
        }

        int ostatok=age%10;
        if(ostatok==1){
            return "год";
        }

        if(ostatok>=2 && ostatok<=4){
            return "года";
        }

        return "лет";
    }

    public String getTableRow(){
        return String.format("id %s, type %s, name %s, age %d, weight %d, color %s \n", id, type, name,age,weight,color.getName());

    }

}

