package DAO;

import Entity.Student;

import java.sql.Connection;

public interface StudentImplement {
     Student GetStudent( Connection conn,String id);
}
