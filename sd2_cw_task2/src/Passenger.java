public class Passenger {
    String mainName;
    int pExpense;

    //Declaration of the constructor
    public Passenger() {
        mainName = "e";
        pExpense = 0;
    }


    //Set method
    public void setName(String aName){
        mainName = aName;
    }

    //Get method
    public String getName() {
        return mainName;
    }
    public void setSurName(String aName){
        mainName = aName;
    }
    public String getSurName(){
        return mainName;
    }
    public void setFirstName(String aName) {mainName = aName;}
    public String getFirstName(){
        return mainName;
    }
    public void setExpense(int aExpense){
        pExpense = aExpense;
    }
    public int getExpense(){return pExpense;}
}
