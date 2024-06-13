<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Announcements</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #ffecd2, #fcb69f);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #333;
        }
        .container {
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            text-align: center;
            width: 100%;
            max-width: 600px;
        }
        h1 {
            margin-bottom: 20px;
            color: #ff4081;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        li {
            background: rgba(0, 123, 255, 0.1);
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 10px;
            color: #007bff;
        }
        li:nth-child(even) {
            background: rgba(0, 123, 255, 0.2);
        }
        .section-title {
            color: #007bff;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Today's Announcements</h1>

        <div id="announcements">
            <h2 class="section-title">General Announcements</h2>
            <ul>
                <c:forEach var="entry" items="${announcements}">
                    <li><strong>${entry.key}:</strong> ${entry.value}</li>
                </c:forEach>
            </ul>
        </div>

        <div id="birthdays">
            <h2 class="section-title">Today's Birthdays</h2>
            <ul>
                <c:forEach var="employee" items="${birthdays}">
                    <li>${employee.name} (${employee.birthday})</li>
                </c:forEach>
            </ul>
        </div>

        <div id="newJoiners">
            <h2 class="section-title">New Joiners</h2>
            <ul>
                <c:forEach var="employee" items="${newJoiners}">
                    <li>${employee.name} (Joined on ${employee.joinDate})</li>
                </c:forEach>
            </ul>
        </div>
    </div>
</body>
</html>
