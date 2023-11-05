package com.source.data.server.client_controller.addressBook;

import com.pojo.RESULT.Result;
import com.pojo.address.AddressBook;
import com.pojo.address.WEBaddressBook.Address_add;
import com.pojo.address.WEBaddressBook.Address_setDefault;
import com.source.data.server.service.addressBook.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * app端 : 地址模块
 */
@RestController
@Slf4j
@RequestMapping("/user/addressBook")
public class AddressBookController {
    @Autowired
    private AddressBookService service;

    @PostMapping
    public Result add(@RequestBody Address_add addressAdd){
        log.info("新增地址 : {}",addressAdd);
        service.insert(addressAdd);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        log.info("查询当前登入用户的地址簿");
        List<AddressBook> addressBookList = service.selectList();
        return Result.success(addressBookList);
    }
    @GetMapping("/default")
    public Result getDefault(){
        log.info("查询默认地址");
        AddressBook addressBook = service.selectDefault();
        return Result.success(addressBook);
    }
    @PutMapping("/default")
    public Result setDefault(@RequestBody Address_setDefault addressSetDefault){
        log.info("设置id为: {} 的地址为默认地址",addressSetDefault.getId());
        service.updateDefault(addressSetDefault.getId());
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result isId_selectAddress(@PathVariable("id") Long id){
        log.info("查询id为: {} 的地址信息",id);
        AddressBook addressBook = service.selectID(id);
        return Result.success(addressBook);
    }

    @PutMapping
    public Result isID_setAddress(@RequestBody Address_add addressAdd){
        log.info("根据id修改地址");
        service.updateAddress(addressAdd);
        return Result.success();
    }

    @DeleteMapping("/")
    public Result isID_removeAddress(Long id){
        log.info("删除当前用户下id为:{} 的地址",id);
        service.deleteAddress(id);
        return Result.success();
    }
}
