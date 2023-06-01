package lab5.collection.SpaceMarine;

public class Coordinates {
    private Double x; //Значение поля должно быть больше -389, Поле не может быть null
    private float y;

    public Coordinates(Double x, float y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }
    

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" + "x=" + x + ", y=" + y + '}';
    }
    
}
