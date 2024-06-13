<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Employee</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #00c6ff, #0072ff);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #fff;
        }
        .container {
            background: rgba(255, 255, 255, 0.1);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            text-align: center;
            width: 100%;
            max-width: 400px;
        }
        h1 {
            margin-bottom: 20px;
            color: #fff;
        }
        label {
            display: block;
            margin: 15px 0 5px;
            color: #ddd;
        }
        input[type="text"],
        input[type="date"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            margin-bottom: 10px;
            background: rgba(255, 255, 255, 0.2);
            color: #fff;
        }
        textarea {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            margin-bottom: 10px;
            background: rgba(255, 255, 255, 0.2);
            color: #fff;
        }
        button {
            padding: 15px 25px;
            background-color: #ff4081;
            color: #fff;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #ff79a9;
        }
        #result {
            margin-top: 20px;
            color: #ffeb3b;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Add New Employee</h1>
        <form id="addEmployeeForm">
            <label for="id">ID:</label>
            <input type="text" id="id" name="id" required>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
            <label for="birthday">Birthday:</label>
            <input type="date" id="birthday" name="birthday" required>
            <label for="joinDate">Join Date:</label>
            <input type="date" id="joinDate" name="joinDate" required>
            <label for="importantNote">Important Note:</label>
            <textarea id="importantNote" name="importantNote" required></textarea>
            <label for="newJoiner">New Joiner:</label>
            <input type="text" id="newJoiner" name="newJoiner" required>
            <label for="onLeave">On Leave:</label>
            <input type="text" id="onLeave" name="onLeave" required>
            <button type="submit">Add Employee</button>
        </form>
        <div id="result"></div>
    </div>
    <script>
        document.getElementById('addEmployeeForm').addEventListener('submit', function(event) {
            event.preventDefault();
            const employee = {
                id: document.getElementById('id').value,
                name: document.getElementById('name').value,
                birthday: document.getElementById('birthday').value,
                joinDate: document.getElementById('joinDate').value,
                importantNote: document.getElementById('importantNote').value,
                newJoiner: document.getElementById('newJoiner').value.toLowerCase() === 'true',
                onLeave: document.getElementById('onLeave').value.toLowerCase() === 'true'
            };
            fetch('${pageContext.request.contextPath}/insert', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(employee)
            })
            .then(response => response.json())
            .then(data => {
                document.getElementById('result').innerHTML = 'Employee added successfully: ' + JSON.stringify(data);
            });
        });
    </script>
</body>
</html>
