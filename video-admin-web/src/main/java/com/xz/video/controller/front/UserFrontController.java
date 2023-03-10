package com.xz.video.controller.front;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xkcoding.http.config.HttpConfig;
import com.xz.video.common.redis.RedisUtils;
import com.xz.video.common.utils.JwtUtils;
import com.xz.video.entity.MessageEntity;
import com.xz.video.entity.UserEntity;
import com.xz.video.entity.UserThirdPartRelationEntity;
import com.xz.video.entity.vo.UserVo;
import com.xz.video.service.UserService;
import com.xz.video.service.UserThirdPartRelationService;
import com.xz.video.utils.TokenServerAssistant;
import io.renren.common.constant.Constant;
import io.renren.common.exception.ErrorCode;
import io.renren.common.exception.RenException;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.enums.scope.AuthGiteeScope;
import me.zhyd.oauth.enums.scope.AuthGithubScope;
import me.zhyd.oauth.enums.scope.AuthWeiboScope;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.*;
import me.zhyd.oauth.utils.AuthScopeUtils;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * ??????????????????
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@RestController
@RequestMapping("video/front/user")
@Slf4j
public class UserFrontController {

    @Autowired
    UserService userService;

    @Autowired
    UserThirdPartRelationService userThirdPartRelationService;

    @Autowired
    RedisUtils redisUtils;


    /**
     * ????????????
     * @param UserEntity ?????????
     */
    @RequestMapping("login")
    public Result login(@RequestBody UserEntity UserEntity) {

        Result<Map<String, Object>> result = new Result<>();
        //??????????????????
        UserEntity userInfo = userService.getUserByName(UserEntity.getUsername());

        String s = SecureUtil.md5(UserEntity.getPassword());
        if (ObjectUtil.isEmpty(userInfo)||!s.equals(userInfo.getPassword())) {
            Result<String> errorResult = new Result<>();
            return errorResult.error("????????????????????????");
        }
        String token = JwtUtils.getJwtToken(userInfo.getUsername(), userInfo.getPassword());
        //????????????????????????redis???
        redisUtils.set(userInfo.getId() + ":" + userInfo.getUsername(), JSONObject.toJSONString(userInfo));
        Map<String, Object> map = new HashMap<>(2);
        map.put(Constant.FRONT_TOKEN_HEADER, token);
        map.put("userInfo", userInfo);
        //??????token?????????
        return result.ok(map);
    }

