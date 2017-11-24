package io.woolford;

import com.univocity.parsers.csv.CsvRoutines;
import io.woolford.database.entity.InfusionsoftContactRecord;
import io.woolford.database.mapper.DbMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;

@Component
public class NameMatcher {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DbMapper dbMapper;

    @PostConstruct
    private void mapNames() throws FileNotFoundException, UnsupportedEncodingException {

//        for (LinkedinPersonRecord linkedinPersonRecord : dbMapper.getLinkedinPersonRecords()){
//
//            logger.info(linkedinPersonRecord.toString());
//
//        }


        File file = new File("/Users/awoolford/infusionsoft_contacts_utf8_fixed.csv");
        InputStream inputStream = new FileInputStream(file);
        List<InfusionsoftContactRecord> infusionsoftContactRecordList = new CsvRoutines().parseAll(InfusionsoftContactRecord.class, inputStream);

        for (InfusionsoftContactRecord infusionsoftContactRecord : infusionsoftContactRecordList){
            dbMapper.insertInfusionsoftContact(infusionsoftContactRecord);

        }



    }


}
