package edu.zut.cs.admin.service.impl;

import edu.zut.cs.admin.dao.ViewRecordMapper;
import edu.zut.cs.admin.model.ViewRecord;
import edu.zut.cs.admin.service.SearchService;
import edu.zut.cs.tools.NowWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create_by Intellij IDEA
 *
 * @author zouguo0212@
 * @package_name edu.zut.cs.admin.service.impl
 * @description
 * @date 2018/8/29 18:19
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    ViewRecordMapper viewRecordMapper;


    /**
     * 查询到从当前周开始之后的申请记录(包括当前周)
     * @param userNum
     * @return
     */
    @Override
    public List<ViewRecord> getApplyRecordByUserNum(String userNum) {
        List<ViewRecord> viewRecordList = viewRecordMapper.selectViewRecordByUserNumAndTimeLimit(userNum,new NowWeek().getNowWeek());
        return viewRecordList;
    }

    /**
     * 查询所有申请记录
     * @param userNum
     * @return
     */
    @Override
    public List<ViewRecord> getHistoryRecordByUserNum(String userNum) {
        List<ViewRecord> viewRecordList = viewRecordMapper.selectViewRecordByUserNumAndTimeLimit(userNum,1);
        return viewRecordList;
    }
}
