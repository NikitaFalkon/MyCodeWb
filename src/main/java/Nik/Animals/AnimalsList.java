package Nik.Animals;

import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalsList {
    private String url;
    private String username;
    private String password;
    private String tabl;

    public AnimalsList(){ //считывает данные из файла, в том числе и название таблицы, которая хранит животных
        //таблица хранит id, nicname, cost
        String[]  read = new String[5];
        int i=0;
        try {
            File file = new File("C:\\Users\\user\\IdeaProjects\\Itibo\\src\\main\\resources\\file.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                read[i]=line;
                i++;
            }
            this.url=read[0];
            this.username=read[1];
            this.password=read[2];
            this.tabl=read[3];
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println(e);

        } catch (IOException e) {
            System.out.println("Your file is incorrect");
            System.out.println(e);
        }

    }

    public List<Animal> getAnimals() {
        try
        {
            List<Animal> animals = new ArrayList<>();
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(this.url, this.username, this.password)){
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM "+tabl+"");
                while(resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    int cost = resultSet.getInt(3);
                    animals.add(new Animal(id, cost, name));
                }
            }
            return animals;
        }
        catch (Exception e) {
            System.out.println("Connection failed...");
            System.out.println(e);
            return null;
        }
    }
    public void  save(Animal animal)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){
                Statement statement = conn.createStatement();
                int rows = statement.executeUpdate("INSERT "+tabl+"(nickname, cost) VALUES ('"+animal.getName()+"',"+animal.getCost()+")");// так как id в таблице заполняется самостоятельно, мы можем не вставлять его
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }
    public void delete(String name)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();

                int rows = statement.executeUpdate("DELETE FROM "+tabl+" WHERE NICKNAME = '"+name+"'");
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }
    public Animal show(int id)
    {
        Animal animal;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM "+tabl+"");
                while(resultSet.next()) {
                    int id1 = resultSet.getInt(1);
                    if(id1==id)
                    {
                        String name = resultSet.getString(2);
                        int cost = resultSet.getInt(3);
                        animal=new Animal(id,cost, name);
                        return animal;
                    }

                }
            }

        }catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);

        }
        return null;
    }
}
