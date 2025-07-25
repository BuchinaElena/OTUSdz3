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

            case ("белый"): {
                return ColorData.WHITE;

            }
            case ("коричневый"): {
                return ColorData.BROWN;

            }
            case ("оранжевый"): {
                return ColorData.ORANGE;

            }
            case ("золотой"): {
                return ColorData.GOLD;

            }
            case ("черный"): {
                return ColorData.BLACK;

            }
            case ("розовый"): {
                return ColorData.PINK;

            }

        }
        return null;
    }
}
