$(document).ready(function () {
    $('#add-button').click(function () {
        $('#add_form').toggle('blind', {}, 500);
    });

    $('input:text').button().addClass('my-textfield');
    $("#pages").spinner({
        min: 1,
        max: 1000000
    });
    $('#publication').datepicker({
        changeMonth: false,
        changeYear: true,
        showButtonPanel: true,
        dateFormat: 'yy',
        onClose: function (dateText, inst) {
            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
            $(this).datepicker('setDate', new Date(year, 1, 1));
        }
    });
    $("#publication").focus(function () {
        $(".ui-datepicker-month").hide();
    });
    $("#rating").selectmenu();
    $("#genre").selectmenu();
    $("isRead").checkboxradio();

    $('#table').basictable();
    $('#table-breakpoint').basictable({
        breakpoint: 768
    });
    $('#table-container-breakpoint').basictable({
        containerBreakpoint: 485
    });
    $('#table-swap-axis').basictable({
        swapAxis: true
    });
    $('#table-force-off').basictable({
        forceResponsive: false
    });
    $('#table-no-resize').basictable({
        noResize: true
    });
    $('#table-two-axis').basictable();
    $('#table-max-height').basictable({
        tableWrapper: true
    });

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/getBooks",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (result) {
            for (var i = 0; i < result.books.length; i++) {
                var book = result.books[i];
                $('<tr>').append(
                        $('<td>').text(book.author),
                        $('<td>').text(book.title),
                        $('<td>').text(book.genre),
                        $('<td>').text(book.pages),
                        $('<td>').text(book.publication),
                        $('<td>').text(getRating(book.rating)),
                        $('<td>').text(book.isRead ? '✔' : '')
                        ).appendTo('#table');
            }
        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>" + e.responseText + "</pre>";
            $('#feedback').html(json);
            console.log("ERROR : ", e);
        }
    });

    $('form[id="add_form"]').validate({
        rules: {
            author: {
                required: true,
                minlength: 3,
                maxlength: 200
            },
            title: {
                required: true,
                minlength: 3,
                maxlength: 200
            },
            genre: 'required',
            pages: {
                required: true,
                digits: true,
                range: [1, 1000000],
                number: true
            },
            publication: {
                required: true,
                digits: true,
                number: true
            },
            rating: 'required'

        },
        messages: {
            author: {
                required: 'This field is required',
                minlength: 'Must be at least 3 characters long',
                maxlength: 'Must be maximum 200 characters long'
            },
            title: {
                required: 'This field is required',
                minlength: 'Must be at least 3 characters long',
                maxlength: 'Must be maximum 200 characters long'
            },
            genre: 'This field is required',
            pages: {
                required: 'This field is required',
                digits: 'This fiels must be an positive integer',
                range: 'This fiels must be a positive integer',
                number: 'This fiels must be an positive integer'
            },
            publication: {
                required: 'This field is required',
//                digits: 'This fiels must be an integer',
                number: 'This fiels must be an integer'
            },
            rating: 'This field is required'
        },
        submitHandler: function (form) {
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: '/addBook',
                data: form,
                cache: false,
                timeout: 600000,
                success: function (resposeJsonObject) {
                    $('#add_form').hide();
                    $("#add_form")[0].reset();
                    if (!resposeJsonObject.validated) {
                        $.each(resposeJsonObject.errors, function (key, value) {
                            $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
                        });
                    }
                }
            });
        }
    });
});

function getRating(rating) {
    var stars = '';
    for (var i = 0; i < rating; i++) {
        stars += '★';
    }
    return stars;
}