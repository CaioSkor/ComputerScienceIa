
package modules;

/**
 *
 * @author caio
 */
public class Investment {
    
    // Attributes
    private String code;
    private String price;
    private String amount;
    private String date;
    private String reason;
    
    // Constructors
    public Investment(){
    }
    
    public Investment(String c, String p, String a, String d, String r){
        this.code = c;
        this.price = p;
        this.amount = a;
        this.date = d;
        this.reason = r;
    }
    
    public String getCode(){
        return code;
    }
    
    public void setCode(String c){
        this.code = c;
    }
    
    public String getPrice(){
        return price;
    }
    
    public void setPrice(String p){
        this.price = p;
    }
    
    public String getAmount(){
        return amount;
    }
    
    public void setAmount(String a){
        this.amount = a;
    }
    
    public String getDate(){
        return date;
    }
    
    public void setDate(String d){
        this.date = d;
    }
    
    public String getReason(){
        return reason;
    }
    
    public void setReason(String r){
        this.reason = r;
    }
    
    
}
