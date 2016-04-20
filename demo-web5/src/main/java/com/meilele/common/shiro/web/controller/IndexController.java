package com.meilele.common.shiro.web.controller;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meilele.common.shiro.entity.Resource;
import com.meilele.common.shiro.entity.User;
import com.meilele.common.shiro.web.bind.annotation.CurrentUser;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/login")
    public String showLoginForm(HttpServletRequest req, Model model) {
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "login";
    }

    /*    @RequestMapping("/menu.json")
        @ResponseBody
        public Resource menu(@CurrentUser User loginUser) {
            Set<String> permissions = userService.findPermissions(loginUser.getUsername());
            List<Resource> menus = resourceService.findMenus(permissions);
            Resource root = new Resource();
            root.setParentId(0l);
            root.setId(1l);
            menus.add(root);
            getMenusTree(menus);
            return menus.get(0);
        }*/
}
