package io.woolford;

import com.optimaize.anythingworks.common.host.Host;
import com.optimaize.command4j.CommandExecutor;
import com.optimaize.command4j.Mode;
import io.woolford.database.entity.LinkedinPersonRecord;
import io.woolford.database.mapper.DbMapper;
import org.nameapi.client.lib.NameApiModeFactory;
import org.nameapi.client.lib.NameApiPortUrlFactory;
import org.nameapi.client.lib.NameApiRemoteExecutors;
import org.nameapi.client.services.parser.personnameparser.PersonNameParserCommand;
import org.nameapi.ontology5.input.context.Context;
import org.nameapi.ontology5.input.context.ContextBuilder;
import org.nameapi.ontology5.input.context.Priority;
import org.nameapi.ontology5.input.entities.person.InputPerson;
import org.nameapi.ontology5.input.entities.person.NaturalInputPersonBuilder;
import org.nameapi.ontology5.input.entities.person.name.InputPersonName;
import org.nameapi.ontology5.input.entities.person.name.builder.NameBuilders;
import org.nameapi.ontology5.services.parser.personnameparser.PersonNameParserResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NameCleaner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${nameapi.key}")
    String nameapiKey;

    @Autowired
    DbMapper dbMapper;

    @PostConstruct
    private void cleanNames() throws Exception {

        for (LinkedinPersonRecord linkedinPersonRecord : dbMapper.getLinkedinPersonRecords()){
            updateName(linkedinPersonRecord.getName());
        }

    }


    private void updateName(String name) throws Exception {

        try {

            Context context = new ContextBuilder()
                    .priority(Priority.REALTIME)
                    .build();

            CommandExecutor executor = NameApiRemoteExecutors.get();

            Mode mode = NameApiModeFactory.withContext(
                    nameapiKey,
                    context,
                    new Host("api.nameapi.org", 80), NameApiPortUrlFactory.versionLatestStable()
            );

            InputPersonName inputPersonName = NameBuilders.western().fullname(name).build();
            InputPerson inputPerson = new NaturalInputPersonBuilder().name(inputPersonName).build();

            PersonNameParserCommand command = new PersonNameParserCommand();
            PersonNameParserResult result = executor.execute(command, mode, inputPerson).get();

            String firstname = result.getBestMatch().getParsedPerson().getAddressingGivenName();
            String surname = result.getBestMatch().getParsedPerson().getAddressingSurname();
            String gender = result.getBestMatch().getParsedPerson().getGender().getGender().toString();
            double genderConfidence = result.getBestMatch().getParsedPerson().getGender().getConfidence();

            LinkedinPersonRecord linkedinPersonRecord = new LinkedinPersonRecord();
            linkedinPersonRecord.setName(name);
            linkedinPersonRecord.setFirstname(firstname);
            linkedinPersonRecord.setSurname(surname);
            linkedinPersonRecord.setGender(gender);
            linkedinPersonRecord.setGenderConfidence(genderConfidence);

            dbMapper.updateFirstnameSurnameGender(linkedinPersonRecord);

        } catch (Exception e){

            logger.error("Error parsing name: " + name + "; message: " + e.getMessage());

        }

    }

}
