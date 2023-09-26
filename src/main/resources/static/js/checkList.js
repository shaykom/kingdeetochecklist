
// var numericInput = document.getElementById("numericInput");
// var virtualKeyboard = document.getElementById("virtualKeyboard");
// var editableTable = document.getElementById("editable-table");
var tbody = document.getElementById("table-body");
var addRow = document.getElementById("addRow");
var deleteRow = document.getElementById("deleteRow");
var code = "";
var orgNumber = "";
var unitNumber = "";
var billTypeNumber = "";
var supplierNumber = "";
var auxPropId = "";
var lotNumber = "";
var extAuxUnitNumber = "";
var qty6 = "";
var baseUnitNumber = "";
var actReceiveQty = "";
var barCode = document.getElementById("barCode");
var popupCounter = 0;
var checker = document.getElementById("checker");
var receiveFid = "";
var receiveFEntryId = "";

barCode.addEventListener("keydown", function(event) {
    if (event.key === "Enter" && !event.shiftKey) {
        event.preventDefault(); // 阻止默认的换行行为
        // 在这里执行你想要触发的事件
        selectBarCode();
    }
});

function checkEnter(event, barCode) {
    if (event.key === "Enter") {
        event.preventDefault(); // 阻止默认的换行行为
        // 在这里执行你想要触发的事件
        var dataBody = document.getElementById("table-body");
        var lastRow = dataBody.rows[dataBody.rows.length - 1];
        var seq = lastRow.cells[1].textContent;
        createRows(seq, barCode);
    }
}

function selectBarCode(){
    var barCode = document.getElementById("barCode");

    if(barCode.value !== "") {
        $.ajax({
            type: "get",
            url: "check/barCode",
            data: {
                param: barCode.value
            },
            success: function (data) {
                if (data.length > 0) {
                    /*这个方法里是ajax发送请求成功之后执行的代码*/
                    showData(data);
                    code = barCode.value;
                } else {
                    alert("该条码不存在！");
                }
            },
            error: function (msg) {
                alert("ajax连接异常：" + msg);
            }
        });
    }
}
var isClick = true;
function saveCheckList(){
    if(isClick) {
    //定时器
        isClick = false;
        setTimeout(function() {
            isClick = true;
        }, 3000);//3秒内不能重复点击
    }else{
        alert("请不要短时间内重复点击按钮")
        return;
    }
    var barCode = document.getElementById("barCode");
    var checkListArray = [];

    // 遍历每一行
    $("#table-body tr").each(function() {
        var meter = $(this).find("td:eq(0)").text();
        var seq = $(this).find("td:eq(1)").text();
        var barCode = $(this).find("td:eq(2)").text();
        var note = $(this).find("td:eq(3)").text();

        checkListArray.push({
            meter: meter,
            seq: seq,
            barCode: barCode,
            note: note
        });
    });

    var checkNumber = $("#checkNumber").val();

    var checkList = {
        materialNumber: $("#materialNumber").val(),
        materialName: $("#materialName").val(),
        checkDate: $("#checkDate").val(),
        specification: $("#doorWidth").val(),
        isPass: $("#isPass").val(),
        orgNumber: orgNumber,
        unitNumber: unitNumber,
        billTypeNumber: billTypeNumber,
        supplierNumber: supplierNumber,
        auxPropId: auxPropId,
        lotNumber: lotNumber,
        extAuxUnitNumber: extAuxUnitNumber,
        qty6: qty6,
        baseUnitNumber: baseUnitNumber,
        actReceiveQty: actReceiveQty,
        bagNumber: $("#bagNumber").val(),
        bagNote: $("#bagProblem").val(),
        sourceBillNo: $("#sourceBillNo").val(),
        checkerNumber: $("#checker").val(),
        fid: checkNumber,
        receiveFid: receiveFid,
        receiveFEntryId: receiveFEntryId,
        checkListArray: checkListArray
    };

    if(barCode.value !== "" || checkNumber !== "") {
        $.ajax({
            type: "post",
            url: "check/checkList",
            data: JSON.stringify(checkList),
            contentType: "application/json",
            success: function (data) {
                if (data["Result"]["ResponseStatus"]["IsSuccess"] === true) {
                    $("#checkNumber").val(data["Result"]["ResponseStatus"]["SuccessEntitys"][0]["Id"]);
                    /*这个方法里是ajax发送请求成功之后执行的代码*/
                    alert("上传成功！" + data["Result"]["ResponseStatus"]["SuccessEntitys"][0]["Number"]);
                } else {
                    var errors = data["Result"]["ResponseStatus"]["Errors"][0]["Message"]
                    alert(errors);
                }
            },
            error: function (msg) {
                alert("ajax连接异常：" + msg);
            }
        });
    }
}

