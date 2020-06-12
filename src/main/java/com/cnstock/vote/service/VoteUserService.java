package com.cnstock.vote.service;

import com.cnstock.vote.dao.VoteUserDao;
import com.cnstock.vote.dto.VoteUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteUserService {
    @Autowired
    private VoteUserDao voteUserDao;

    public String getUserNameByUserId(){
        VoteUserDto voteUserDto = voteUserDao.getVoteUserByUserId("123");

        return voteUserDto.getUserName();
    }
}
