package com.oldking.todo.repository;

import com.oldking.todo.domain.PInformation;
import com.oldking.todo.mapper.InformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangzhiyong
 */
@Repository
public class InformationRepository {
    @Autowired
    private InformationMapper informationMapper;

    public void batchSave(List<PInformation> list) {
        list.forEach(x -> {
            informationMapper.insert(x);
        });
    }
}
