$("#sortable").sortable();
$("#sortable").disableSelection();

// mark task as done
$('.task').on('change','#sortable td input[type="checkbox"]',function(){
    if($(this).prop('checked')){
        var doneItem = $(this).parent().parent().find('label').text();
        $(this).parent().parent().parent().addClass('remove');
        done(doneItem);
    }
});

//delete done task from Tasks Completed
$('.task').on('click','.remove-item',function(){
    removeItem(this);
});
