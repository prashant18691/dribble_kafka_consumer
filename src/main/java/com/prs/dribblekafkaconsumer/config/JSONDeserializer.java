package com.prs.dribblekafkaconsumer.config;

import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prs.dribblekafkaconsumer.vo.CompanyVO;


public class JSONDeserializer implements Deserializer<CompanyVO> {
    private static final ObjectMapper mapper = new ObjectMapper();
    @Override
    public void close() {
        // TODO Auto-generated method stub
    }
    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {
        // TODO Auto-generated method stub
    }
    @Override
    public CompanyVO deserialize(String arg0, byte[] arg1) {
        CompanyVO companyVO = null;
        try {
            companyVO = mapper.readValue(arg1, CompanyVO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companyVO;
    }
}
