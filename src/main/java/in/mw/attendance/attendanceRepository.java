package in.mw.attendance;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
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

    public void addAttendance(attendance attendance){
        System.out.println("Inside Post method attendance");
        DefaultTransactionDefinition dtd = new DefaultTransactionDefinition();
        TransactionStatus status = platformTransactionManager.getTransaction(dtd);

        String date = attendance.getDate();
        String task = attendance.getTask();
        String inTime = attendance.getInTime();
        String outTime = attendance.getOutTime();

        jdbcTemplate.update("insert into {dbname}.attendance(date_value,task,inTime,outTime) values(?,?,?,?)",date,task,inTime,outTime);
        platformTransactionManager.commit(status);
        System.out.println("Attendance Post Success");
    }

    public List<attendance> getAllAttendance(){
        List<attendance> listAttendance = jdbcTemplate.query("SELECT * FROM {dbname}.attendance where extract(month from date_value) = 3", new RowMapper<attendance>() {
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
        String sql = "select cast(sec_to_time(sum(time_to_sec(duration))) as char(50)) from {dbname}.attendance";
        String total_hours = jdbcTemplate.queryForObject(sql,String.class);
        List<String> list = new ArrayList<>();
        list.add(total_hours);
        //JSONObject jsonObject = new JSONObject();
        //jsonObject.put("duration",total_hours);
        return list;
    }
}
