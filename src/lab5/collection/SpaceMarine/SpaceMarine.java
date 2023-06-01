package lab5.collection.SpaceMarine;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class SpaceMarine {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer health; //Поле может быть null, Значение поля должно быть больше 0
    private Integer heartCount; //Поле может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 3
    private String achievements; //Поле может быть null
    private Weapon weaponType; //Поле не может быть null
    private Chapter chapter; //Поле не может быть null

    public SpaceMarine(long id, String name, Coordinates coordinates, LocalDateTime creationDate, Integer health, Integer heartCount, String achievements, Weapon weaponType, Chapter chapter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.heartCount = heartCount;
        this.achievements = achievements;
        this.weaponType = weaponType;
        this.chapter = chapter;
    }

    public SpaceMarine() {
        this.creationDate = Date.from(Instant.now()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getHeartCount() {
        return heartCount;
    }

    public void setHeartCount(Integer heartCount) {
        this.heartCount = heartCount;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    @Override
    public String toString() {
        return "SpaceMarine{" + "id=" + id + ", name=" + name + ", coordinates=" + coordinates + ", creationDate=" + creationDate + ", health=" + health + ", heartCount=" + heartCount + ", achievements=" + achievements + ", weaponType=" + weaponType + ", chapter=" + chapter + '}';
    }
    
}
