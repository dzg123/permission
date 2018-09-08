package com.dzg.service;

import com.dzg.dao.SysDeptMapper;
import com.dzg.domain.SysDept;
import com.dzg.dto.DeptLevelDto;
import com.dzg.util.LevelUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.MultiMap;
import org.springframework.stereotype.Service;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SysTreeService {
    @Resource
    private SysDeptMapper sysDeptMapper;

    public List<DeptLevelDto> deptTree() {
        List<SysDept> deptList = sysDeptMapper.getAllDept();
        ArrayList<DeptLevelDto> dtoList = Lists.newArrayList();
        for (SysDept dept : deptList) {
            DeptLevelDto dto = DeptLevelDto.adapt(dept);
            dtoList.add(dto);
        }
        return deptListToTree(dtoList);
    }

    public List<DeptLevelDto> deptListToTree(List<DeptLevelDto> deptLevelList) {
        if (org.apache.commons.collections.CollectionUtils.isEmpty(deptLevelList)) {
            return Lists.newArrayList();
        }
        Multimap<String, DeptLevelDto> levelDeptMap = ArrayListMultimap.create();
        List<DeptLevelDto> rootList = Lists.newArrayList();
        for (DeptLevelDto dto : deptLevelList) {
            levelDeptMap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }
//        按照seq从小到大排序
        Collections.sort(rootList, new Comparator<DeptLevelDto>() {
            @Override
            public int compare(DeptLevelDto o1, DeptLevelDto o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });
//        递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }
//    level:0,0,all 0->0.1,0.2
//    level:0.1
//    level:0.2

    public void transformDeptTree(List<DeptLevelDto> deptLevelList, String level,
                                  Multimap<String, DeptLevelDto> levelDeptMap) {
        for (int i = 0; i < deptLevelList.size(); i++) {
//            遍历该层的每个元素
            DeptLevelDto deptLevelDto = deptLevelList.get(i);
//            处理层级
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDto.getId());
//            处理下一层
            List<DeptLevelDto> tempDeptList = (List<DeptLevelDto>) levelDeptMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempDeptList)) {
//                排序
                Collections.sort(tempDeptList, new Comparator<DeptLevelDto>() {
                    @Override
                    public int compare(DeptLevelDto o1, DeptLevelDto o2) {
                        return o1.getSeq() - o2.getSeq();
                    }
                });
//                设置下一层部门
                deptLevelDto.setDeptList(tempDeptList);
//                进入下一层处理
                transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
            }
        }
    }
}
