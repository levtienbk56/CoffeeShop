
$(document).on('click', '#btn_remove_cup', function(){
      var str = $(this).parent('p').parent('td').parent('tr');
      str.hide();
});

function addCupFunction(){
	var str="<tr><td >	choose coffee	</td>	<td><div class='checkbox'><input type='checkbox' name='coffee-size' value='big'>Big Size?</div></td>	<td >choose condiment</td>	<td><input type='number' class='form-control' id='coffee-quantity' min='1' value='1'></td><td>0VND</td> <td>  <p><button id='btn_remove_cup' type='button' class='btn btn-danger'><span class='glyphicon glyphicon-remove' > </span>Remove</button>   </p></td>	</tr>";	
	$("#table_order tr#add_new").before(str);
};

$("button#btn_coffee").click(function() {
 	alert("sdada");
});
	