package com.example.Repo;

import java.util.List;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.Models.AccountDTO;

@Repository
public interface AccountRepository extends CrudRepository<AccountDTO, Integer> {
    
    AccountDTO findById(int id);
}