    /**
     * ???????????????token????????????????????????
     * @param token token??????
     * @return ?????????
     */
    @RequestMapping("getUserInfoByToken")
    public Result getUserInfoByToken(String token) {
        Result<UserEntity> result = new Result();
        String username = JwtUtils.getUsername(token);
        UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>().eq("username", username));
        return result.ok(userEntity);
    }


    /**
     * ????????????
     * @param userVo ????????????????????????
     */
    @RequestMapping("register")
    public Result regist(@RequestBody UserVo userVo) {
        String code = redisUtils.get("code");
        if (StringUtils.isEmpty(code)) {
            return new Result().error("?????????????????????");
        }
        if (!code.equals(userVo.getCode())) {
            return new Result().error("?????????????????????");
        }
        Result<String> result = new Result<>();

        UserEntity currentUser = userService.getOne(new QueryWrapper<UserEntity>().eq("email", userVo.getEmail()));

        if (currentUser == null) {
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userVo, userEntity);
            userEntity.setAvatar("https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png");
            String password = SecureUtil.md5(userEntity.getPassword());
            userEntity.setPassword(password);
            userService.save(userEntity);
            UserThirdPartRelationEntity userThirdPartRelationEntity = new UserThirdPartRelationEntity();
            userThirdPartRelationEntity.setUserId(userEntity.getId());
            userThirdPartRelationEntity.setPhone(userEntity.getMobile());
            userThirdPartRelationEntity.setEmail(userEntity.getEmail());
            userThirdPartRelationService.save(userThirdPartRelationEntity);
        } else {
            if (currentUser.getPassword() == null || StringUtils.isEmpty(currentUser.getPassword())) {
                //????????????
                currentUser.setPassword(SecureUtil.md5(userVo.getPassword()));
                currentUser.setUsername(userVo.getUsername());
                currentUser.setMobile(userVo.getMobile());
                userService.updateById(currentUser);
            } else {
                return result.error("????????????????????????");
            }
        }

        return result.ok();
    }

    /**
     *????????????
     */
    @RequestMapping("setting")
    public Result setting(@RequestBody UserVo userVo) {
        Result<Integer> result = new Result<>();
        Integer it = userService.setting(userVo);
        return result.ok(it);
    }

    /**
     * ????????????id?????????????????????
     * @param userId ??????id
     * @return ????????????
     */
    @RequestMapping("getUserInfoByUserId/{userId}")
    public Result getUserInfoByUserId(@PathVariable String userId) {
        Result<UserEntity> result = new Result<>();
        UserEntity user = userService.getById(userId);
        return result.ok(user);
    }

     /**
      *?????????????????????
      */
    @RequestMapping("/render/{source}")
    public Result renderAuth(@PathVariable("source") String source, HttpServletResponse response) throws IOException {
        log.info("??????render???" + source);
        AuthRequest authRequest = getAuthRequest(source);
        String authorizeUrl = authRequest.authorize(AuthStateUtils.createState());
        log.info(authorizeUrl);
        return new Result<>().ok(authorizeUrl);
    }

    /**
     * ?????????????????????
     */
    @RequestMapping("/callback/{source}")
    public void loginBySource(@PathVariable("source") String source, AuthCallback callback, HttpServletRequest request,HttpServletResponse rep) throws IOException {
        log.info("??????callback???" + source + " callback params???" + JSONObject.toJSONString(callback));
        AuthRequest authRequest = getAuthRequest(source);
        AuthResponse<AuthUser> response = authRequest.login(callback);
        log.info(JSONObject.toJSONString(response));
        if (response.ok()) {
            //??????????????????????????????????????????
            AuthUser user = response.getData();
            /*????????????????????????????????????????????????
             *
             * ???????????????????????????
             *  ???????????????????????????????????????
             *  ??????????????????????????????
             */
            UserEntity userEntity = null;
            UserThirdPartRelationEntity entity = userThirdPartRelationService.getOne(new QueryWrapper<UserThirdPartRelationEntity>().eq("third_part_user_id", user.getUuid()));
            if (entity != null) {
                userEntity = userService.getById(entity.getUserId());
            } else {
                UserThirdPartRelationEntity model = userThirdPartRelationService.getOne(new QueryWrapper<UserThirdPartRelationEntity>().eq("email", user.getEmail()).or().eq("phone", ""));
                if (model != null) {
                    model.setThirdPartUserId(user.getUuid());
                    model.setThirdPartUsername(user.getUsername());
                    model.setThirdPartAvatar(user.getAvatar());
                    userThirdPartRelationService.updateById(model);
                    userEntity = userService.getById(model.getUserId());
                } else {
                    userEntity = new UserEntity();
                    userEntity.setUsername(user.getUsername());
                    userEntity.setAvatar(user.getAvatar());
                    userEntity.setEmail(user.getEmail());
                    userService.save(userEntity);

                    UserThirdPartRelationEntity userThirdPartRelationEntity = new UserThirdPartRelationEntity();
                    userThirdPartRelationEntity.setUserId(userEntity.getId());
                    userThirdPartRelationEntity.setThirdPartUserId(user.getUuid());
                    userThirdPartRelationEntity.setThirdPartUsername(user.getUsername());
                    userThirdPartRelationEntity.setThirdPartAvatar(user.getAvatar());
                    userThirdPartRelationEntity.setEmail(user.getEmail());
                    userThirdPartRelationService.save(userThirdPartRelationEntity);
                }
            }
            //????????????????????????redis???
            redisUtils.set(userEntity.getId() + ":" + userEntity.getUsername(), JSONObject.toJSONString(userEntity));
            String token = JwtUtils.getJwtToken(userEntity.getUsername(), "12345");
            rep.sendRedirect("http://43.248.97.192:9956/dashboard/index?token=" + token);
        }
    }

    /**
     * ??????zim???token??????
     * @param userId ?????????id
     */
    @RequestMapping("/getZimToken/{userId}")
    private Result getZimToken(@PathVariable String userId){
        TokenServerAssistant.TokenInfo tokenInfo = TokenServerAssistant.generateToken04(1562974438,userId,"516253e568dce2b1739b9c4019277309",300,"");
        return new Result<>().ok(tokenInfo.data);
    }

    /**
     * ???????????????????????????
     */
    private AuthRequest getAuthRequest(String source) {
        AuthRequest authRequest = null;
        switch (source.toLowerCase()) {
            case "github":
                authRequest = new AuthGithubRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://localhost:8443/oauth/callback/github")
                        .scopes(AuthScopeUtils.getScopes(AuthGithubScope.values()))
                        // ??????????????????????????????
                        .httpConfig(HttpConfig.builder()
                                .timeout(15000)
                                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10080)))
                                .build())
                        .build());
                break;
            case "gitee":
                authRequest = new AuthGiteeRequest(AuthConfig.builder()
                        .clientId("9e3e980b7f65a4125ea75f54a398229434f5b3bb20fd2d20c1afd4737033a68f")
                        .clientSecret("d52c7e4df06ef2067fdb3a79d8e98249b2195c61f4fd5cee469388a3a6a10cf1")
                        .redirectUri("http://43.248.97.192:9956/gitee/video/front/user/callback/gitee")
                        .scopes(AuthScopeUtils.getScopes(AuthGiteeScope.values()))
                        .build());
                break;
            case "weibo":
                authRequest = new AuthWeiboRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://dblog-web.zhyd.me/oauth/callback/weibo")
                        .scopes(Arrays.asList(
                                AuthWeiboScope.EMAIL.getScope(),
                                AuthWeiboScope.FRIENDSHIPS_GROUPS_READ.getScope(),
                                AuthWeiboScope.STATUSES_TO_ME_READ.getScope()
                        ))
                        .build());
                break;
            case "qq":
                authRequest = new AuthQqRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://localhost:8443/oauth/callback/qq")
                        .build());
                break;
            case "wechat_open":
                authRequest = new AuthWeChatOpenRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://www.zhyd.me/oauth/callback/wechat")
                        .build());
                break;
            case "csdn":
                authRequest = new AuthCsdnRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://dblog-web.zhyd.me/oauth/callback/csdn")
                        .build());
                break;
            default:
                break;
        }
        if (null == authRequest) {
            throw new AuthException("?????????????????????Auth??????");
        }
        return authRequest;
    }

}
