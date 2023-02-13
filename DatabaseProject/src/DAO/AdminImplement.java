package DAO;

import Entity.Admin;
import Entity.Instructor;

import java.sql.Connection;

public interface AdminImplement {
    //根据Id,得到Admin信息
    Admin GetAdmin(Connection conn, String id);
    //验证院系管理员身份
    Boolean IsAdmin(Connection conn,String id) ;
}
