$(document).ready(function(){
    var i=1;
    $("#add_row").click(function(){
        $('#addr'+i).html("<td>"+ (i+1) +"</td><td><input name='ingredient"+i+"' type='text' placeholder='Ingredient' class='form-control input-md'  /> </td><td><input  name='quantity"+i+"' type='text' placeholder='Quantity'  class='form-control input-md'></td><td><input name='dimension"+ i +"' type='text' placeholder='Dimension' class='form-control input-md'  /></td>");

        $('#tab_logic').append('<tr id="addr'+(i+1)+'"></tr>');
        i++;
    });
    $("#delete_row").click(function(){
        if(i>1){
            $("#addr"+(i-1)).html('');
            i--;
        }
    });

});