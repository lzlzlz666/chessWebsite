package com.lz.chessWebsite.controller;

import com.lz.chessWebsite.pojo.Admin;
import com.lz.chessWebsite.pojo.AdminGrade;
import com.lz.chessWebsite.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 检查数据库里admin是否有匹配的数据，有的话可以登录后台！
     * @param username
     * @param password
     * @param request
     * @return
     */
    @PostMapping("/adminLogin")
    public ModelAndView adminLogin(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   HttpServletRequest request) {
        Admin admin = adminService.authenticate(username, password);
        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("adminName", username);
            return new ModelAndView("redirect:/admin_index.html");
        } else {
            return new ModelAndView("redirect:/admin_loginFail.html");
        }
    }

    // 新增管理员
    @PostMapping("/addAdmin")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
        Admin newAdmin = adminService.addAdmin(admin);

        //当新增管理员时，对应管理员权限管理表admingrade表，响应增加一条数据(grade默认为0)
/*      AdminGrade admingrade = new AdminGrade();
        admingrade.setAdminId(admin.getAdminId());
        admingrade.setGrade(admin.getGrade());
        adminService.addAdminGrade(admingrade);*/

        return ResponseEntity.ok().body(newAdmin);
    }

    // 删除管理员
    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("id") Integer id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.ok().build();
    }

    // 更新管理员信息
    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") Integer id, @RequestBody Admin adminDetails) {
        Admin updatedAdmin = adminService.updateAdmin(id, adminDetails);
        return ResponseEntity.ok().body(updatedAdmin);
    }

    // 查询管理员信息
    @GetMapping("/searchAdmin")
    public ResponseEntity<List<Admin>> searchAdmin(@RequestParam("username") String username) {
        List<Admin> admins = adminService.searchAdmin(username);
        return ResponseEntity.ok().body(admins);
    }

    /**
     * 获取管理员所有数据，接口-->给管理员权限管理信息渲染到前端
     * @return
     */
    @GetMapping("/admin/all")
    @ResponseBody
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @PostMapping("/admin/updateGrade")
    @ResponseBody
    public ResponseEntity<?> updateAdminGrade(@RequestBody Map<String, Integer> payload) {
        int adminId = payload.get("adminId");
        int grade = payload.get("grade");
        adminService.updateAdminGrade(adminId, grade);
        return ResponseEntity.ok().build();
    }


    /**
     * 根据登录的admin-->来获取admingrade表里的grade
     */
    @GetMapping("/admin/grade")
    @ResponseBody
    public Integer getAdminGrade(HttpServletRequest request){
        HttpSession session = request.getSession();
        String adminName = (String) session.getAttribute("adminName");
        Admin admin = adminService.getAdminByAdminName(adminName);

        //根据admingrade表里的admin_id --> grade
        return adminService.getAdminGrade(admin.getAdminId());
    }

}
