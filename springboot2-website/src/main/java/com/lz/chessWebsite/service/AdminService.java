package com.lz.chessWebsite.service;

import com.lz.chessWebsite.mapper.AdminMapper;
import com.lz.chessWebsite.mapper.AdminGradeMapper;
import com.lz.chessWebsite.pojo.Admin;
import com.lz.chessWebsite.pojo.AdminGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminGradeMapper adminGradeMapper;

    public Admin authenticate(String adminName, String password) {
        return adminMapper.findByAdminNameAndPassword(adminName, password);
    }

    public Admin addAdmin(Admin admin) {
        adminMapper.addAdmin(admin);
        return admin;
    }

    public void deleteAdmin(Integer id) {
        adminMapper.deleteAdmin(id);
/*        adminGradeMapper.deleteAdminGradeByAdminId(id);*/
    }

    public Admin updateAdmin(Integer id, Admin adminDetails) {
        adminDetails.setAdminId(id);
        adminMapper.updateAdmin(adminDetails);
        return adminDetails;
    }

    public List<Admin> searchAdmin(String username) {
        return adminMapper.searchAdmin(username);
    }

    public List<Admin> getAllAdmins() {
        List<Admin> admins = adminMapper.getAllAdmins();
        for (Admin admin : admins) {
            int grade = adminGradeMapper.getAdminGrade(admin.getAdminId());
            admin.setGrade(grade);
        }
        return admins;
    }

    public void updateAdminGrade(int adminId, int grade) {
        adminGradeMapper.updateAdminGrade(adminId, grade);
    }

    public void addAdminGrade(AdminGrade admingrade){
        adminGradeMapper.addAdminGrade(admingrade);
    }

    public Admin getAdminByAdminName(String adminName){
        return adminMapper.getAdminByAdminName(adminName);
    }

    public Integer getAdminGrade(int adminId){
        return adminGradeMapper.getAdminGrade(adminId);
    }
}
