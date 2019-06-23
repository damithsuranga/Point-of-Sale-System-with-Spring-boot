$(document).ready(function () {
    $("#txtItemCode").focus();
    generateItemTable();
});

// ----------------loadCustomer---------------------------

function generateItemTable() {

    var agaxConfig = {
        method : "GET",
        url : "http://localhost:8080/api/v1/items",
        async : true
    };

    $.ajax(agaxConfig).done(function (item) {

        for (var i = 0; i < item.length; i++) {

            var html = "<tr>"+
                "<td>"+item[i].code+"</td>"+
                "<td>"+item[i].description+"</td>"+
                "<td>"+item[i].qtyOnHand+"</td>"+
                "<td>"+item[i].unitPrice+"</td>"+
                "<td><img src='images/trash-icon.png' style='width: 30px' class='img-bin'></td>"+
                "</tr>"
            $("#tblItem tbody").append(html);
        }
        console.log("working");

        // ---------------------Delete Customer------------------------

        $("#tblItem tbody tr td:last-child").click(function () {
            var itemCode = $(this).parents("tr").find("td:first-child").text();
            var row = $(this).parents("tr");

            var deleteAjaxConfig={
                method:"DELETE",
                url:"http://localhost:8080/api/v1/items/"+itemCode,
                async:true,
                dataType: 'json',
                contentType: 'application/json',
                data:JSON.stringify({code:itemCode})
            };

            $.ajax(deleteAjaxConfig).done(function (data) {
                alert("Item has been successfully Deleted");
                $(row).remove();
            }).fail(function (error) {
                console.log(error);
                alert("Failed to delete Item");
            });

        });



    }).fail(function (xrJhr,textStatus,errorMsg) {
        console.log(errorMsg);
    });



    // $("#tblItem tbody tr td:last-child").click(function () {
    //     var itemCode = $(this).parents("tr").find("td:first-child").html();
    //     var row = $(this).parents("tr");
    //
    //     var agaxConfig={
    //         method:"DELETE",
    //         url:"http://localhost:8080/item",
    //         async:true,
    //         dataType: 'json',
    //         contentType: 'application/json',
    //         data:JSON.stringify({code:itemCode})
    //     }
    //
    //     $.ajax(deleteAjaxConfig).done(function (data) {
    //         alert("Item has been successfully Deleted");
    //         $(row).remove();
    //     }).fail(function (error) {
    //         console.log(error);
    //         alert("Failed to delete Item");
    //     });
    //
    // });

}
//-----------------SaveCustomer-------------------------------
$("#btn-save").click(function () {

    var item= {
        code: $("#txtItemCode").val(),
        description : $("#txtItemDescription").val(),
        qtyOnHand : parseInt($("#txtItemQty").val()),
        unitPrice : parseFloat($("#txtItemUnitPrice").val())

    };


    console.log("SAVE");

    var ajxConfig = {
        method: "POST",
        url: "http://localhost:8080/api/v1/items",
        async: true,
        data: JSON.stringify(item),
        contentType: "application/json"
    }
    $.ajax(ajxConfig).done(function (response) {
        if(response){
            alert("Item Saved Successfully")
        }else {
            alert("Faild to save")
        }
    })

});