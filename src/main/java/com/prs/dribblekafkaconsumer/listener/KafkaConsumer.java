package com.prs.dribblekafkaconsumer.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.prs.dribblekafkaconsumer.dao.ConsumerDao;
import com.prs.dribblekafkaconsumer.helper.ConsumerHelper;
import com.prs.dribblekafkaconsumer.vo.CompanyVO;


@Service
public class KafkaConsumer {

    @Autowired
    private ConsumerDao consumerDao;

    @KafkaListener(topics = "kafka_dribble_topic", groupId = "group_java",
            containerFactory = "kafkaListenerFactory")
    public void consumeJson(CompanyVO company) {
        consumerDao.save(ConsumerHelper.transformVOToEntity(company));
    }
}
