package in.mw.attendance;

import org.apache.commons.codec.DecoderException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jpulipati on 3/25/17.
 */
@RestController
public class attendanceController {

    @Autowired
    private attendanceRepository attendanceRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/post/attendance", consumes = "application/x-www-form-urlencoded")
    public void addAttendance(@RequestBody String attendance_encoded) throws UnsupportedEncodingException, DecoderException {
        System.out.println("Controller: Inside Post of attendance - " + attendance_encoded);

        attendance attendance = new attendance();
        String attendance_decoded = URLDecoder.decode(attendance_encoded, "UTF-8").substring(11);

        JSONObject jsonObject = new JSONObject(attendance_decoded);
        String date =jsonObject.get("date").toString();
        String task =jsonObject.get("task").toString();
        String inTime =jsonObject.get("inTime").toString();
        String outTime =jsonObject.get("outTime").toString();

        System.out.println("Controller: Set attendance class - " + attendance_decoded);

        attendance.setDate(date);
        attendance.setTask(task);
        attendance.setInTime(inTime);
        attendance.setOutTime(outTime);

        attendanceRepository.addAttendance(attendance);
    }

    @RequestMapping("/get/attendance")
    public List<attendance> getAllAttendance(){
        System.out.println("Controller: Inside Get of attendance");
        return attendanceRepository.getAllAttendance();
    }

    @RequestMapping("/get/totalHours")
    public List<String> getTotalHours(){
        List<String> ret = attendanceRepository.getTotalHours();
        System.out.println(ret);
        return ret;
    }

}
