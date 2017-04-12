package in.mw.Feedback;

import in.mw.Budget.expenseSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jpulipati on 3/25/17.
 */
@Repository
public class feedbackRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    public void addFeedback(feedback feedback){
        System.out.println("Java Controller: Feedback: Inside Post method");
        DefaultTransactionDefinition dtd = new DefaultTransactionDefinition();
        TransactionStatus status = platformTransactionManager.getTransaction(dtd);

        String date = feedback.getDate();
        String customerName = feedback.getCustomerName();
        String customerID = feedback.getCustomerID();
        String description = feedback.getFeedback();
        String address = feedback.getAddress();
        String phoneNumber = feedback.getPhoneNumber();
        String shopName = feedback.getShopName();

        jdbcTemplate.update("insert into {dbname}.feedback(date_value,customerName,customerID,description,address,phoneNumber,shopName) values(?,?,?,?,?,?,?)",
                date,customerName,customerID,description,address,phoneNumber,shopName);
        platformTransactionManager.commit(status);
        System.out.println("Java Controller: Feedback: Post Success");
    }

    public List<feedback> getAllFeedback(){
        List<feedback> listFeedback = jdbcTemplate.query("SELECT * FROM {dbname}.feedback where extract(month from date_value) = 3", new RowMapper<feedback>() {
            @Override
            public feedback mapRow(ResultSet resultSet, int i) throws SQLException {
                feedback feedback = new feedback();
                feedback.setDate(resultSet.getString("date_value"));
                feedback.setFeedback(resultSet.getString("description"));
                feedback.setCustomerName(resultSet.getString("customerName"));
                feedback.setPhoneNumber(resultSet.getString("phoneNumber"));
                feedback.setShopName(resultSet.getString("shopName"));
                feedback.setAddress(resultSet.getString("address"));

                return feedback;
            }
        });

        for(feedback feedback: listFeedback) {
            System.out.println("Java Controller: Feedback: Get JSON values : " + feedback.getDate() +" "+ feedback.getFeedback() + " " + feedback.getCustomerName());
        }
        return listFeedback;
    }

    public Integer getTotalFeedback(){
        String sql = "select sec_to_time(sum(time_to_sec(amount))) from {dbname}.feedback";
        Integer total_hours = jdbcTemplate.queryForObject(sql,Integer.class);
        return total_hours;
    }
}
