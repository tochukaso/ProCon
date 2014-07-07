package codeIQ;

public class HumanEnum {

    static final int age = 20;
    
    public static void main(String[] args) {
        
        int a = 5;
        int b = 10;
        
        b -=  a;
        a += a; 
        
        System.out.println(a);
        System.out.println(b);

        Human human = null;
        if (age < 20) {
            human = Human.CHAILD;
        } else if (age < 60) {
            human = Human.ADULT;
        } else {
            human = Human.SILVER;
        }
        System.out.println(human.Name() + "の料金は" + human.Price() +"です");
    }
}

enum Human {
    ADULT(100, "おとな"),
    CHAILD(50, "こども"),
    SILVER(20, "シルバー");
    
    private final int price;
    private final String name;

    private Human(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int Price() {
        return price;
    }

    public String Name() {
        return name;
    }
}


