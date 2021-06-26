package in.mw.attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpulipati on 3/25/17.
 */
@Repository
public class attendanceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    public void addAttendance(attendance attendance) throws SQLException {
        System.out.println("Inside Post method attendance" + attendance.getDate());
       // DefaultTransactionDefinition dtd = new DefaultTransactionDefinition();
        //TransactionStatus status = platformTransactionManager.getTransaction(dtd);
        System.out.println("MySQL JDBC Connection Testing ~");

        String SQL_SELECT = "Select count(*) from attendance";

//        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/martech", "root", "1234");
//             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//        System.out.println(resultSet.getString("task"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/martech", "root", "1234");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
            System.out.println("connection suc" );
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("connection suc1" );
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
            System.out.println("connection suc2" );
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("connection suc3" );
        System.out.println(jdbcTemplate.queryForObject(SQL_SELECT, Integer.class));

        System.out.println("Attendance post method- DB connection set");
        String date = attendance.getDate();
        String task = attendance.getTask();
        String inTime = attendance.getInTime();
        String outTime = attendance.getOutTime();

        jdbcTemplate.update("insert into martech.attendance(date_value,task,inTime,outTime) values(?,?,?,?)",date,task,inTime,outTime);
       // platformTransactionManager.commit(status);
        System.out.println("Attendance Post Success");
    }

    //duplicate for publicity
    public void addPublicity(publicity publicity) throws SQLException {
        System.out.println("Inside Post method publicity" + publicity.getItemname());
        System.out.println("MySQL JDBC Connection Testing ~");

        String SQL_SELECT = "Select count(*) from publisity";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/martech", "root", "1234");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
            System.out.println("connection suc" );
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("connection suc1 with resultsets" );
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
            System.out.println("connection suc2" );
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\nconnection suc3, PRINTING count result" );
        System.out.println(jdbcTemplate.queryForObject(SQL_SELECT, Integer.class));

        System.out.println("Publicity post method- DB connection set");
        String upload1 = publicity.getUpload1();
        String upload2 = publicity.getUpload2();
        String itemn = publicity.getItemname();
        String itemp = publicity.getItemprice();
        String op = publicity.getOprice();

        jdbcTemplate.update("insert into martech.publisity(sideview,topview,price,pr_name,outsideprice) values(?,?,?,?,?)",upload1,upload2,itemp,itemn,op);

        // platformTransactionManager.commit(status);
        System.out.println("Attendance Post Success");
    }

    public List<attendance> getAllAttendance(){
        System.out.println("Inside List method attendance");
        List<attendance> listAttendance = jdbcTemplate.query("SELECT * FROM martech.attendance where extract(month from date_value) = 3", new RowMapper<attendance>() {
            @Override
            public attendance mapRow(ResultSet resultSet, int i) throws SQLException {
                attendance attendance = new attendance();
                attendance.setDate(resultSet.getString("date_value"));
                attendance.setTask(resultSet.getString("task"));
                attendance.setInTime(resultSet.getString("inTime"));
                attendance.setOutTime(resultSet.getString("outTime"));
                attendance.setDuration(resultSet.getString("duration"));
                return attendance;
            }
        });

        for(attendance attendance: listAttendance) {
            System.out.println("Attendance JSON values : " + attendance.getDate() +" "+ attendance.getTask() + " " + attendance.getDuration());
        }
        return listAttendance;
    }

    public List<String> getTotalHours(){
        System.out.println("Inside get total hours attendance");
        String sql = "select cast(sec_to_time(sum(time_to_sec(duration))) as char(50)) from martech.attendance";
        String total_hours = jdbcTemplate.queryForObject(sql,String.class);
        List<String> list = new ArrayList<>();
        list.add(total_hours);
        //JSONObject jsonObject = new JSONObject();
        //jsonObject.put("duration",total_hours);
        return list;
    }
}
