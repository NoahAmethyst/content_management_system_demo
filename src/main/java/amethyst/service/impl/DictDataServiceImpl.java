package amethyst.service.impl;

import amethyst.mapper.DictDataMapper;
import amethyst.po.sys.DictData;
import amethyst.service.DictDataServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictDataServiceImpl implements DictDataServiceI {
    @Autowired
    private DictDataMapper dictDataMapper;

    //查询
    @Override
    public List<DictData> findDictDataByType(String type) {
        DictData dictData=new DictData();
        dictData.setDictType(type);
        return dictDataMapper.selectDictDataList(dictData);
    }
}
