package com.besscroft.xanadu.controller;

import com.besscroft.xanadu.common.entity.Storage;
import com.besscroft.xanadu.common.result.AjaxResult;
import com.besscroft.xanadu.common.result.CommonResult;
import com.besscroft.xanadu.common.util.CommonPage;
import com.besscroft.xanadu.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 存储接口
 * @Author Bess Croft
 * @Date 2022/12/18 21:23
 */
@RequestMapping("/storage")
@RestController
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @GetMapping("/storagePage")
    @Operation(summary = "驱动分页列表")
    public CommonResult<CommonPage<Storage>> storagePage(@RequestParam("pageNum") Integer pageNum,
                                                         @RequestParam("pageSize") Integer pageSize) {
        List<Storage> storageList = storageService.storagePage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(storageList));
    }

    @Operation(summary = "用户删除接口")
    @DeleteMapping("/delete/{storageId:[\\d]+}")
    public AjaxResult delete(@PathVariable(name = "storageId") Long storageId) {
        storageService.deleteStorage(storageId);
        return AjaxResult.success("删除成功！");
    }

}
