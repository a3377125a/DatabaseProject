package DAO;

import Entity.Student;

import java.sql.Connection;
import java.sql.SQLException;

public interface StudentImplement {
     //根据Id,得到student信息
     Student GetStudent( Connection conn,String id);
     //验证学生身份
     Boolean IsStudent(Connection conn,String id) ;
}
