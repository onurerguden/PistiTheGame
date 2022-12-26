public class Card {
    private int No;
    private int Value;
    private String Type;
    private String Name;



    Card(int No, int Value, String Type, String Name){
        this.No=No;
        this.Value=Value;
        this.Type=Type;
        this.Name=Name;
    }


    public int getNo() {
        return No;
    }

    public void setNo(int no) {
        No = no;
    }

    public int getValue() {
        return Value;
    }

    public String getType() {
        return Type;
    }

    public String getName() {
        return Name;
    }

    public String Anc(){
        return "   "+Type+" "+Name +"\n Value: "+ Value+ "\n  No: "+(No+1) +"\n --------";
    }



}