function showData(data) {
    $("#checkNumber").val("自动生成");
    $("#sourceBillNo").val(data[0][0]);
    $("#supplier").val(data[0][1]);
    $("#materialNumber").val(data[0][2]);
    $("#materialName").val(data[0][3]);
    $("#doorWidth").val(data[0][5]);
    $("#checker").val("");
    $("#checkDate").val(formatDate(new Date(),"yyyy-MM-dd"));
    orgNumber = data[0][6];
    unitNumber = data[0][7];
    billTypeNumber = data[0][8];
    supplierNumber = data[0][9];
    auxPropId = data[0][10];
    lotNumber = data[0][11];
    extAuxUnitNumber = data[0][12];
    qty6 = data[0][13];
    baseUnitNumber = data[0][14];
    actReceiveQty = data[0][15];
    receiveFid = data[0][16];
    receiveFEntryId = data[0][17];
    // for (var i = 0; i < data.length; i++) {
    //     var row = tbody.insertRow();
    //     var msCell = row.insertCell(0);
    //     msCell.contentEditable = "true";
    //     var jhCell = row.insertCell(1);
    //     var tmCell = row.insertCell(2);
    //     var wtCell = row.insertCell(3);
    //     wtCell.contentEditable = "true";
    //
    //     tmCell.innerHTML = data[i][4];
    //     msCell.innerHTML = data[i][5];
    //     // makeEditable('editable-table', {
    //     //     editableColumns: [1, 4] // 米数和问题列可编辑
    //     // });
    // }
}


function showCheckListData(data) {
    $("#checkNumber").val("自动生成");
    $("#sourceBillNo").val(data[0][0]);
    // $("#supplier").val(data[0][1]);
    // $("#materialNumber").val(data[0][2]);
    // $("#materialName").val(data[0][3]);
    // $("#doorWidth").val(data[0][5]);
    // $("#checker").val("");
    // $("#checkDate").val(formatDate(new Date(),"yyyy-MM-dd"));
    // orgNumber = data[0][6];
    // unitNumber = data[0][7];
    // billTypeNumber = data[0][8];
    // supplierNumber = data[0][9];
    // auxPropId = data[0][10];
    // lotNumber = data[0][11];
    // extAuxUnitNumber = data[0][12];
    // qty6 = data[0][13];
    // baseUnitNumber = data[0][14];
    // actReceiveQty = data[0][15];
    // for (var i = 0; i < data.length; i++) {
    //     var row = tbody.insertRow();
    //     var msCell = row.insertCell(0);
    //     msCell.contentEditable = "true";
    //     var jhCell = row.insertCell(1);
    //     var tmCell = row.insertCell(2);
    //     var wtCell = row.insertCell(3);
    //     wtCell.contentEditable = "true";
    //
    //     tmCell.innerHTML = data[i][4];
    //     msCell.innerHTML = data[i][5];
    //     // makeEditable('editable-table', {
    //     //     editableColumns: [1, 4] // 米数和问题列可编辑
    //     // });
    // }
}

function clearData() {
    $("#barCode").val("");
    $("#isPass").val("合格");
    $("#checkNumber").val("");
    $("#materialNumber").val("");
    $("#materialName").val("");
    $("#doorWidth").val("");
    // $("#checker").val("");
    $("#difficulty").val("一级");
    $("#bagNumber").val("");
    $("#bagProblem").val("");
    $("#sourceBillNo").val("");
    $("#checkDate").val("");
    $("#supplier").val("");
    orgNumber = "";
    unitNumber = "";
    tbody.innerHTML = "";
    code = "";
    billTypeNumber = "";
    supplierNumber = "";
    auxPropId = "";
    lotNumber = "";
    extAuxUnitNumber = "";
    qty6 = "";
    baseUnitNumber = "";
    actReceiveQty = "";
    receiveFid = "";
    receiveFEntryId = "";
}

