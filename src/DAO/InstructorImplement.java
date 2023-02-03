package DAO;

import Entity.Instructor;


import java.sql.Connection;

public interface InstructorImplement {
    //根据Id,得到Instructor信息
    Instructor GetInstructor(Connection conn, String id);
    //验证辅导员身份身份
    Boolean IsInstructor(Connection conn,String id) ;
}
