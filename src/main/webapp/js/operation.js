
function submitForm(event) {
    event.preventDefault(); // 阻止默认的表单提交行为

    // 创建一个空对象来存储表单数据
    var formData = {};

    // 获取并设置表单数据
    formData.student_id = document.getElementById('student_id').value;
    formData.name = document.getElementById('name').value;
    formData.mentor = document.getElementById('mentor').value;
    formData.nativePlace = document.getElementById('nativePlace').value;
    formData.college = document.getElementById('college').value;
    formData.major = document.getElementById('major').value;

    // 将对象转换为 JSON 字符串
    var jsonData = JSON.stringify(formData);

    // 使用 Fetch API 发送 POST 请求，但不处理响应
    // 假设 jsonData 是一个 JavaScript 对象，我们需要将其转换为 JSON 字符串
    fetch('SearchStudents.do', { // 替换为您的服务器端点
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' // 设置请求头为 application/json
        },
        body: jsonData // 发送 JSON 字符串
    })
        .then(response => {
            // 检查响应状态码，确保响应成功
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            // 响应是 JSON 格式，所以我们使用 response.json() 方法来解析它
            return response.json();
        })

        .then(data => {
            // Assuming data is an array of JSON objects
            const studentsPerPage = 10;
            let currentPage = 1;


            function displayStudents(startIndex, endIndex) {
                var tableBody=document.getElementById("studentTableBody");
                var DataDetail=document.getElementById("studentDataContainer");
                tableBody.innerHTML = ""; // Clear table content



                for (let i = startIndex; i < endIndex; i++) {
                    if (data[i]) { // Check if the data exists at index i
                        const student = data[i];
                        const row = document.createElement("tr");

                        // Create and set ID cell
                        const idCell = document.createElement("td");
                        idCell.textContent = student.id;
                        row.appendChild(idCell);

                        // Create and set name cell
                        var nameCell = document.createElement("td");
                        nameCell.textContent = student.name;
                        row.appendChild(nameCell);

                        // Create and set college cell
                        var collegeCell = document.createElement("td");
                        collegeCell.textContent = student.college;
                        row.appendChild(collegeCell);

                        // Create and set major cell
                        var majorCell = document.createElement("td");
                        majorCell.textContent = student.major;
                        row.appendChild(majorCell);

                        // Create and set mentor cell
                        var mentorCell = document.createElement("td");
                        mentorCell.textContent = student.mentor;
                        row.appendChild(mentorCell);

                        const detailButton = document.createElement("button");
                        detailButton.className = "studentdatabutton";
                        detailButton.textContent = "详细信息";

                        // 查询所得表
                        detailButton.addEventListener("click", function() {
                            DataDetail.innerHTML = "";
                            const table = document.createElement("table");

                            const rowButton = document.createElement("tr");
                            rowButton.innerHTML = `
        <td colspan="2">
            <button class="close-btn" onclick="hideCanvas()"></button>
        </td>
    `;
                            table.appendChild(rowButton);


                            const rowDetailid = document.createElement("tr");
                            rowDetailid.innerHTML = `
                                <td><b>学号:</b></td>
                                <td>${student.id}</td>
                            `;
                            table.appendChild(rowDetailid);

                            const rowDetailname = document.createElement("tr");
                            rowDetailname.innerHTML = `
                                        <td><b>姓名:</b></td>
                                        <td>${student.name}</td>
                                    `;
                            table.appendChild(rowDetailname);

                            const rowDetailgender = document.createElement("tr");
                            rowDetailgender.innerHTML = `
                                    <td><b>性别:</b></td>
                                    <td>${student.gender}</td>
                                `;
                            table.appendChild(rowDetailgender);

                            const rowDetailemail = document.createElement("tr");
                            rowDetailemail.innerHTML = `
                                    <td><b>email:</b></td>
                                    <td>${student.email}</td>
                                `;

                            const rowDetailcollege = document.createElement("tr");
                            rowDetailcollege.innerHTML = `
                                    <td><b>学院:</b></td>
                                    <td>${student.college}</td>
                                `;
                            table.appendChild(rowDetailcollege);

                            const rowDetailmajor = document.createElement("tr");
                            rowDetailmajor.innerHTML = `
                                    <td><b>专业:</b></td>
                                    <td>${student.major}</td>
                                `;
                            table.appendChild(rowDetailmajor);

                            const rowDetaildegree = document.createElement("tr");
                            rowDetaildegree.innerHTML = `
                                    <td><b>学位:</b></td>
                                    <td>${student.degree}</td>
                                `;
                            table.appendChild(rowDetaildegree);

                            const rowDetailaddress = document.createElement("tr");
                            rowDetailaddress.innerHTML = `
                                    <td><b>出生地:</b></td>
                                    <td>${student.address}</td>
                                `;
                            table.appendChild(rowDetailaddress);

                            const rowDetailnativePlace = document.createElement("tr");
                            rowDetailnativePlace.innerHTML = `
                                    <td><b>籍贯:</b></td>
                                    <td>${student.nativePlace}</td>
                                `;
                            table.appendChild(rowDetailnativePlace);

                            const rowDetailphonenumber = document.createElement("tr");
                            rowDetailphonenumber.innerHTML = `
                                    <td><b>电话:</b></td>
                                    <td>${student.phonenumber}</td>
                                `;
                            table.appendChild(rowDetailphonenumber);

                            const rowDetailtrainstart = document.createElement("tr");
                            rowDetailtrainstart.innerHTML = `
                                    <td><b>火车起始站:</b></td>
                                    <td>${student.trainstart}</td>
                                `;
                            table.appendChild(rowDetailtrainstart);

                            const rowDetailtrainend = document.createElement("tr");
                            rowDetailtrainend.innerHTML = `
                                    <td><b>火车终点站:</b></td>
                                    <td>${student.trainend}</td>
                                `;
                            table.appendChild(rowDetailtrainend);

                            const rowDetailPolicy = document.createElement("tr");
                            rowDetailPolicy.innerHTML = `
                                    <td><b>政治面貌:</b></td>
                                    <td>${student.policyStatus}</td>
                                `;
                            table.appendChild(rowDetailPolicy);

                            const rowDetailmarry = document.createElement("tr");
                            rowDetailmarry.innerHTML = `
                                    <td><b>婚姻状况:</b></td>
                                    <td>${student.marryStatus}</td>
                                `;
                            table.appendChild(rowDetailmarry);

                            DataDetail.appendChild(table);
                            DataDetail.style.display = 'block';
                        });

                        // 详细信息
                        const buttonCell = document.createElement("td");
                        buttonCell.appendChild(detailButton);
                        row.appendChild(buttonCell);
                        tableBody.appendChild(row);
                    }

                }
                const prevButton = document.createElement("button");
                prevButton.textContent = "上一个";
                prevButton.style.padding = "10px 20px";
                prevButton.style.backgroundColor = "#f44336";
                prevButton.style.color = "white";
                prevButton.style.border = "none";
                prevButton.style.textAlign = "center";
                prevButton.style.textDecoration = "none";
                prevButton.style.display = "inline-block";
                prevButton.style.fontSize = "16px";
                prevButton.style.margin = "4px 2px";
                prevButton.style.cursor = "pointer";
                prevButton.style.borderRadius = "8px";
                prevButton.addEventListener("click", function() {
                    if (currentPage > 1) {
                        currentPage--;
                        const startIndex = (currentPage - 1) * studentsPerPage;
                        const endIndex = currentPage * studentsPerPage;
                        displayStudents(startIndex, endIndex);
                    }
                });
                tableBody.appendChild(prevButton);

                // Create and append the "Next" button
                const nextButton = document.createElement("button");
                nextButton.textContent = "下一个";
                nextButton.style.padding = "10px 20px";
                nextButton.style.backgroundColor = "#4CAF50";
                nextButton.style.color = "white";
                nextButton.style.border = "none";
                nextButton.style.textAlign = "center";
                nextButton.style.textDecoration = "none";
                nextButton.style.display = "inline-block";
                nextButton.style.fontSize = "16px";
                nextButton.style.margin = "4px 2px";
                nextButton.style.cursor = "pointer";
                nextButton.style.borderRadius = "8px";
                nextButton.addEventListener("click", function() {
                    if (currentPage < Math.ceil(data.length / studentsPerPage)) {
                        currentPage++;
                        const startIndex = (currentPage - 1) * studentsPerPage;
                        const endIndex = currentPage * studentsPerPage;
                        displayStudents(startIndex, endIndex);
                    }
                });
                tableBody.appendChild(nextButton);

            }


            displayStudents(0, studentsPerPage);

        })
}


