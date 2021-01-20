package me.leehao.multidbsource.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface PersonMapper {
    String getPerson(Integer id);
}