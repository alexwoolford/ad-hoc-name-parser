package io.woolford.database.service;


import io.woolford.database.entity.InfusionsoftContactRecord;
import io.woolford.database.entity.LinkedinPersonRecord;
import io.woolford.database.mapper.DbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DbService {

    @Autowired
    private DbMapper dbMapper;

    public List<LinkedinPersonRecord> getLinkedinPersonNoFirstnameRecords(){
        return dbMapper.getLinkedinPersonNoFirstnameRecords();
    }

    public List<LinkedinPersonRecord> getLinkedinPersonRecords(){
        return dbMapper.getLinkedinPersonRecords();
    }

    public void updateFirstnameSurnameGender(LinkedinPersonRecord linkedinPersonRecord){
        dbMapper.updateFirstnameSurnameGender(linkedinPersonRecord);
    }

    public void insertInfusionsoftContact(InfusionsoftContactRecord infusionsoftContactRecord){
        dbMapper.insertInfusionsoftContact(infusionsoftContactRecord);
    }


}