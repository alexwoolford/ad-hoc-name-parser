package io.woolford.database.mapper;

import io.woolford.database.entity.InfusionsoftContactRecord;
import io.woolford.database.entity.LinkedinPersonRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface DbMapper {

    @Select("SELECT                  " +
            "   name,                " +
            "   title,               " +
            "   url,                 " +
            "   page                 " +
            "FROM linkedin_people    " +
            "WHERE firstname IS NULL ") // temporary hack to avoid duplicate API calls
    List<LinkedinPersonRecord> getLinkedinPersonNoFirstnameRecords();

    @Select("SELECT               " +
            "   name,             " +
            "   title,            " +
            "   url,              " +
            "   page,             " +
            "   firstname,        " +
            "   surname,          " +
            "   gender,           " +
            "   genderConfidence  " +
            "FROM linkedin_people ")
    List<LinkedinPersonRecord> getLinkedinPersonRecords();

    @Update("UPDATE linkedin_people                   " +
            "SET firstname=#{firstname},              " +
            "    surname=#{surname},                  " +
            "    gender=#{gender},                    " +
            "    genderConfidence=#{genderConfidence} " +
            "WHERE name=#{name}")
    void updateFirstnameSurnameGender(LinkedinPersonRecord linkedinPersonRecord);

    @Insert("INSERT INTO infusionsoft_contacts                                 " +
            " (id, name, firstname, surname, company, email)                   " +
            "VALUES                                                            " +
            " (#{id}, #{name}, #{firstname}, #{surname}, #{company}, #{email}) ")
    void insertInfusionsoftContact(InfusionsoftContactRecord infusionsoftContactRecord);

}