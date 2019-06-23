$(document).ready(function () {
    $("#txtCustomerID").focus();
    generateCustomerTable();
});

// ----------------loadCustomer---------------------------

function generateCustomerTable() {

    var agaxConfig = {
        method : "GET",
        url : "http://localhost:8080/api/v1/customers",
        async : true
    };

    $.ajax(agaxConfig).done(function (customer) {

        for (var i = 0; i < customer.length; i++) {

            var html = "<tr>"+
                "<td>"+customer[i].id+"</td>"+
                "<td>"+customer[i].name+"</td>"+
                "<td>"+customer[i].address+"</td>"+
                "<td><img src='images/trash-icon.png' style='width: 30px' class='img-bin'></td>"+
                "</tr>"
            $("#tblcustomer tbody").append(html);
        }
        console.log("working");




//---------------------Delete Customer------------------------

        // var customerid = $("table tbody tr td:first-child").text();

        $("table tbody tr td:last-child").click(function () {
            var customerID = $(this).parents("tr").find("td:first-child").text();
            var row = $(this).parents("tr");



            var data = {
                id: customerID
            };

            var deleteAjaxConfig={
                method:"DELETE",
                url:"http://localhost:8080/api/v1/customers/"+customerID,
                async:true,
                contentType: 'application/json',
                data:JSON.stringify(data)
            };

            console.log("DLETE");

            $.ajax(deleteAjaxConfig).done(function (data) {
                alert("Customer has been successfully Deleted");
                $(row).remove();
            }).fail(function (jqxhr, textStatus,error) {
                console.log(error);
                alert("Failed to delete Customer");
            });

        });




    }).fail(function (xrJhr,textStatus,errorMsg) {
        console.log(errorMsg);
    });



    // $("#tblcustomer tr td:last-child").click(function () {
    //    var customerID = $(this).parents("tr").find("td:first-child").html();
    //    var row = $(this).parents("tr");
    //
    //    var data = {
    //        id: customerID
    //    };
    //
    //    var agaxConfig={
    //        method:"DELETE",
    //        url:"http://localhost:8080/api/v1/customers",
    //        async:true,
    //        contentType: 'application/json',
    //        data:JSON.stringify(data)
    //    }
    //
    //     $.ajax(deleteAjaxConfig).done(function (data) {
    //         alert("Customer has been successfully Deleted");
    //         $(row).remove();
    //     }).fail(function (jqxhr, textStatus,error) {
    //         console.log(error);
    //         alert("Failed to delete Customer");
    //     });
    //
    // });

}





//-----------------SaveCustomer-------------------------------
$("#btn-save").click(function () {

    var customer= {
        id: $("#txtCustomerID").val(),
    name : $("#txtCustomerIName").val(),
    address : $("#txtCustomerAddress").val()
}
    var ajxConfig = {
        method: "POST",
        url: "http://localhost:8080/api/v1/customers",
        async: true,
        data: JSON.stringify(customer),
        contentType: "application/json"
    }
    $.ajax(ajxConfig).done(function (response) {
        if(response){
            alert("Customer Saved Successfully")
        }else {
            alert("Faild to save")
        }
    })

});