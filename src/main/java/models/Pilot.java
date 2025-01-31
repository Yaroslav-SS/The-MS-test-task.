package models;

public class Pilot {
    private String id; // id пилота
    private String name; // имя пилота

    public Pilot() {
    }

    public Pilot(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pilot pilot = (Pilot) o;

        if (id != null ? !id.equals(pilot.id) : pilot.id != null) return false;
        return name != null ? name.equals(pilot.name) : pilot.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

