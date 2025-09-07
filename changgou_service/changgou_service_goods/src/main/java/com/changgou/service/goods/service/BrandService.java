package com.changgou.service.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.Page;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Map;

public interface BrandService {

    /**
     * 品牌的列表查询（业务层）
     */

    List<Brand> findList();

    /**
     * 根据id查询品牌的数据
     */
    Brand findById(Integer id);

    /**
     * 根据id删除品牌数据
     */
    void add(Brand brand);

    /**
     * 修改品牌数据
     */
    void update(Brand brand);

    /**
     * 删除品牌数据
     */
    void delById(Integer id);

    /**
     * 品牌列表条件查询
     */
    List<Brand> list(Map<String, Object> searchMap);

    /**
     * 品牌的列表分页查询
     * page:当前是多少页
     * size：每页显示多少条数据
     */
    Page<Brand> findPage(int page,int  size);

    /**
     * 品牌列表条件+分页查询
     */
    Page<Brand> findPage(Map<String, Object> searchMap, int page, int size);

}
