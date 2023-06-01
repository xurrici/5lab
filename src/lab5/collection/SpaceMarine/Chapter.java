package lab5.collection.SpaceMarine;

public class Chapter {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long marinesCount; //Поле может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 1000

    public Chapter(String name, Long marinesCount) {
        this.name = name;
        this.marinesCount = marinesCount;
    }

    public Chapter() {
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMarinesCount() {
        return marinesCount;
    }

    public void setMarinesCount(Long marinesCount) {
        this.marinesCount = marinesCount;
    }

    @Override
    public String toString() {
        return "Chapter{" + "name=" + name + ", marinesCount=" + marinesCount + '}';
    }
    
}
