function getData(){
    $.ajax({
        url: '/customers/new/idCheck',
        type: 'post',
        data: { "id" : $('#inputIdText').val() },
        dataType: "json",
        success: function (data) {
            if ($.trim(data) == 0) {
                alert("사용가능");
            } else {
                alert("사용불가");

            }
    });

}