function createRows(seq, barCode) {
    // 创建新行
    var newRow = document.createElement("tr");
    newRow.className = "height30";

    // 创建新单元格并设置内容
    var cell1 = document.createElement("td");
    // cell1.className = "editable";
    cell1.contentEditable = "true";
    cell1.textContent = "";
    cell1.onkeydown = function(event) {
        checkEnter(event, barCode);
    }
    // 将焦点设置在新单元格中
    setTimeout(function() {
        cell1.focus();
    }, 0);
    newRow.appendChild(cell1);

    var cell2 = document.createElement("td");
    cell2.textContent = (parseInt(seq) + 1).toString();
    newRow.appendChild(cell2);

    var cell3 = document.createElement("td");
    cell3.textContent = barCode + "-" + (parseInt(seq) + 1).toString();
    newRow.appendChild(cell3);

    var cell4 = document.createElement("td");
    // cell4.className = "editable";
    cell4.contentEditable = "true";
    cell4.textContent = "";
    cell4.onkeydown = function(event) {
        checkEnter(event, barCode);
    }
    newRow.appendChild(cell4);

    // 将新行添加到tbody中
    tbody.appendChild(newRow);

    // makeEditable('editable-table', {
    //     editableColumns: [1, 4] // 米数和问题列可编辑
    // });
}

addRow.addEventListener("click", function() {
    var seq = "0";
    var barCode = document.getElementById("barCode").value;
    if(code !== "")
    {
        barCode = code;
    }
    var dataBody = document.getElementById("table-body");

    if(dataBody.rows.length > 0) {
        var lastRow = dataBody.rows[dataBody.rows.length - 1];
        seq = lastRow.cells[1].textContent;
        createRows(seq, barCode);
    }

    if(dataBody.rows.length === 0) {
        if (barCode !== "") {
            $.ajax({
                type: "get",
                url: "check/selectCheckList",
                data: {
                    param: barCode
                },
                success: function (data) {
                    if (data.length > 0) {
                        /*这个方法里是ajax发送请求成功之后执行的代码*/
                        seq = data[0][0];
                        createRows(seq, barCode);
                    }
                    else
                    {
                        createRows(seq, barCode);
                    }
                }
            });
        }
        else {
            createRows(seq, barCode);
        }
    }
});

deleteRow.addEventListener("click", function() {
    // var rows = document.querySelectorAll("tbody tr");
    //
    // rows.forEach(function(row) {
    //     row.addEventListener("click", function() {
    //         // 移除其他行的焦点样式
    //         rows.forEach(function(r) {
    //             r.classList.remove("focused-row");
    //         });
    //
    //         // 添加焦点样式到当前行
    //         row.classList.add("focused-row");
    //     });
    // });
    // var focusedRow = document.querySelector(".focused-row");
    // if (focusedRow) {
    //     focusedRow.remove(); // 从DOM中删除焦点行
    // }
    var dataBody = document.getElementById("table-body");
    var rowCount = dataBody.rows.length;
    dataBody.deleteRow(rowCount - 1);
});


// numericInput.addEventListener("focus", function() {
//     virtualKeyboard.style.display = "block";
// });

// // 在虚拟键盘按钮上添加点击事件处理程序
// virtualKeyboard.addEventListener("click", function(event) {
//     if (event.target.tagName === "BUTTON") {
//         if(event.target.textContent !== 'X') {
//             numericInput.value += event.target.textContent;
//         }
//         else
//         {
//             var currentText = numericInput.value;
//             if (currentText.length > 0) {
//                 numericInput.value = currentText.slice(0, -1); // 删除最后一个字符
//             }
//         }
//     }
// });

var printButton = document.getElementById("printButton");

printButton.addEventListener("click", function() {
    window.print(); // 调用浏览器的打印功能
});


// function makeEditable(tableId, config) {
//     // 默认配置
//     const defaultConfig = {
//         editableColumns: [1,4], // 可编辑列的下标（从 0 开始）
//     };
//     // 合并配置
//     const mergedConfig = Object.assign(defaultConfig, config);
//     // 获取表格元素
//     const table = document.getElementById(tableId);
//     // 获取表体
//     const tbody = table.querySelector('tbody');
//     // 获取所有可编辑单元格
//     const editableCells = Array.from(tbody.querySelectorAll('.editable'));
//     // 为每个可编辑单元格添加 click 事件监听器
//     editableCells.forEach(cell => {
//         cell.addEventListener('click', () => {
//             // 获取单元格中的原数据
//             const originalData = cell.textContent;
//             // 创建一个文本框
//             const input = document.createElement('input');
//             // 设置文本框的初始值为原数据
//             input.value = originalData;
//             // 将文本框插入到单元格中
//             cell.textContent = '';
//             cell.appendChild(input);
//             // 为文本框添加 blur 事件监听器
//             input.addEventListener('blur', () => {
//                 // 获取文本框中的新数据
//                 const newData = input.value;
//                 // 获取单元格的下标
//                 const cellIndex = Array.from(cell.parentElement.children).indexOf(cell);
//                 // 判断单元格是否可编辑
//                 if (mergedConfig.editableColumns.includes(cellIndex)) {
//                     cell.textContent = newData;
//                 } else {
//                     // 单元格不可编辑，直接更新数据
//                     cell.textContent = newData;
//                 }
//             });
//             // 让文本框获得焦点
//             input.focus();
//         });
//     });
// }

