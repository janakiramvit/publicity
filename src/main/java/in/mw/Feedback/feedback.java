package in.mw.Feedback;

/**
 * Created by jpulipati on 4/1/17.
 */
public class feedback {

    private String customerID;
    private String customerName;
    private String shopName;
    private String address;
    private String phoneNumber;
    private String description;
    private String date;
    private String feedbackID;

    public feedback(){}


    public feedback(String customerID, String customerName, String description, String date, String feedbackID, String shopName, String address, String phoneNumber) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.description = description;
        this.date = date;
        this.feedbackID = feedbackID;
        this.shopName = shopName;
        this.address = address;
        this.phoneNumber = phoneNumber;
     }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFeedback() {
        return description;
    }

    public void setFeedback(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(String feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
