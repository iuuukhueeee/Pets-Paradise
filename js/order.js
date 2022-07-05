$('.arrow').on('click', function() {
    $(this).toggleClass('active');
});

$('.arrow--l-r').on('click', function() {
    $(this).toggleClass('left right');
});

function showHideRow(row) {
    $("#" + row).toggle();
}