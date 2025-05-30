package com.transacationlearn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transacationlearn.model.Learn;
import com.transacationlearn.repository.LearnRepository;

import jakarta.transaction.Transactional;

@Service
public class LearnService {

    @Autowired
    private LearnRepository learnRepository;

    @Transactional
    public Learn createLearn(String topic, String description) {
        Learn learn = new Learn();
        learn.setTopic(topic);
        learn.setDescription(description);
        return learnRepository.save(learn);
    }
}