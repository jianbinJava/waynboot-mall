package com.wayn.mobile.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wayn.common.base.BaseController;
import com.wayn.common.util.R;
import com.wayn.mobile.api.domain.SearchHistory;
import com.wayn.mobile.api.service.ISearchHistoryService;
import com.wayn.mobile.framework.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 搜索历史表 前端控制器
 * </p>
 *
 * @author wayn
 * @since 2020-09-23
 */
@RestController
@RequestMapping("search")
public class SearchHistoryController extends BaseController {

    @Autowired
    private ISearchHistoryService iSearchHistoryService;

    @GetMapping("list")
    public R list() {
        return R.success().add("data", iSearchHistoryService.selectList());
    }

    @PostMapping
    public R add(@RequestBody SearchHistory searchHistory) {
        Long memberId = SecurityUtils.getUserId();
        searchHistory.setUserId(memberId);
        return R.result(iSearchHistoryService.save(searchHistory));
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Long id) {
        return R.result(iSearchHistoryService.removeById(id));
    }

    @DeleteMapping("all")
    public R delete() {
        Long memberId = SecurityUtils.getUserId();
        return R.result(iSearchHistoryService.remove(new QueryWrapper<SearchHistory>().eq("user_id", memberId)));
    }

}
