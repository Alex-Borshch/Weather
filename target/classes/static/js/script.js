$('.button').click(function(){
    alert ("fdkljghskldjfgklsdjfhg!!!");
    $.ajax({
        url: "asdf",
        type: "get", //send it through get method
        data: {
            name: 'Kyiv'
        },
        success: function(response) {
            alert(response.toString());
            console.log(response);
        },
        error: function(xhr) {
            alert (xhr);
            console.log(xhr);
        }
    });

})

