<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> <!-- BẮT BUỘC CÓ DÒNG NÀY -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Tất Cả Video Yêu Thích</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #f0f2f5 0%, #e4e6eb 100%);
            font-family: 'Segoe UI', sans-serif;
            padding: 40px 0;
        }
        .table-container {
            background: white;
            border-radius: 16px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            overflow: hidden;
        }
        .table-header {
            background: linear-gradient(135deg, #1976d2, #1565c0);
            color: white;
        }
        .table thead th {
            border: none;
            font-weight: 600;
            font-size: 1.1rem;
        }
        .table tbody tr:hover {
            background-color: #f8f9fa;
            transition: all 0.2s;
        }
        .badge-like {
            background: #e53935;
            color: white;
            font-weight: bold;
        }
        .no-data {
            text-align: center;
            padding: 60px 20px;
            color: #6c757d;
        }
        .no-data i {
            font-size: 4rem;
            margin-bottom: 20px;
            opacity: 0.5;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-11">
            <div class="table-container">

                <!-- Tiêu đề -->
                <div class="p-4 text-center table-header">
                    <h2 class="mb-1">
                        <i class="fas fa-heart fa-beat text-warning"></i>
                        Danh Sách Toàn Bộ Video Được Yêu Thích
                    </h2>
                    <p class="mb-0 opacity-90">
                        Tổng cộng:
                        <span class="badge bg-light text-dark fs-5">
                            <c:choose>
                                <c:when test="${not empty favorites}">${favorites.size()}</c:when>
                                <c:otherwise>0</c:otherwise>
                            </c:choose>
                        </span> lượt thích
                    </p>
                </div>

                <!-- Bảng dữ liệu -->
                <div class="table-responsive p-4">
                    <c:choose>
                        <c:when test="${not empty favorites}">
                            <table class="table table-hover align-middle">
                                <thead>
                                <tr>
                                    <th width="8%">STT</th>
                                    <th width="35%">Tiêu Đề Video</th>
                                    <th width="25%">Người Thích</th>
                                    <th width="20%">Ngày Thích</th>
                                    <th width="12%">Trạng Thái</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="fav" items="${favorites}" varStatus="loop">
                                    <tr>
                                        <td class="text-center fw-bold">${loop.count}</td>
                                        <td>
                                            <strong>${fav.video.title}</strong>
                                            <br><small class="text-muted">ID: ${fav.video.id}</small>
                                        </td>
                                        <td>
                                            <i class="fas fa-user text-primary me-2"></i>
                                            <strong>${fav.user.fullname}</strong>
                                            <br><small class="text-muted">${fav.user.email}</small>
                                        </td>
                                        <td>
                                            <fmt:formatDate value="${fav.likeDate}" pattern="dd/MM/yyyy HH:mm" />
                                        </td>
                                        <td class="text-center">
                                                <span class="badge badge-like">
                                                    <i class="fas fa-heart"></i> Đã Thích
                                                </span>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <div class="no-data">
                                <i class="fas fa-heart-broken"></i>
                                <h3>Chưa có lượt thích nào</h3>
                                <p class="lead">Khi người dùng nhấn "Thích" video, dữ liệu sẽ xuất hiện tại đây.</p>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>