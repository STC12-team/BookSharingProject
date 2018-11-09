$(document).ready(function() {
    var password;
    $('#userEdit').click(function(event) {
        event.preventDefault();
    });

    $('#submit').click(function(){
        password = $('#password__confirmation').val();
        console.log(password);
        $('#password__confirmation__form').submit();
    });
});