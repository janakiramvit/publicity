package in.mw.attendance;

/**
 * Created by jpulipati on 3/25/17.
 */
public class attendance {

    private String Date;
    private String Task;
    private String InTime;
    private String OutTime;
    private String Duration;

    public attendance(){

    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public attendance(String date, String task, String inTime, String outTime) {
        Date = date;
        Task = task;
        InTime = inTime;
        OutTime = outTime;
    }

    public attendance(String date, String task, String inTime, String outTime, String duration) {
        Date = date;
        Task = task;
        InTime = inTime;
        OutTime = outTime;
        Duration = duration;

    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTask() {
        return Task;
    }

    public void setTask(String task) {
        Task = task;
    }

    public String getInTime() {
        return InTime;
    }

    public void setInTime(String inTime) {
        InTime = inTime;
    }

    public String getOutTime() {
        return OutTime;
    }

    public void setOutTime(String outTime) {
        OutTime = outTime;
    }
}
