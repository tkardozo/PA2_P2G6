package common;

/**
 * Helper class to change the behaviour of all the project, in a simple file
 *
 * @author P2G6
 */
public class Properties {

    /**
     * KAFKA server endpoints
     */
    public static final String SERVERS = "localhost:9092";

    /**
     * Source file path with all messages to be sent by the CollectEntity
     *
     * @see collectEntity.CollectEntity
     */
    public static final String COLLECT_File = "src/data/source.txt";

    /**
     * Mongodb database name for the ReportEntity to use
     *
     * @see reportEntity.ReportEntity
     */
    public static final String REPORT_Database = "PA2_P2G6";

    /**
     * Mongodb collection name for the ReportEntity to use
     *
     * @see reportEntity.ReportEntity
     */
    public static final String REPORT_Collection = "logs";

    /**
     * Log file path for the BatchEntity to log
     *
     * @see batchEntity.BatchEntity
     */
    public static final String BATCH_File = "src/data/batch.txt";

    /**
     * Message type - HEARTBEAT
     */
    public static final String MSG_HEARTBEAT = "00";

    /**
     * Message type - SPEED
     */
    public static final String MSG_SPEED = "01";

    /**
     * Message type - STATUS
     */
    public static final String MSG_STATUS = "02";

    /**
     * Topic name between the CollectEntity and the DigestionEntity
     *
     * @see collectEntity.CollectEntity
     * @see digestionEntity.DigestionEntity
     */
    public static final String TOPIC_ToDigest = "ToDigestion";

    /**
     * Topic name between the DigestionEntity and the BatchEntity and
     * ReportEntity
     *
     * @see digestionEntity.DigestionEntity
     * @see batchEntity.BatchEntity
     * @see reportEntity.ReportEntity
     */
    public static final String TOPIC_FromDigestion = "FromDigestion_1";

    /**
     * Topic name between the DigestionEntity and the AlarmEntity
     *
     * @see digestionEntity.DigestionEntity
     * @see alarmEntity.AlarmEntity
     */
    public static final String TOPIC_FromDigest_ALARM = "FromDigestion_2"; // specific to alarm

    /**
     * Group ID for the DigestionEntity KAFKA consumers
     *
     * @see digestionEntity.DigestionEntity
     */
    public static final String GROUP_ID_Digestion = "digestion";

    /**
     * Group ID for the BatchEntity KAFKA consumers
     *
     * @see batchEntity.BatchEntity
     */
    public static final String GROUP_ID_Batch = "batch";

    /**
     * Group ID for the ReportEntity KAFKA consumers
     *
     * @see reportEntity.ReportEntity
     */
    public static final String GROUP_ID_Report = "report";

    /**
     * Group ID for the AlarmEntity KAFKA consumers
     *
     * @see alarmEntity.AlarmEntity
     */
    public static final String GROUP_ID_Alarm = "alarm";

}
