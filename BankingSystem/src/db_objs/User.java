package db_objs;
//
import java.math.BigDecimal;
import java.math.RoundingMode;
//
/*
    User entity which is used to store user information (i.e. id, username, password, and current balance)
 */
public class User {
    private final int id;
    private final String username, password;
    private BigDecimal currentBalance;
    private int cardnumber;
//
    public User(int id, String username, String password, BigDecimal currentBalance, int cardnumber){
        this.id = id;
        this.username = username;
        this.password = password;
        this.currentBalance = currentBalance;
        this.cardnumber = cardnumber;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }
    public int getCardnumber(){
        return cardnumber;
    }

    public void setCurrentBalance(BigDecimal newBalance){
        // store new value to the 2nd decimal place
        currentBalance = newBalance.setScale(2, RoundingMode.FLOOR);
    }
}

