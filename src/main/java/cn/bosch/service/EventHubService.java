package cn.bosch.service;

import cn.bosch.model.viewobject.StasticRespVO;
import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventDataBatch;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Service
public class EventHubService {

    private static final String CONNECTION_ATRING = "Endpoint=sb://ehns-pscn-pm-int-dev.servicebus.chinacloudapi.cn/;SharedAccessKeyName=wyTest;SharedAccessKey=o53YBczMkSrh2pbnbnhDyxNTrZ3K/nMOFWWaSg4yKe8=;EntityPath=eh-pscn-pm-int-dev";

    private static final String EVENTHUB_NAME = "eh-pscn-pm-int-dev";

    public static Integer num = 0;

    @Autowired
    ObjectMapper objectMapper;

    public void publishMessage() {
        num = 1;
        StringBuilder stringBuilder = new StringBuilder();
        while (num.equals(1)) {
            try {
                Reader csvFile = new FileReader("src/main/resources/csv/012008_2021-08-27_0x0022.csv");
                Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(csvFile);
                for (CSVRecord record : records) {
                    for (String item : record) {
                        if (stringBuilder.length() == 0) {
                            stringBuilder.append(item);
                        } else {
                            stringBuilder.append(";");
                            stringBuilder.append(item);
                        }
                    }
                    pubshToEventHub(stringBuilder.toString());
                    stringBuilder.setLength(0);
                    Thread.sleep(1000);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void pubshToEventHub(String content) {
        EventHubProducerClient producer = new EventHubClientBuilder()
                .connectionString(CONNECTION_ATRING, EVENTHUB_NAME)
                .buildProducerClient();
        EventDataBatch eventDataBatch = producer.createBatch();
        eventDataBatch.tryAdd(new EventData(content));
        producer.send(eventDataBatch);
    }

    public void stopMessage() {
        num = 0;
    }

    public StasticRespVO getStaticMessage() {
        //获取函奇event hub发送的消息
        // 返回统计信息
        return null;
    }
}
