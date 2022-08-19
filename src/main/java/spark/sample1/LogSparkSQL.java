package spark.sample1;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

@Slf4j
public class LogSparkSQL {
    public static void main(String[] args){
        SparkConf conf = new SparkConf().setAppName("SparkSQL").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

//        HiveConf hiveConf = new HiveConf(sc);
        SQLContext sqlContext = new SQLContext(sc);

        JavaRDD<String> lines = sc.textFile("src/main/resources/spark/access.log");

        //将字符串转换成LogInfoRDD
        JavaRDD<LogInfo> logInfo = lines.map( line -> {
            String[] str = line.split(" ");
            long timeStamp = Long.valueOf(str[0]);
            String phone = str[1];
            long down = Long.valueOf(str[2]);
            long up = Long.valueOf(str[3]);
            LogInfo log = new LogInfo(timeStamp, phone, down, up);
            return log;
        });

        //将RDD转换成DataSet数据集
        Dataset<Row>  df = sqlContext.createDataFrame(logInfo, LogInfo.class);
        //log.info("在dataset数据集上进行查询操作");
        df.select("phoneNo", "down").where("up > 0").show();

        //log.info("将df注册成临时视图，这样可以用SQL表达式进行查询操作");
        df.createOrReplaceTempView("log");
        Dataset<Row> seleRs = sqlContext.sql("select * from log where up > 50000 and down < 10000");

        seleRs.toJavaRDD().foreach(row -> System.out.println(row.get(1)));
        


    }
}