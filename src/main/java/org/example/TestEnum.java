package org.example;

public class TestEnum {
    public static void main(String[] args) {
        for(int i=0; i<AccountType.values().length; i++){
            String name = AccountType.values()[i].toString();
            System.out.println("name " + name);
            System.out.println(AccountType.getAccountType(name));
        }
    }
}
enum AccountType{
    CHECKING, SAVINGS, FD;

    public String toString(){ return this.name(); }

    public static String getAccountType(String type){
        try{
            AccountType at = AccountType.valueOf(type);
            return (at.ordinal()%2 == 0) ? at.name().toLowerCase():at.name().toUpperCase();
        }catch(Exception e){
            return "NOT_SUPPORTED";
        }
    }


}

//enum Pets implements java.io.Serializable
//{
//    DOG("D"), CAT("C"), FISH("F");
//    String name;
//    Pets(String s) { }
//    public String getData(){ return name; }
//}
//public enum Pets
//{
//    DOG(1, "D"),
//    CAT(2, "C")
//            {
//                public String getData(){ return type+name; }
//            },
//    FISH(3, "F");
//    int type;
//    String name;
//    Pets(int t, String s) { this.name = s; this.type = t;}
//    public String getData(){ return name+type; }
//}