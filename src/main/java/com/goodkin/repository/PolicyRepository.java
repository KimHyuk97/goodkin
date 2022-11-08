package com.goodkin.repository;

import org.apache.ibatis.annotations.Mapper;

import com.goodkin.model.site.Policy;
import com.goodkin.model.site.PolicyType;

@Mapper
public interface PolicyRepository {
    public Policy getPolicy(PolicyType privacy);
    public int update(Policy policy);
}
