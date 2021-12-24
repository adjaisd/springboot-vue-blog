package com.adjaisd.controller;


import com.adjaisd.common.DateTimeUtils;
import com.adjaisd.common.exception.VerifyCodeException;
import com.adjaisd.common.result.Result;
import com.adjaisd.common.result.ResultCode;
import com.adjaisd.common.utils.JwtUtils;
import com.adjaisd.common.utils.verify.IVerifyCodeGen;
import com.adjaisd.common.utils.verify.SimpleCharVerifyCodeGenImpl;
import com.adjaisd.common.utils.verify.VerifyCode;
import com.adjaisd.entity.User;
import com.adjaisd.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author adjaisd
 * @since 2021-12-06
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 获取验证码， get: /user/verifycode
    @ApiOperation(value = "验证码")
    @GetMapping("/verifycode")
    public void verifyCode(HttpServletRequest request,
                           HttpServletResponse response) {
        request.getParameter("time");
        HttpSession session = request.getSession();
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();

            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            //将VerifyCode绑定session
            // request.getSession().setAttribute("VerifyCode", code);
            session.setAttribute("VerifyCode", code);
            response.getOutputStream().flush();
        } catch (IOException e) {
            throw new VerifyCodeException(ResultCode.ERROR, e.getMessage());
        }
    }

    // 注册： post: /user/register
    @ApiOperation(value = "注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result register(HttpServletRequest request, @RequestBody Map<String,
            String> registerMap) {
        String verifyCode = (String) request.getSession().getAttribute(
                "VerifyCode");
        String inputCode = registerMap.get("code");
        boolean verify = inputCode.equalsIgnoreCase(verifyCode); // 验证码是否正确
        if (!verify) return Result.fail()
                .Code(500)
                .message("验证码输入不正确.");
        String username = registerMap.get("username");
        // 是否存在用户
        boolean existUserByUsername = userService.existUserByUsername(username);
        if (existUserByUsername) return Result.fail()
                .Code(500)
                .message("用户已存在.");
        String nickname = registerMap.get("nickname");
        boolean existUserByNickname = userService.existUserByNickname(nickname);
        if (existUserByNickname) return Result.fail()
                .code(500)
                .message("昵称已存在");
        String password = registerMap.get("password");
        String email = registerMap.get("email");
        Long createDate = DateTimeUtils.getCurrentDate().getTime();
        User user = new User(null, username, password, email,
                createDate, nickname, "");
        int i = userService.addUser(user);
        if (i > 0)
            return Result.succ().message("注册成功.");
        else
            return Result.fail().code(400).message("注册失败.");
    }

    // 登陆： post: /user/login
    @ApiOperation(value = "登陆")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(HttpServletRequest request, @RequestBody Map<String,
            String> loginMap) {
        String verifyCode = (String) request.getSession().getAttribute(
                "VerifyCode");
        String inputCode = loginMap.get("code");
        boolean verify = inputCode.equalsIgnoreCase(verifyCode);  // 验证码是否正确
        if (!verify) return Result.fail()
                .Code(500)
                .message("验证码验证失败");

        String username = loginMap.get("username");
        String password = loginMap.get("password");

        boolean exist = userService.existUser(username, password); // 是否存在用户

        if (exist) {
            String jwtToken = JwtUtils.getJwtToken(username, password);
            request.getSession().setAttribute("token", jwtToken);
            Long userId = userService.getUserByName(username).getId();
            return Result.succ()
                    .data("userId", userId)
                    .data("token", jwtToken)
                    .message("登陆成功");
        } else {
            return Result.fail()
                    .Code(500)
                    .message("用户名或密码不正确");
        }
    }

    // 登出： get: /user/logout
    @ApiOperation(value = "登出")
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        if (request.getSession().getAttribute("token") != null)
            request.getSession().removeAttribute("token");
        return Result.succ()
                .message("登出成功");
    }

}
