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
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jpulipati on 3/25/17.
 */
@RestController
public class attendanceController {

    @Autowired
    private attendanceRepository attendanceRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/post/attendance", consumes = "application/x-www-form-urlencoded")
    public void addAttendance(@RequestBody String attendance_encoded) throws UnsupportedEncodingException, DecoderException, SQLException {
        System.out.println("java Controller: Inside Post of attendance - " + attendance_encoded);

        attendance attendance = new attendance();
        String attendance_decoded = URLDecoder.decode(attendance_encoded, "UTF-8").substring(11);

        JSONObject jsonObject = new JSONObject(attendance_decoded);
        String date =jsonObject.get("date").toString();
        String task =jsonObject.get("task").toString();
        String inTime =jsonObject.get("inTime").toString();
        String outTime =jsonObject.get("outTime").toString();

        System.out.println("java Controller: Set attendance class - " + attendance_decoded);

        attendance.setDate(date);
        attendance.setTask(task);
        attendance.setInTime(inTime);
        attendance.setOutTime(outTime);

        attendanceRepository.addAttendance(attendance);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/post/publicity", consumes = "application/x-www-form-urlencoded")
    public void addPublicity(@RequestBody String attendance_encoded) throws UnsupportedEncodingException, DecoderException, SQLException {
        System.out.println("java Controller: Inside Post of attendance - " + attendance_encoded);

        publicity publicity = new publicity();
        String attendance_decoded = URLDecoder.decode(attendance_encoded, "UTF-8").substring(11);

        JSONObject jsonObject = new JSONObject(attendance_decoded);
        String upload1 = jsonObject.get("upload1").toString();
        String upload2 = jsonObject.get("upload2").toString();
        String itemprice =jsonObject.get("itemprice").toString();
        String itemname =jsonObject.get("itemname").toString();
        String oprice =jsonObject.get("oprice").toString();

        System.out.println("java Controller: Set publicity class - " + attendance_decoded);

        publicity.setUpload1(upload1);
        publicity.setUpload2(upload2);
        publicity.setItemprice(itemprice);
        publicity.setItemname(itemname);
        publicity.setOprice(oprice);

        attendanceRepository.addPublicity(publicity);
    }

    @RequestMapping("/get/attendance")
    public List<attendance> getAllAttendance(){
        System.out.println("Controller: Inside Get of attendance");
        return attendanceRepository.getAllAttendance();
    }

    @RequestMapping("/get/totalHours")
    public List<String> getTotalHours(){
        List<String> ret = attendanceRepository.getTotalHours();
        System.out.println("totalhours - java controller " + ret);
        return ret;
    }

}
