<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> <!-- BẮT BUỘC CÓ DÒNG NÀY ĐỂ EL HOẠT ĐỘNG -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Video Yêu Thích - ${sessionScope.currentUser.fullname}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">
    <style>
        /* ===== RESET STYLE ===== */
        body {
            background: #f0f0f3;
            min-height: 100vh;
            padding: 40px 0;
            font-family: "Segoe UI", sans-serif;
            color: #1f1f1f;
        }

        /* ===== CARD TỔNG ===== */
        .card {
            border-radius: 14px;
            border: none;
            background: #ffffffcc;
            backdrop-filter: blur(10px);
            box-shadow: 0 10px 25px rgba(0,0,0,0.08);
        }

        /* ===== HEADER ===== */
        .card-header {
            background: #ffffffdd !important;
            border-bottom: 2px solid #e6e6e6 !important;
            border-radius: 14px 14px 0 0 !important;
            padding: 28px 20px !important;
        }

        .card-header h2 {
            color: #333;
            font-weight: 800;
        }

        .card-header i {
            color: #ff4d4d !important;
        }

        .card-header p {
            color: #555;
            font-size: 1.1rem;
        }

        /* ===== KHỐI CHƯA CÓ VIDEO ===== */
        .text-center i {
            opacity: 0.4;
        }

        .btn-danger {
            background: #ff4d4d;
            border: none;
            border-radius: 8px;
            font-weight: 600;
            padding: 12px 24px;
            transition: 0.25s ease;
        }

        .btn-danger:hover {
            background: #e63b3b;
        }

        /* ===== GRID VIDEO ===== */
        .card-img-top {
            height: 200px;
            object-fit: cover;
            border-radius: 9px 9px 0 0;
        }

        .hover-card {
            border-radius: 12px;
            background: #ffffff;
            transition: 0.25s ease;
            box-shadow: 0 6px 16px rgba(0,0,0,0.05);
        }

        .hover-card:hover {
            transform: translateY(-6px);
            box-shadow: 0 16px 30px rgba(0,0,0,0.12);
        }

        /* BODY VIDEO */
        .card-body {
            padding: 18px;
            border-radius: 0 0 12px 12px;
            background: #fafafa !important;
        }

        .card-title {
            font-size: 1.05rem;
            font-weight: 700;
            color: #222;
        }

        .card-body p {
            font-size: 0.9rem;
        }

        .like-date {
            font-weight: 600;
            color: #ff3b3b;
        }

        /* ===== RESPONSIVE ===== */
        @media (max-width: 576px) {
            .card-header h2 {
                font-size: 1.4rem;
            }
        }

    </style>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-10 col-xl-9">
            <div class="card">
                <!-- Header -->
                <div class="card-header text-white text-center py-4">
                    <h2 class="mb-2">
                        <i class="fas fa-heart fa-beat"></i> Video Yêu Thích Của Bạn
                    </h2>
                    <p class="mb-0 fs-5 opacity-90">
                        Xin chào <strong>${sessionScope.currentUser.fullname}</strong>!
                        Bạn đã thích
                        <strong class="text-warning">
                            <c:choose>
                                <c:when test="${not empty favorites}">${favorites.size()}</c:when>
                                <c:otherwise>0</c:otherwise>
                            </c:choose>
                        </strong> video.
                    </p>
                </div>

                <!-- Body -->
                <div class="card-body p-5">

                    <!-- Trường hợp chưa có video yêu thích -->
                    <c:if test="${empty favorites}">
                        <div class="text-center py-5">
                            <i class="fas fa-heart-broken fa-5x text-muted mb-4"></i>
                            <h3 class="text-muted">Bạn chưa thích video nào</h3>
                            <p class="text-muted lead">Hãy khám phá và nhấn <i class="fas fa-heart text-danger"></i> để lưu lại những video bạn yêu thích!</p>
                            <a href="${pageContext.request.contextPath}/home" class="btn btn-danger btn-lg px-5 mt-3">
                                <i class="fas fa-play-circle"></i> Xem Video Ngay
                            </a>
                        </div>
                    </c:if>

                    <!-- Danh sách video yêu thích -->
                    <c:if test="${not empty favorites}">
                        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
                            <c:forEach var="fav" items="${favorites}">
                                <div class="col">
                                    <div class="card h-100 border-0 shadow hover-card">
                                        <a href="${pageContext.request.contextPath}/detail?videoId=${fav.video.id}" class="text-decoration-none">
                                            <img src="${fav.video.poster}"
                                                 class="card-img-top"
                                                 alt="${fav.video.title}">
                                            <div class="card-body d-flex flex-column bg-light">
                                                <h5 class="card-title text-dark fw-bold text-truncate">
                                                        ${fav.video.title}
                                                </h5>
                                                <div class="mt-auto">
                                                    <p class="text-muted small mb-2">
                                                        <i class="fas fa-eye"></i> ${fav.video.views} lượt xem
                                                    </p>
                                                    <p class="like-date small mb-0">
                                                        <i class="fas fa-heart"></i>
                                                        Đã thích:
                                                        <fmt:formatDate value="${fav.likeDate}" pattern="dd/MM/yyyy HH:mm" />
                                                    </p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>

                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>