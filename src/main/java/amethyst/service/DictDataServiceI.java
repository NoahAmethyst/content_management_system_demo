package amethyst.service;

import amethyst.po.sys.DictData;

import java.util.List;

public interface DictDataServiceI {
    List<DictData> findDictDataByType(String type);
}
