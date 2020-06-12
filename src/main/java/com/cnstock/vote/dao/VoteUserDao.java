package com.cnstock.vote.dao;

import com.cnstock.vote.dto.VoteUserDto;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteUserDao {

    VoteUserDto getVoteUserByUserId(String userId);
}