/**
 * 格式化函数 ， 给日期格式化
 * date为 new Date()对象， fmt为 'yyyy-MM-dd hh:mm:ss'的格式
 */
function formatDate(date, fmt) {
    //获取年份
    if (/(y+)/.test(fmt)) {
        // 把数字变成字符串
        let dateY = date.getFullYear() + "";
        //RegExp.$1 在判断中出现过，且是括号括起来的，所以 RegExp.$1 就是 "yyyy"
        fmt = fmt.replace(RegExp.$1, dateY.substr(4 - RegExp.$1.length));
    }

    //获取其他
    let o = {
        "M+": date.getMonth() + 1,
        "d+": date.getDate(),
        "h+": date.getHours(),
        "m+": date.getMinutes(),
        "s+": date.getSeconds(),
    };
    for (const k in o) {
        if (new RegExp(`(${k})`).test(fmt)) {
            let str = o[k] + "";
            fmt = fmt.replace(
                RegExp.$1,
                RegExp.$1.length == 1 ? str : padLeftZero(str)
            );
        }
    }
    return fmt;
}

function padLeftZero(str) {
    return ("00" + str).substr(str.length);
}

var openHistoryButton = document.getElementById("openHistoryButton");

// 模拟历史记录数据
var historyRecords = [
    { id: 1, details: ["Details for Entry 1"] },
    { id: 2, details: ["Details for Entry 2"] }
    // ...
];

// 绑定按钮点击事件
openHistoryButton.addEventListener("click", function() {
    openHistoryPopup();
});

var popup = null; // 用于存储当前弹出窗口的引用

