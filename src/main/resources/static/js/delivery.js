let deliveryList;
$(document).ready(function() {
    if(localStorage.jwt === undefined || localStorage.jwt === null){
        alert('로그인 정보가 없습니다.');
        window.location.href = '/';
    }
});

$("#btn-search").click(function () {
    let start = $("#start-date").val();
    let end = $("#end-date").val();
    let status = $("#status").val();
    let url = '/order/api/delivery?start='+start+'&end='+end+'&status='+status;
    $.ajax({
        type : 'get',
        url : url,
        contentType : 'application/json; charset=utf-8',
        beforeSend : function(xhr){
            xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.jwt);
        },
        success : function(json){
            console.log(json);
            deliveryList =  json;
            $("table tbody").empty();

            json.forEach(function(item) {
                // 테이블 행 추가
                let row = "<tr data-id='" + item.id + "'>"; // 'data-id' 속성에 id 값 추가
                row += "<td>" + item.orderDate + "</td>";
                row += "<td>" + item.address + " " + item.addressDetail;
                row += " <button class='btn-change-address'>주소변경</button></td>"; // 주소변경 버튼 추가
                row += "<td>" + (item.status === 1 ? '배달대기' : item.status === 2 ? '배달중' : '배달완료') + "</td>";
                row += "<td>" + item.createdAt + "</td>"; // 필요한 값을 맞게 수정
                row += "<td>" + item.updatedAt + "</td>"; // 필요한 값을 맞게 수정
                row += "</tr>";

                // 테이블에 행 추가
                $("table tbody").append(row);
            });

            $(".btn-change-address").click(function() {
                let rowId = $(this).closest("tr").data("id");
                let target = deliveryList.find(e => e.id === Number(rowId));
                $('#target-id').val(target.id);
                $('#address').val(target.address);
                $('#address-detail').val(target.addressDetail);
                $('#modalContainer').removeClass('hidden');
            });
        },
        error: function(xhr) {
            alert(xhr.responseText);
        }
    });
});

$("#btn-close").click(function() {
    $('#target-id').val('');
    $('#address').val('');
    $('#address-detail').val('');
    $('#modalContainer').addClass('hidden');
});

$("#btn-save").click(function() {
    let id = $("#target-id").val();
    let address = $("#address").val();
    let address_detail = $("#address-detail").val();
    let data = {
        'id': id,
        'address': address,
        'addressDetail': address_detail,
    }
    $.ajax({
        method: 'PATCH',
        url: '/order/api/delivery',
        data: JSON.stringify(data),
        contentType : 'application/json; charset=utf-8',
        beforeSend : function(xhr){
            xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.jwt);
        },
        success: function(response) {
            alert(response.message);
            $('#modalContainer').addClass('hidden');
            $('#btn-search').trigger('click');
        },
        error: function(xhr) {
            console.log(xhr);
            alert(xhr.responseText);
        }
    })
});
