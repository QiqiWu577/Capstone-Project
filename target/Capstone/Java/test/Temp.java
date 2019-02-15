package test;

import javax.persistence.*;

@Entity
public class Temp {

    @Column
    String name;

    @Id
    @GeneratedValue
    int num;

    public Temp(){}

    public Temp(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
