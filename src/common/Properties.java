package common;

public class Properties {
    public static final String SERVERS = "localhost:9092";

    public static final String COLLECT_File = "src/data/source.txt";
    public static final String REPORT_Database = "PA2_P2G6";
    public static final String REPORT_Collection = "logs";
    public static final String BATCH_File = "src/data/batchLogs.txt";

    public static final String MSG_HEARTBEAT = "00";
    public static final String MSG_SPEED = "01";
    public static final String MSG_STATUS = "02";

    public static final String TOPIC_ToDigest = "ToDigestion";
    public static final String TOPIC_FromDigest = "FromDigestion_1";
    public static final String TOPIC_FromDigest_ALARM = "FromDigestion_2"; // specific to alarm

    public static final String GROUP_ID_Digestion = "digestion";
    public static final String GROUP_ID_Batch = "batch";
    public static final String GROUP_ID_Report = "report";
    public static final String GROUP_ID_Alarm = "alarm";

}
