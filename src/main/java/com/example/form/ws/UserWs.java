package com.example.form.ws;

import com.example.form.bean.User;
import com.example.form.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user ")
public class UserWs {
    public User findByRef(String ref) {

        return userService.findByRef(ref);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(String ref) {
        return userService.deleteByRef(ref);
    }
    @GetMapping("/formulaire/ncin/{ncin}")
    public User findByFormulaireNcin(String ncin) {
        return userService.findByFormulaireNcin(ncin);
    }
    @Autowired
    private UserService userService;


}
