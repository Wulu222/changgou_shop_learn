package com.changgou.service.goods.service.impl;

import com.changgou.goods.pojo.Brand;
import com.changgou.service.goods.dao.BrandMapper;
import com.changgou.service.goods.service.BrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.DocFlavor;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import tk.mybatis.mapper.entity.Example;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;//数据访问层对象

    /**
     * 品牌列表查询（业务层实现类）
     * @return
     */

    @Override
    public List<Brand> findList() {

        List<Brand> brandList = brandMapper.selectAll();

        return brandList;
    }


    /**
     * 根据id查询品牌信息
     * @param id
     * @return
     */
    @Override
    public Brand findById(Integer id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        return  brand;
    }

    /**
     * 新增品牌信息
     * @param brand
     */
    @Override
    @Transactional//事务控制，确保add方法中的数据库操作要么全部成功提交，要么在出现异常时全部回滚，保证数据一致性。
    public void add(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    /**
     * 修改品牌信息
     * @param brand
     */
    @Override
    @Transactional
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKey( brand);
    }

    /**
     * 根据id删除品牌信息
     * @param id
     */
    @Override
    @Transactional
    public void delById(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 品牌列表条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Brand> list(Map<String, Object> searchMap) {
        Example example = new Example(Brand.class);
        //封装查询条件
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            //品牌名称（模糊） % xxx %
            if(searchMap.get("name") != null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name", "%" + searchMap.get("name") + "%");
            }
            //品牌首字母（精确）
            if(searchMap.get("letter") != null && !"".equals(searchMap.get("letter"))){
                criteria.andEqualTo("letter", searchMap.get("letter"));
            }
        }

        List<Brand> brandList = brandMapper.selectByExample(example);
        return brandList;
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Brand> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Brand> page1 = (Page<Brand>) brandMapper.selectAll();
        return page1;
    }

    @Override
    public Page<Brand> findPage(Map<String, Object> searchMap, int page, int size) {
        //设置分页
        PageHelper.startPage(page,size);

        //设置查询条件
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            //品牌名称（模糊） % xxx %
            if(searchMap.get("name") != null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name", "%" + searchMap.get("name") + "%");
            }
            //品牌首字母（精确）
            if(searchMap.get("letter") != null && !"".equals(searchMap.get("letter"))){
                criteria.andEqualTo("letter", searchMap.get("letter"));
            }
        }
        //强转成 Page
        Page<Brand> pageInfo = (Page<Brand>) brandMapper.selectByExample(example);
        return pageInfo;
    }
}
