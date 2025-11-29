<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập hệ thống</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #4b79a1 0%, #283e51 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Segoe UI', sans-serif;
        }

        .login-box {
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(18px);
            padding: 45px;
            border-radius: 20px;
            box-shadow: 0 10px 50px rgba(0,0,0,0.35);
            width: 100%;
            max-width: 420px;
            border: 1px solid rgba(255,255,255,0.2);
            animation: fadeDown 0.7s ease;
        }

        /* Animation */
        @keyframes fadeDown {
            from { opacity: 0; transform: translateY(-25px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .login-title {
            text-align: center;
            color: #fff;
            font-weight: 700;
            margin-bottom: 35px;
            letter-spacing: 1px;
            text-shadow: 1px 1px 3px rgba(0,0,0,0.4);
        }

        .btn-login {
            background: #ff5252;
            border: none;
            border-radius: 40px;
            padding: 12px;
            font-weight: 600;
            font-size: 1.1rem;
            transition: 0.25s ease-in-out;
            box-shadow: 0 4px 10px rgba(255,82,82,0.4);
        }

        .btn-login:hover {
            background: #ff1f1f;
            transform: translateY(-2px);
            box-shadow: 0 8px 18px rgba(255,82,82,0.5);
        }

        .form-control {
            border-radius: 40px;
            padding: 12px 20px;
            border: 1px solid rgba(255,255,255,0.3);
            background: rgba(255,255,255,0.4);
            backdrop-filter: blur(10px);
            color: #000;
            font-weight: 500;
        }

        .form-control:focus {
            border-color: #ff5252;
            box-shadow: 0 0 12px rgba(255,82,82,0.5);
        }

        .input-group-text {
            border-radius: 40px 0 0 40px;
            background: rgba(255,255,255,0.7);
            font-weight: 600;
        }

        .error-alert {
            border-radius: 14px;
            font-weight: 600;
            box-shadow: 0 6px 20px rgba(255,0,0,0.35);
        }

    </style>
</head>
<body>

<div class="container">
    <div class="login-box mx-auto">
        <h2 class="login-title">
            Đăng Nhập Hệ Thống
        </h2>

        <!-- Hiển thị lỗi nếu có -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger alert-dismissible fade show error-alert" role="alert">
                <i class="fas fa-exclamation-triangle"></i> ${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </c:if>

        <form action="<%= request.getContextPath() %>/login" method="post">
            <div class="mb-3">
                <label class="form-label fw-bold">Tên đăng nhập hoặc Email</label>
                <div class="input-group">
                    <span class="input-group-text">User</span>
                    <input type="text" name="idOrEmail" class="form-control"
                           placeholder="Nhập ID hoặc Email" required autofocus>
                </div>
            </div>

            <div class="mb-4">
                <label class="form-label fw-bold">Mật khẩu</label>
                <div class="input-group">
                    <span class="input-group-text">Lock</span>
                    <input type="password" name="password" class="form-control"
                           placeholder="Nhập mật khẩu" required>
                </div>
            </div>

            <div class="d-grid">
                <button type="submit" class="btn btn-danger btn-login">
                    Đăng Nhập
                </button>
            </div>
        </form>

        <div class="text-center mt-4">
            <small class="text-muted">
                Chưa có tài khoản?
                <a href="${pageContext.request.contextPath}/register" class="text-decoration-none fw-bold text-danger">
                    Đăng ký ngay
                </a>
            </small>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>