// 打开金蝶检验单弹出框
function openHistoryPopup() {
    if (popup !== null && !popup.closed) {
        popup.close();
        popup = null; // 将窗口引用置为空
    }

    popupCounter++;
    var left = (window.innerWidth - 800) / 2; // 800是新窗口的宽度
    var top = (window.innerHeight - 600) / 2; // 600是新窗口的高度
    popup = window.open('URL', '金蝶检验单' + popupCounter, 'width=800, height=600, left=' + left + ', top=' + top);
    var popupDocument = popup.document;

    popupDocument.write("<h1>" +
        "<meta charset=\"UTF-8\">\n" +
        "    <meta name=\"viewport\"\n" +
        "          content=\"maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0\"/>\n" +
        "    <meta name=\"format-detection\" content=\"telephone=no, email=no, date=no, address=no\">\n" +
        "    <meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n" +
        "    <meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">\n" +
        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n" +
        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
        "    <meta name=\"format-detection\" content=\"telephone=no\"/>\n" +
        "    <meta name=\"apple-mobile-web-app-capable\" content=\"yes\"/>\n" +
        "    <meta content=\"black\" name=\"apple-mobile-web-app-status-bar-style\">\n" +
        "    <link href=\"/css/bksystem.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
        "    <link href=\"/font/iconfont.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
        "    <link href=\"/css/module.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
        "    <link href=\"/css/pages.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
        "    <title>白坯检验</title>\n" +
        "    <script src=\"/js/jquery-1.9.1.min.js\" type=\"text/javascript\"></script>\n" +
        "    <script src=\"/js/jquery.nicescroll.js\" type=\"text/javascript\"></script>\n" +
        "    <script src=\"/js/HUpages.js\" type=\"text/javascript\"></script>\n" +
        "    <script src=\"/js/common.js\" type=\"text/javascript\"></script>" +
        "</h1>");

    var buttonDiv = popupDocument.createElement("div");
    buttonDiv.className = "operation mb15";

    var buttonSubmit = popupDocument.createElement("button");
    buttonSubmit.className = "btn button_btn btn-green margin";
    buttonSubmit.type = "button";
    buttonSubmit.textContent = "提交";
    buttonSubmit.addEventListener("click", function () {
        var deepBlueRows = popupDocument.querySelectorAll(".clicked"); // 获取具有深蓝色类的所有行
        if (deepBlueRows.length > 0) {
            // 如果有深蓝色行
            if(deepBlueRows[1].textContent === "创建" || deepBlueRows[1].textContent === "重新审核")
            {
                $.ajax({
                    type: "get",
                    url: "check/submitCheckList",
                    data: {
                        param: deepBlueRows[0].textContent
                    },
                    success: function (data) {
                        if (data["Result"]["ResponseStatus"]["IsSuccess"] === true) {
                            /*这个方法里是ajax发送请求成功之后执行的代码*/
                            popup.alert("提交成功！");
                            deepBlueRows[1].textContent = "审核中";
                        } else {
                            var errors = data["Result"]["ResponseStatus"]["Errors"][0]["Message"]
                            popup.alert(errors);
                        }
                    },
                    error: function (msg) {
                        popup.alert("ajax连接异常：" + msg);
                    }
                });
            }
            else {
                popup.alert("只有创建或重新审核的单据才可以提交！");
            }

        } else {
            // 如果没有深蓝色行
            popup.alert("请选择行");
        }
    });

    var buttonAudit = popupDocument.createElement("button");
    buttonAudit.className = "btn button_btn btn-green margin";
    buttonAudit.type = "button";
    buttonAudit.textContent = "审核";
    buttonAudit.addEventListener("click", function () {
        var deepBlueRows = popupDocument.querySelectorAll(".clicked"); // 获取具有深蓝色类的所有行
        if (deepBlueRows.length > 0) {
            // 如果有深蓝色行
            if(deepBlueRows[1].textContent === "审核中")
            {
                $.ajax({
                    type: "get",
                    url: "check/auditCheckList",
                    data: {
                        param: deepBlueRows[0].textContent
                    },
                    success: function (data) {
                        if (data["Result"]["ResponseStatus"]["IsSuccess"] === true) {
                            /*这个方法里是ajax发送请求成功之后执行的代码*/
                            popup.alert("审核成功！");
                            deepBlueRows[1].textContent = "已审核";
                        } else {
                            var errors = data["Result"]["ResponseStatus"]["Errors"][0]["Message"]
                            popup.alert(errors);
                        }
                    },
                    error: function (msg) {
                        popup.alert("ajax连接异常：" + msg);
                    }
                });
            }
            else {
                popup.alert("只有审核中的单据才可以审核！");
            }

        } else {
            // 如果没有深蓝色行
            popup.alert("请选择行");
        }
    });

    var buttonUnAudit = popupDocument.createElement("button");
    buttonUnAudit.className = "btn button_btn btn-danger margin";
    buttonUnAudit.type = "button";
    buttonUnAudit.textContent = "反审核";
    buttonUnAudit.addEventListener("click", function () {
        var deepBlueRows = popupDocument.querySelectorAll(".clicked"); // 获取具有深蓝色类的所有行
        if (deepBlueRows.length > 0) {
            // 如果有深蓝色行
            if(deepBlueRows[1].textContent === "审核中" || deepBlueRows[1].textContent === "已审核")
            {
                $.ajax({
                    type: "get",
                    url: "check/unAuditCheckList",
                    data: {
                        param: deepBlueRows[0].textContent
                    },
                    success: function (data) {
                        if (data["Result"]["ResponseStatus"]["IsSuccess"] === true) {
                            /*这个方法里是ajax发送请求成功之后执行的代码*/
                            popup.alert("反审核成功！");
                            deepBlueRows[1].textContent = "重新审核";
                        } else {
                            var errors = data["Result"]["ResponseStatus"]["Errors"][0]["Message"]
                            popup.alert(errors);
                        }
                    },
                    error: function (msg) {
                        popup.alert("ajax连接异常：" + msg);
                    }
                });
            }
            else {
                popup.alert("只有审核中或已审核的单据才可以反审核！");
            }

        } else {
            // 如果没有深蓝色行
            popup.alert("请选择行");
        }
    });

    var buttonDelete = popupDocument.createElement("button");
    buttonDelete.className = "btn button_btn btn-danger margin";
    buttonDelete.type = "button";
    buttonDelete.textContent = "删除";
    buttonDelete.addEventListener("click", function () {
        var deepBlueRows = popupDocument.querySelectorAll(".clicked"); // 获取具有深蓝色类的所有行
        if (deepBlueRows.length > 0) {
            // 如果有深蓝色行
            if(deepBlueRows[1].textContent === "暂存" || deepBlueRows[1].textContent === "创建" || deepBlueRows[1].textContent === "重新审核")
            {
                $.ajax({
                    type: "get",
                    url: "check/deleteCheckList",
                    data: {
                        param: deepBlueRows[0].textContent
                    },
                    success: function (data) {
                        if (data["Result"]["ResponseStatus"]["IsSuccess"] === true) {
                            /*这个方法里是ajax发送请求成功之后执行的代码*/
                            popup.alert("删除成功！");
                            deepBlueRows[1].textContent = "已删除";
                        } else {
                            var errors = data["Result"]["ResponseStatus"]["Errors"][0]["Message"]
                            popup.alert(errors);
                        }
                    },
                    error: function (msg) {
                        popup.alert("ajax连接异常：" + msg);
                    }
                });
            }
            else {
                popup.alert("只有暂存、创建、重新审核的单据才可以删除！");
            }

        } else {
            // 如果没有深蓝色行
            popup.alert("请选择行");
        }
    });

    var pageDiv = popupDocument.createElement("div");
    pageDiv.className = "pages-style";

    var tableDiv = popupDocument.createElement("div");
    tableDiv.className = "page_content clearfix mb15 table-module";
    tableDiv.id = "iframe_box";
    tableDiv.style.overflow = "auto";

    var table = popupDocument.createElement("table");
    table.className = "gallery table table_list table_striped table-bordered";

    var thead = popupDocument.createElement("thead");
    var tr = popupDocument.createElement("tr");
    var th1 = popupDocument.createElement("th");
    th1.textContent = "检验单号";
    var th2 = popupDocument.createElement("th");
    th2.textContent = "单据状态";
    var th3 = popupDocument.createElement("th");
    th3.textContent = "物料编码";
    var th4 = popupDocument.createElement("th");
    th4.textContent = "物料名称";

    var tableBody = popupDocument.createElement("tbody");
    tableBody.id="tableBody";

    $.ajax({
        type: "get",
        url: "check/selectBPCheckList",
        data: {
            param: ""
        },
        success: function (data) {
            if (data.length > 0) {
                /*这个方法里是ajax发送请求成功之后执行的代码*/
                data.forEach(function (eveData) {
                    var historyEntry = popupDocument.createElement("tr");
                    // historyEntry.style.cursor = "pointer";
                    historyEntry.addEventListener("mouseenter", function () {
                        this.style.backgroundColor = 'lightblue'; // 鼠标悬停时的颜色
                    });
                    historyEntry.addEventListener("mouseleave", function () {
                        this.style.backgroundColor = 'initial'; // 恢复默认颜色
                    });
                    // historyEntry.addEventListener("click", function () {
                    //     console.log("click");
                    //     var body = popupDocument.getElementById("tableBody");
                    //     var rows = body.getElementsByTagName('tr');
                    //     for (var j = 0; j < rows.length; j++) {
                    //         rows[j].classList.remove('clicked'); // 取消其他行的深蓝色
                    //     }
                    //     this.classList.add('clicked'); // 单击时添加深蓝色
                    // });
                    var documentStatus = "";
                    switch (eveData[1]) {
                        case "Z":
                            documentStatus = "暂存";
                            break;
                        case "A":
                            documentStatus = "创建";
                            break;
                        case "B":
                            documentStatus = "审核中";
                            break;
                        case "C":
                            documentStatus = "已审核";
                            break;
                        case "D":
                            documentStatus = "重新审核";
                            break;
                    }
                    var cell = popupDocument.createElement("td");
                    cell.textContent = eveData[0];
                    cell.style.cursor = "pointer";
                    cell.addEventListener("dblclick", function () {
                        var deepBlueRows = popupDocument.querySelectorAll(".clicked"); // 获取具有深蓝色类的所有行
                        if(deepBlueRows[1].textContent === "审核中" || deepBlueRows[1].textContent === "已审核") {
                            popup.alert(deepBlueRows[1].textContent + "的单据不允许修改！");
                        }
                        else
                        {
                            fillCurrentTable(eveData);
                            popup.close();
                        }
                    });
                    var cell2 = popupDocument.createElement("td");
                    cell2.textContent = documentStatus;
                    cell2.style.cursor = "pointer";
                    cell2.addEventListener("dblclick", function () {
                        var deepBlueRows = popupDocument.querySelectorAll(".clicked"); // 获取具有深蓝色类的所有行
                        if(deepBlueRows[1].textContent === "审核中" || deepBlueRows[1].textContent === "已审核") {
                            popup.alert(deepBlueRows[1].textContent + "的单据不允许修改！");
                        }
                        else
                        {
                            fillCurrentTable(eveData);
                            popup.close();
                        }
                    });
                    var cell3 = popupDocument.createElement("td");
                    cell3.textContent = eveData[2];
                    cell3.style.cursor = "pointer";
                    cell3.addEventListener("dblclick", function () {
                        var deepBlueRows = popupDocument.querySelectorAll(".clicked"); // 获取具有深蓝色类的所有行
                        if(deepBlueRows[1].textContent === "审核中" || deepBlueRows[1].textContent === "已审核") {
                            popup.alert(deepBlueRows[1].textContent + "的单据不允许修改！");
                        }
                        else
                        {
                            fillCurrentTable(eveData);
                            popup.close();
                        }
                    });
                    var cell4 = popupDocument.createElement("td");
                    cell4.textContent = eveData[3];
                    cell4.style.cursor = "pointer";
                    cell4.addEventListener("dblclick", function () {
                        var deepBlueRows = popupDocument.querySelectorAll(".clicked"); // 获取具有深蓝色类的所有行
                        if(deepBlueRows[1].textContent === "审核中" || deepBlueRows[1].textContent === "已审核") {
                            popup.alert(deepBlueRows[1].textContent + "的单据不允许修改！");
                        }
                        else
                        {
                            fillCurrentTable(eveData);
                            popup.close();
                        }
                    });

                    cell.addEventListener("click", function () {
                        resetTableColors();
                        addClickedClass(cell,cell2,cell3,cell4);
                    });
                    cell2.addEventListener("click", function () {
                        resetTableColors();
                        addClickedClass(cell,cell2,cell3,cell4);
                    });
                    cell3.addEventListener("click", function () {
                        resetTableColors();
                        addClickedClass(cell,cell2,cell3,cell4);
                    });
                    cell4.addEventListener("click", function () {
                        resetTableColors();
                        addClickedClass(cell,cell2,cell3,cell4);
                    });

                    function resetTableColors() {
                        var body = popupDocument.getElementById("tableBody");
                        var rows = body.getElementsByTagName('tr');
                        for (var j = 0; j < rows.length; j++) {
                            var cells = rows[j].getElementsByTagName('td');
                            for (var i = 0; i < cells.length; i++) {
                                cells[i].classList.remove('clicked'); // 取消其他行的深蓝色
                            }
                        }
                    }

                    function addClickedClass() {
                        for (var i = 0; i < arguments.length; i++) {
                            arguments[i].classList.add('clicked'); // 单击时添加深蓝色
                        }
                    }

                    historyEntry.appendChild(cell);
                    historyEntry.appendChild(cell2);
                    historyEntry.appendChild(cell3);
                    historyEntry.appendChild(cell4);
                    tableBody.appendChild(historyEntry);
                });
            } else {
                alert("白坯检验单不存在！");
            }
        },
        error: function (msg) {
            alert("ajax连接异常：" + msg);
        }
    });

    buttonDiv.appendChild(buttonSubmit);
    buttonDiv.appendChild(buttonAudit);
    buttonDiv.appendChild(buttonUnAudit);
    buttonDiv.appendChild(buttonDelete);
    popupDocument.body.appendChild(buttonDiv);
    tr.appendChild(th1);
    tr.appendChild(th2);
    tr.appendChild(th3);
    tr.appendChild(th4);
    thead.appendChild(tr);
    table.appendChild(thead);
    table.appendChild(tableBody);
    tableDiv.appendChild(table);
    pageDiv.appendChild(tableDiv);
    popupDocument.body.appendChild(pageDiv);
}