function TeachersubmitForm(event){
    event.preventDefault();
    var formData = {};

    // 获取并设置表单数据
    formData.teacher_id = document.getElementById('teacher_id').value;
    formData.Tname = document.getElementById('Tname').value;
    formData.Tcollege = document.getElementById('Tcollege').value;
    formData.status= document.getElementById('status').value;

    // 将对象转换为 JSON 字符串
    var jsonData = JSON.stringify(formData);

    // 使用 Fetch API 发送 POST 请求，但不处理响应
    // 假设 jsonData 是一个 JavaScript 对象，我们需要将其转换为 JSON 字符串
    fetch('SearchTeacher.do', { // 替换为您的服务器端点
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' // 设置请求头为 application/json
        },
        body: jsonData // 发送 JSON 字符串
    })
        .then(response => {
            // 检查响应状态码，确保响应成功
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            // 响应是 JSON 格式，所以我们使用 response.json() 方法来解析它
            return response.json();
        })

        .then(data => {
            // Assuming data is an array of JSON objects
            const teachersPerPage = 10;
            let currentPage = 1;


            function displayTeacher(startIndex, endIndex) {
                var tableBody=document.getElementById("teacherTableBody");
                var DataDetail=document.getElementById("teacherDataContainer");
                tableBody.innerHTML = ""; // Clear table content



                for (let i = startIndex; i < endIndex; i++) {
                    if (data[i]) { // Check if the data exists at index i
                        const teacher = data[i];
                        const row = document.createElement("tr");

                        // Create and set ID cell
                        const idCell = document.createElement("td");
                        idCell.textContent = teacher.id;
                        row.appendChild(idCell);

                        // Create and set name cell
                        var nameCell = document.createElement("td");
                        nameCell.textContent = teacher.name;
                        row.appendChild(nameCell);

                        // Create and set college cell
                        var collegeCell = document.createElement("td");
                        collegeCell.textContent = teacher.college;
                        row.appendChild(collegeCell);

                        // Create and set major cell
                        var statusCell = document.createElement("td");
                        statusCell.textContent = teacher.status;
                        row.appendChild(statusCell);

                        const detailButton = document.createElement("button");
                        detailButton.className = "teacherdatabutton";
                        detailButton.textContent = "详细信息";

                        // 查询所得表
                        detailButton.addEventListener("click", function() {
                            DataDetail.innerHTML = "";
                            const table = document.createElement("table");

                            const rowButton = document.createElement("tr");
                            rowButton.innerHTML = `
        <td colspan="2">
            <button class="close-btn" onclick="hideCanvasteacher()"></button>
        </td>
    `;
                            table.appendChild(rowButton);


                            const rowDetailid = document.createElement("tr");
                            rowDetailid.innerHTML = `
                                <td><b>教师编号:</b></td>
                                <td>${teacher.id}</td>
                            `;
                            table.appendChild(rowDetailid);

                            const rowDetailname = document.createElement("tr");
                            rowDetailname.innerHTML = `
                                        <td><b>姓名:</b></td>
                                        <td>${teacher.name}</td>
                                    `;
                            table.appendChild(rowDetailname);

                            const rowDetailgender = document.createElement("tr");
                            rowDetailgender.innerHTML = `
                                    <td><b>性别:</b></td>
                                    <td>${teacher.gender}</td>
                                `;
                            table.appendChild(rowDetailgender);

                            const rowDetailemail = document.createElement("tr");
                            rowDetailemail.innerHTML = `
                                    <td><b>email:</b></td>
                                    <td>${teacher.email}</td>
                                `;

                            const rowDetailcollege = document.createElement("tr");
                            rowDetailcollege.innerHTML = `
                                    <td><b>学院:</b></td>
                                    <td>${teacher.college}</td>
                                `;
                            table.appendChild(rowDetailcollege);

                            const rowDetailmajor = document.createElement("tr");
                            rowDetailmajor.innerHTML = `
                                    <td><b>授课专业:</b></td>
                                    <td>${teacher.major}</td>
                                `;
                            table.appendChild(rowDetailmajor);

                            const rowDetailaddress = document.createElement("tr");
                            rowDetailaddress.innerHTML = `
                                    <td><b>出生地:</b></td>
                                    <td>${teacher.address}</td>
                                `;
                            table.appendChild(rowDetailaddress);

                            const rowDetailnativePlace = document.createElement("tr");
                            rowDetailnativePlace.innerHTML = `
                                    <td><b>籍贯:</b></td>
                                    <td>${teacher.nativePlace}</td>
                                `;
                            table.appendChild(rowDetailnativePlace);

                            const rowDetailphonenumber = document.createElement("tr");
                            rowDetailphonenumber.innerHTML = `
                                    <td><b>电话:</b></td>
                                    <td>${teacher.phonenumber}</td>
                                `;
                            table.appendChild(rowDetailphonenumber);

                            const rowDetailtrainstart = document.createElement("tr");
                            rowDetailtrainstart.innerHTML = `
                                    <td><b>火车起始站:</b></td>
                                    <td>${teacher.trainstart}</td>
                                `;
                            table.appendChild(rowDetailtrainstart);

                            const rowDetailtrainend = document.createElement("tr");
                            rowDetailtrainend.innerHTML = `
                                    <td><b>火车终点站:</b></td>
                                    <td>${teacher.trainend}</td>
                                `;
                            table.appendChild(rowDetailtrainend);

                            const rowDetailPolicy = document.createElement("tr");
                            rowDetailPolicy.innerHTML = `
                                    <td><b>政治面貌:</b></td>
                                    <td>${teacher.policyStatus}</td>
                                `;
                            table.appendChild(rowDetailPolicy);

                            const rowDetailmarry = document.createElement("tr");
                            rowDetailmarry.innerHTML = `
                                    <td><b>婚姻状况:</b></td>
                                    <td>${teacher.marryStatus}</td>
                                `;
                            table.appendChild(rowDetailmarry);

                            const rowDetailstatus = document.createElement("tr");
                            rowDetailstatus.innerHTML = `
                                    <td><b>职务:</b></td>
                                    <td>${teacher.status}</td>
                                `;
                            table.appendChild(rowDetailstatus);

                            DataDetail.appendChild(table);
                            DataDetail.style.display = 'block';
                        });

                        // 详细信息
                        const buttonCell = document.createElement("td");
                        buttonCell.appendChild(detailButton);
                        row.appendChild(buttonCell);
                        tableBody.appendChild(row);
                    }

                }
                const prevButton = document.createElement("button");
                prevButton.textContent = "上一个";
                prevButton.style.padding = "10px 20px";
                prevButton.style.backgroundColor = "#f44336";
                prevButton.style.color = "white";
                prevButton.style.border = "none";
                prevButton.style.textAlign = "center";
                prevButton.style.textDecoration = "none";
                prevButton.style.display = "inline-block";
                prevButton.style.fontSize = "16px";
                prevButton.style.margin = "4px 2px";
                prevButton.style.cursor = "pointer";
                prevButton.style.borderRadius = "8px";
                prevButton.addEventListener("click", function() {
                    if (currentPage > 1) {
                        currentPage--;
                        const startIndex = (currentPage - 1) * studentsPerPage;
                        const endIndex = currentPage * studentsPerPage;
                        displayStudents(startIndex, endIndex);
                    }
                });
                tableBody.appendChild(prevButton);

                // Create and append the "Next" button
                const nextButton = document.createElement("button");
                nextButton.textContent = "下一个";
                nextButton.style.padding = "10px 20px";
                nextButton.style.backgroundColor = "#4CAF50";
                nextButton.style.color = "white";
                nextButton.style.border = "none";
                nextButton.style.textAlign = "center";
                nextButton.style.textDecoration = "none";
                nextButton.style.display = "inline-block";
                nextButton.style.fontSize = "16px";
                nextButton.style.margin = "4px 2px";
                nextButton.style.cursor = "pointer";
                nextButton.style.borderRadius = "8px";
                nextButton.addEventListener("click", function() {
                    if (currentPage < Math.ceil(data.length / teachersPerPage)) {
                        currentPage++;
                        const startIndex = (currentPage - 1) * teachersPerPage;
                        const endIndex = currentPage * teachersPerPage;
                        displayTeacher(startIndex, endIndex);
                    }
                });
                tableBody.appendChild(nextButton);

            }


            displayTeacher(0, teachersPerPage);

        })


}
function showContent(targetId) {
    // 隐藏所有的center_bar
    var centerBars = document.getElementsByClassName('center_bar');
    for (var i = 0; i < centerBars.length; i++) {
        centerBars[i].style.display = 'none';
    }

    // 显示指定的center_bar
    var target = document.getElementById(targetId);
    if (target) {
        target.style.display = 'block';
    }
}
function hideCanvas() {
    var container = document.getElementById('studentDataContainer');
    container.style.display = 'none';
}

