package com.example.webproject.SmallActionServlet.manager;

import com.example.webproject.DaoImpl.LoginDaoImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AdminManageUserServlet")
public class Change_role extends HttpServlet {

    private static final long serialVersionUID = 1L;
    protected LoginDaoImpl dao = new LoginDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("manager_jsp/change_role.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String userId = request.getParameter("userId");

        if ("update".equals(action)) {
            String role = request.getParameter("role");
            boolean success = updateUserRole(userId, Integer.parseInt(role));
            if (success) {
                request.setAttribute("message", "角色更新成功");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "角色更新失败");
                request.setAttribute("messageType", "error");
            }
            request.getRequestDispatcher("manager_jsp/change_role.jsp").forward(request, response);
        } else {
            // 查询用户信息
            User user = getUserById(userId);
            if (user != null) {
                request.setAttribute("userId", user.getId());
                request.setAttribute("userType", user.getType());
            } else {
                request.setAttribute("message", "用户未找到");
                request.setAttribute("messageType", "error");
            }
            request.getRequestDispatcher("manager_jsp/change_role.jsp").forward(request, response);
        }
    }

    private User getUserById(String id) {
        User user = null;
        try (Connection connection = dao.getConnection()) {
            String sql = "SELECT * FROM login WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int type = resultSet.getInt("type");
                        user = new User(id, type);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    private boolean updateUserRole(String id, int type) {
        try (Connection connection = dao.getConnection()) {
            String sql = "UPDATE login SET type = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, type);
                statement.setString(2, id);
                int rowsUpdated = statement.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public class User {
        private String id;
        private int type;

        public User(String id, int type) {
            this.id = id;
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