// 填充当前表格内容
function fillCurrentTable(details) {
    tbody.innerHTML = ""; // 清空表格内容
    // var checkNumber = document.getElementById("checkNumber");
    // checkNumber.textContent = details[0];
    // checkNumber.value = details[10];
    $("#checkNumber").val(details[10]);
    $("#materialNumber").val(details[2]);
    $("#materialName").val(details[3]);
    $("#bagNumber").val(details[4]);
    $("#bagProblem").val(details[5]);
    $("#doorWidth").val(details[7]);
    $("#supplier").val(details[8]);
    $("#sourceBillNo").val(details[9]);
    // $("#checker").val("");
    $("#checkDate").val(details[6].substring(0, details[6].indexOf("T")));
    orgNumber = details[11];
    unitNumber = details[13];
    if(details[12] === "2") {
        billTypeNumber = "SLD03_SYS";
    }
    else{
        billTypeNumber = "SLD01_SYS";
    }
    supplierNumber = details[14];
    receiveFid = details[15];
    receiveFEntryId = details[16];
    // auxPropId = details[6];
    // lotNumber = details[6];
    // extAuxUnitNumber = details[6];
    // qty6 = details[6];
    // baseUnitNumber = details[6];
    actReceiveQty = "0";

    $.ajax({
        type: "get",
        url: "check/selectBPCheckListDetails",
        data: {
            param: details[0]
        },
        success: function (data) {
            if (data.length > 0) {
                /*这个方法里是ajax发送请求成功之后执行的代码*/
                data.forEach(function (eveData) {
                    var newRow = tbody.insertRow();

                    var newCell = newRow.insertCell();
                    newCell.textContent = eveData[0];
                    newCell.contentEditable = "true";
                    var newCell2 = newRow.insertCell();
                    newCell2.textContent = eveData[1];
                    var newCell3 = newRow.insertCell();
                    newCell3.textContent = eveData[2];
                    var newCell4 = newRow.insertCell();
                    newCell4.textContent = eveData[3];
                    newCell4.contentEditable = "true";
                });
                code = data[0][2].substring(0,data[0][2].indexOf("-"));
            } else {
                alert("白坯检验单不存在！");
            }
        },
        error: function (msg) {
            alert("ajax连接异常：" + msg);
        }
    });
}

