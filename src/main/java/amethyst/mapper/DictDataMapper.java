package amethyst.mapper;

import amethyst.po.sys.DictData;

import java.util.List;

public interface DictDataMapper {
    List<DictData> selectDictDataList(DictData dictData);
}
