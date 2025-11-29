<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tìm Kiếm Video Theo Từ Khóa</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">
    <style>
        /* =======================
   RESET + BODY
======================= */
        body {
            margin: 0;
            padding: 0;
            font-family: "Inter", sans-serif;
            background: #f0f2f5;
            min-height: 100vh;
        }

        /* =======================
           SEARCH CONTAINER
        ======================= */
        .search-container {
            background: rgba(255, 255, 255, 0.85);
            backdrop-filter: blur(12px);
            border-radius: 18px;
            padding-bottom: 25px;
            margin-top: 40px;
            border: 1px solid rgba(200, 200, 200, 0.4);
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.1);
        }

        /* =======================
           HEADER
        ======================= */
        .search-header {
            background: #111827;
            padding: 35px 20px;
            text-align: center;
            color: white;
            border-radius: 18px 18px 0 0;
        }

        .search-header h2 {
            font-size: 1.9rem;
            font-weight: 700;
        }

        .search-header form input[type="text"] {
            border: 1.8px solid #374151;
            border-radius: 10px;
            padding: 10px 15px;
            font-size: 1.05rem;
            width: 260px;
            transition: 0.25s;
            background: #f9fafb;
        }

        .search-header form input[type="text"]:focus {
            border-color: #3b82f6;
            background: white;
            box-shadow: 0 0 0 4px rgba(59,130,246,0.2);
        }

        .search-header button {
            background: #3b82f6;
            color: white;
            border-radius: 10px;
            padding: 10px 25px;
            font-weight: 600;
            border: none;
            transition: 0.2s;
        }

        .search-header button:hover {
            background: #2563eb;
            transform: translateY(-1px);
        }

        /* =======================
           TABLE STYLE
        ======================= */
        .table thead {
            background: #111827;
            color: white;
        }

        .table tbody tr {
            transition: 0.2s;
        }

        .table tbody tr:hover {
            background: #e5e7eb;
            cursor: pointer;
        }

        /* Badge lượt thích */
        .badge-like {
            background: #f43f5e !important;
            color: white;
            padding: 7px 14px;
            border-radius: 30px;
            font-size: 0.95rem;
        }

        /* =======================
           STATUS COLORS
        ======================= */
        .status-active {
            color: #059669;
            font-weight: 600;
        }

        .status-inactive {
            color: #dc2626;
            font-weight: 600;
        }

        /* =======================
           KẾT QUẢ + TRỐNG
        ======================= */
        .no-result {
            text-align: center;
            padding: 80px 20px;
        }

        .no-result i {
            font-size: 5rem;
            color: #9ca3af;
        }

        .no-result h3 {
            margin-top: 15px;
            color: #374151;
        }

        .text-center .opacity-50 {
            opacity: 0.45;
        }

    </style>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-10">
            <div class="search-container">

                <div class="search-header">
                    <h2 class="mb-3"><i class="fas fa-search fa-lg"></i> Tìm Kiếm Video</h2>
                    <form action="${pageContext.request.contextPath}/search-video" method="get" class="d-inline-flex gap-2">
                        <input type="text" name="keyword" value="${fn:escapeXml(keyword)}"
                               placeholder="Nhập từ khóa..." class="form-control" autofocus>
                        <button type="submit" class="btn"><i class="fas fa-search"></i> Tìm</button>
                    </form>
                    <c:if test="${not empty keyword}">
                        <p class="mt-3 mb-0">Kết quả cho: <strong class="text-warning">"${fn:escapeXml(keyword)}"</strong></p>
                    </c:if>
                </div>

                <div class="p-4">
                    <c:choose>
                        <c:when test="${not empty videos}">
                            <div class="table-responsive">
                                <table class="table table-hover align-middle">
                                    <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Tiêu Đề Video</th>
                                        <th>Số Lượt Thích</th>
                                        <th>Trạng Thái</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="v" items="${videos}" varStatus="stt">
                                        <tr>
                                            <td class="text-center fw-bold">${stt.count}</td>
                                            <td>
                                                <strong>${fn:escapeXml(v.title)}</strong>
                                                <br><small class="text-muted">ID: ${v.id}</small>
                                            </td>
                                            <td class="text-center">
                                                    <span class="badge badge-like">
                                                        <i class="fas fa-heart"></i>
                                                        ${v.favoriteCount}
                                                    </span>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${v.active}">
                                                        <span class="status-active"><i class="fas fa-check-circle"></i> Còn hiệu lực</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="status-inactive"><i class="fas fa-times-circle"></i> Đã ẩn</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="text-center mt-3">
                                <strong class="text-success fs-4">Tìm thấy ${videos.size()} video</strong>
                            </div>
                        </c:when>
                        <c:when test="${empty videos && keyword != null}">
                            <div class="no-result">
                                <i class="fas fa-search-minus"></i>
                                <h3>Không tìm thấy video nào</h3>
                                <p class="lead">Từ khóa "<strong>${fn:escapeXml(keyword)}</strong>" không khớp.</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="text-center py-5 text-muted">
                                <i class="fas fa-film fa-5x mb-4 opacity-50"></i>
                                <h4>Nhập từ khóa để tìm kiếm video</h4>
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