// // 获取显示区域元素
// var barcodeDisplay = document.getElementById("barcodeDisplay");
//
// // 初始化变量
// var scannedData = "";
// var scanTimeout;
//
// // 监听键盘按键事件
// document.addEventListener("keydown", function(event) {
//     // 清除之前的超时计时
//     clearTimeout(scanTimeout);
//
//     // 获取按下的键
//     var key = event.key;
//
//     // 排除特殊键，如功能键等
//     if (key.length === 1) {
//         // 追加按键到扫描数据
//         scannedData += key;
//
//         // 设置超时，判断扫描完成
//         scanTimeout = setTimeout(function() {
//             displayScannedData(scannedData);
//             scannedData = ""; // 清除扫描数据
//         }, 100); // 超时时间根据扫码枪的速度进行调整
//     }
// });
//
// // 显示扫描到的条码数据
// function displayScannedData(data) {
//     barcodeDisplay.textContent = "Scanned Barcode: " + data;
//     // 这里你可以将数据发送到后端进行进一步处理
// }

$.ajax({
    type: "get",
    url: "check/checker",
    data: {
        param: ""
    },
    success: function (data) {
        if (data.length > 0) {
            /*这个方法里是ajax发送请求成功之后执行的代码*/
            data.forEach(function (eveData) {
                var option = document.createElement("option");
                option.textContent = eveData[1];
                option.value = eveData[0];
                checker.appendChild(option);
            });
        }
    },
    error: function (msg) {
        alert("ajax连接异常：" + msg);
    }
});

$(function () {
    //内页框架结构编辑
    $("#situation").Hupage({
        slide: '#breadcrumb',
        padding: 15,
        menuModule: '#bk-con-menu', //菜单模块
        // menuModule2: '#bk-con-menu2', //菜单模块
        menuPosition:'left',
        // menuPosition2:'right',
        pagecontent: '.page_content',//自定义属性
        operation: '.operation',//自定义属性
        scrollbar: function (e) {
            e.niceScroll({
                cursorcolor: "#dddddd",
                cursoropacitymax: 1,
                touchbehavior: false,
                cursorwidth: "3px",
                cursorborder: "0",
                cursorborderradius: "3px",
            });
        },
        expand: function (thisBox, settings) {
            var pc = "";
            $(settings.pagecontent).css("margin-bottom") != null ? pc = parseInt($(settings.pagecontent).css("margin-bottom").replace("px", "")) : '';
            $(settings.pagecontent).css({height: $(window).height() - $(settings.operation).outerHeight() - pc - (settings.padding * 2)})
            settings.scrollbar($(settings.slide) && $(settings.pagecontent));
        }//自定义方法
    });
});