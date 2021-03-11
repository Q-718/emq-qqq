package com.q.auth;





import com.q.dao.MqttUserRepository;
import com.q.entity.MqttUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

;


/**
 *
 * @Author: qyp
 * @Date: 2021/3/9 15:59
 * @Description:
 */
@RestController
@RequestMapping("/mqtt")
public class AuthController {

    public static final Logger log= LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private MqttUserRepository repository;

    @PostMapping("/auth")
    public ResponseEntity auth(@RequestParam String clientid,
                               @RequestParam String username,
                               @RequestParam String password){
        log.info("emqx 请求服务器是时的参数客户id:{},用户名:{},密码:{}",clientid,username,password);
        MqttUser user=repository.findByUsername(username);
        if(user==null){
            log.info("用户表未查询到用户，鉴权过滤！");
            return new ResponseEntity("ignore",HttpStatus.OK);
        }

        if (password.equals(user.getPassword())){
            log.info("本地表中存在登录信息，http登录成功！");
            return new ResponseEntity(HttpStatus.OK);
        }else {
            log.info("密码错误，登录失败！");
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }


    }
}
