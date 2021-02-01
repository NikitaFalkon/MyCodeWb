package Nik.Animals;

import org.springframework.stereotype.Component;

@Component
public  class Animal {
    public String name;
    public int cost;
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName()
    {
        return name;
    }
    public String Scream() {
        return "Brr";
    }

    public Animal(int cost, String name) {
        this.name = name;
        this.cost = cost;
    }
    public Animal(int id, int cost, String name) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }
    /*public Animal(String name)
    {
        this.name=name;
        //this.cost = cost;
    }*/
    public Animal(){}
}
