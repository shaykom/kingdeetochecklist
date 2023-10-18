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
var tableBody = document.getElementById("table-body");

$.ajax({
    type: "get",
    url: "check/selectBPCheckList",
    data: {
        param: "F_BPJY"
    },
    success: function (data) {
        if (data.length > 0) {
            /*这个方法里是ajax发送请求成功之后执行的代码*/
            data.forEach(function(eveData) {
                var historyEntry = document.createElement("tr");
                var cell = document.createElement("td");
                cell.textContent = eveData[0];
                cell.style.cursor = "pointer";
                cell.addEventListener("dblclick", function() {
                    fillCurrentTable(eveData);
                    window.close();
                });
                var documentStatus="";
                switch (eveData[1]) {
                    case "Z":
                        documentStatus="暂存";
                        break;
                    case "A":
                        documentStatus="创建";
                        break;
                    case "B":
                        documentStatus="审核中";
                        break;
                    case "C":
                        documentStatus="已审核";
                        break;
                    case "D":
                        documentStatus="重新审核";
                        break;
                }
                var cell2 = document.createElement("td");
                cell2.textContent = documentStatus;
                cell2.style.cursor = "pointer";
                cell2.addEventListener("dblclick", function() {
                    fillCurrentTable(eveData);
                    window.close();
                });
                var cell3 = document.createElement("td");
                cell3.textContent = eveData[2];
                cell3.style.cursor = "pointer";
                cell3.addEventListener("dblclick", function() {
                    fillCurrentTable(eveData);
                    window.close();
                });
                var cell4 = document.createElement("td");
                cell4.textContent = eveData[3];
                cell4.style.cursor = "pointer";
                cell4.addEventListener("dblclick", function() {
                    fillCurrentTable(eveData);
                    window.close();
                });

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




