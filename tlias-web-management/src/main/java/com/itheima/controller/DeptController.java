package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
//@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    /**
     * 查询全部部门数据
     * @return
     */
    //@RequestMapping(value = "/depts",method = RequestMethod.GET) //指定请求方式为GET
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");

        //调用service查询部门数据
        List<Dept> deptList =  deptService.list();
        return Result.success(deptList);
    }

//    /**
//     *根据id查询部门数据
//     * @return
//     */
//    @GetMapping
//    public Result findDept(Integer id){
//        log.info("根据id查询部门数据");
//        //调用service查询部门数据
//        Dept dept =  deptService.findDept(id);
//        return Result.success(dept);
//    }

    /**
     * 删除部门
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门:{}",id);
        //调用service删除部门
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 删除部门2
     *
     * **/
    //@DeleteMapping("/{id}")
    public Result delete2(@PathVariable Integer id){
        log.info("根据id删除部门：{}",id);
        deptService.delete2(id);
        return Result.success();
    }


    /**
     * 新增部门
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门: {}" , dept);
        //调用service新增部门
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 修改部门
     * @return
     */
    @PutMapping
    public Result modifyDept(@RequestBody Dept dept){
        //先回显
        //this.findDept(dept.getId());
        log.info("修改部门：{}",dept);
        deptService.modifyDept(dept);
        return Result.success(dept);
    }
}