function hideCanvasMentor() {
    var container = document.getElementById('MentorDetail');
    container.style.display = 'none';
}
function hideCanvasteacher() {
    var container = document.getElementById('teacherDataContainer');
    container.style.display = 'none';
}
function mentorQuary(){
    //var teacherid = teacher.id; // 假设 teacher.id 包含了期望的值
    //const data = { teacherid: teacherid };
    fetch('mentorQuary.do', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        //body: JSON.stringify(data)
    })

.then(response => response.json())
            .then(data => {
                function MentorShow(){
                    var tableBody=document.getElementById("MentorBody");
                    var DataDetail=document.getElementById("MentorDetail");
                    tableBody.innerHTML = ""; // Clear table content
                    for (var i=0;i<data.length;i++){
                        if (data[i]) { // Check if the data exists at index i
                            const student = data[i];
                            const row = document.createElement("tr");

                            // Create and set ID cell
                            const idCell = document.createElement("td");
                            idCell.textContent = student.id;
                            row.appendChild(idCell);

                            // Create and set name cell
                            var nameCell = document.createElement("td");
                            nameCell.textContent = student.name;
                            row.appendChild(nameCell);

                            // Create and set college cell
                            var collegeCell = document.createElement("td");
                            collegeCell.textContent = student.college;
                            row.appendChild(collegeCell);

                            // Create and set major cell
                            var statusCell = document.createElement("td");
                            statusCell.textContent = student.email;
                            row.appendChild(statusCell);

                            const detailButton = document.createElement("button");
                            detailButton.className = "teacherdatabutton";
                            detailButton.textContent = "详细信息";

                            // 查询所得表
                            detailButton.addEventListener("click", function() {
                                DataDetail.innerHTML = "";
                                const table = document.createElement("table");

                                const rowButton = document.createElement("tr");
                                rowButton.innerHTML = `
        <td colspan="2">
            <button class="close-btn" onclick="hideCanvasMentor()"></button>
        </td>
    `;
                                table.appendChild(rowButton);


                                const rowDetailid = document.createElement("tr");
                                rowDetailid.innerHTML = `
                                <td><b>学号:</b></td>
                                <td>${student.id}</td>
                            `;
                                table.appendChild(rowDetailid);

                                const rowDetailname = document.createElement("tr");
                                rowDetailname.innerHTML = `
                                        <td><b>姓名:</b></td>
                                        <td>${student.name}</td>
                                    `;
                                table.appendChild(rowDetailname);

                                const rowDetailgender = document.createElement("tr");
                                rowDetailgender.innerHTML = `
                                    <td><b>性别:</b></td>
                                    <td>${student.gender}</td>
                                `;
                                table.appendChild(rowDetailgender);

                                const rowDetailemail = document.createElement("tr");
                                rowDetailemail.innerHTML = `
                                    <td><b>email:</b></td>
                                    <td>${student.email}</td>
                                `;

                                const rowDetailcollege = document.createElement("tr");
                                rowDetailcollege.innerHTML = `
                                    <td><b>学院:</b></td>
                                    <td>${student.college}</td>
                                `;
                                table.appendChild(rowDetailcollege);

                                const rowDetailmajor = document.createElement("tr");
                                rowDetailmajor.innerHTML = `
                                    <td><b>专业:</b></td>
                                    <td>${student.major}</td>
                                `;
                                table.appendChild(rowDetailmajor);

                                const rowDetaildegree = document.createElement("tr");
                                rowDetaildegree.innerHTML = `
                                    <td><b>学位:</b></td>
                                    <td>${student.degree}</td>
                                `;
                                table.appendChild(rowDetaildegree);

                                const rowDetailaddress = document.createElement("tr");
                                rowDetailaddress.innerHTML = `
                                    <td><b>出生地:</b></td>
                                    <td>${student.address}</td>
                                `;
                                table.appendChild(rowDetailaddress);

                                const rowDetailnativePlace = document.createElement("tr");
                                rowDetailnativePlace.innerHTML = `
                                    <td><b>籍贯:</b></td>
                                    <td>${student.nativePlace}</td>
                                `;
                                table.appendChild(rowDetailnativePlace);

                                const rowDetailphonenumber = document.createElement("tr");
                                rowDetailphonenumber.innerHTML = `
                                    <td><b>电话:</b></td>
                                    <td>${student.phonenumber}</td>
                                `;
                                table.appendChild(rowDetailphonenumber);

                                const rowDetailtrainstart = document.createElement("tr");
                                rowDetailtrainstart.innerHTML = `
                                    <td><b>火车起始站:</b></td>
                                    <td>${student.trainstart}</td>
                                `;
                                table.appendChild(rowDetailtrainstart);

                                const rowDetailtrainend = document.createElement("tr");
                                rowDetailtrainend.innerHTML = `
                                    <td><b>火车终点站:</b></td>
                                    <td>${student.trainend}</td>
                                `;
                                table.appendChild(rowDetailtrainend);

                                const rowDetailPolicy = document.createElement("tr");
                                rowDetailPolicy.innerHTML = `
                                    <td><b>政治面貌:</b></td>
                                    <td>${student.policyStatus}</td>
                                `;
                                table.appendChild(rowDetailPolicy);

                                const rowDetailmarry = document.createElement("tr");
                                rowDetailmarry.innerHTML = `
                                    <td><b>婚姻状况:</b></td>
                                    <td>${student.marryStatus}</td>
                                `;
                                table.appendChild(rowDetailmarry);

                                DataDetail.appendChild(table);
                                DataDetail.style.display = 'block';
                            });

                            // 详细信息
                            const buttonCell = document.createElement("td");
                            buttonCell.appendChild(detailButton);
                            row.appendChild(buttonCell);
                            tableBody.appendChild(row);
                        }

                    }
                }
                MentorShow();
            })


            .catch(error => {
                console.error('发生错误:', error);
            });
}