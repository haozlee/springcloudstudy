package me.leehao.multidbsource.service;

import me.leehao.multidbsource.common.DatabaseContextHolder;
import me.leehao.multidbsource.common.DatabaseType;
import me.leehao.multidbsource.mapper.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonMapper personMapper;

    public void setDataSourceByName(String db){
        if (db.equals(DatabaseType.TOTAL.getValue())){
            DatabaseContextHolder.setDatabaseType(DatabaseType.TOTAL);
        }
        if (db.equals(DatabaseType.PART1.getValue())){
            DatabaseContextHolder.setDatabaseType(DatabaseType.PART1);
        }
    }

    public String getPerson(String db, Integer id) {
        setDataSourceByName(db);

        return personMapper.getPerson(id);
    }

}
