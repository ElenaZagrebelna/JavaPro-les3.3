//Написать код, который сериализирует и десериализирует в/из файла
// все значения полей класса, которые отмечены аннотацией @Save.

package ua.kiev.prog;

import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) throws Exception {
        String nameNew = "Viktor";
        int ageNew = 45;
        String genderNew = "Man";
        String professionNew = "Programmer";

        SomeClass someClass = new SomeClass();

        Class<?> reflection = SomeClass.class;
        Field[] fields = reflection.getDeclaredFields();
        for (Field field : fields) {

            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(Save.class)) {

                    if (field.getName().equals("name")) {
                        someClass.setName(nameNew);
                    } else if (field.getName().equals("age")) {
                        someClass.setAge(ageNew);
                    } else if (field.getName().equals("gender")) {
                        someClass.setGender(genderNew);
                    } else if (field.getName().equals("profession")) {
                        someClass.setProfession(professionNew);
                    }
                }
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream("man");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(someClass);
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("man");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        SomeClass someClassNew = (SomeClass) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println(someClassNew.toString());
    }
}


class SomeClass implements Serializable {
    @Save
    String name;
    @Save
    int age;
    String gender;
    String profession;

    public SomeClass() {
    }

    public SomeClass(String name, int age, String gender, String profession) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "SomeClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@interface Save {

}