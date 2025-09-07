package com.changgou.service.goods.controller;

import com.changgou.common.pojo.PageResult;
import com.changgou.common.pojo.Result;
import com.changgou.common.pojo.StatusCode;
import com.changgou.goods.pojo.Brand;
import com.changgou.service.goods.service.BrandService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//表现层
@RequestMapping("/brand")
@RestController
public class BrandController {

    @Autowired
    private BrandService brandService;//业务层注入

    @GetMapping
    public Result<List<Brand>> findList(){
        List<Brand> brandList = brandService.findList();
        return new Result<>(true, StatusCode.OK,"查询成功",brandList);
    }

    @GetMapping("/{id}")//路径变量
    public Result<Brand> findById(@PathVariable("id") Integer id){ //接收路径变量@PathVariable
        Brand brand = brandService.findById(id);
        return new Result<>(true, StatusCode.OK,"查询成功",brand);
    }

    @PostMapping//请求体,方法接收前端传递的 Brand 对象，调用 BrandService 的 add 方法保存数据，并返回操作结果。
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Brand brand,@PathVariable("id") Integer id){
        brand.setId(id);
        brandService.update(brand);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    @DeleteMapping("/{id}")
    public Result delById(@PathVariable("id") Integer id){
        brandService.delById(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    @GetMapping("/search")
    public Result<List<Brand>> search(@RequestParam Map searchMap){
        List<Brand> list = brandService.list(searchMap);
        return new Result<>(true, StatusCode.OK,"查询成功",list);
    }

    @GetMapping("/search/{page}/{size}")
    public Result findPage(@PathVariable("page")int page,@PathVariable("size")int size){
        Page<Brand> pageInfo = brandService.findPage(page, size);
        PageResult pageResult = new PageResult(pageInfo.getTotal(), pageInfo.getResult());
        return new Result<>(true, StatusCode.OK,"查询成功",pageResult);
    }

    @GetMapping("/searchPage/{page}/{size}")
    public Result<Page<Brand>> findPage(@PathVariable("page")int page,@PathVariable("size")int size,@RequestParam Map<String,Object> searchMap){
        Page<Brand> pageInfo = brandService.findPage(searchMap, page, size);
        PageResult pageResult = new PageResult(pageInfo.getTotal(), pageInfo.getResult());
        return new Result<>(true, StatusCode.OK,"查询成功",pageResult);
    }

}
