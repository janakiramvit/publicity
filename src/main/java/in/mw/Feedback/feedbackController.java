package in.mw.Feedback;

import org.apache.commons.codec.DecoderException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by jpulipati on 3/25/17.
 */
@RestController
public class feedbackController {

    @Autowired
    private feedbackRepository feedbackRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/post/feedback", consumes = "application/x-www-form-urlencoded")
    public void addExpense(@RequestBody String feedback_encoded) throws UnsupportedEncodingException, DecoderException {
        System.out.println("Java Controller: Feedback: Inside Post - " + feedback_encoded);
        feedback feedback = new feedback();
        // change this. substring until equal to
        String feedback_decoded = URLDecoder.decode(feedback_encoded, "UTF-8").substring(9);
        System.out.println("Java Controller: Feedback: JSON Object to POST "+ feedback_decoded);

        JSONObject jsonObject = new JSONObject(feedback_decoded);
        String date =jsonObject.get("date").toString();
        String customerID =jsonObject.get("customerID").toString();
        String customerName =jsonObject.get("customerName").toString();
        String description =jsonObject.get("description").toString();
        String shopName = jsonObject.get("shopName").toString();
        String address = jsonObject.get("address").toString();
        String phoneNumber = jsonObject.get("phoneNumber").toString();

        feedback.setDate(date);
        feedback.setCustomerID(customerID);
        feedback.setCustomerName(customerName);
        feedback.setFeedback(description);
        feedback.setShopName(shopName);
        feedback.setAddress(address);
        feedback.setPhoneNumber(phoneNumber);

        feedbackRepository.addFeedback(feedback);

        System.out.println("Java Controller: Feedback: Post success ");
    }

    @RequestMapping("/get/feedback")
    public List<feedback> getAllFeedback(){
        System.out.println("Java Controller: Feedback: Inside Get");
        return feedbackRepository.getAllFeedback();
    }

    @RequestMapping("/get/total_feedback")
    public Integer getTotalHours(){
        return feedbackRepository.getTotalFeedback();
    }

}
