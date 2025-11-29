<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Báo cáo chia sẻ video</title>
    <style>
        /* =======================
    GLOBAL STYLE
 ======================= */
        body {
            font-family: "Segoe UI", sans-serif;
            margin: 0;
            padding: 40px 0;
            background: linear-gradient(120deg, #e0e7ff, #fdf2f8);
        }

        /* =======================
           TITLE
        ======================= */
        h2 {
            text-align: center;
            color: #1e293b;
            font-size: 2rem;
            margin-bottom: 25px;
            letter-spacing: 1px;
        }

        /* =======================
           TABLE WRAPPER
        ======================= */
        table {
            width: 85%;
            margin: 0 auto;
            border-collapse: separate;
            border-spacing: 0;
            background: white;
            border-radius: 18px;
            overflow: hidden;
            box-shadow: 0 10px 25px rgba(0,0,0,0.12);
        }

        /* =======================
           TABLE HEADER
        ======================= */
        th {
            background: #1e40af;
            color: #fff;
            padding: 15px;
            font-size: 1.05rem;
            font-weight: 600;
            border-bottom: 3px solid #1e3a8a;
        }

        /* =======================
           TABLE BODY
        ======================= */
        td {
            padding: 14px;
            color: #334155;
            font-size: 0.95rem;
            border-bottom: 1px solid #e2e8f0;
        }

        tr:nth-child(even) {
            background: #f8fafc;
        }

        tr:hover {
            background: #e0f2fe;
            transition: 0.2s;
        }

        /* =======================
           EMPTY MESSAGE
        ======================= */
        .empty-message {
            text-align: center;
            color: #e11d48;
            font-size: 20px;
            margin-top: 20px;
            font-weight: 600;
        }

    </style>
</head>
<body>

<h2>Báo cáo tổng hợp chia sẻ video</h2>

<table>
    <tr>
        <th>Tiêu đề video</th>
        <th>Số lượt chia sẻ</th>
        <th>Ngày chia sẻ đầu tiên</th>
        <th>Ngày chia sẻ cuối cùng</th>
    </tr>

    <c:forEach var="r" items="${report}">
        <tr>
            <td><c:out value="${r[0]}"/></td>
            <td><c:out value="${r[1]}"/></td>
            <td><c:out value="${r[2]}"/></td>
            <td><c:out value="${r[3]}"/></td>
        </tr>
    </c:forEach>
</table>

<c:if test="${empty report}">
    <p style="text-align:center; color:red; font-size:18px;">Không có dữ liệu chia sẻ!</p>
</c:if>

</body>
</html>