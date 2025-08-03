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

    public static ColorData getByName(String name){
        switch (name) {

            case ("WHITE"): {
                return ColorData.WHITE;
            }
            case ("BROWN"): {
                return ColorData.BROWN;
            }
            case ("ORANGE"): {
                return ColorData.ORANGE;
            }
            case ("GOLD"): {
                return ColorData.GOLD;
            }
            case ("BLACK"): {
                return ColorData.BLACK;
            }
            case ("PINK"): {
                return ColorData.PINK;
            }

        }
        return null;
    }
}
