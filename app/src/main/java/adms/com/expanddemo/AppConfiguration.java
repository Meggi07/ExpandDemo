package adms.com.expanddemo;

/**
 * Created by Harsh on 04-Aug-16.
 */
public class AppConfiguration {

    public enum Domain {
        LIVE, LOCAL
    }

    static Domain domain = Domain.LIVE;//only Change this for changing environment

    public static String getUrl(String methodName) {
        String url = "";
        switch (domain) {
            case LIVE:
                url = DOMAIN_LIVE + methodName;
                break;
            case LOCAL:
                url = DOMAIN_LOCAL + methodName;
                break;
            default:
                break;
        }
        return url;
    }


    //Local
    public static String DOMAIN_LOCAL = "http://192.168.1.10:8085/MobileApp_Service.asmx/";
    public static String DOMAIN_LIVE = "http://103.8.216.132/MobileApp_Service.asmx/";

    public static String StudentLogin = "StudentLogin";
    public static String GetUserProfile = "GetUserProfile";
    public static String ChangePassword = "ChangePassword";
    public static String GetHomework = "GetHomework";
    public static String GetAttendence = "GetAttendence";
    public static String GetTimetable = "GetTimetable";
    public static String GetTerm = "GetTerm";
    public static String GetImprest = "GetImprest";
    public static String GetCanteenMenu = "GetCanteenMenu";
    public static String GetTestDetail = "GetTestDetail";
    public static String AddDeviceDetail = "AddDeviceDetail";
    public static String GetStudentResult = "GetStudentResult";
    public static String GetReportcard = "GetReportcard";
    public static String GetFeesStatus = "GetFeesStatus";
    public static String GetPaymentLedger = "PaymentLedger";
    public static String GetPrincipalMessage = "GetPrincipalMessage";
    public static String PTMTeacherStudentGetDetail = "PTMTeacherStudentGetDetail";
    public static String PTMTeacherStudentInsertDetail = "PTMTeacherStudentInsertDetail";
    public static String PTMDeleteMeeting = "PTMDeleteMeeting";
    public static String PTMStudentWiseTeacher = "PTMStudentWiseTeacher";
    public static String GetCircularDetail = "GetCircularDetail";
    public static String DeviceVersion = "DeviceVersion";
}
