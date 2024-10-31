package com.ohgiraffers.understand.model;

import com.ohgiraffers.understand.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface MenuDAO {

    MenuDTO selectOneMenu(MenuDTO menuDTO);

    List<MenuDTO> selectAllMenu();

    int regist(MenuDTO menuDTO);

    int update(MenuDTO menuDTO);

    int delete(MenuDTO menuDTO);
}
