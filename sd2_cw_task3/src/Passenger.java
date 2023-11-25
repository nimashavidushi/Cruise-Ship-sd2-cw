public class Passenger {
    String mainName;
    int pExpense;

    public Passenger(){
        mainName = "e";
        pExpense = 0;
    }

    public void setName(String aName){mainName = aName;}
    public String getName(){return mainName;}

    public void setSurName(String aName){mainName = aName;}
    public String getSurName(){return mainName;}

    public void setFirstName(String aName){mainName = aName;}
    public String getFirstName(){return mainName;}

    public void setExpense(int aExpense){pExpense = aExpense;}
    public int getExpense(){return pExpense;}

    public void setQueName(String aName){mainName = aName;}
    public String getQueName(){return mainName;}

    public void setQueSurName(String aName){mainName = aName;}
    public String getQueSurName(){return mainName;}

}
