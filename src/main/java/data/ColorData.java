package data;

public enum ColorData {
    WHITE("белый"),
    BROWN("коричневый"),
    ORANGE("оранжевый"),
    GOLD("золотой"),
    BLACK("черный"),
    PINK("розовый");

    private String name;

    ColorData(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
